package Exam.SocketTask;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Exam.Schema.Note;

public class Manager {
    
    List<Note> db;
    public Manager(){
        this.db = new ArrayList<Note>();
    }

    public void create(Note note){
        note.setid(db.size());
        note.setTimestamp(new Date());
        db.add(note);
    }

    public void update (Note up){
        int i = 0;
        for (Note note : db){
            if (note.getid() == up.getid()){
                db.set(i, up);
                break;
            }
            i++;
        }
    }

    public void delete(int id){
        int i = 0;
        for (Note note : db){
            if (note.getid() == id){
                db.remove(i);
                break;
            }
            i++;
        }
    }

    public Note read(int id){
        for (Note note : db){
            if (note.getid() == id){
                return note;
            }
        }

        return null; 
    }
}
