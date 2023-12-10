package lab8.Task1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

import lab8.Task1.Schema.Folder;
import lab8.Task1.Schema.MyFile;

public class Server {
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
            DAO dao = new DAO();
            while (!stoped){
                int x = (Integer)in.readObject();

                switch (x) {
                    case 1:
                        MyFile createFile = (MyFile)in.readObject();
                        status = dao.createFile(createFile);
                        out.writeObject(status);
                        break;
                    case 2: 
                        Folder createFolder = (Folder)in.readObject();
                        status = dao.createFolder(createFolder);
                        out.writeObject(status);
                        break;
                    case 3: 
                        out.writeObject(dao.getAllFolders());
                        break;
                    case 4:
                        out.writeObject(dao.getAllFiles());
                        break;
                    case 5:
                        String id = (String)in.readObject();
                        try{
                            ArrayList<MyFile> list = dao.getFilesFromFolder(id);
                            out.writeObject(list);
                        } catch(SQLException e){
                            out.writeObject(e.toString());
                        }
                        break;
                    case 6:
                        Folder newName = (Folder)in.readObject();
                        out.writeObject(dao.updateFolder(newName));
                        break;
                    case 7:
                        int i = (Integer)in.readObject();
                        String fileUpdate = (String)in.readObject();
                        String val = (String)in.readObject();
                        switch(i){
                            case 1:
                                out.writeObject(dao.updateFileName(fileUpdate, val));
                                break;
                            case 2:
                                out.writeObject(dao.updateFileType(fileUpdate, val));
                                break;
                            case 3:
                                out.writeObject(dao.updateFileSize(fileUpdate, Integer.parseInt(val)));
                                break;
                        }
                        break;
                    case 8:
                        String folderDel = (String)in.readObject();
                        out.writeObject(dao.deleteFolder(folderDel));
                        break;
                    case 9:
                        String fileDel = (String)in.readObject();
                        out.writeObject(dao.deleteFile(fileDel));
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
