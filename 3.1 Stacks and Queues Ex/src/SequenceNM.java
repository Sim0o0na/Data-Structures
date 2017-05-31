import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by Simona Simeonova on 5/25/2017.
 */
public class SequenceNM {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int start = in.nextInt();
        int end = in.nextInt();

        if(end<start){
            return;
        }

        Deque<Item> queue = new LinkedList<>();
        Item firstItem = new Item(start, null);
        int count = 0;
        queue.addLast(firstItem);
        while (true){
            Item element = queue.removeFirst();
            if(element.value==end){
                while(element!=null){
                    System.out.printf("%s -> ",element.value);
                    element=element.prevItem;
                }
                return;
            }
            queue.addLast(new Item(element.value+1,element));
            queue.addLast(new Item(element.value+2,element));
            queue.addLast(new Item(element.value*2,element));
        }
    }
    private static class Item{
        private int value;
        private Item prevItem;

        public Item(){

        }

        public Item(int value, Item prevItem){
            this.value = value;
            this.prevItem = prevItem;
        }
    }
}
