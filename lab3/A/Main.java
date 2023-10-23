package lab3.A;
 
public class Main {
 
    public static void main(String[] args) {
         
        MySemaphore sem = new MySemaphore(); 
        Jar res = new Jar(5);
        new Thread(new BearThread(res, sem)).start();
        new Thread(new BeeThread(res, sem)).start();
    }
}

class MySemaphore{
    int semaphore = 0;
}

class Jar{
     int capacity;
     int filled = 0;

    public Jar(int n){
        capacity = n;
    }

}
 
class BearThread implements Runnable{
 
    Jar jar;
    MySemaphore sem;

    BearThread(Jar res, MySemaphore sem){
        this.jar=res;
        this.sem=sem;
    }
      
    public void run(){
         
        try{
            while (true){
            if (sem.semaphore == 1){
                while (jar.filled != 0){
                    jar.filled--;
                    System.out.println("Bear drunk honey. There is "+jar.filled+" in jar");
                    Thread.sleep(2000);
                }
                sem.semaphore--;
            }
            else{
                System.out.println("Bear sleeps");
                Thread.sleep(2000);
            }
        }
        }
        catch(InterruptedException e){System.out.println(e.getMessage());}
    }
}

class BeeThread implements Runnable{
    Jar jar;
    MySemaphore sem;

    BeeThread(Jar res, MySemaphore sem){
        this.jar=res;
        this.sem=sem;
    }
      
    public void run(){
         
        try{
            while(true){
            if (sem.semaphore == 0){
                while (jar.filled != jar.capacity){
                    jar.filled++;
                    System.out.println("Bees brought honey. There is "+jar.filled+" in jar");
                    Thread.sleep(2000);
                }
                sem.semaphore++;
            } else {
                System.out.println("Its to dangerous to make honey");
                Thread.sleep(2000);
            }
        }
        }
        catch(InterruptedException e){System.out.println(e.getMessage());}
    }
}