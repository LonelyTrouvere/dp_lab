package lab3.B;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main {
 
    public static void main(String[] args) {
         
        Semaphore barber = new Semaphore(1); 
        int n = 0;

        Random rand = new Random();
        int min = 1000; // Minimum value
        int max = 10000; // Maximum value

        while (true){
            try{
            int randomInt = rand.nextInt(max - min + 1) + min;
            Thread.sleep(randomInt);
            new Thread(new Customer(barber, n)).start();
            n++;
            }
            catch (InterruptedException e) { System.out.println(e);}
        }
    }
}

class Customer implements Runnable{
    Semaphore barber;
    int custmoer;

    Customer(Semaphore res, int n){
        this.barber=res;
        this.custmoer=n;
    }
      
    public void run(){
         
        try{
            System.out.println("Customer "+custmoer+" has arrived");
            barber.acquire();
            System.out.println("Customer "+custmoer+" has haircut");
            Thread.sleep(5000);
            barber.release();
            System.out.println("Customer's "+custmoer+" hair is done");
        }
        catch(InterruptedException e){System.out.println(e.getMessage());}
    }
}