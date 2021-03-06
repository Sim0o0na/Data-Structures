package quad;
import java.util.ArrayList;
import java.util.List;

public class Node<T> {

    public static final int MAX_ITEM_COUNT = 4;

    private Rectangle bounds;

    private List<T> items;

    public Node(int x, int y, int width, int height) {
        this.bounds = new Rectangle(x, y, width, height);
        this.items = new ArrayList<>();
    }

    public Rectangle getBounds() {
        return this.bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public List<T> getItems() {
        return this.items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }


    public boolean shouldSplit() {
        return MAX_ITEM_COUNT<=this.items.size();
    }

    @Override
    public String toString() {
        return this.bounds.toString();
    }
}
