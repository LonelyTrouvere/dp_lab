package lab8.Task3.Schema;

import java.io.Serializable;

public class UpdateFile implements Serializable {
    public String id;
    public String name; 
    public String type;
    public int size;

    public UpdateFile(String id, String val, boolean type){
        this.id = id;
        
        if (type){
            this.type = val;
        } else {
            this.name = val;
        }
    }

    public UpdateFile(String id, int size){
        this.id = id;
        this.size = size;
    }
}
