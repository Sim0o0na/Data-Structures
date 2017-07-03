import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

public class IntervalTree {

    private class Node {

        private Interval interval;
        private Double max;
        private Node right;
        private Node left;

        public Node(Interval interval) {
            this.interval = interval;
            this.max = interval.getHi();
        }
    }

    private Node root;

    public void insert(double lo, double hi) {
        this.root = this.insert(this.root, lo, hi);
    }

    public void eachInOrder(Consumer<Interval> consumer) {
        this.eachInOrder(this.root, consumer);
    }

    public Interval searchAny(double lo, double hi) {
        Node current = this.root;
        while(current!=null && !current.interval.intersects(lo,hi)){
            if(current.left!=null && current.left.interval.getHi()>lo){
                current = current.left;
            }
            else {
                current = current.right;
            }
        }
        if(current==null){
            return null;
        }
        return current.interval;
    }

    public Iterable<Interval> searchAll(double lo, double hi) {
        List<Interval> intersectIntervals = new ArrayList<>();
        Node current = this.root;
        while(current!=null){
            if(current.interval.intersects(lo,hi)){
                intersectIntervals.add(current.interval);
            }
            if(current.left!=null && current.left.interval.getLo()>lo){
                current = current.left;
            }
            else{
                current=current.right;
            }
        }
        return intersectIntervals;
    }

    private void eachInOrder(Node node, Consumer<Interval> consumer) {
        if (node == null) {
            return;
        }

        this.eachInOrder(node.left, consumer);
        consumer.accept(node.interval);
        this.eachInOrder(node.right, consumer);
    }

    private Node insert(Node node, double lo, double hi) {
        if (node == null) {
            return new Node(new Interval(lo, hi));
        }

        int cmp = Double.compare(lo, node.interval.getLo());
        if (cmp < 0) {
            node.left = insert(node.left, lo, hi);
        } else if (cmp > 0) {
            node.right = insert(node.right, lo, hi);
        }

        this.updateMax(node);

        return node;
    }

    private void updateMax(Node node) {
        Node maxChild = getMax(node.left,node.right);
        Node max = getMax(node,maxChild);
    }

    private Node getMax(Node nodeA, Node nodeB){
        if(nodeA==null){
            return nodeB;
        }
        if(nodeB==null){
            return nodeA;
        }
        return (nodeA.max.compareTo(nodeB.max) > 0) ? nodeA : nodeB;
    }
}
