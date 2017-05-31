import java.util.*;

/**
 * Created by Simona Simeonova on 5/25/2017.
 */
public class ReverseNumbers {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Deque<Integer> stack = new ArrayDeque<>();
        int[] arr = Arrays.stream(scan.nextLine().split("\\s+")).mapToInt(Integer::valueOf).toArray();
        for(Integer num:arr){
            stack.push(num);
        }
        while(!stack.isEmpty()){
            System.out.printf("%d ", stack.pop());
        }
    }
}
