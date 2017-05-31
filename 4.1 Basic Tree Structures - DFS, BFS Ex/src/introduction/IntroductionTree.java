package introduction;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by Simona Simeonova on 5/31/2017.
 */
public class IntroductionTree<T> {
    private T value;
    private List<IntroductionTree<T>> children;

    public IntroductionTree(T value){
        this.value = value;
        this.children = new ArrayList<>();
    }

    public T getValue(){
        return this.value;
    }

    public void addChild(IntroductionTree<T> newChild){
        this.children.add(newChild);
    }

    public IntroductionTree<T> findNode(T value){
        IntroductionTree<T> node = this.DFS(this, value);
        return node;
    }

    private IntroductionTree<T> DFS(IntroductionTree<T> tree, T value){
        IntroductionTree<T> element = null;
        for (IntroductionTree child:tree.children) {
            if(child.getValue()==value){
                element = child;
                break;
            }else{
                this.DFS(child, value);
            }
        }
        return element;
    }
}
