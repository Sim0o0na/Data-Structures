package homework;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by Simona Simeonova on 5/23/2017.
 */
public class SortWords {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<String> list = Arrays.stream(scan.nextLine().split("\\s+")).collect(Collectors.toList());
        list.sort(String::compareTo);
        list.forEach(s->System.out.printf("%s ", s));
    }
}
