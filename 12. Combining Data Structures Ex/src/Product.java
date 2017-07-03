/**
 * Created by Simona Simeonova on 6/27/2017.
 */
public class Product implements Comparable {
    public String name;
    public Double price;
    public String producer;

    public Product(){};

    public Product(String name, Double price, String producer){
        this.name = name;
        this.price = price;
        this.producer = producer;
    }

    @Override
    public int compareTo(Object o1) {
        return 0;
    }
}
