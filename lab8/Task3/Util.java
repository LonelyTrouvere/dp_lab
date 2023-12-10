package lab8.Task3;

import java.io.Serializable;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
public class Util {
    public static byte[] serializeObject(Serializable object) throws IOException{
        try(ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(byteArrayOutputStream)){
            objectOutputStream.writeObject(object);
            return byteArrayOutputStream.toByteArray();
        }
    }
    public static Data deserializeObject(byte[] bytes) throws IOException,ClassNotFoundException{
        try(ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)){
            return (Data) objectInputStream.readObject();
        }
    }
}