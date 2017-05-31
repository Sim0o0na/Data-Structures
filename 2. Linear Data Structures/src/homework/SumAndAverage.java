package homework;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by Simona Simeonova on 5/23/2017.
 */
public class SumAndAverage {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<Integer> list = Arrays.stream(scan.nextLine().split("\\s+")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());

        Integer sum = list.stream().mapToInt(Integer::valueOf).sum();
        Double avg = list.stream().mapToDouble(Double::valueOf).average().getAsDouble();

        System.out.printf("Sum=%d; Average=%.2f", sum, avg);
    }
}
