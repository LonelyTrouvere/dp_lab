package lab8.Task3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import lab8.Task3.Schema.Folder;
import lab8.Task3.Schema.MyFile;
import lab8.Task3.Schema.UpdateFile;

import static lab8.Task3.Util.deserializeObject;

public class Server {
    private static final DAO dao = new DAO();
    static String status;
    static Channel channel;

    public static void main(String[] args){
        try{
            ConnectionFactory connectionFactory = new ConnectionFactory();
            connectionFactory.setHost("localhost");
            connectionFactory.setPort(8080);
            Connection rabbitmqConnection = connectionFactory.newConnection();

            channel = rabbitmqConnection.createChannel();

            channel.queueDeclare("server-queue",false,false,false,null);
            channel.queueDeclare("client-queue",false,false,false,null);

            channel.basicConsume("server-queue",true,(consumerTag,delivery)->handleRequest(channel,delivery.getBody()),consumerTag->{});
        }
        catch (IOException e){ e.printStackTrace(); }
        catch (TimeoutException e){ e.printStackTrace(); }
    }
    private static void handleRequest(Channel channel,byte[] input){
        try{
            Data data = deserializeObject(input);
            switch(data.getCommand()){
                case 1: 
                    MyFile file = (MyFile)data.getData();
                    status = dao.createFile(file);
                    sendResponse();
                    break;
                case 2:
                    Folder folder = (Folder)data.getData();
                    status = dao.createFolder(folder);
                    sendResponse();
                    break;
                case 3:
                    ArrayList<Folder> folderList = dao.getAllFolders();
                    channel.basicPublish("", "client-queue", null, folderList.toString().getBytes());
                    break;
                case 4:
                    ArrayList<MyFile> fileList = dao.getAllFiles();
                    channel.basicPublish("", "client-queue", null, fileList.toString().getBytes());
                    break;
                case 5:
                    String folderId = (String)data.getData();
                    ArrayList<MyFile> folderFileList = dao.getFilesFromFolder(folderId);
                    channel.basicPublish("", "client-queue", null, folderFileList.toString().getBytes());
                    break;
                case 6:
                    Folder updateFolder = (Folder)data.getData();
                    status = dao.updateFolder(updateFolder);
                    sendResponse();
                    break;
                case 7:
                    UpdateFile fileName = (UpdateFile)data.getData();
                    status = dao.updateFileName(fileName.id, fileName.name);
                    sendResponse();
                    break;
                case 8:
                    UpdateFile fileType = (UpdateFile)data.getData();
                    status = dao.updateFileType(fileType.id, fileType.type);
                    sendResponse();
                    break;
                case 9:
                    UpdateFile fileSize = (UpdateFile)data.getData();
                    status = dao.updateFileSize(fileSize.id, fileSize.size);
                    sendResponse();
                    break;
                case 10: 
                    String delFolder = (String)data.getData();
                    status = dao.deleteFolder(delFolder);
                    sendResponse();
                    break;
                case 11:
                    String delFile = (String)data.getData();
                    status = dao.deleteFile(delFile);
                    sendResponse();
                    break;
            }
        }
        catch (IOException e){e.printStackTrace();}
        catch (ClassNotFoundException e) {e.printStackTrace();}
    }

    static void sendResponse() throws IOException{
        channel.basicPublish("", "client-queue", null, status.getBytes());
    }
}
