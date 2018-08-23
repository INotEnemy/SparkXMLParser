import Contrillers.Controller;
import Contrillers.ControllerImpl.ControllerImpl;
import Entity.Person;
import Entity.Product;
import org.apache.spark.sql.*;
import java.util.ArrayList;
import java.util.List;
import static org.apache.spark.sql.functions.col;

public class SimpleApp {
    public static void main(String[] args) {
        Controller controller = new ControllerImpl() ;

        String filePath = "E:\\client.xml";

        String pearsonTableName = "Pearsons";

        String productTableName = "Products";

        SparkSession spark = SparkSession
                .builder()
                .appName("Simple Application")
                .config("spark.master", "local")
                .getOrCreate();
        Person malfoy =  new Person("Malfoy", "Draco", 11, "18.04.1980", "Wizzard");
        Product owl = new Product("Owl", 10000 , 1 );
        List<Product> good = new ArrayList<>();
        malfoy.setProduct(good);
        malfoy.buyProduct(owl);
        controller.ConvertPearsonToXML(malfoy, "E:\\xml.Malfoy.xml");

        Dataset mafoy = controller.readFile(spark, "Client","E:\\xml.Malfoy.xml" );
        Dataset mafoyGoods = controller.readFile(spark, "product","E:\\xml.Malfoy.xml" );

        mafoy.select(col("FirstName"), col("LastName"), col("Age"),
                col("DateOfBirthday"), col("Profession")).write()
                .format("jdbc")
                .option("url","jdbc:postgresql://localhost:5432/TableForMySparkApp")
                .option("dbtable", pearsonTableName)
                .option("user","postgres")
                .option("password", "1234")
                .mode(SaveMode.Append)
                .save();

        mafoyGoods.select(col("Name"), col("Price"), col("Count")).write()
                .format("jdbc")
                .option("url","jdbc:postgresql://localhost:5432/TableForMySparkApp")
                .option("dbtable", productTableName)
                .option("user","postgres")
                .option("password", "1234")
                .mode(SaveMode.Append)
                .save();

        Dataset people = controller.readFile(spark, "Client", filePath);

        people.select(col("FirstName"), col("LastName"), col("Age"),
                col("DateOfBirthday"), col("Profession")).write()
                .format("jdbc")
                .option("url","jdbc:postgresql://localhost:5432/TableForMySparkApp")
                .option("dbtable", pearsonTableName)
                .option("user","postgres")
                .option("password", "1234")
                .mode(SaveMode.Append)
                .save();

        Dataset goods = controller.readFile(spark, "Product", filePath);

        goods.select(col("Name"), col("Price"), col("Count")).write()
                .format("jdbc")
                .option("url","jdbc:postgresql://localhost:5432/TableForMySparkApp")
                .option("dbtable", productTableName)
                .option("user","postgres")
                .option("password", "1234")
                .mode(SaveMode.Append)
                .save();

        spark.stop();
    }
}