package Exam.RmiTask;

import java.rmi.Remote;
import java.rmi.RemoteException;

import Exam.Schema.Note;


public interface RmiInterface extends Remote {
    void create(Note note) throws RemoteException;
    void update(Note note) throws RemoteException;
    void delete(int id) throws RemoteException;
    Note read(int id) throws RemoteException;
}