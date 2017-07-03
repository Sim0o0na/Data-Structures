/**
 * Created by Simona Simeonova on 6/15/2017.
 */
public class RopeNode {
    public RopeNode left;
    public RopeNode right;
    public String value;
    public int weight;

    public RopeNode(){
        this.value = null;
        this.left = null;
        this.right = null;
        this.weight = 0;
    }

    public RopeNode(String value){
        this.value = value;
        this.left = null;
        this.right = null;
        this.weight = this.value.length();
    }
}
