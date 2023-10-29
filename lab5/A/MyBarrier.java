package lab5.A;

public class MyBarrier {
    public int totalThreads;
    private int workingThreads;

    public MyBarrier() {
        totalThreads = 4;
        workingThreads = 4;
    }

    public synchronized void await(int n) throws InterruptedException {
        workingThreads--;
        System.out.println("Cadets in group "+n+": waiting");
        if(workingThreads > 0) {
            this.wait();
        }
        workingThreads = totalThreads;
        notifyAll();
    }
}
