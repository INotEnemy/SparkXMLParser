package Contrillers;

import Entity.Person;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;

public interface Controller {

    public Dataset readFile(SparkSession spark, String tag, String path);

    public boolean ConvertPearsonToXML(Person person, String path);
}

