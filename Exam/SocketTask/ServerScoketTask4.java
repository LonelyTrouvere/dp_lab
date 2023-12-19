package Exam.SocketTask;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import Exam.Schema.Note;

public class ServerScoketTask4 {
    static ObjectInputStream in;
    static ObjectOutputStream out;
    static Socket socket;
    static ServerSocket serverSocket;

    static String status;
    static Boolean stoped;
    public static void main(String[] args) {
        try{
            System.out.println("Ready for connection...");
            serverSocket = new ServerSocket(8080);
            socket = serverSocket.accept();
            System.out.println("Client from port "+socket.getPort()+" connected");
            stoped = false;
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            out.flush();
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }

        try{
            Manager manager = new Manager();
            while (!stoped){
                int x = (Integer)in.readObject();
                switch (x) {
                    case 1:
                        Note note = (Note)in.readObject();
                        manager.create(note);
                        break;
                    case 2: 
                        int readId = (Integer)in.readObject();
                        Note readNote = manager.read(readId);
                        out.writeObject(readNote);
                        break;
                    case 3: 
                        Note upnote = (Note)in.readObject();
                        manager.update(upnote);
                        break;
                    case 4:
                        int deleteId = (Integer)in.readObject();
                        manager.delete(deleteId);
                        break;
                    case 0:
                        System.out.println("Exinting...");
                        stoped = true;
                    default:
                        break;
                }
            }
        } catch(Exception e){

        }
    }
}
