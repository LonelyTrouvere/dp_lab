package lab7.Task1;

public class Folder {
    public String id;
    public String name;

    public Folder(String id, String name){
        this.id = id;
        this.name = name;
    }

    public String toString(){
        return ("Id: "+id+"\nName: "+name);
    }
}
