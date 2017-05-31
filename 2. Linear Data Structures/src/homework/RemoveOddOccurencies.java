package homework;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Simona Simeonova on 5/18/2017.
 */
public class RemoveOddOccurencies {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<Integer> list = Arrays.stream(scan.nextLine().split("\\s+")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());

        HashMap<Integer, Integer> occurencies = new HashMap<>();
        List<Integer> line = new ArrayList<>();

        for(int i = 0; i < list.size(); i++){
            if(occurencies.containsKey(list.get(i))){
                occurencies.put(list.get(i), occurencies.get(list.get(i))+1);
            }else{
                occurencies.put(list.get(i), 1);
            }
        }

        for(int i = 0; i < list.size(); i++){
            if(occurencies.get(list.get(i))%2==0){
                line.add(list.get(i));
            }
        }

        line.forEach(num-> System.out.printf("%s ", num));

        String debug = "";
    }
}
