package Exam.SocketTask;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

import Exam.Schema.Note;

public class ClienSocketTask4 {
    static ObjectInputStream in;
    static ObjectOutputStream out;
    static Scanner scanner;

    static String status;
    static Boolean stoped;

    static Socket socket;
    public static void main(String[] args) {
        try{
            System.out.println("Connection to the socket...");
            socket = new Socket("localhost", 8080);
            System.out.println("Connected");
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            stoped = false;
            scanner = new Scanner(System.in);

            while (!stoped){
                System.out.println("Enter operation");
                System.out.println("1. Create note");
                System.out.println("2. Read note");
                System.out.println("3. Update note");
                System.out.println("4. Delete note");
                System.out.println("0. Exit");
                int x = Integer.parseInt(scanner.nextLine());
                out.writeObject(x);

                switch (x) {
                    case 1:
                        System.out.println("Enter note priority");
                        int p = Integer.valueOf(scanner.nextLine());
                        System.out.println("Enter note type");
                        String t = scanner.nextLine();

                        Note note = new Note(p, t);
                        out.writeObject(note);
                        break;
                    case 2: 
                        System.out.println("Enter note id");
                        int readId = Integer.valueOf(scanner.nextLine());

                        out.writeObject(readId);
                        var readNote = (Note)in.readObject();
                        System.out.println(readNote);
                        break;
                    case 3:
                        System.out.println("Enter id");
                        int updateId = Integer.valueOf(scanner.nextLine());
                        System.out.println("Enter new priority");
                        int pu = Integer.valueOf(scanner.nextLine());
                        System.out.println("Enter new type");
                        String tu = scanner.nextLine();
                        Note upnote = new Note(updateId, pu, tu, new Date());

                        out.writeObject(upnote);
                        break;
                    case 4:
                        System.out.println("Enter note id");
                        int deleteId = Integer.valueOf(scanner.nextLine());

                        out.writeObject(deleteId);
                        break;
                    case 0:
                        System.out.println("Exinting...");
                        stoped = true;
                    default:
                        break;
                }
            }

            scanner.close();
            socket.close();

        } catch(Exception e) {e.printStackTrace();}
    }
}
