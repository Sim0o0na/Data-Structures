import java.util.ArrayList;

/**
 * Created by Simona Simeonova on 5/25/2017.
 */
public class Main {
    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>();

        stack.push(5);
        stack.push(4);
        stack.push(3);
        stack.push(2);
        stack.push(1);

        Object[] arr = stack.toArray();
    }
}
