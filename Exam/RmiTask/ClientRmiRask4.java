package Exam.RmiTask;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import java.util.Scanner;

import Exam.Schema.Note;

public class ClientRmiRask4 {
    static Scanner scanner;
    static Boolean stoped;
    public static void main(String[] args) {
        try{
            Registry registry = LocateRegistry.getRegistry(8080);
            RmiInterface rmi = (RmiInterface)registry.lookup("dao");

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

                switch (x) {
                    case 1:
                        System.out.println("Enter note priority");
                        int p = Integer.valueOf(scanner.nextLine());
                        System.out.println("Enter note type");
                        String t = scanner.nextLine();

                        rmi.create(new Note(p, t));
                        break;
                    case 2: 
                        System.out.println("Enter note id");
                        int readId = Integer.valueOf(scanner.nextLine());

                        Note note = rmi.read(readId);
                        System.out.println(note);
                        break;
                    case 3:
                        System.out.println("Enter id");
                        int updateId = Integer.valueOf(scanner.nextLine());
                        System.out.println("Enter new priority");
                        int pu = Integer.valueOf(scanner.nextLine());
                        System.out.println("Enter new type");
                        String tu = scanner.nextLine();

                        rmi.update(new Note(updateId, pu, tu, new Date()));
                        break;
                    case 4:
                        System.out.println("Enter note id");
                        int deleteId = Integer.valueOf(scanner.nextLine());

                        rmi.delete(deleteId);
                    case 0:
                        System.out.println("Exinting...");
                        stoped = true;
                    default:
                        break;
                }
            }

            scanner.close();

        } catch(Exception e) {e.printStackTrace();}
    }
}
