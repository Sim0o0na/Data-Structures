import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.function.Consumer;

public class BinarySearchTree<T extends Comparable<T>> {
    private Node root;

    public BinarySearchTree() {
    }

    private BinarySearchTree(Node node) {
        this.root = node;
    }

    public Node getRoot() {
        return this.root;
    }

    public void insert(T value) {
        if(this.root==null){
            this.root = new Node(value);
            return;
        }
        Node parent = null;
        Node current = this.root;
        while (current!=null){
            parent = current;
            if(current.value.compareTo(value)>0) {
                current = current.getLeft();
            }
            else if(current.value.compareTo(value)<0) {
                current = current.getRight();
            }
            else {
                return;
            }
        }
        current = new Node(value);
        if(parent.value.compareTo(current.value)<0){
            parent.setRight(current);
        }else{
            parent.setLeft(current);
        }
    }

    private void copy(Node node){
        if(node==null){
            return;
        }
        this.insert(node.value);
        this.copy(node.left);
        this.copy(node.right);
    }

    public boolean contains(T value) {
        Node current = this.root;
        while (current!=null){
            if(current.value.compareTo(value)>0) {
                current = current.getLeft();
            }
            else if(current.value.compareTo(value)<0) {
                current = current.getRight();
            }
            else {
                break;
            }
        }

        if(current==null){
            return false;
        }
        return true;
    }

    public void deleteMin(){
        if(this.root==null){
            return;
        }
        Node current= this.root;
        Node parent= null;

        while (current.getLeft()!=null){
            parent = current;
            current=current.getLeft();
        }

        if(parent==null){
            this.root = this.root.getRight();
        }else{
            parent.setLeft(current.getRight());
        }
    }

    public BinarySearchTree<T> search(T item) {
        Node current = this.root;
        while (current!=null){
            if(current.value.compareTo(item)>0) {
                current = current.getLeft();
            }
            else if(current.value.compareTo(item)<0) {
                current = current.getRight();
            }
            else {
                break;
            }
        }
        return new BinarySearchTree<>(current);
    }

    public void eachInOrder(Consumer<T> consumer) {
        this.eachInOrder(this.root, consumer);
    }

    private void eachInOrder(Node node, Consumer<T> consumer){
        if(node==null){
            return;
        }
        this.eachInOrder(node.getLeft(), consumer);
        consumer.accept(node.value);
        this.eachInOrder(node.getRight(), consumer);
    }

    public Iterable<T> range(T from, T to) {

        Queue<T> range = new ArrayDeque<>();
        this.range(from,to,range,this.root);
        return range;
    }

    private void range(T start, T end, Queue<T> range, Node node){
        if(node==null){
            return;
        }

        int compareLow = start.compareTo(node.value);
        int compareHigh = end.compareTo(node.value);

        if(compareLow<0){
            this.range(start,end,range,node.getLeft());
        }
        else if(compareLow>=0 && compareHigh<=0){
            range.add(node.value);
        }
        else if(compareHigh>0){
            this.range(start,end,range,node.getRight());
        }
    }

    class Node {
        private T value;
        private Node left;
        private Node right;

        public Node(T value) {
            this.value = value;
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
    }
}

