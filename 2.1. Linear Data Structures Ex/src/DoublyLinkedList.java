public class DoublyLinkedList<E> implements Iterable<E> {
    private Node head;
    private Node tail;
    private int size;

    public DoublyLinkedList(){
        this.size = 0;
    }

    public boolean isEmpty(){
        return this.size==0;
    }

    public int size() {
        return this.size;
    }

    public void add(E element, int position){
        Node nextTemp = new Node(element, null, null);
        if(position==1){
            addFirst(element);
            return;
        }
        //begin from head of list
        Node previousTemp = head;
        for(int i = 2; i<=size; i++){
            if(i==position){
                Node temp = previousTemp.next;
                previousTemp.next = nextTemp;
                nextTemp.previous = previousTemp;
                nextTemp.next = temp;
                temp.previous = nextTemp;
//                System.out.printf("Added %s at position %s\n", element, position);
            }
            previousTemp = previousTemp.next;
        }
        size++;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void addFirst(E element) {
        Node temp = new Node(element, head, null);
        if(head!=null){
            head.previous = temp;
        }
        head = temp;
        if(tail == null){
            this.tail = temp;
        }
        size++;
//        System.out.println("Adding: " + temp.element);
    }

    public void addLast(E element) {
        Node temp = new Node(element, null, tail);
        if(tail!=null){
            tail.next = temp;
        }
        tail = temp;
        if(head == null){
            this.head = temp;
        }
        size++;
//        System.out.println("Adding: " + temp.element);
    }

    public E removeFirst() {
        if(size == 0){
            throw new IllegalArgumentException();
        }
        Node tmp = head;
        head = head.next;
        if(head!=null){
            head.previous = null;
        }
        size--;
//        System.out.println("deleted: " + tmp.element);
        return tmp.element;
    }

    public E removeLast() {
        if(size == 0){
            throw new IllegalArgumentException();
        }
        Node tmp = tail;
        if(size==1){
            tail=null;
           head=null;
        } else {
            tail = tail.previous;
            tail.next = null;
        }
        size--;
//        System.out.println("deleted: " + tmp.element);
        return tmp.element;
    }

    public E[] toArray() {
        E[] elements = (E[]) new Object[this.size];
        Node node = head;
        int counter = 0;
        while(node!=null){
            elements[counter] = node.element;
            node = node.next;
            counter++;
        }
        return elements;
    }

    @Override
    public java.util.Iterator<E> iterator() {
        return new DoublyLinkedListIterator<E>();
    }

    private class DoublyLinkedListIterator<E> implements java.util.Iterator{
        Node node = head;

        @Override
        public boolean hasNext() {
            return node!=null;
        }

        @Override
        public Object next() {
            if(!hasNext()){
                throw new IllegalArgumentException();
            }
            E tmp = (E)node.element;
            node = node.next;  // if next is null, hasNext will return false.
            return tmp;
        }
    }
    @Override
    public void forEach(java.util.function.Consumer<? super E> action) {
        Node node = head;
        while(node!=null){
            action.accept(node.element);
            node=node.next;
        }
    }

    private class Node{
        private Node previous;
        private Node next;
        private E element;

        public Node(E element, Node next, Node previous){
            this.element = element;
            this.previous = previous;
            this.next = next;
        }
    }
}
