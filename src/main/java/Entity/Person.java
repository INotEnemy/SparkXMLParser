package Entity;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;


@XmlRootElement(name = "Client")
public class Person implements Serializable {

    private String firstName;
    private String lastName;
    private int age;
    private String dateOfBirthday;
    private String profession;
    private List<Product> Product;

    public Person(){}

    public Person(String firstName, String lastName, int age, String dateOfBirthday, String profession){
        setFirstName(firstName);
        setLastName(lastName);
        setAge(age);
        setDateOfBirthday(dateOfBirthday);
        setProfession(profession);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDateOfBirthday() {
        return dateOfBirthday;
    }

    public void setDateOfBirthday(String dateOfBirthday) {
        this.dateOfBirthday = dateOfBirthday;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public List<Product> getProduct() {
        return Product;
    }

    public void setProduct(List<Product> product) {
        this.Product = product;
    }

    public void buyProduct(Product goods) {
        Product.add(goods);
    }
}

