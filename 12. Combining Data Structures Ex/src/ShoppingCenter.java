import com.google.common.collect.Ordering;
import com.google.common.collect.TreeMultimap;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Simona Simeonova on 6/27/2017.
 */
public class ShoppingCenter {
    public TreeMultimap<String, Product> productsByProducer;
    public TreeMultimap<String, Product> productsByName;
    public ProductsComparator comparator = new ProductsComparator();

    public ShoppingCenter(){
        this.productsByName = TreeMultimap.create(Ordering.natural().reverse(), Ordering.natural());
        this.productsByProducer = TreeMultimap.create(Ordering.natural().reverse(), Ordering.natural());
    }

    public String addProduct(String name, double price, String producer){
        Product product = new Product(name, price,producer);
        if(this.productsByName.containsKey(name)){
            this.productsByName.get(name).add(product);
            this.productsByProducer.get(name).add(product);
            return "Product added";
        }
        this.productsByProducer.put(producer, product);
        this.productsByName.put(name, product);
        return "Product added";
    }

    public String deleteProducts(String producer){
        int result = this.deleteProductsByProducer(producer);
        if(result==0){
            return "No products found";
        }
        return String.format("%s products deleted",result);
    }

    public String deleteProducts(String name,String producer){
        int result = this.deleteProductsByName(name,producer);
        if(result==0){
            return "No products found";
        }
        return String.format("%s products deleted",result);
    }

    public int deleteProductsByName(String name,String producer){
        int count = 0;
        NavigableSet<Product> productsToDelete = this.productsByName.get(name);
        for (Product p: productsToDelete) {
            if(p.producer.equals(producer)){
                this.productsByName.remove(name,p);
                this.productsByProducer.remove(producer,p);
                count++;
            }
        }
        return count;
    }

    private int deleteProductsByProducer(String producer){
        int count = 0;
        while(this.productsByProducer.containsKey(producer)){
            count++;
            this.productsByProducer.remove(producer, productsByProducer.get(producer));
        }
        return count;
    }

    public String findProductsByName(String name){
        StringBuilder sb = new StringBuilder();
        List<Product> products = this.findByName(name);
        if(products.isEmpty()){
            return "No products found";
        }
        for(Product p:products){
            sb.append(String.format("{%s;%s;%f}\n", p.name,p.producer,p.price));
        }
        return sb.toString();
    }

    private List<Product> findByName(String name){
        return new ArrayList<>(this.productsByName.get(name));
    }
}
