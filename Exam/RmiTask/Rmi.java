package Exam.RmiTask;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Exam.Schema.Note;

public class Rmi extends UnicastRemoteObject implements RmiInterface {
    
    List<Note> db;
    public Rmi() throws RemoteException{
        this.db = new ArrayList<Note>();
    }

    public void create(Note note) throws RemoteException{
        note.setid(db.size());
        note.setTimestamp(new Date());
        db.add(note);
    }

    public void update (Note up) throws RemoteException{
        int i = 0;
        for (Note note : db){
            if (note.getid() == up.getid()){
                db.set(i, up);
                break;
            }
            i++;
        }
    }

    public void delete(int id) throws RemoteException{
        int i = 0;
        for (Note note : db){
            if (note.getid() == id){
                db.remove(i);
                break;
            }
            i++;
        }
    }

    public Note read(int id) throws RemoteException{
        for (Note note : db){
            if (note.getid() == id){
                return note;
            }
        }

        return null; 
    }
}
