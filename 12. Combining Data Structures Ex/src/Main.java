/**
 * Created by Simona Simeonova on 6/27/2017.
 */
public class Main {
    public static void main(String[] args) {
        ShoppingCenter center = new ShoppingCenter();

        center.addProduct("Chereshi", 3.50, "Simona Simenova");
        center.addProduct("Chereshi", 4.50, "Penka Simenova");
        center.addProduct("Chereshi", 7.80, "Mimi Simenova");
        center.addProduct("Chushki", 5.50, "Pesho Simenova");
        center.addProduct("Patlajani", 4.50, "Simenova");
        center.addProduct("Krastavici", 3.00, "Simona");
        center.addProduct("Domati", 2.50, "Simona Simenova");

        System.out.println(center.findProductsByName("Chereshi"));
        String debug = "";
    }
}
