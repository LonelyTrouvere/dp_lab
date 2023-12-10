package lab8.Task2;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import lab8.Task2.Schema.Folder;
import lab8.Task2.Schema.MyFile;


public interface RMIInterface extends Remote {
    String createFolder(Folder folder) throws RemoteException;
    String createFile(MyFile file) throws RemoteException;
    String deleteFolder(String id) throws RemoteException;
    String deleteFile(String id) throws RemoteException;
    String updateFolder(Folder folder) throws RemoteException;
    String updateFileType(String id, String newVal) throws RemoteException;
    String updateFileName(String id, String newVal) throws RemoteException;
    String updateFileSize(String id, int newVal) throws RemoteException;
    ArrayList<Folder> getAllFolders() throws RemoteException;
    ArrayList<MyFile> getAllFiles() throws RemoteException;
    ArrayList<MyFile> getFilesFromFolder(String id) throws RemoteException;
}