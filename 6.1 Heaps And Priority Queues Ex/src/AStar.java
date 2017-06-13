public class AStar {
    private PriorityQueue open;
    private
    private char[][] map;

    public AStar(char[][] map) {
       this.map = map;
       this.start = new PriorityQueue();
    }

    public static int getH(Node current, Node goal) {
        return Math.abs(current.getRow() - goal.getRow()) + Math.abs(current.getCol() - goal.getCol());
    }

    public Iterable<Node> getPath(Node start, Node goal) {

    }
}
