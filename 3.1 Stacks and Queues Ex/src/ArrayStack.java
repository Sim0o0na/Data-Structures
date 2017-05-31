public class ArrayStack<T> {

    private static final int INITIAL_CAPACITY = 2;

    private T[] elements;
    private int size;
    private int count;

    public ArrayStack() {
        this.elements = (T[]) new Object[INITIAL_CAPACITY];
    }

    public ArrayStack(int capacity) {
        this.elements = (T[]) new Object[capacity];
    }

    public int size() {
        return this.count;
    }

    public void push(T element) {
        if(this.count==this.elements.length-1){
            this.grow();
        }
        this.elements[count] = element;
        this.count++;
    }

    public T pop() {
        if(this.count==0){
            throw new IllegalArgumentException();
        }
        T result = this.elements[count-1];
        this.elements[count-1] = null;
        this.count--;
        return result;
    }

    public T[] toArray() {
        int internalIndex = this.count-1;
        T[] array = (T[]) new Object[this.count];
        int arrayIndex = 0;
        while(internalIndex>=0){
            array[arrayIndex++] = this.elements[internalIndex--];
        }
        return array;
    }

    private void grow() {
        T[] newArray = (T[]) new Object[this.elements.length*2];
        int internalIndex = 0;
        while(internalIndex<=this.count){
            newArray[internalIndex] = this.elements[internalIndex];
            internalIndex++;
        }
        this.elements = newArray;
    }
}