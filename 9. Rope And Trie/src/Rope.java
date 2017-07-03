////The rope splits up a long string so insertion deletion and random
////access can be done efficiently
//
////Each node contains a short string
//
////Each node has a weight equal to the length of its string
////plus the sum of all the weights in the left subtree
//
////Operations include index, split, concat, insert, delete, report
//public class Rope
//{
//    private Node root;
//    public Rope()
//    {
//
//    }
//
//    //Index returns the character at position i
//    //Runs in O(log n) n being the length of the rope
//
//    //Given the position of the character i, start at the root, given the weight of a node.
//    //If the weight is greater than or equal to i and a left child exists, go to the left child.
//    //If the weight is greater than or equal to i and no left child exists, return the node's string
//    //at character position i
//    //If the weight is less than the given i then subtract the weight from i (i = i-weight) and go
//    //to the right child
//
//    //recursive method, private because the non-recursive method should be the one starting the call
//
//    private char index(int i, Node n)
//    {
//        //Only call the recursive method to get weight once, recursion kills performance
//        int weight = n.getWeight();
//        if(weight< i)
//        {
//            i = i-weight;
//            return index(i, n.getRight());
//        }
//        else
//        {
//            if(n.getLeft() != null)
//            {
//                return index(i, n.getLeft());
//            }
//            else
//            {
//                return (n.getString().charAt(i));
//            }
//        }
//    }
//
//    //Split: (i,s) splits s into two new strings s1 and s2, s1 = c1...ci, s2=ci+1...clast
//    public Tuple<String, String> split(int i, String s)
//    {
//        return null;
//    }
//
//    //Call the recursive method starting at the root with i
//
//    //I seperate the recursive method from the method I want users to use to allow for easier
//    //use and easier for me to implement and understand
//    public char getChar(int index)
//    {
//        return index(index, root);
//    }
////----------------------------------------------------------------------------------------------
//
//    //Holds methods specific to when you have access to a certain node and not searching through
//    //the rope for a specific node
//
//    //Confused deciding whether or not getWeight should stay in Node or in Rope like how I put
//    //the non recursive method of index in rope.
//
//    public class Node
//    {
//        private String string;
//        private Node leftNode;
//        private Node rightNode;
//
//        public Node(String s)
//        {
//            string = s;
//        }
//
//
//        public void addChild(Node n)
//        {
//
//        }
//        public String getString()
//        {
//            return string;
//        }
//
//        //To calculate the weight, get the weight of the node's left child and the weight of all
//        //the nodes in it's right subtree
//
//        //Recursive method
//        private int weight(Node n)
//        {
//            if(n.getRight() == null)
//            {
//                return n.getString().length();
//            }
//            return n.getString().length() + weight(n.getRight());
//        }
//
//        //To get the weight you have to call the getWeight starting with the first node on the left subtree
//        public int getWeight()
//        {
//            //If there's no left subtree, return the length of the string
//            if(this.leftNode == null)
//            {
//                return string.length();
//            }
//            return weight(this.leftNode);
//        }
//
//
//
//
//
//        public Node getLeft()
//        {
//            return leftNode;
//        }
//        public Node getRight()
//        {
//            return rightNode;
//        }
//    }
//
//}