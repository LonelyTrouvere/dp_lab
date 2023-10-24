package Module;

import java.util.ArrayList;
import java.util.Random;

public class Task10Java {
    static ArrayList<Plane> planes = new ArrayList<Plane>();
    static public String[] destinations = {"Washington", "Toronto", "Berlin", "Vienna"};
    public static void main(String[] args) {
         
        Random random = new Random();
        for(int i=0; i<planes.size(); i++){
            int rn = random.nextInt(3);
            int time = rn < 2 ? 2000 : 3000;
            new Thread(new Plane(i, random.nextInt(400) + 100, destinations[rn], time)).start();
            System.out.println(i);
        }

    }
}

class Plane implements Runnable{
    int n;
    int capacity;
    String destination;
    int flyTime;

    public Plane(int n, int capacity, String dest, int ft){
        this.n = n;
        this.capacity = capacity;
        destination = dest;
        flyTime = ft;
    }

    @Override
    public void run(){
        while(!Thread.interrupted()){
            try{
                System.out.println("Plane "+n+" is being borded");
                Thread.sleep(capacity*10);
                System.out.println("Plane "+n+" is ready to takeof");
                Thread.sleep(flyTime);
                System.out.println("Plane "+n+" has returned to the airoport");
                Thread.sleep(1000);
            } catch(InterruptedException e){}
        }
    }
}