package lab4.B;

import java.util.concurrent.ThreadLocalRandom;

public class Gardener implements Runnable {
    Garden garden;
    int size;

    public Gardener(Garden g){
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
                        garden.water(i, j);
                    }
                }
            } finally {
                garden.writeLock.unlock();
            }
        }
    }

}
