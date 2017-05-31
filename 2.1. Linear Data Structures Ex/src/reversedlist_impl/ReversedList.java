////package reversedlist_impl;
//import java.util.Iterator;
//
///**
// * Created by Simona Simeonova on 5/19/2017.
// */
public class ReversedList<T> implements Iterable{
    private T[] elements;
    private int count;
    private int capacity = 2;

    public ReversedList(){
        this.elements = createArray(capacity);
    }

    public void add(T element){
        if(count==capacity){
            capacity=capacity*2;
        }
        T[] newElements = createArray(capacity);
        for(int i = 0; i<count; i++){
            newElements[i] = this.elements[i];
        }
        newElements[count] = element;
        count++;
        this.elements = newElements;
    }

    public T get(int index){
        if(index>=count){
            throw new ArrayIndexOutOfBoundsException();
        }
		
        index = reverseIndex(index);
		
        return this.elements[index];
    }

    public void set(int index, T element){
        if(index>=this.count){
            throw new ArrayIndexOutOfBoundsException();
        }
        index = reverseIndex(index);
		
        this.elements[index] = element;
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] createArray(int size){
        return (T[])new Object[size];
    }
	
	// Този метод ти обръща index-a все едно си търсиш в нормален reverse-нат array.
	// Kато има на предвид и остатъка на неинициализираните клетки в масива 'elements'. ^.^
	private int reverseIndex(int index)
	{
		int lengthOfNotInitializedCells = this.elements.length - this.count;
        int reversedIndex = this.elements.length - (lengthOfNotInitializedCells + (index + 1));
		
		return reversedIndex;
	}

    public int count(){
        return this.count;
    }

    public int capacity(){
        return this.capacity;
    }

    public void removeAt(int index){
        int indexToRemoveAt = reverseIndex(index);
		
        if(index>=count){
            throw new ArrayIndexOutOfBoundsException();
        }
        T element = elements[indexToRemoveAt];
        count--;
        for(int i = indexToRemoveAt; i<count; i++){
            elements[i] = elements[i+1];
        }
    }

    @Override
    public java.util.Iterator iterator() {
        return new ReversedListIterator();
    }

    private class ReversedListIterator implements java.util.Iterator<T>{
        private int internalIndex = count-1;

        @Override
        public boolean hasNext() {
            if(internalIndex>=0){
                return true;
            }
            return false;
        }

        @Override
        public T next() {
            T currElement = (T) elements[internalIndex];
            internalIndex--;
            return currElement;
        }
    }
}
