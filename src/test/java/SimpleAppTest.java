import Contrillers.Controller;
import Contrillers.ControllerImpl.ControllerImpl;
import Entity.Person;
import Entity.Product;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class SimpleAppTest {

   private SparkSession spark;
   private Controller controller;
   private Person malfoy;
   private Product owl;
   private List<Product> good;

    @Before
    public void init(){
       spark  = SparkSession
                .builder()
                .appName("Simple Application")
                .config("spark.master", "local")
                .getOrCreate();
        controller = new ControllerImpl();
        malfoy =  new Person("Malfoy", "Draco", 11, "18.04.1980", "Wizzard");
        owl = new Product("Owl", 10000 , 1 );
        good = new ArrayList<>();
        malfoy.setProduct(good);
        malfoy.buyProduct(owl);
    }

    @After
    public void tearDown(){
        controller = null;
        malfoy = null;
        owl = null;
        good = null;
    }

    @Test
    public void ConvertMalfoyObjectToXMLFile(){
        Assert.assertEquals(true, controller.ConvertPearsonToXML(malfoy, "E:\\Malfoy.xml"));

    }

    @Test
    public void ReadMAlfoyFromXmlFile(){

        Dataset malfoy = controller.readFile(spark, "Client", "E:\\Malfoy.xml");
        Dataset malfoyGoods = controller.readFile(spark, "product", "E:\\Malfoy.xml");

        malfoy.show();
        malfoyGoods.show();
    }



}
