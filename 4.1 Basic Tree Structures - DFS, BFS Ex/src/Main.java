import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Simona Simeonova on 6/5/2017.
 */
public class Main {
    static HashMap<Integer, Tree<Integer>> nodeByValue = new HashMap<>();
    static Tree<Integer> deepest;
    public static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        readTree();
//        Tree<Integer> root = getTreeRoot();
//        System.out.println(printTree(root,0,new StringBuilder()));
//        System.out.printf("Leaf nodes: %s",findLeafNodes());
//            System.out.printf("Middle nodes: %s",findMiddleNodes());
//        Tree<Integer> root = getTreeRoot();
//        findDeepest(root);
//        String debug = "";
//        System.out.printf("Deepest node: %s", deepest.value);
    }

    public static List<Tree<Integer>> getPathsWithSum(){
        List<Tree<Integer>> leafs = new ArrayList<>();
        int target = Integer.parseInt(scanner.nextLine());
        DFS(getTreeRoot(),target,0,leafs);
        return leafs;
    }
//    public static Tree<Integer> findDeepest(Tree<Integer> root){
//        int maxLevel = 0;
//        DFS(root,1,maxLevel);
//        return deepest;
//    }
    public static void DFS(Tree<Integer> tree, int target, int current, List<Tree<Integer>> leafs) {
        if(tree==null){
            return;
        }
        current+=tree.value;

        if(current==target && tree.children.size()==0){
            leafs.add(tree);
        }

        for(Tree<Integer> child:tree.children){
            DFS(child,target,current,leafs);
        }
    }


//    public static void DFS(Tree<Integer> tree, int level, int maxLevel){
//        if(tree==null){
//            return;
//        }
//
//        if(level>maxLevel){
//            deepest = tree;
//            maxLevel = level;
//        }
//        for (Tree<Integer> child:tree.children) {
//            DFS(child, level+1, maxLevel);
//        }
//    }

    public static String findMiddleNodes(){
        StringBuilder builder = new StringBuilder();
        nodeByValue.entrySet().stream()
                .filter(pair->!pair.getValue().children.isEmpty() && pair.getValue().parent!=null)
                .sorted((i,j) -> i.getValue().value.compareTo(j.getValue().value))
                .forEach(p-> builder.append(String.valueOf(p.getValue().value) + " "));
        return builder.toString();
    }
    public static String findLeafNodes(){
        StringBuilder builder = new StringBuilder();
        nodeByValue.entrySet().stream()
                .filter(pair->pair.getValue().children.isEmpty())
                .sorted((i,j) -> i.getValue().value.compareTo(j.getValue().value))
                .forEach(p-> builder.append(String.valueOf(p.getValue().value) + " "));
        return builder.toString();
    }
    public static String printTree(Tree<Integer> tree,int indent,StringBuilder builder){
        builder.append(new String(new char[indent]).replace("\0", "  "));
        builder.append(tree.value + "\n");
        for (Tree child:tree.children) {
            builder.append(printTree(child,indent+1, new StringBuilder()));
        }
        return builder.toString();
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
