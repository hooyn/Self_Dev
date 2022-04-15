package chattingViaSocket.chat;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class writingMessage extends Thread {
    Socket socket = null;
    Scanner scanner = new Scanner(System.in);

    public writingMessage(Socket socket){
        this.socket = socket;
    }

    public void run(){
        try{
            OutputStream out = socket.getOutputStream();
            PrintWriter clientToServer = new PrintWriter(out, true);

            while (true) {
                clientToServer.println(scanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
