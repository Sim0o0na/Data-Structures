/**
 * Created by Simona Simeonova on 6/20/2017.
 */
public class ProblemObject {
    private String id;
    private int X1;
    private int Y1;
    private int X2;
    private int Y2;

    public ProblemObject(String id, int X1, int Y1){
        this.X1 = X1;
        this.Y1 = Y1;
        this.id = id;
        this.setY2(Y1);
        this.setX2(X1);
    }

    public String getId(){
        return this.id;
    }

    public int getX1(){
        return this.X1;
    }

    public int getY1(){
        return this.Y1;
    }

    public void setX1(int x1){
        this.X1 = x1;
        this.setX2(this.X1);
    }

    public void setY1(int y1){
        this.Y1 = y1;
        this.setY2(this.Y2);
    }
    public void setX2(int x1){
        this.X2 = x1+10;
    }

    public void setY2(int y1){
        this.Y2 = y1+10;
    }

    public int getX2(){
        return this.X2;
    }

    public int getY2(){
        return this.Y2;
    }

    public boolean collides(ProblemObject that){
        return this.X1 < that.X2 &&
        this.X2 > that.X1 &&
        this.Y1 < that.Y2 &&
        this.Y2 > that.Y1;
    }
}
