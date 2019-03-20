package GameElements;
import java.util.*;
public class Node{

    private Tuple t;
    private boolean isRoot;
    private ArrayList<Node> children;
    private Node parent;
    private boolean isLeaf;

    //it's the root node, no parent
    public Node(int x, int y){
        t = new Tuple(x,y);
        isRoot = true;
        children = new ArrayList<Node>();
        isLeaf = true;
    }

    //it's a child node, parent node is no longer a leaf
    public Node(int x, int y, Node parent){
        t = new Tuple(x,y);
        isRoot = false;
        children = new ArrayList<Node>();
        this.parent = parent;
        isLeaf = true;
        parent.addChild(this);
    }

    public Node(Tuple t, Node parent){
        this.t = t;
        isRoot = false;
        children = new ArrayList<Node>();
        this.parent = parent;
        isLeaf = true;
        parent.addChild(this);
    }

    public void addChild(Node child){
        children.add(child);
        isLeaf = false;
    }

    public void addChild(int x, int y){
        Node child = new Node (x,y,this);
        children.add(child);
        isLeaf = false;
    }

    public void addChild(int x, int y, Node parent){
        Node child = new Node (x,y,parent);
        children.add(child);
    }

    public int x(){
        return t.x();
    }

    public int y(){
        return t.y();
    }

    public boolean isRoot(){
        return isRoot;
    }

    public boolean isLeaf(){
        return isLeaf;
    }

    public Node parent(){
        return parent;
    }

    public Tuple t(){
        return t;
    }

    @Override
    public String toString(){
        return t.x() + ", " + t.y();
    }
}
