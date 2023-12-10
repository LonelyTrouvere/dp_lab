package lab8.Task3.Schema;

import java.io.Serializable;

public class MyFile implements Serializable {
    public String id;
    public String name; 
    public String type;
    public int size;
    public String parentId;

    public MyFile(String id, String name, int size, String type, String parent){
        this.type = type;
        this.id = id;
        this.name = name;
        this.size = size;
        this.parentId = parent;
    }

    public MyFile(String id, String name, String type, int size){
        this.type = type;
        this.id = id;
        this.name = name;
        this.size = size;
    }

    public MyFile(String name, int size, String type, String parent){
        this.type = type;
        this.name = name;
        this.size = size;
        this.parentId = parent;
    }

    public String toString(){
        return "Id: "+id+"\nName: "+name+"\nType: "+type+"\nSize: "+size+"\nParent folder ID: "+parentId;
    }
}
