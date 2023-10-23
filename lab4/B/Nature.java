package lab4.B;

import java.util.concurrent.ThreadLocalRandom;

public class Nature implements Runnable {
    Garden garden;
    int size;

    public Nature(Garden g){
        garden = g;
        size = g.size;
    }

    @Override
    public void run(){
        while(!Thread.interrupted()) {
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(2000, 4000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            garden.writeLock.lock();
            try {
                for (int i = 0; i < size; i += 1) {
                    for (int j = 0; j < size; j += 1) {
                        boolean toDry = ThreadLocalRandom.current().nextBoolean();
                        if (toDry){
                            garden.dry(i, j);
                        }
                    }
                }
            } finally {
                garden.writeLock.unlock();
            }
        }
    }

}
