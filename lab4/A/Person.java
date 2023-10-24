package lab4.A;

public class Person {
    public String name;
    public String phone;

    public Person(String input){
        String[] sp = input.split(" ");
        name = sp[0];
        phone = sp[1];
    }
}
