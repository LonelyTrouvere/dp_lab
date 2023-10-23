package lab4.B;

public class ConsoleOut implements Runnable {
    Garden garden;
    int size;

    public ConsoleOut(Garden g){
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
            boolean[][] matrix = garden.read();
            for (int i=0; i<size; i++){
                for(int j=0; j<size; j++){
                    System.out.print(matrix[i][j]+" ");
                }
                System.out.println();
            }
            System.out.println();
            garden.readLock.unlock();
        }
    }

}
