package linkedlist;

import java.util.Iterator;
import java.util.function.Consumer;

public class LinkedList<E> implements Iterable<E> {
    private int count;
    private Node head;
    private Node tail;


    public int size() {
        return this.count;
    }


    public void addFirst(E item) {
        Node newHead = new Node(item, head);
        if(head!=null){
            head.next = head;
        }
        head = newHead;
        if(tail == null){
            this.tail = newHead;
        }
        count++;
    }

    public void addLast(E item) {
        Node newTail = new Node(item, null);
        if(tail!=null){
            tail.next = newTail;
        }
        tail = newTail;
        if(head == null){
            this.head = newTail;
        }
        count++;
    }

    public E removeFirst() {
        if(count == 0){
            throw new IllegalArgumentException();
        }
        Node tmp = head;
        head = head.next;
        count--;
//        System.out.println("deleted: " + tmp.element);
        return tmp.value;
    }

    public E removeLast() {
        if(count == 0){
            throw new IllegalArgumentException();
        }
        Node tmp = tail;
        if(count==1){
            tail=null;
            head=null;
        } else {
            tail = null;
            Node temp = this.head;
            while(true){
                if(temp.next==null){
                    tail = temp;
                    break;
                }
                temp = temp.next;
            }
        }
        count--;
//        System.out.println("deleted: " + tmp.element);
        return tmp.value;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }


    private class Node{
        private E value;
        private Node next;

        public Node(E element, Node next){
            this.value = element;
            this.next = next;
        }
    }

    private class LinkedListIterator implements Iterator<E> {
        Node node = head;
        @Override
        public boolean hasNext() {
            return node!=null;
        }

        @Override
        public E next() {
            if(!hasNext()){
                throw new IllegalArgumentException();
            }
            E tmp = (E)node.value;
            node = node.next;  // if next is null, hasNext will return false.
            return tmp;
        }
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        Node node = head;
        while(node!=null){
            action.accept(node.value);
            node=node.next;
        }
    }
}
