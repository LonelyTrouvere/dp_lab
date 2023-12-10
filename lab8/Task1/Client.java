package lab8.Task1;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import lab8.Task1.Schema.Folder;
import lab8.Task1.Schema.MyFile;

public class Client {
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
                System.out.println("1. Create file");
                System.out.println("2. Create folder");
                System.out.println("3. Read all folders");
                System.out.println("4. Read all files");
                System.out.println("5. Read files from folder");
                System.out.println("6. Update folder");
                System.out.println("7. Update file");
                System.out.println("8. Delete folder");
                System.out.println("9. Delete file");
                System.out.println("0. Exit");
                int x = Integer.parseInt(scanner.nextLine());
                out.writeObject(x);

                switch (x) {
                    case 1:
                        System.out.println("Enter folder id");
                        String folderId = scanner.nextLine();
                        System.out.println("Enter file name");
                        String fileName = scanner.nextLine();
                        System.out.println("Enter file type");
                        String type = scanner.nextLine();
                        System.out.println("Enter file size");
                        int size = Integer.parseInt(scanner.nextLine());

                        MyFile file = new MyFile(fileName, size, type, folderId);
                        out.writeObject(file);
                        status = (String)in.readObject();
                        System.out.println(status);
                        break;
                    case 2: 
                        System.out.println("Enter folder name");
                        String folderName = scanner.nextLine();

                        Folder folder = new Folder(folderName);
                        out.writeObject(folder);

                        status = (String)in.readObject();
                        System.out.println(status);
                        break;
                    case 3:
                        ArrayList<Folder> folderList = (ArrayList<Folder>)in.readObject(); 
                        for(Folder f : folderList){
                            System.out.println(f);
                        }
                        break;
                    case 4:
                        ArrayList<MyFile> fileList = (ArrayList<MyFile>)in.readObject(); 
                        for(MyFile f : fileList){
                            System.out.println(f);
                        }
                        break;
                    case 5:
                        System.out.println("Enter folder id");
                        String id = scanner.nextLine();
                        out.writeObject(id);
                        var output = (ArrayList<MyFile>)in.readObject(); 
                            for(MyFile f : output){
                                System.out.println(f);
                            }
                        break;
                    case 6:
                        System.out.println("Enter fodler id");
                        String fodlerUpdate = scanner.nextLine();
                        System.out.println("Enter new name");
                        String newName = scanner.nextLine();
                        out.writeObject(new Folder(fodlerUpdate, newName));
                        status = (String)in.readObject();
                        System.out.println(status);
                        break;
                    case 7:
                        System.out.println("Choose what to update");
                        System.out.println("1. Name");
                        System.out.println("2. Type");
                        System.out.println("3. Size");
                        int i = Integer.parseInt(scanner.nextLine());
                        out.writeObject(i);
                        System.out.println("Enter file id");
                        String fileUpdate = scanner.nextLine();
                        System.out.println("Enter new value");
                        String newVal = scanner.nextLine();
                        out.writeObject(fileUpdate);
                        out.writeObject(newVal);
                        status = (String)in.readObject();
                        System.out.println(status);
                        break;
                    case 8:
                        System.out.println("Enter fodler id");
                        String deleteFodler = scanner.nextLine();
                        out.writeObject(deleteFodler);
                        status = (String)in.readObject();
                        System.out.println(status);
                        break;
                    case 9:
                        System.out.println("Enter file id");
                        String deleteFile = scanner.nextLine();
                        out.writeObject(deleteFile);
                        status = (String)in.readObject();
                        System.out.println(status);  
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
