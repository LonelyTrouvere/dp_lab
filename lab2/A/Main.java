package lab2.A;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        Manager.start();
    }
}

class Manager{
    static public AtomicBoolean bearFound;
    static public AtomicInteger fieldSize;
    static public boolean[][] field;
    static public int bearColumn;
    static public int bearRow;

    public static void start(){
        Scanner input = new Scanner( System.in ); 

        bearFound = new AtomicBoolean(false);
        System.out.print( "Enter field size: " );
        fieldSize = new AtomicInteger(input.nextInt());

        field = new boolean[fieldSize.get()][fieldSize.get()];

        bearColumn = (int)(Math.random()*fieldSize.get());
        bearRow = (int)(Math.random()*fieldSize.get());

        System.out.println("Bear is in "+bearRow+" "+bearColumn);

        field[bearRow][bearColumn] = true;

        for (int i=0; i<fieldSize.get(); i++){
            new Thread(new BeeThread(i)).start();
        }
    }

    static class BeeThread extends Thread{
      
    private int group;
    private int col;

    public BeeThread(int i){
        this.group = i;
        col = 0;
    }

    public void run(){
        System.out.println("Bee group "+this.group+" is searching");
        try{
         while (!isInterrupted() && !bearFound.get() && this.col < fieldSize.get()){
            if (field[group][col]){
                System.out.println("Bear found by group "+this.group+" in position ("+this.group+"; "+this.col+")");
                bearFound.set(true);
                this.interrupt();
                break;
            }
            System.out.println("Group "+this.group+" didnt find bear in column "+this.col);
            this.col++;
            Thread.sleep(2000);
         }
        } catch (InterruptedException e){
            System.out.println(e);
        }
    }
    }

}
