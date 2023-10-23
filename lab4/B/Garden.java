package lab4.B;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Garden {
    private boolean garden[][];
    public int size;
    ReadWriteLock rwLock = new ReentrantReadWriteLock();
    public Lock readLock = rwLock.readLock();
    public Lock writeLock = rwLock.writeLock();

    public Garden(int size){
        garden = new boolean[size][size];
        for (var i : garden){
            Arrays.fill(i, false);
        }
        this.size = size;
    }

    public void dry(int i, int j){
        garden[i][j] = true;
    }

    public void water(int i, int j){
        garden[i][j] = false;
    }

    public boolean[][] read(){
        return garden;
    }
}
