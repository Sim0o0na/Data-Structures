import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

/**
 * Created by Simona Simeonova on 6/5/2017.
 */
public class RootNode {
    static HashMap<Integer, Tree<Integer>> nodeByValue = new HashMap<>();
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        readTree();
        System.out.printf("Root node: %s", getTreeRoot().value);
    }
    static Tree<Integer> getTreeNodeByValue(int value){
        if(!nodeByValue.containsKey(value)){
            nodeByValue.put(value,new Tree<>(value));
        }
        return nodeByValue.get(value);
    }
    public static void addEdge(int parent, int child){
        Tree<Integer> parentNode = getTreeNodeByValue(parent);
        Tree<Integer> childNode = getTreeNodeByValue(child);
        parentNode.children.add(childNode);
        childNode.parent = parentNode;
    }
    public static void readTree(){
        int nodesCount = Integer.parseInt(scanner.nextLine());
        for(int i = 1; i < nodesCount; i++){
            String[] input = scanner.nextLine().split("\\s+");
            addEdge(Integer.parseInt(input[0]),Integer.parseInt(input[1]));
        }
    }
    static Tree<Integer> getTreeRoot() {
        return nodeByValue.entrySet().stream().filter(pair -> pair.getValue().parent == null).findFirst().get().getValue();
    }
}
