package Exam.RmiTask;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerRmiTask4 {
    public static void main(String[] args) {
        try{
            RmiInterface remote = new Rmi();

            Registry registry = LocateRegistry.createRegistry(8080);
            registry.bind("dao", remote);
        } catch(RemoteException e) {e.printStackTrace();}
        catch(AlreadyBoundException e) {e.printStackTrace();}
    }
}
