package lab4.B;

public class Main {
 
    public static void main(String[] args) {
         Garden garden = new Garden(5);

         new Thread(new Nature(garden)).start();
         new Thread(new Gardener(garden)).start();
         new Thread(new ConsoleOut(garden)).start();
         new Thread(new FileOut(garden)).start();
    }
}
