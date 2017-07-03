//import java.util.*;
//import com.google.common.collect.TreeMultimap;
//
//public class FirstLastList<T extends Comparable<T>> implements IFirstLastList<T> {
//    private T root;
//    //value and next value to preserve order of adding
//    private TreeMultimap<T,T> elements;
//    private DoublyLinkedList<> listedElements;
//
//    public FirstLastList(){
//        this.elements = TreeMultimap.create();
//    };
//
//    @Override
//    public void add(T element) {
//        if(element==null){
//            throw new IllegalArgumentException("Element cannot be empty");
//        }
//        if(elements.size()==0){
//            this.root = element;
//            this.elements.put(element,null);
//        }
//    }
//
//    @Override
//    public int getCount() {
//        return this.elements.size();
//    }
//
//    @Override
//    public Iterable<T> first(int count) {
////        if(count>this.getCount()){
////            throw new IllegalArgumentException("There are less elements");
////        }
////        List<T> firstElements = new ArrayList<>();
////        int i = 0;
////        this.iter = elements.iterator();
////        while(i<count){
////            firstElements.add((T)this.iter.next());
////            i++;
////        }
////        return firstElements;
//        throw new IllegalArgumentException();
//    }
//
//    @Override
//    public Iterable<T> last(int count) {
////        if(count>this.getCount()){
////            throw new IllegalArgumentException("There are less elements");
////        }
////        List<T> lastElements = new ArrayList<>();
////        this.iter = elements.descendingIterator();
////        int i = 0;
////        while(i<count){
////            lastElements.add((T) this.iter.next());
////            i++;
////        }
////        return lastElements;
//        throw new IllegalArgumentException();
//    }
//
//    @Override
//    public Iterable<T> min(int count) {
//        throw new UnsupportedOperationException();
//    }
//
//    @Override
//    public Iterable<T> max(int count) {
//        throw new UnsupportedOperationException();
//    }
//
//    @Override
//    public void clear() {
//        throw new UnsupportedOperationException();
//    }
//
//    @Override
//    public int removeAll(T element) {
//        throw new UnsupportedOperationException();
//    }
//
//}