package lab7.Task1;

import java.util.ArrayList;
import java.util.UUID;

public class FileSystem {
    private ArrayList<Folder> folderList;
    private ArrayList<MyFile> fileList;
    private DOM dom;

    public FileSystem(){
        dom = new DOM();

        folderList = dom.getFolders();
        fileList = dom.getFiles();
    }

    public ArrayList<MyFile> getFiles(){
        return fileList;
    }

    public ArrayList<Folder> getFolder(){
        return folderList;
    }

    public MyFile getFileById(String id){
        for (int i=0; i<fileList.size(); i++){
            MyFile file = fileList.get(i);
            if (id.equals(file.id))
                return file;
        }

        return null;
    }

    public Folder getFolderById(String id){
        for (int i=0; i<fileList.size(); i++){
            Folder folder = folderList.get(i);
            if (id.equals(folder.id))
                return folder;
        }

        return null;
    }

    public ArrayList<MyFile> getFilesFromFolder(String id){
        ArrayList<MyFile> files = new ArrayList<>();
        for (int i=0; i<fileList.size(); i++){
            var file = fileList.get(i);
            if (id.equals(file.parentId))
                files.add(file);
        }

        return files;
    }

    public void updateFile (MyFile file){
        for (int i=0; i<fileList.size(); i++){
            MyFile original = fileList.get(i);
            if (original.id.equals(file.id)){
                original.name = file.name;
                original.type = file.type;
                original.size = file.size;
                dom.updateFile(original);
                return;
            }
        }
    }

    public void updateFolder(Folder folder){
        for (int i=0; i<folderList.size(); i++){
            Folder original = folderList.get(i);
            if (original.id.equals(folder.id)){
                original.name = folder.name;
                dom.updateFolder(original);
                return;
            }
        }
    }

    public int createFile(String name, String type, int size, String folder){
        MyFile newFile = new MyFile("id_"+UUID.randomUUID(), name, type, size, folder);
        int res = dom.createFile(newFile);
        if (res == 0){
            fileList.add(newFile);
        }

        return res;
    }

    public void createFolder(String name){
        Folder newFolder = new Folder("id_"+UUID.randomUUID(), name);
        dom.createFolder(newFolder);
    }

    public void deleteFile(String id){
        for (int i=0; i<fileList.size(); i++){
            var file = fileList.get(i);
            if (id.equals(file.id)){
                fileList.remove(file);
                dom.deleteFile(file);
                break;
            }
        }
    }

    public void deleteFolder(String id){
        ArrayList<MyFile> files = getFilesFromFolder(id);
        for(int i=0; i<files.size(); i++){
            fileList.remove(files.get(i));
            dom.deleteFile(files.get(i));
        }

        for(int i=0; i<folderList.size(); i++){
            var folder = folderList.get(i);
            if (id.equals(folder.id)){
                folderList.remove(folder);
                dom.deleteFolder(folder);
                break;
            }
        }
    }
}
