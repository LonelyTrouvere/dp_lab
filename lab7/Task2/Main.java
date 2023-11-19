package lab7.Task2;

import java.util.ArrayList;
import java.util.UUID;

public class Main {
    
    public static void main(String[] args) {
        DB db = new DB();

        ArrayList<MyFile> f = db.getFilesFromFolder("0f8bfce6-b4aa-4915-add2-1edb25813bf5");
        for(MyFile fo : f)
            System.out.println(fo);
    }
}