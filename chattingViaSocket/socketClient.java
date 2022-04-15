package chattingViaSocket;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class socketClient {
    public static void main(String[] args) {
        try{
            Socket socket = new Socket("localhost", 3333);
            //소켓 서버에 접속

            System.out.println("successful connection to " + socket.getLocalAddress());

            OutputStream clientToServer = socket.getOutputStream();
            //소켓의 outputStream 정보를 저장
            PrintWriter clientMSG = new PrintWriter(clientToServer, true);

            clientMSG.println("client to server");
            //클라이언트에서 서버로 메세지 보내기

            InputStream serverToClient = socket.getInputStream();
            //소켓의 inputStream 정보를 저장
            BufferedReader serverMSG = new BufferedReader(new InputStreamReader(serverToClient));

            System.out.println(serverMSG.readLine());
            //서버에서 온 메세지 확인
            System.out.println("client socket exit");
            socket.close();
            //소켓 종료

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
