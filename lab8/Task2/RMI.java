package lab8.Task2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import lab8.Task2.Schema.Folder;
import lab8.Task2.Schema.MyFile;

public class RMI extends UnicastRemoteObject implements RMIInterface {
    private DAO dao;

    public RMI() throws RemoteException{
        dao = new DAO();
    }

    public String createFolder(Folder folder) throws RemoteException{
        return dao.createFolder(folder);
    }

    public String createFile(MyFile file) throws RemoteException{
        return dao.createFile(file);
    }

    public String deleteFolder(String id) throws RemoteException{
        return dao.deleteFolder(id);
    }

    public String deleteFile(String id) throws RemoteException{
        return dao.deleteFile(id);
    }

    public String updateFolder(Folder folder) throws RemoteException
    {
        return dao.updateFolder(folder);
    }

    public String updateFileType(String id, String newVal) throws RemoteException{
        return dao.updateFileType(id, newVal);
    }

    public String updateFileName(String id, String newVal) throws RemoteException{
        return dao.updateFileName(id, newVal);
    }

    public String updateFileSize(String id, int newVal) throws RemoteException{
        return dao.updateFileSize(id, newVal);
    }

    public ArrayList<Folder> getAllFolders() throws RemoteException{
        return dao.getAllFolders();
    }

    public ArrayList<MyFile> getAllFiles() throws RemoteException{
        return dao.getAllFiles();
    }

    public ArrayList<MyFile> getFilesFromFolder(String id) throws RemoteException{
        return dao.getFilesFromFolder(id);
    }

}