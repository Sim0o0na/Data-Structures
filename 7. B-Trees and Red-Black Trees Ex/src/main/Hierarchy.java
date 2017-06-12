//package main;

import java.util.*;
import java.util.stream.Collectors;

public class Hierarchy<T> implements IHierarchy<T> {
    private static final String DOES_NOT_EXIST  = "Element does not exist in hierarchy";
    private static final String ALREADY_IN_HIERARCHY = "Hierarchy already contains this child!";
    private static final String CANNOT_REMOVE_ROOT = "Cannot remove root!";
    private static final String NONEXISTING_PARENT = "Parent does not exist!";

    private Node<T> root;
    private int elementsCount;

    public Hierarchy(T root){
        this.setHierarchy(root);
    }

    private void setHierarchy(T rootValue){
        this.root = new Node<T>(rootValue);
        this.elementsCount = 1;
    }

    public int getCount(){
        return this.elementsCount;
    }

    public void add(T parent, T child){
        if(this.root.value.equals(parent) && this.elementsCount==1){
            this.root.addChild(child);
            elementsCount++;
            return;
        }
        Node<T> parentNode = this.findChild(parent);
        if(parentNode==null){
            throw new IllegalArgumentException(NONEXISTING_PARENT);
        }
        if(this.contains(child)){
            throw new IllegalArgumentException(ALREADY_IN_HIERARCHY);
        }
        parentNode.addChild(child);
        this.elementsCount++;
    }

    private Node<T> findParent(T element){ //returns parent index
        Node<T> child = this.findChild(element);
        if(child.isRoot()){
            return child;
        }
        return child.parent;
    }

    private Node<T> findChild(T element){
        Queue<Node<T>> queue = new ArrayDeque<>();
        queue.add(root);
        Node<T> result = null;
        boolean found = true;
        while(true){
            if(queue.isEmpty()){
                found = false;
                break;
            }
            result = queue.poll();
            if(result.getValue().equals(element)){
                break;
            }else{
                queue.addAll(result.getChildren());
            }
        }
        if(!found){
            return null;
        }
        return result;
    }


    public void remove(T element){
        if(!this.contains(element)){
            throw new IllegalArgumentException(DOES_NOT_EXIST);
        }
        Node<T> child = findChild(element);
        if(child.isRoot()){
            throw new IllegalStateException(CANNOT_REMOVE_ROOT);
        }
        child.parent.removeChild(element);
        this.elementsCount--;
    }

    public boolean contains(T element){
        return this.root.containsChild(element);
    }

    public T getParent(T element){
        if(!this.contains(element)){
            throw new IllegalArgumentException(DOES_NOT_EXIST);
        }
        Node<T> child = this.findChild(element);
        if(child.isRoot()){
            return null;
        }
        return child.parent.value;
    }

    public Iterable<T> getChildren(T element){
        if(!this.contains(element)){
            throw new IllegalArgumentException(DOES_NOT_EXIST);
        }
        return this.findChild(element).getChildren().stream().map(Node::getValue).collect(Collectors.toList());
    }

    public Iterable<T> getCommonElements(IHierarchy<T> other){
        List<Node<T>> allElements = this.getAllElements();
        allElements.add(this.root);
        List<T> commonElements = new ArrayList<>();
        for (Node<T> node : allElements) {
            if (other.contains(node.getValue())) {
                commonElements.add(node.getValue());
            }
        }
        return commonElements;
    }

    public List<Node<T>> getAllElements(List<Node<T>> elements, Queue<Node<T>> queue){
        if(queue.isEmpty()){
            return elements;
        }
        Node<T> result = null;
        while(!queue.isEmpty()){
            result = queue.poll();
            if(result.children.size()!=0){
                elements.addAll(result.getChildren().stream().collect(Collectors.toList()));
                queue.addAll(result.getChildren());
                this.getAllElements(elements, queue);
            }

        }
        return elements;
    }

    private List<Node<T>> getAllElements(){
        List<Node<T>> elements = new ArrayList<>();
        Queue<Node<T>> queue = new ArrayDeque<>();
        queue.add(root);
        this.getAllElements(elements,queue);
        return elements;
    }

    @Override
    public Iterator<T> iterator() {
        return new HierarchyIterator<>(this.root);
    }
    private class HierarchyIterator<T> implements Iterator<T> {

        private Deque<Node<T>> queue;

        public HierarchyIterator(Node<T> rootNode) {
            this.queue = new LinkedList<>();

            this.queue.addLast(rootNode);
        }

        @Override
        public boolean hasNext() {
            return !this.queue.isEmpty();
        }

        @Override
        public T next() {
            Node<T> head = queue.removeFirst();
            for (Node<T> child : head.getChildren()) {
                this.queue.addLast(child);
            }

            return head.getValue();
        }
    }

    private class Node<T> {
        private List<Node<T>> children = new ArrayList<>();
        private Node<T> parent;
        private T value = null;

        private Node(T value) {
            this.value = value;
        }

        private Node(T value, Node<T> parent) {
            this.value = value;
            this.parent = parent;
        }

        private int getChildrenCount(int childrenCount){
            for (Node child:this.children){
                childrenCount+=child.getChildrenCount(childrenCount);
            }
            return childrenCount;
        }

        private List<Node<T>> getChildren() {
            return children;
        }

        private void moveChildrenToParent(){
            for(Node<T> child:this.children){
                parent.addChild(child);
            }
        }

        private void removeChild(T element){
            boolean hasChild = this.children.stream().anyMatch(child->child.value==element);
            if(hasChild){
                Node<T> childToRemove = this.children.stream().filter(child->child.value==element).findFirst().get();
                this.children.remove(childToRemove);
                childToRemove.moveChildrenToParent();
            }
        }

        private boolean containsChild(T value){
            Queue<Node<T>> queue = new ArrayDeque<>();
            queue.add(this);
            boolean contains = false;
            Node<T> current = null;
            while(!contains){
                if(queue.isEmpty()){
                    break;
                }
                current = queue.poll();
                if(current.value.equals(value)){
                    contains=true;
                }
                if(current.children.stream().anyMatch(child->child.value==value)){
                    contains = true;
                    break;
                }else{
                    queue.addAll(current.getChildren());
                }
            }
            return contains;
        }

        private void setParent(Node<T> parent) {
            this.parent = parent;
        }

        private void addChild(T value) {
            Node<T> child = new Node<T>(value);
            child.setParent(this);
            this.children.add(child);
        }

        private void addChild(Node<T> child) {
            child.setParent(this);
            this.children.add(child);
        }

        private T getValue() {
            return this.value;
        }

        private void setValue(T value) {
            this.value = value;
        }

        private boolean isRoot() {
            return (this.parent == null);
        }

        private boolean isLeaf() {
            if (this.children.size() == 0) {
                return true;
            }
            return false;
        }

        private void removeParent() {
            this.parent = null;
        }
    }
}
