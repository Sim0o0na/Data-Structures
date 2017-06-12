import java.util.Deque;
import java.util.LinkedList;
import java.util.function.Consumer;

public class BinarySearchTree<T extends Comparable<T>> {
    private Node root;
    private int nodesCount;

    public BinarySearchTree() {
    }

    private BinarySearchTree(Node root) {
        this.preOrderCopy(root);
    }

    private void preOrderCopy(Node node) {
        if (node == null) {
            return;
        }

        this.insert(node.value);
        this.preOrderCopy(node.left);
        this.preOrderCopy(node.right);
    }

    public Node getRoot() {
        return this.root;
    }

    public int count(){
        return this.count(this.root);
    }
    private int count(Node node) {
        if(node==null){
            return 0;
        }
        return node.childrenCount;
    }

    public void insert(T value) {
        this.nodesCount++;

        if (this.root == null) {
            this.root = new Node(value);
            return;
        }

        Node parent = null;
        Node current = this.root;
        while (current != null) {
            parent = current;
            parent.childrenCount++;

            if (value.compareTo(current.value) < 0) {
                current = current.left;
            } else if (value.compareTo(current.value) > 0) {
                current = current.right;
            } else {
                return;
            }
        }

        Node newNode = new Node(value);
        if (value.compareTo(parent.value) < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        newNode.childrenCount = 1 + this.count(newNode.getRight()) + this.count(newNode.getLeft());
    }

    public boolean contains(T value) {
        Node current = this.root;
        while (current != null) {
            if (value.compareTo(current.value) < 0) {
                current = current.left;
            } else if (value.compareTo(current.value) > 0) {
                current = current.right;
            } else {
                break;
            }
        }

        return current != null;
    }

    public BinarySearchTree<T> search(T item) {
        Node current = this.root;
        while (current != null) {
            if (item.compareTo(current.value) < 0) {
                current = current.left;
            } else if (item.compareTo(current.value) > 0) {
                current = current.right;
            } else {
                break;
            }
        }

        return new BinarySearchTree<>(current);
    }

    public void eachInOrder(Consumer<T> consumer) {
        this.eachInOrder(this.root, consumer);
    }

    private void eachInOrder(Node node, Consumer<T> consumer) {
        if (node == null) {
            return;
        }

        this.eachInOrder(node.left, consumer);
        consumer.accept(node.value);
        this.eachInOrder(node.right, consumer);
    }

    public Iterable<T> range(T from, T to) {
        Deque<T> queue = new LinkedList<>();
        this.range(this.root, queue, from, to);
        return queue;
    }

    private void range(Node node, Deque<T> queue, T startRange, T endRange) {
        if (node == null) {
            return;
        }

        int compareStart = startRange.compareTo(node.value);
        int compareEnd = endRange.compareTo(node.value);
        if (compareStart < 0) {
            this.range(node.left, queue, startRange, endRange);
        }
        if (compareStart <= 0 && compareEnd >= 0) {
            queue.addLast(node.value);
        }
        if (compareEnd > 0) {
            this.range(node.right, queue, startRange, endRange);
        }
    }

    private T minValue(Node root) {
        T minv = root.value;
        while (root.left != null) {
            minv = root.left.value;
            root = root.left;
        }

        return minv;
    }

    public void deleteMin(){
        this.root = this.deleteMin(this.root);
    }

    private Node deleteMin(Node node) {
        if (this.root == null) {
            throw new IllegalArgumentException("Tree is empty!");
        }

//        Node min = this.root;
//        Node parent = null;
//
//        while (min.left != null) {
//            parent = min;
//            parent.childrenCount--;
//            min = min.left;
//        }
//
//        if (parent == null) {
//            this.root = this.root.right;
//        } else {
//            parent.left = min.right;
//        }

        if(node.left==null){
            return node.getRight();
        }

        node.left = this.deleteMin(node.left);
        node.childrenCount = this.count(node.getLeft()) + this.count(node.getRight()) + 1;

        return node;
    }

    public void deleteMax() {
        if(this.root==null){
            throw new IllegalArgumentException();
        }

        Node max = this.root;
        Node parent = null;

        while (max.right != null) {
            parent = max;
            parent.childrenCount--;
            max = max.right;
        }

        if (parent == null) {
            this.root = this.root.left;
        } else {
            parent.right = max.left;
        }

        this.nodesCount--;
    }

    public T ceil(T element) {
        T nearest = ceil(this.root, element);
        return nearest;
    }

    private T ceil(Node node, T target){
        if(node==null){
            return null;
        }
        int compare = target.compareTo(node.value);
        if(compare>0){
            return this.ceil(node.getRight(), target);
        }
        T ceilElement = ceil(node.getLeft(), target);
        if(ceilElement==null){
            return node.value;
        }
        return ceilElement;
    }

    public T floor(T element) {
        T nearest = floor(this.root, element);
        return nearest;
    }

    private T floor(Node node, T target){
        if(node==null){
            return null;
        }
        int compare = target.compareTo(node.value);
        if(compare>0){
            return node.value;
        }
        else if(compare<0){
            return floor(node.getLeft(),target);
        }
        T floor = floor(node.getRight(),target);
        if(floor==null){
            return node.value;
        }
        return floor;
    }

    public void delete(T key) {
        this.root = delete(this.root, key);
    }

    private Node delete(Node node, T element){
        if(node==null){
            return null;
        }

        int compare = element.compareTo(node.value);
        if(compare<0){
            node.left = delete(node.left, element);
        }
        if(compare>0){
            node.right = delete(node.right, element);
        }
        else{
            if(node.right==null){
                return node.left;
            }
            else if(node.left==null){
                return node.right;
            }
            else{
                Node min = findMin(node.right);
                min.right = deleteMin(node.right);
                min.left = node.left;
                node = min;
            }
        }
        node.childrenCount = count(node.getLeft()) + count(node.getRight()) + 1;
        return node;
    }

    private Node findMin(Node node) {
        if(node==null){
            return null;
        }
        if(node.left==null){
            return node;
        }
        return findMin(node.left);
    }

    public int rank(T value) {
        return this.rank(value,this.root);
    }

    private int rank(T value, Node node) {
        if(node==null){
            return 0;
        }

        int compare = value.compareTo(node.value);

        if(compare<0){
            return this.rank(value,node.getLeft());
        }
        if(compare>0){
            return 1+this.count(node.getLeft())+this.rank(value,node.getRight());
        }
        return this.count(node.getLeft());
    }

    public T select(int n) {
        int count = 0;
        Node target = this.peekForElement(this.root, count, n);
        return target.value;
    }

    private Node peekForElement(Node node, int count, int target){
        if(node==null){
            return null;
        }
        if(count==target){
            return node;
        }
        return peekForElement(node.getRight(),count+1,target);
    }

    class Node {
        private T value;
        private Node left;
        private Node right;

        private int childrenCount;

        public Node(T value) {
            this.value = value;
            this.childrenCount = 1;
        }

        public T getValue() {
            return this.value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node getLeft() {
            return this.left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return this.right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        @Override
        public String toString() {
            return this.value + "";
        }
    }
}

