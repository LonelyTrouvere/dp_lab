package lab2.B;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Main {
    private static final Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        final int CAPACITY = 5;
        final int NUM_DETAILS = 20;

        Task pcc = new Task(CAPACITY);

        Thread ivanovThread = new Thread(() -> {
            try {
                pcc.carry(NUM_DETAILS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread petrovThread = new Thread(() -> {
            try {
                pcc.load(NUM_DETAILS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread nechiporchukThread = new Thread(() -> {
            try {
                pcc.count(NUM_DETAILS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        ivanovThread.start();
        petrovThread.start();
        nechiporchukThread.start();

        ivanovThread.join();
        petrovThread.join();
        nechiporchukThread.join();
    }

    private static void justSleep() {
        try {
            Thread.sleep(random.nextInt(450) + 10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    static class Task {
        private final BlockingQueue<Integer> carriedItems;
        private final BlockingQueue<Integer> loadedItems;

        public Task(Integer capacity) {
            this.carriedItems = new LinkedBlockingDeque<>(capacity);
            this.loadedItems = new LinkedBlockingDeque<>(capacity);
        }

        public void carry(int numItems) throws InterruptedException {
            int curDetail = 1;
            while (true) {
                System.out.println("Ivanov carried #" + curDetail);
                this.carriedItems.put(curDetail++);

                if (curDetail == numItems + 1)
                    break;

                justSleep();
            }
        }

        public void load(int numItems) throws InterruptedException {
            while (true) {
                int removed = this.carriedItems.take();
                numItems--;
                System.out.println("Petrov loaded item #" + removed);
                this.loadedItems.put(removed);

                if (numItems == 0)
                    break;

                justSleep();
            }
        }

        public void count(int numItems) throws InterruptedException {
            int count = 1;
            while (true) {
                int removed = loadedItems.take();
                System.out.println("Nechuporchyk counted item #" + removed);
                count++;

                if (count == numItems + 1)
                    break;

                justSleep();
            }
        }
    }
}