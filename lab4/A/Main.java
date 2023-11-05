package lab4.A;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    static public AtomicInteger readLock = new AtomicInteger(0);
    static public AtomicBoolean writeLock = new AtomicBoolean(false);
    public static void main(String[] args) {
         
       new Thread(new Writer(readLock, writeLock)).start();
       new Thread(new NameReader(readLock, writeLock)).start();
       new Thread(new PhoneReader(readLock, writeLock)).start();
       new Thread(new Deleter(readLock, writeLock)).start();

    }
}
