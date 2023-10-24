package lab4.A;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class NameReader implements Runnable {
    AtomicInteger readLock;
    AtomicBoolean writeLock;
    String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String path = "C:\\Users\\spery\\dp_lab\\lab4\\A\\cat.txt";

    public NameReader(AtomicInteger rl, AtomicBoolean wl){
        readLock = rl;
        writeLock = wl;
    }

    @Override
    public void run(){
        while(!Thread.interrupted()){
            try{
                if (!writeLock.get()){
                    readLock.incrementAndGet();
                    Random rand = new Random();
                    String toFind = String.valueOf(abc.charAt(rand.nextInt(abc.length())));

                    System.out.println("Looking for person with name "+toFind);

                    BufferedReader br = new BufferedReader(new FileReader(path));
                    String line = br.readLine();
                    
                    while(line != null){
                        Person person = new Person(line);
                        if (toFind.equals(person.name)){
                            System.out.println("Person "+person.name+" found with phone "+person.phone);
                            break;
                        }
                        line = br.readLine();
                    }

                    if (line == null){
                        System.out.println("Person not found");
                    }
                    br.close();
                    readLock.decrementAndGet();
                    Thread.sleep(3000);
                }
            } catch(Exception e) {}
        }
    }

}
