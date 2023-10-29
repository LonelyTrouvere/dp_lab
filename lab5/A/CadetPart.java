package lab5.A;

import java.util.Arrays;

public class CadetPart implements Runnable {
    
    boolean cadets[];
    int left;
    int right;
    MyBarrier barrier;
    int n;

    public CadetPart(boolean c[], int l, int r, MyBarrier b, int n){
        cadets = c;
        left = l;
        right = r;
        barrier = b;
        this.n = n;
    }

    @Override
    public void run(){
        while(true){
            try{
                System.out.println("Cadets in group "+n+": started");
                boolean sorted = true;
                for (int i = left; i<right-1; i++){
                    if(cadets[i] != cadets[i+1]){
                        cadets[i] = !cadets[i];
                        sorted = false;
                    }
                }

                if (sorted){
                    System.out.println("Cadets in group "+n+": finised with result"+Arrays.toString(cadets));
                    barrier.await(n);
                    break;
                }
                
                barrier.await(n);
            } catch (InterruptedException e){}

        }
    }

}
