package lab7.Task1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class DOM {
    private File file;
    private Document document;

    public DOM(){
        file = new File("C:\\Users\\spery\\dp_lab\\lab7\\Task1\\file.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        DocumentBuilder builder = null;
        try{
            builder = factory.newDocumentBuilder();
            builder.setErrorHandler(new MyErrorHandler());
        } catch (ParserConfigurationException e) {e.printStackTrace();}

        try{
            document = builder.parse(file);
        } catch(SAXException e) {e.printStackTrace();}
          catch(IOException e) {e.printStackTrace();}
    }

    public ArrayList<Folder> getFolders(){
        var nodes = document.getElementsByTagName("Folder");
        ArrayList<Element> elements = new ArrayList<>();
        for (int i=0; i< nodes.getLength(); i++)
            elements.add((Element)nodes.item(i));
       
        ArrayList<Folder> folders = new ArrayList<>();
        for(int i=0; i<elements.size(); i++){
            Element el = elements.get(i);
            folders.add(
                new Folder(el.getAttribute("id"),
                el.getAttribute("name")));
        }

        return folders;
    }

    public ArrayList<MyFile> getFiles(){
        var nodes = document.getElementsByTagName("MyFile");
        ArrayList<Element> elements = new ArrayList<>();
        for (int i=0; i< nodes.getLength(); i++)
            elements.add((Element)nodes.item(i));
       
        ArrayList<MyFile> files = new ArrayList<>();
        for(int i=0; i<elements.size(); i++){
            Element el = elements.get(i);
            Element parent = (Element)el.getParentNode();
            files.add(
                new MyFile(el.getAttribute("id"),
                 el.getAttribute("name"),
                  el.getAttribute("type"),
                   Integer.parseInt(el.getAttribute("size")),
                   parent.getAttribute("id")));
        }

        return files;
    }

    public void updateFile(MyFile file){
        var el = document.getElementById(file.id);
        el.setAttribute("name", file.name);
        el.setAttribute("type", file.type);
        el.setAttribute("size", Integer.toString(file.size));
        this.saveXml();
    }

    public void updateFolder(Folder folder){
        var el = document.getElementById(folder.id);
        el.setAttribute("name", folder.name);
        this.saveXml();
    }

    public int createFile(MyFile newFile){
        var folder = document.getElementById(newFile.parentId);
        if (folder == null)
            return -1;
        Element file = document.createElement("MyFile");
        file.setAttribute("name", newFile.name);
        file.setAttribute("size", Integer.toString(newFile.size));
        file.setAttribute("type", newFile.type);
        file.setAttribute("id", newFile.id);
        folder.appendChild(file);
        saveXml();
        return 0;
    }

    public void saveXml(){
        Source domSource = new DOMSource(document);
        Result fileResult = new StreamResult(new File("C:\\Users\\spery\\dp_lab\\lab7\\Task1\\file.xml"));
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transform = null;
        try{
             transform = tf.newTransformer();
        } catch(TransformerConfigurationException e) {e.printStackTrace();}

        try{
            DocumentType documentType = document.getDoctype();
            if (documentType != null) {
                transform.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, documentType.getSystemId());
            }
        
            transform.transform(domSource, fileResult);
        } catch (TransformerException e) {e.printStackTrace();}
        
    }
}
