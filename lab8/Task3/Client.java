package lab8.Task3;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import lab8.Task2.Schema.Folder;
import lab8.Task2.Schema.MyFile;
import lab8.Task3.Schema.UpdateFile;

import static lab8.Task3.Util.serializeObject;

public class Client {
    static Scanner scanner;
    static boolean stoped;
    public static void main(String[] args){
        ConnectionFactory connectionFactory = new ConnectionFactory();

        try(Connection rabbitmqConnection= connectionFactory.newConnection()){
            Channel channel = rabbitmqConnection.createChannel();

            channel.queueDeclare("server-queue",false,false,false,null);
            channel.queueDeclare("client-queue",false,false,false,null);
            scanner = new Scanner(System.in);
            stoped = false;
            try {
                while (true) {
                    channel.basicConsume("client-queue", true, (consumerTag, delivery) -> {
                        String receivedData = new String(delivery.getBody());
                        System.out.println("Received from server:" + receivedData);
                    }, consumerTag -> {
                    });

                    Data dataObjectInput = getInput();
                    if (stoped){
                        break;
                    }
                    channel.basicPublish("", "server-queue", null, serializeObject(dataObjectInput));

                    System.out.println("Message has been sent to server");
                    Thread.sleep(10);
                }

            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            channel.close();
        }
        catch (IOException e){ e.printStackTrace(); }
        catch (TimeoutException e) { e.printStackTrace(); } 
    }
    public static Data getInput(){
        int x;
        while(true){
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
            x = Integer.parseInt(scanner.nextLine());
            try{
                switch (x){
                    case 1:
                        System.out.println("Enter folder id");
                        String folderId = scanner.nextLine();
                        System.out.println("Enter file name");
                        String fileName = scanner.nextLine();
                        System.out.println("Enter file type");
                        String type = scanner.nextLine();
                        System.out.println("Enter file size");
                        int size = Integer.parseInt(scanner.nextLine());

                        return new Data(x, new MyFile(fileName, size, type, folderId));
                    case 2: 
                        System.out.println("Enter folder name");
                        String folderName = scanner.nextLine();

                        return new Data(x, new Folder(folderName));
                    case 3:
                    case 4:
                        return new Data(x, null);
                    case 5:
                        System.out.println("Enter folder id");
                        String id = scanner.nextLine();
                        return new Data(x, id);
                    case 6:
                        System.out.println("Enter Folder id");
                        String FolderUpdate = scanner.nextLine();
                        System.out.println("Enter new name");
                        String newName = scanner.nextLine();
                        return new Data(x, new Folder(FolderUpdate, newName));
                    case 7:
                        System.out.println("Enter file id");
                        String fileNameU = scanner.nextLine();
                        System.out.println("Enter new name");
                        String newNameU = scanner.nextLine();
                        return new Data(x, new UpdateFile(fileNameU, newNameU, false));
                    case 8:
                        System.out.println("Enter file id");
                        String fileType = scanner.nextLine();
                        System.out.println("Enter new type");
                        String newType = scanner.nextLine();
                        return new Data(x, new UpdateFile(fileType, newType, true));
                    case 9:
                        System.out.println("Enter file id");
                        String fileSize = scanner.nextLine();
                        System.out.println("Enter new size");
                        int newSize = Integer.parseInt(scanner.nextLine());
                        return new Data(x, new UpdateFile(fileSize, newSize));
                    case 10:
                        System.out.println("Enter folder id");
                        String deleteFolder = scanner.nextLine();
                        return new Data(x, deleteFolder);
                    case 11:
                        System.out.println("Enter file id");
                        String deleteFile = scanner.nextLine();
                        return new Data(x, deleteFile);
                    case 0:
                        System.out.println("Exinting...");
                        stoped = true;
                }
            }catch (Exception e){
                System.out.println("Error happened:"+e.getMessage());
            }
        }
    }
}