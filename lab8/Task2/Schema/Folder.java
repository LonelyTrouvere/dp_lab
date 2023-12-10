package lab8.Task2.Schema;

import java.io.Serializable;

public class Folder implements Serializable {
    public String id;
    public String name;

    public Folder(String id, String name){
        this.id = id;
        this.name = name;
    }

    public Folder(String name){
        this.name = name;
    }

    public String toString(){
        return ("Id: "+id+"\nName: "+name);
    }
}

