import java.util.function.Consumer;

public class KdTree {

    public class Node {

        private Point2D point2D;
        private Node left;
        private Node right;

        public Node(Point2D point) {
            this.setPoint2D(point);
        }

        public Point2D getPoint2D() {
            return this.point2D;
        }

        public void setPoint2D(Point2D point2D) {
            this.point2D = point2D;
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

    private Node root;

    public Node getRoot() {
        return this.root;
    }

    public boolean contains(Point2D point) {
        Node current = this.root;
        int depth = 0;
        int compare = this.compare(point, current.getPoint2D(),depth);
        while (current != null) {
            if (compare < 0) {
                current = current.left;
            } else if (compare > 0) {
                current = current.right;
            } else {
                break;
            }
            compare = this.compare(point, current.getPoint2D(),depth+1);
        }

        return current != null;
    }

    public void insert(Point2D point) {
        this.root = this.insert(this.root, point, 0);
    }

    private Node insert(Node node, Point2D point2D, int depth){
        if(node==null){
            return new Node(point2D);
        }
        int compare = this.compare(node.point2D, point2D, depth);

        if(compare>0){
            node.left = this.insert(node.getLeft(), point2D, depth+1);
        }else if(compare<0){
            node.right = this.insert(node.getRight(), point2D, depth+1);
        }
        else{
            return node;
        }
        return node;
    }

    public void eachInOrder(Consumer<Point2D> consumer) {
       this.eachInOrder(this.root, consumer);
    }

    private void eachInOrder(Node node, Consumer<Point2D> consumer) {
        if (node == null) {
            return;
        }

        this.eachInOrder(node.getLeft(), consumer);
        consumer.accept(node.getPoint2D());
        this.eachInOrder(node.getRight(), consumer);
    }

    private int compare(Point2D a, Point2D b, int depth){
        int compare = 0;
        if(depth%2==0){
            compare = a.getX().compareTo(b.getX());
            if(compare==0){
                compare = a.getY().compareTo(b.getY());
            }
        }else{
            compare = a.getY().compareTo(b.getY());
            if(compare==0){
                compare = a.getX().compareTo(b.getX());
            }
        }
        return compare;
    }
}
