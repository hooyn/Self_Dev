package chattingViaSocket.chat;

import java.net.Socket;

public class chattingClient02 {
    public static void main(String[] args) {
        try{
            Socket socket = null;

            socket = new Socket("localhost", 3333);
            System.out.println("-------Chatting Program-------");

            readingMessage readMessage = new readingMessage(socket);
            writingMessage writeMessage = new writingMessage(socket);

            readMessage.start();
            writeMessage.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
