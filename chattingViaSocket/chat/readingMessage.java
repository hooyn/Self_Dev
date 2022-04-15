package chattingViaSocket.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class readingMessage extends Thread {
    Socket socket = null;

    public readingMessage(Socket socket){
        this.socket = socket;
    }

    public void run(){
        try{
            InputStream in = socket.getInputStream();
            BufferedReader serverToClient = new BufferedReader(new InputStreamReader(in));

            while (true) {
                System.out.println(serverToClient.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
