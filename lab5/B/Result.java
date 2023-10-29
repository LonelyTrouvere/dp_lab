package lab5.B;

public class Result {
    int[] results;
    int curr_thread;
    int res_num;
    Boolean run;
    Boolean arrived;

    public Result(){
        results = new int[8];
        curr_thread = 0;
        res_num = 0;
        run = false;
        arrived = false;
    }

    private Boolean check(){
        int a = 0;
        int b = 0;

        for(int i=0; i<8; i++){
            if(i % 2 == 0)
                a += results[i];
            else 
                b += results[i];
        }

        if (a == b){
            System.out.println("Task is ready");
            return true;
        }
        else {
            System.out.println("Task continues");
            return false;
        }
    }

    public synchronized void addRes(int a, int b){
        results[curr_thread] = a;
        curr_thread++;
        results[curr_thread] = b;
        curr_thread++;

        res_num++;
        
        if (res_num == 4) {
            arrived = true;
            notifyAll();
        }

        try{
            while(!arrived){
                wait();
            }
            res_num--;

            if (res_num == 0) {
                run = check();
                arrived = false;
                curr_thread = 0;
            }
        } catch(InterruptedException e) {}
    }

}
