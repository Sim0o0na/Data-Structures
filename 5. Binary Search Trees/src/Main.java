/**
 * Created by Simona Simeonova on 6/6/2017.
 */
public class Main {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(5);
        bst.insert(4);
        bst.insert(7);
        bst.insert(6);
        bst.insert(4);

        BinarySearchTree<Integer> searchTree = bst.search(7);
        String debug = "";

    }
}
