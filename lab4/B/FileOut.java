package lab4.B;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileOut implements Runnable {
    Garden garden;
    int size;
    String filename = "C:\\Users\\spery\\dp_lab\\lab4\\B\\out.txt";

    public FileOut(Garden g){
        garden = g;
        size = g.size;
    }

    @Override
    public void run(){
        while(!Thread.interrupted()){
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            garden.readLock.lock();
            
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true));
                boolean[][] matrix = garden.read();
                for (int i = 0; i < matrix.length; i++) {
                    for (int j = 0; j < matrix[i].length; j++) {
                        bw.write(matrix[i][j] + ((j == matrix[i].length-1) ? "" : ","));
                    }
                bw.newLine();
                }
                bw.newLine();
                bw.flush();
                bw.close();
            } catch (IOException e) {}
            garden.readLock.unlock();
        }
    }

}
