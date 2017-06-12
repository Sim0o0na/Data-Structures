import java.util.function.Consumer;

public class BinaryTree<T> {

    public T value;
    public BinaryTree<T> left;
    public BinaryTree<T> right;

    public BinaryTree(T value) {
        this.value = value;
    }

    public BinaryTree(T value, BinaryTree<T> child) {
        this.value = value;
        this.left = child;
    }

    public BinaryTree(T value, BinaryTree<T> leftChild, BinaryTree<T> rightCHild) {
        this.value = value;
        this.left = leftChild;
        this.right = rightCHild;
    }

    // append output to builder
    public String printIndentedPreOrder(int indent, StringBuilder builder) {
        builder.append(new String(new char[indent]).replace("\0", "  "));
        builder.append(this.value + "\n");
        if(this.left!=null){
            builder.append(this.left.printIndentedPreOrder(indent+1, new StringBuilder()));
        }
        if(this.right!=null){
            builder.append(this.right.printIndentedPreOrder(indent+1, new StringBuilder()));
        }
        return builder.toString();
    }

    public void eachInOrder(Consumer<T> consumer) {
        if(this.left!=null){
            this.left.eachInOrder(consumer);
        }
        consumer.accept(this.value);
        if(this.right!=null){
            this.right.eachInOrder(consumer);
        }
    }



    public void eachPostOrder(Consumer<T> consumer) {
        if(this.left!=null){
            this.left.eachPostOrder(consumer);
        }
        if(this.right!=null){
            this.right.eachPostOrder(consumer);
        }
        consumer.accept(this.value);
    }
}
