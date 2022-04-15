package chattingViaSocket.chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class chattingServer extends Thread {
    static ArrayList<Socket> userList = new ArrayList<Socket>();
    //유저를 확인하기 위한 리스트 생성

    static Socket socket = null;

    public chattingServer(Socket socket) {
        this.socket = socket; // 사용자 소켓을 생성자로 전달
        userList.add(socket); // 사용자를 list에 추가
    }

    /**
     * 쓰레드에서 start()메소드를 실행하면 자동으로 run() 메소드가 시작된다. (개별적으로 수행)
     */
    public void run(){
        try{
            System.out.println("connect to server from client: " + socket.getInetAddress());

            //클라이언트에서 서버로 보낸 입력 정보
            InputStream in = socket.getInputStream();
            BufferedReader clientToServer = new BufferedReader(new InputStreamReader(in));

            //서버에서 클라이언트로 보낼 입력 정보
            OutputStream out = socket.getOutputStream();
            PrintWriter serverToClient = new PrintWriter(out, true);

            serverToClient.println("server connection successful! please input nickname");

            //클라이언트에서 보낼 메세지 값과 닉네임 값 저장
            String clientMessage;
            String nickname = null;
            boolean identify = false;

            while ((clientMessage = clientToServer.readLine()) != null) {
                if(!identify){
                    nickname = clientMessage;
                    identify = true;
                    serverToClient.println("(" + nickname + ") permit access privileges!");

                    continue;
                }

                for (int i = 0; i < userList.size(); i++) {
                    out = userList.get(i).getOutputStream();
                    serverToClient = new PrintWriter(out, true);

                    //다른 클라이언트들에게 메세지 전송
                    serverToClient.println(nickname + ": " + clientMessage);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try{
            int port = 3333;
            ServerSocket serverSocket = new ServerSocket(port);

            System.out.println("server via socket open " + port + "..." );

            while (true) {
                Socket socketUser = serverSocket.accept(); //서버에 클라이언트 접속했을 때 정보 저장

                Thread thread = new chattingServer(socketUser); //스레드 생성
                thread.start(); //스레드 시작
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
