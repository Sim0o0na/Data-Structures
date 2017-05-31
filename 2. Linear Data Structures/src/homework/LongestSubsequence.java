package homework;

        import java.util.*;
        import java.util.stream.Collectors;

/**
 * Created by Simona Simeonova on 5/18/2017.
 */
public class LongestSubsequence {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<Integer> list = Arrays.stream(scan.nextLine().split("\\s+")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        List<Integer> longestSub = new ArrayList<>();

        Integer bestLen = 1;
        Integer mostRepeatedElement = 0;
        //2 1 1 2 3 3 2 2 2 1
        Integer element = list.get(0);
        Integer currLen = 1;

        for(int position = 1; position<list.size(); position++){
            if(list.get(position).equals(element)){
                currLen++;
                if(currLen>bestLen){
                    bestLen=currLen;
                    mostRepeatedElement=element;
                }
            }
            else{
                currLen=1;
                element = list.get(position);
            }
        }

        for(int repeat = 0; repeat<bestLen; repeat++){
            longestSub.add(mostRepeatedElement);
        }

        longestSub.stream().forEach(num-> System.out.print(num + " "));
        String debug = "";
    }
}
