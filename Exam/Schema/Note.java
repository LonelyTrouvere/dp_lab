package Exam.Schema;

import java.util.Date;

public class Note {
    private int priority;
    private String type;
    private Date timestamp;
    private int id;

    public Note(int id, int priority, String type, Date timestamp){
        this.id = id;
        this.priority = priority;
        this.type = type;
        this.timestamp = timestamp;
    }

    public Note(int priority, String type){
        this.priority = priority;
        this.type = type;
    }

    public int getPriority(){
        return this.priority;
    }
    public int getid(){
        return this.id;
    }
    public String getType(){
        return this.type;
    }
    public Date getTimestamp(){
        return this.timestamp;
    }

    public void setPriority(int p){
        this.priority = p;
    }
    public void setid(int i){
        this.id = i;
    }
    public void setType(String t){
        this.type = t;
    }
    public void setTimestamp(Date d){
        this.timestamp = d;
    }
}
