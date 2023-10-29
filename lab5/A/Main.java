package lab5.A;

import java.util.Random;

public class Main {
    static Random rand = new Random();
    public static void main(String[] args) {
        boolean cadets[] = new boolean[200];
        MyBarrier bar = new MyBarrier();

        for (int i=0; i<200; i++)
            cadets[i] = rand.nextBoolean();

        new Thread(new CadetPart(cadets, 0, 50, bar, 0)).start();
        new Thread(new CadetPart(cadets, 50, 100, bar, 1)).start();
        new Thread(new CadetPart(cadets, 100, 150, bar, 2)).start();
        new Thread(new CadetPart(cadets, 150, 199, bar, 3)).start();

    }
}