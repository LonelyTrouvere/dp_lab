package lab8.Task2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import lab8.Task2.Schema.Folder;
import lab8.Task2.Schema.MyFile;

public class DAO {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/lab7";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";
    private Connection connection;

    static {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public DAO(){
        try{
            this.connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void closeConnection(){
        try{
            this.connection.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public String createFolder(Folder folder){
        try{
            PreparedStatement ps = connection.prepareStatement("INSERT INTO `folders` (id, name)  VALUES(?,?) ");
            ps.setString(1, UUID.randomUUID().toString());
            ps.setString(2, folder.name);
            ps.executeUpdate();
            return "Success";
        }
        catch(SQLException e){
            return e.toString();
        }
    }

    public String createFile(MyFile file){
        try{
            PreparedStatement ps = connection.prepareStatement("INSERT INTO `files` (id, name, size, type, folder_id) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, UUID.randomUUID().toString());
            ps.setString(2, file.name);
            ps.setInt(3, file.size);
            ps.setString(4, file.type);
            ps.setString(5, file.parentId);
            ps.executeUpdate();
            return "Success";
        } catch (SQLException e) {return e.toString();}
    }

    public String deleteFolder(String id){
        try{
            PreparedStatement ps = connection.prepareStatement("DELETE FROM `folders` WHERE id=?");
            ps.setString(1, id);
            ps.executeUpdate();
            return "Success";
        } catch(SQLException e) {return e.toString();}
    }

    public String deleteFile(String id){
        try{
            PreparedStatement ps = connection.prepareStatement("DELETE FROM `files` WHERE id=?");
            ps.setString(1, id);
            ps.executeUpdate();
            return "Success";
        } catch(SQLException e) {return e.toString();}
    }

    public String updateFolder(Folder folder){
        try{
            PreparedStatement ps = connection.prepareStatement("UPDATE `folders` SET name=? WHERE id=?");
            ps.setString(1, folder.name);
            ps.setString(2, folder.id);
            ps.executeUpdate();
            return "Success";
        } catch(SQLException e) {return e.toString();}
    }

    private String fileUpdater(MyFile file){
        try{
            PreparedStatement ps = connection.prepareStatement("UPDATE `files` SET name=?, size=?, type=? WHERE id=?");
            ps.setString(1, file.name);
            ps.setInt(2, file.size);
            ps.setString(3, file.type);
            ps.setString(4, file.id);
            ps.executeUpdate();
            return "Success";
        } catch(SQLException e) {return e.toString();}
    }

    public String updateFileName(String id, String newVal){
        MyFile fileToUpdate = this.getFile(id);
        fileToUpdate.name = newVal;
        return this.fileUpdater(fileToUpdate);
    }

    public String updateFileType(String id, String newVal){
        MyFile fileToUpdate = this.getFile(id);
        fileToUpdate.type = newVal;
        return this.fileUpdater(fileToUpdate);
    }   
    
    public String updateFileSize(String id, int newVal){
        MyFile fileToUpdate = this.getFile(id);
        fileToUpdate.size = newVal;
        return this.fileUpdater(fileToUpdate);
    } 

    public Folder getFolder(String id){
        Folder f = null;
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `folders` WHERE id=?");
            ps.setString(1, id);
            ResultSet res = ps.executeQuery();
            if(res.next())
                f = new Folder(res.getString("id"), res.getString("name"));
        } catch(SQLException e) {e.printStackTrace();}

        return f;
    }

    public MyFile getFile(String id){
        MyFile f = null;
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `files` WHERE id=?");
            ps.setString(1, id);
            ResultSet res = ps.executeQuery();
            if(res.next())
                f = new MyFile(res.getString("id"), res.getString("name"), res.getInt("size"), res.getString("type"), res.getString("folder_id"));
        } catch(SQLException e) {e.printStackTrace();}

        return f;
    }

    public ArrayList<Folder> getAllFolders(){
        ArrayList<Folder> folders = new ArrayList<>();
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `folders`");
            ResultSet res = ps.executeQuery();

            while(res.next())
                folders.add(new Folder(res.getString("id"), res.getString("name")));
        } catch(SQLException e) {e.printStackTrace();}
        return folders;
    }

    public ArrayList<MyFile> getAllFiles(){
        ArrayList<MyFile> files = new ArrayList<>();
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `files`");
            ResultSet res = ps.executeQuery();

            while(res.next())
                files.add(new MyFile(res.getString("id"), res.getString("name"), res.getInt("size"), res.getString("type"), res.getString("folder_id")));
        } catch(SQLException e) {e.printStackTrace();}
        return files;
    }

    public ArrayList<MyFile> getFilesFromFolder(String id){
        ArrayList<MyFile> files = new ArrayList<>();
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `files` WHERE folder_id=?");
            ps.setString(1, id);
            ResultSet res = ps.executeQuery();

            while(res.next())
                files.add(new MyFile(res.getString("id"), res.getString("name"), res.getInt("size"), res.getString("type"), res.getString("folder_id")));
        } catch(SQLException e) { e.printStackTrace(); }
        return files;
    }
}
