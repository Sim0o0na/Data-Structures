package circular_queue_impl;
public class CircularQueue<E> {

    private static final int initialCapacity = 4;
    private E[] elements;
    private int startIndex; //head
    private int endIndex; //tail
    private int count;
    private int capacity;

    public CircularQueue() {
        this.elements = (E[])new Object[initialCapacity];
    }

    public CircularQueue(int initialCapacity) {
        this.elements = (E[])new Object[initialCapacity];
    }

    public int size() {
        return this.count;
    }

    public void enqueue(E element) {
        if(this.count>=this.elements.length){
            this.resize();
        }
        this.elements[this.endIndex] = element;
        this.endIndex = (this.endIndex+1)%this.elements.length;
        this.count++;
    }

    public E dequeue() {
       if(this.count == 0){
           throw new IllegalArgumentException();
       }
       E result = this.elements[this.startIndex];
       startIndex = (startIndex+1)%this.elements.length;
       this.count--;
       return result;
    }

    public E[] toArray() {
        E[] arr = (E[])new Object[this.count];
        int currentIndex = this.startIndex;
        while(currentIndex<this.count){
            arr[currentIndex] = this.elements[currentIndex];
            currentIndex++;
        }
        return arr;
    }

    private void resize(){
        E[] newArray = (E[]) new Object[this.elements.length*2];
        this.copyAllElements(newArray);
        this.elements = newArray;
        this.startIndex = 0;
        this.endIndex = this.count;
    }

    private void copyAllElements(E[] newArray){
        int currentIndex = 0;
        int currentHead = this.startIndex;

        while(currentIndex<this.count){
            newArray[currentIndex++] = this.elements[currentHead];
            currentHead = (currentHead+1)%this.elements.length;
        }
    }

}
