package lab7.Task1;

public class MyFile {
    public String id;
    public String name; 
    public String type;
    public int size;
    public String parentId;

    public MyFile(String id, String name, String type, int size, String parent){
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

    public String toString(){
        return "Id: "+id+"\nName: "+name+"\nType: "+type+"\nSize: "+size+"\nParent folder ID: "+parentId;
    }
}
