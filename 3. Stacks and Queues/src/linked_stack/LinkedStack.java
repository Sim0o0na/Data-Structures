//package linked_stack;

import java.util.Iterator;

/**
 * Created by Simona Simeonova on 5/25/2017.
 */
public class LinkedStack<T> implements Iterable<T>{

    private Node top;
    private int count;
    public int size(){
        return this.count;
    }

    public int count(){
        return this.count;
    }

    public void push(T element){
        this.top = new Node(element, this.top);
        this.count++;
    }

    public T pop(){
        if(this.count()==0){
            throw new IllegalArgumentException();
        }
        T result = this.top.value;
        this.top = this.top.previous;
        this.count--;
        return result;
    }

    public T peek(){
        if(this.count()==0){
            throw new IllegalArgumentException();
        }
        T result = this.top.value;
        return result;
    }

    public T[] toArray(){
        T[] arr = (T[])new Object[this.count];
        Node current = this.top;
        int index = 0;
        while(current!=null){
            arr[index++] = current.value;
            current = current.previous;
        }
        return arr;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    private class Node{
        private T value;
        private Node previous;

        public Node(T element, Node previous){
            this.value = element;
            this.previous = previous;
        }
    }
}
