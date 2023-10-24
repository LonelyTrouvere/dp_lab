package lab4.A;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Deleter implements Runnable {
    AtomicInteger readLock;
    AtomicBoolean writeLock;
    String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String path = "C:\\Users\\spery\\dp_lab\\lab4\\A\\cat.txt";
    File curFile = new File(path);
    File nextFile = new File("C:\\Users\\spery\\dp_lab\\lab4\\A\\temp.txt");

    public Deleter(AtomicInteger rl, AtomicBoolean wl){
        readLock = rl;
        writeLock = wl;
    }

    @Override
    public void run(){
        while(!Thread.interrupted()){
            try{
                if (readLock.get() == 0){
                    writeLock.set(true);
                    BufferedReader br = new BufferedReader(new FileReader(curFile));
                    BufferedWriter bw = new BufferedWriter(new FileWriter(nextFile, true));

                    Random rand = new Random();
                    String toDelete = String.valueOf(abc.charAt(rand.nextInt(abc.length())));
                    String line = br.readLine();

                    while(line != null){
                        Person person = new Person(line);
                        if(!toDelete.equals(person.name)){
                            bw.write(line+"\n");
                        }
                        line = br.readLine();
                    }

                    br.close();
                    bw.close();
                    curFile.delete();
                    nextFile.renameTo(curFile);
                    System.out.print("Deleted all person with name "+ toDelete);
                    writeLock.set(false);
                    Thread.sleep(10000);
                }
            } catch (Exception e) {}
        }
    }
}
