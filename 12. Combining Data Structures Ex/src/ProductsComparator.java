import java.util.Comparator;

/**
 * Created by Simona Simeonova on 6/27/2017.
 */
public class ProductsComparator implements Comparator{
    @Override
    public int compare(Object o1, Object o2) {
        return 1;
    }

    public int compareByName(Product o1, Product o2) {
        return o1.name.compareTo(o2.name);
    }

    public int compareByProducer(Product o1, Product o2) {
        return o1.producer.compareTo(o2.producer);
    }

    public int compareByPrice(Product o1, Product o2){
        return o1.price.compareTo(o2.price);
    }
}
