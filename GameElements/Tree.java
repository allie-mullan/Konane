package GameElements;
import java.util.*;

public class Tree{

    private Node root;
    private ArrayList<Node> map;

    public Tree(){
        map = new ArrayList<Node>();
    }

    public Tree(Node root){
        this.root = root;
        map = new ArrayList<Node>();
    }

    public Tree(int x, int y){
        root = new Node(x,y);
        map = new ArrayList<Node>();
    }

    public void addNode(Node n){
        map.add(n);
    }

    public void addNode(int x, int y, Node parent){
        Node n = new Node(x,y,parent);
        map.add(n);
    }

    public int size(){
        return map.size();
    }

    public ArrayList<Node> map(){
        return map;
    }

    public Node get(int i){
        return map.get(i);
    }

    public Node root(){
        if(map.size() > 0)
            return root;
        else
            return null;
    }

    public Node getRoot(){
        return root;
    }
}
