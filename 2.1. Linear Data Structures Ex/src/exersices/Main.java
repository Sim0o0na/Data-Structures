package exersices;

import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by Simona Simeonova on 5/19/2017.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReversedList<Integer> listche = new ReversedList<>();

        listche.add(4);
        listche.add(5);
        listche.add(6);
        listche.add(7);
        listche.add(8);

        listche.removeAt(2);

        Iterator<Integer> iterator = listche.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        String debug = "";
    }
}
