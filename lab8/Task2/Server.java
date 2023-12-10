package lab8.Task2;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try{
            RMIInterface remote = new RMI();

            Registry registry = LocateRegistry.createRegistry(8080);
            registry.bind("dao", remote);
        } catch(RemoteException e) {e.printStackTrace();}
        catch(AlreadyBoundException e) {e.printStackTrace();}
    }
}
