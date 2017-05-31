package doubly_linkedlist_implementation;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<T> {
    private Node head;
    private Node tail;
    private int size;

    public DoublyLinkedList(){
        this.size = 0;
    }

    public int size(){
        return this.size;
    }

    public boolean isEmpty(){
        return this.size==0;
    }

    public void add(T element, int position){
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
                System.out.printf("Added %s at position %s\n", element, position);
            }
            previousTemp = previousTemp.next;
        }
        size++;
    }

    public void delete(int position){
        if(position == 1){
            if(size == 1){
                head = null;
                tail = null;
                size = 0;
                return;
            }
            head = head.next;
            head.previous = null;
            size--;
            return;
        }
        if(position==size){
            tail = tail.previous;
            tail.next = null;
            size--;
        }
        Node previousNode = head.next;
        for(int i = 2; i<=size; i++){
            if(i==position){
                Node next = previousNode.next;
                Node previous = previousNode.previous;

                previous.next = next;
                next.previous = previous;
                size--;
                System.out.printf("Deleted %s\n", previousNode.element);
                return;
            }
            previousNode = previousNode.next;
        }
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        Node temp = head.next;
        while(temp!=null){
            sb.append(temp.element+" ");
            temp = temp.next;
        }
        sb.append("\n");
        return sb.toString();
    }

    public void addFirst(T element){
        Node temp = new Node(element, head, null);
        if(head!=null){
            head.previous = temp;
        }
        head = temp;
        if(tail == null){
            this.tail = temp;
        }
        size++;
        System.out.println("Adding: " + temp.element);
    }

    public void addLast(T element){
        Node temp = new Node(element, null, tail);
        if(tail!=null){
            tail.next = temp;
        }
        tail = temp;
        if(head == null){
            this.head = temp;
        }
        size++;
        System.out.println("Adding: " + temp.element);
    }

//    public void iterateForward(){
//        System.out.println("iterating forward");
//        Node tmp = head;
//        while(tmp!=null){
//            System.out.println(tmp.element);
//            tmp=tmp.next;
//        }
//    }
//
//    public void iterateBackward(){
//        System.out.println("iterating forward");
//        Node tmp = tail;
//        while(tmp!=null){
//            System.out.println(tmp.element);
//            tmp=tmp.previous;
//        }
//    }

    public T removeFirst(){
        if(size == 0){
            throw new NoSuchElementException();
        }
        Node tmp = head;
        head = head.next;
        head.previous = null;
        size--;
        System.out.println("deleted: " + tmp.element);
        return tmp.element;
    }

    public T removeLast(){
        if(size == 0){
            throw new NoSuchElementException();
        }
        Node tmp = tail;
        tail = tail.previous;
        head.next = null;
        size--;
        System.out.println("deleted: " + tmp.element);
        return tmp.element;
    }


    private class Node{
        private Node previous;
        private Node next;
        private T element;

        public Node(T element, Node next, Node previous){
            this.element = element;
            this.previous = previous;
            this.next = next;
        }
    }
}
