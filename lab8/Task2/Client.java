package lab8.Task2;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;

import lab8.Task2.Schema.Folder;
import lab8.Task2.Schema.MyFile;


public class Client {
    static Scanner scanner;
    static Boolean stoped;
    static String status;
    public static void main(String[] args) {
        try{
            Registry registry = LocateRegistry.getRegistry(8080);
            RMIInterface rmi = (RMIInterface)registry.lookup("dao");

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
                System.out.println("7. Update files name");
                System.out.println("8. Update files type");
                System.out.println("9. Update files size");
                System.out.println("10. Delete folder");
                System.out.println("11. Delete file");
                System.out.println("0. Exit");
                int x = Integer.parseInt(scanner.nextLine());

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

                        status = rmi.createFile(new MyFile(fileName, size, type, folderId));
                        System.out.println(status);
                        break;
                    case 2: 
                        System.out.println("Enter folder name");
                        String folderName = scanner.nextLine();

                        status = rmi.createFolder(new Folder(folderName));
                        System.out.println(status);
                        break;
                    case 3:
                        ArrayList<Folder> folderList = rmi.getAllFolders();
                        for(Folder f : folderList){
                            System.out.println(f);
                        }
                        break;
                    case 4:
                        ArrayList<MyFile> fileList = rmi.getAllFiles();
                        for(MyFile f : fileList){
                            System.out.println(f);
                        }
                        break;
                    case 5:
                        System.out.println("Enter folder id");
                        String id = scanner.nextLine();
                        var output = rmi.getFilesFromFolder(id);
                            for(MyFile f : output){
                                System.out.println(f);
                            }
                        break;
                    case 6:
                        System.out.println("Enter Folder id");
                        String FolderUpdate = scanner.nextLine();
                        System.out.println("Enter new name");
                        String newName = scanner.nextLine();
                        status = rmi.updateFolder(new Folder(FolderUpdate, newName));
                        System.out.println(status);
                        break;
                    case 7:
                        System.out.println("Enter file id");
                        String fileNameU = scanner.nextLine();
                        System.out.println("Enter new name");
                        String newNameU = scanner.nextLine();
                        status = rmi.updateFileName(fileNameU, newNameU);
                        break;
                    case 8:
                        System.out.println("Enter file id");
                        String fileType = scanner.nextLine();
                        System.out.println("Enter new type");
                        String newType = scanner.nextLine();
                        status = rmi.updateFileType(fileType, newType);
                        break;
                    case 9:
                        System.out.println("Enter file id");
                        String fileSize = scanner.nextLine();
                        System.out.println("Enter new size");
                        int newSize = Integer.parseInt(scanner.nextLine());
                        status = rmi.updateFileSize(fileSize, newSize);
                        break;
                    case 10:
                        System.out.println("Enter folder id");
                        String deleteFolder = scanner.nextLine();
                        status = rmi.deleteFolder(deleteFolder);
                        System.out.println(status);
                        break;
                    case 11:
                        System.out.println("Enter file id");
                        String deleteFile = scanner.nextLine();
                        status = rmi.deleteFile(deleteFile);
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

        } catch(Exception e) {e.printStackTrace();}
    }
}
