package Module;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Task10Java {
    static public String[] destinations = {"Washington", "Toronto", "Berlin", "Vienna"};
    static public Semaphore sm;
    public static void main(String[] args) {
        
        sm = new Semaphore(1);
        Random random = new Random();
        for(int i=0; i<5; i++){
            int rn = random.nextInt(3);
            int time = rn < 2 ? 2000 : 3000;
            new Thread(new Plane(i, random.nextInt(400) + 100, destinations[rn], time, sm)).start();
        }

    }
}

class Plane implements Runnable{
    int n;
    int capacity;
    String destination;
    int flyTime;
    Semaphore sm;

    public Plane(int n, int capacity, String dest, int ft, Semaphore sm){
        this.n = n;
        this.capacity = capacity;
        destination = dest;
        flyTime = ft;
        this.sm = sm;
    }

    @Override
    public void run(){
        while(!Thread.interrupted()){
            try{
                sm.acquire();
                System.out.println("Plane "+n+" is being borded");
                Thread.sleep(capacity*10);
                sm.release();
                System.out.println("Plane "+n+" is ready to takeof");
                Thread.sleep(flyTime);
                System.out.println("Plane "+n+" has returned to the airoport");
                Thread.sleep(1000);
            } catch(InterruptedException e){}
        }
    }
}