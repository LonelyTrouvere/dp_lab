package lab4.A;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Writer implements Runnable {
    AtomicInteger readLock;
    AtomicBoolean writeLock;
    String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String nums = "0123456789";
    String path = "C:\\Users\\spery\\dp_lab\\lab4\\A\\cat.txt";

    public Writer(AtomicInteger rl, AtomicBoolean wl){
        readLock = rl;
        writeLock = wl;
    }

    @Override
    public void run(){
        while(!Thread.interrupted()){
            if (readLock.get() == 0){
                try{
                    writeLock.set(true);
                    System.out.println("New person is being added to the catalog");
                    Random rand = new Random();
                    String name = String.valueOf(abc.charAt(rand.nextInt(abc.length())));
                    String num = String.valueOf(nums.charAt(rand.nextInt(nums.length())));

                    BufferedWriter bw = new BufferedWriter(new FileWriter(path, true));
                    bw.write(name+" "+num+"\n");
                    bw.close();

                    System.out.println("Person "+name+" with number "+num+" was added to the catalog");

                    writeLock.set(false);
                    Thread.sleep(5000);
                } catch(Exception e) {}
            }
        }
    }

}
