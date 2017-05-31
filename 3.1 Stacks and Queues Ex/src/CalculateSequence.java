import java.util.*;

/**
 * Created by Simona Simeonova on 5/25/2017.
 */
public class CalculateSequence {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        Deque<Integer> queue = new LinkedList<>();
        queue.add(n);

        int index = 0;
        int[] members = new int[50];

        while (true){
            int element = queue.removeFirst();

            members[index] = element;
            index++;

            if(index==50){
                break;
            }

            queue.addLast(element+1);
            queue.addLast(2*element+1);
            queue.addLast(element+2);
        }
        System.out.println(Arrays.toString(members).replace("[", "").replace("]", ""));
    }
}
