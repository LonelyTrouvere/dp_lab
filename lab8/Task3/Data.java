package lab8.Task3;

import java.io.Serializable;


public class Data implements Serializable {
    private final int command;
    private final Object data;
    public Data(int command,Object data){
        this.command = command;
        this.data = data;
    }
    public int getCommand(){
        return this.command;
    }
    public Object getData(){
        return this.data;
    }
}
