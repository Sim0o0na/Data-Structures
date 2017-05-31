package doubly_linkedlist_implementation;

import java.util.Scanner;

/**
 * Created by Simona Simeonova on 5/15/2017.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = Integer.parseInt(scan.nextLine());

        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        //test add function
        for(int i = 0; i<n; i++){
            list.addFirst(i);
        }


        list.add(55, 2);
        System.out.println(list.toString());
        list.delete(4);
        String debug = "";
        list.iterateBackward();
    }
}
