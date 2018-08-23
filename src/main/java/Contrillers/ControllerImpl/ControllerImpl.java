package Contrillers.ControllerImpl;

import Contrillers.Controller;
import Entity.Person;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class ControllerImpl implements Controller {

    public Dataset readFile(SparkSession spark, String tag, String path){

        return spark.read()
                .format("com.databricks.spark.xml")
                .option("rowTag", tag)
                .load(path);
    }
    public boolean ConvertPearsonToXML(Person person, String filePath) {
        try {
            JAXBContext context = JAXBContext.newInstance(Person.class);
            Marshaller marshaller = context.createMarshaller();
            // устанавливаем флаг для читабельного вывода XML в JAXB
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // маршаллинг объекта в файл
            marshaller.marshal(person, new File(filePath));
            return true;
        } catch (JAXBException e) {
            e.printStackTrace();
            return false;
        }
    }
}


