package lab5.B;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class Main {
    static Random rand = new Random();
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(4);
        Result res = new Result();

            new Thread(new StringThread("ABCDCDAABCD", barrier, res, 1)).start();
            new Thread(new StringThread("AAACAACBBAC", barrier, res, 2)).start();
            new Thread(new StringThread("ACDCADCACDC", barrier, res, 3)).start();
            new Thread(new StringThread("CDABBABCDAB", barrier, res, 4)).start();
    }
}