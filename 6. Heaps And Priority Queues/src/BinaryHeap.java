import java.util.ArrayList;
import java.util.List;

public class BinaryHeap<T extends Comparable<T>> {
    private List<T> heap;
    private int size;

    public BinaryHeap() {
        this.heap = new ArrayList<>();
    }

    public int size() {
        return this.size;
    }

    public int count(){return this.heap.size();}

    public void insert(T element) {
        //insert element at the end of heap
        this.heap.add(element);
        //move element to it's place, pass it's current index
        this.heapifyUp(this.heap.size()-1);
        size++;
    }

    private void heapifyUp(int index){
        int parentIndex = (index-1)/2; //parent index
        while(isGreater(index, parentIndex)){
            swapElements(index,parentIndex);
            index = (index-1)/2;
            parentIndex = (index-1)/2;
        }
    }

    private void heapifyDown(int index) {
        while(index<this.size/2){
            int child = 2*index+1;
            if(child+1<this.size && isGreater(index,child+1)){
                child=child+1;
            }
            if(isGreater(index,child)){
                break;
            }
            this.swapElements(index,child);
            index=child;
        }
    }

    private boolean isGreater(int index, int parent) {
        //check if the element is bigger than parent
        return this.heap.get(index).compareTo(this.heap.get(parent))>0;
    }

    private void swapElements(int index, int secondIndex) {
        T temp = this.heap.get(index);
        this.heap.set(index, this.heap.get(secondIndex));
        this.heap.set(secondIndex, temp);
    }

    public T peek() {
        if(this.heap.size()<=0){
            throw new IllegalArgumentException();
        }
        return this.heap.get(0);
    }

    public T pull() {
        if(this.heap.size()==0){
            throw new IllegalArgumentException();
        }
        T result = this.heap.get(0);
        swapElements(0, this.heap.size()-1);
        this.heap.remove(this.size-1);
        this.heapifyDown(0);
        return result;
    }


}
