package chattingViaSocket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class socketServer {
    public static void main(String[] args) {
        try{
            final int port = 3333;
            ServerSocket serverSocket = new ServerSocket(port); //서버 소켓 생성

            Socket socketUser = null; //클라이언트가 접속 했을 때 사용할 소켓 생성
            System.out.println("server via socket open " + port + "..." );

            while (true) {
                socketUser = serverSocket.accept();
                //클라이언트가 서버 소켓에 접속했을 때 소켓유저에 클라이언트 정보를 넘겨준다.

                System.out.println("grant access: (client) " + socketUser.getLocalAddress());
                //접속자 주소 출력

                InputStream clientToServer = socketUser.getInputStream();
                //클라이언트 소켓의 inputStream 정보를 저장

                BufferedReader clientInfo = new BufferedReader(new InputStreamReader(clientToServer));
                //BufferedReader 객체에 InputStream 담아서 사용.

                System.out.println(clientInfo.readLine());
                //클라이언트에서 온 메세지 확인

                OutputStream serverToClient = socketUser.getOutputStream();
                //서버 소켓의 outputStream 정보를 저장

                PrintWriter serverMSG = new PrintWriter(serverToClient, true);

                serverMSG.println("server to client");
                //서버에서 클라이언트로 메세지 보내기
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
