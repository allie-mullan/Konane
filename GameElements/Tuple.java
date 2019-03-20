package GameElements;
public class Tuple{
    private int x;
    private int y;

    public Tuple(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString(){
        return x + " " + y;
    }

    public int x(){
        return x;
    }

    public int y(){
        return y;
    }
}
