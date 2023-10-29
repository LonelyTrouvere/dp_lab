package lab5.B;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class StringThread implements Runnable {
    
    private String str;
    CyclicBarrier bar;
    Result res;
    int numA = 0;
    int numB = 0;
    int n;

    public StringThread(String s, CyclicBarrier b, Result r, int n){
        str = s;
        bar = b;
        res = r;
        this.n = n;

        for (int i=0; i<str.length(); i++){
            if (str.charAt(i) == 'A')
                numA++;
            if (str.charAt(i) == 'B')
                numB++;
        }

    }

    @Override
    public void run(){
        while(!res.run){
            try{
                Random rand = new Random();
                int pos = rand.nextInt(str.length());
                if (str.charAt(pos) == 'A'){
                    str = str.substring(0, pos) + 'C' + str.substring(pos+1);
                    numA--;
                } else 
                if (str.charAt(pos) == 'B'){
                    str = str.substring(0, pos) + 'D' + str.substring(pos+1);
                    numB--;
                } else 
                if (str.charAt(pos) == 'C'){
                    str = str.substring(0, pos) + 'A' + str.substring(pos+1);
                    numA++;
                } else
                if (str.charAt(pos) == 'D'){
                    str = str.substring(0, pos) + 'B' + str.substring(pos+1);
                    numB++;
                }

                System.out.println("String "+n+" is awaiting");
                res.addRes(numA, numB);
                bar.await();
            } catch (InterruptedException e) {}
              catch (BrokenBarrierException e) {}
            
        }
    }

}
