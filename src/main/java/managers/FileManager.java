package managers;

import data.LabWorkCatalog;
import data.LabWork;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.TreeSet;

/**
 * Класс, отвечающий за загрузку коллекции в XML-файл и чтению коллекции из XML-файла
 */
public class FileManager {
    private String envVar;
    public FileManager(String envVar){
        this.envVar = envVar;
    }

    /**
     * Метод осуществляет запись коллекции в XML-файл
     * @param collection
     */
    public void fromCollectionToXML(TreeSet<LabWork> collection){
        try {
            var catalog = new LabWorkCatalog();
            catalog.setLabWorkCatalog(collection);
            var context = JAXBContext.newInstance(LabWorkCatalog.class);
            var m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(envVar),"UTF-8");
            m.marshal(catalog, outputStreamWriter);
        } catch (JAXBException ex) {
            ConsoleManager.printError("Произошла ошибка при загрузке коллекции в файл!");
        } catch (FileNotFoundException ex) {
            ConsoleManager.printError("Файл с таким именем не найден!");
        } catch (UnsupportedEncodingException ex) {
            ConsoleManager.printError("Кодировка символов не поддерживается!");
        }
    }

    /**
     * Метод осуществляет чтение коллекции из XML-файла
     * @return collection
     */
    public TreeSet<LabWork> fromXMLtoCollection() {
        try {
            var context = JAXBContext.newInstance(LabWorkCatalog.class);
            var um = context.createUnmarshaller();
            var catalog = (LabWorkCatalog) um.unmarshal(new BufferedReader(new InputStreamReader(new FileInputStream(envVar), StandardCharsets.UTF_8)));
            return catalog.getLabWorkCatalog();
        } catch (JAXBException ex) {
            ConsoleManager.printError("Произошла ошибка при чтении xml-файла в коллекцию!");
        } catch (FileNotFoundException ex) {
            ConsoleManager.printError("Файл с таким именем не найден!");
        }
        return null;
    }
}
