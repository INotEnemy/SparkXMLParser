package Entity;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlRootElement(name = "Product")
@XmlType(propOrder = {"name", "price", "count"})
public class Product implements Serializable  {
    private String name;
    private double price;
    private int count;

    public  Product(){}

    public  Product(String name, double price, int count) {
        setName(name);
        setPrice(price);
        setCount(count);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
