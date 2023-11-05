package lab7.Task1;

public class Main {
    
    public static void main(String[] args) {
        FileSystem fs = new FileSystem();

        fs.deleteFolder("id_ffde648d-9efd-4350-ad60-5c2ec303dd32");
        var k = fs.getFiles();

        for (int i=0; i<k.size(); i++)
            System.out.println(k.get(i).toString());
    }
}