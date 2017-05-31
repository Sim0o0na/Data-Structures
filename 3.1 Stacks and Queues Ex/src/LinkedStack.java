public class LinkedStack<E> {

    private Node<E> firstNode;
    private int size;


    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void push(E element) {
        // TODO
    }

    public E pop() {
        // TODO
        throw new UnsupportedOperationException();
    }

    public E[] toArray() {
        // TODO
        throw new UnsupportedOperationException();
    }

    private class Node<E> {

        private E value;
        private Node<E> nextNode;

        public Node(E value) {
            // TODO
        }

        public Node(E value, Node<E> nextNode) {
            // TODO
        }

        public Node<E> getNextNode() {
            return this.nextNode;
        }

        public void setNextNode(Node<E> nextNode) {
            this.nextNode = nextNode;
        }
    }
}