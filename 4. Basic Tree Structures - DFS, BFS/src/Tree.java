import java.util.*;
import java.util.function.Consumer;

public class Tree<T> {
    public T value;
    public List<Tree> children;

    public Tree(T value, Tree<T>... children) {
        this.value = value;
        this.setChildren(children);
    }

    // append output to builder
    public String print(int indent, StringBuilder builder) {
        builder.append(new String(new char[indent]).replace("\0", "  "));
        builder.append(this.value + "\n");
        for (Tree child:this.children) {
            builder.append(child.print(indent+1, new StringBuilder()));
        }
        return builder.toString();
    }

    public void each(Consumer<T> consumer) {
        consumer.accept(this.value);
        for (Tree child:this.children) {
            child.each(consumer);
        }
    }

    public Iterable<T> orderDFS() {
        List<T> result = new ArrayList<>();
        this.DFS(this, result);
        return result;
    }

    private void DFS(Tree<T> tree, List<T> result){
        for (Tree child:tree.children) {
            this.DFS(child,result);
        }
        result.add(tree.value);
    }

    public Iterable<T> orderBFS() {
        List<T> result = new ArrayList<>();
        Queue<Tree<T>> queue = new ArrayDeque<>();
        queue.add(this);
        while (!queue.isEmpty()){
            Tree<T> curr = queue.poll();
            result.add(curr.value);
            for (Tree<T> child:curr.children) {
                queue.add(child);
            }
        }
        return result;
    }

    public void setChildren(Tree<T>[] children) {
        this.children = new ArrayList<>();
        for (Tree<T> child:children) {
            this.children.add(child);
        }
    }
}