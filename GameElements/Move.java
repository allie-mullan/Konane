package GameElements;
import java.util.*;
import java.io.*;

public class Move implements Comparable<Move>{

    private Tuple currentLocation;
    private Tuple futureLocation;
    private boolean noMove;
    private int utility;
    private int v;

    private ArrayList<Tuple> removeList;

    public Move(int cX, int cY, int fX, int fY){
        currentLocation = new Tuple(cX, cY);
        futureLocation = new Tuple(fX, fY);
        removeList = new ArrayList<Tuple>();
        noMove = false;
    }

    public Move(Tuple c, Tuple f){
        currentLocation = c;
        futureLocation = f;
        removeList = new ArrayList<Tuple>();
        noMove = false;
    }

    public Move(){
        removeList = new ArrayList<Tuple>();
        currentLocation = new Tuple(-1,-1);
        futureLocation = new Tuple(-1,-1);
        noMove = false;
    }

    public Move(boolean b){
        noMove = b;
        removeList = new ArrayList<Tuple>();
    }


    public boolean noMove(){
        return noMove;
    }

    public int numRemoves(){
        return removeList.size();
    }

    public int cLX(){
        return currentLocation.x();
    }

    public int fLX(){
        return futureLocation.x();
    }

    public int cLY(){
        return currentLocation.y();
    }

    public int fLY(){
        return futureLocation.y();
    }

    public Tuple currentLocation(){
        return currentLocation;
    }

    public Tuple futureLocation(){
        return futureLocation;
    }

    public ArrayList<Tuple> removeList(){
        return removeList;
    }

    public void addRemoval(Tuple t){
        removeList.add(t);
    }

    public void addRemoval(int x, int y){
        removeList.add(new Tuple(x,y));
    }

    public void setUtility(int u){
      utility = u;
    }

    public int getUtility(){
      return utility;
    }

    public void setV(int x){
      v = x;
    }

    public int getV(){
      return v;
    }

    public void calculateUtility(char[][] gb, MoveGenerator mG, char myColor){ //opposite player's move generator
      v = mG.getMoves(gb, myColor).size();
    }

    @Override
    public String toString(){
        String s = "";
        if(cLX() != -1){
          s+="Move piece at " + currentLocation + " to " + futureLocation + "\n   ";
          s += "Removes opponent's piece at " + removeList.toString()+"\n";
        }else{
          s+="Remove piece at " + removeList.toString()+"\n";
        }

        return s;
    }

    @Override
    public int compareTo(Move anotherMove) {
        if(removeList.size() != anotherMove.removeList().size()){
            return -1; //theyre not the same if theyre not the same size
        }
        if(currentLocation.x() != anotherMove.cLX() || currentLocation.y() != anotherMove.cLY()){
            return -1; //theyre not the same if current location coordinates are different
        }
        if(futureLocation.x() != anotherMove.fLX() || futureLocation.y() != anotherMove.fLY()){
            return -1; //theyre not the same if future location coordinates are different
        }
        for(int i = 0 ; i < removeList.size(); i ++){
            int x1 = removeList.get(i).x();
            int x2 = anotherMove.removeList().get(i).x();
            if(removeList.get(i).x() != anotherMove.removeList().get(i).x()){
                return -1;
            }
            int y1 = removeList.get(i).y();
            int y2 = anotherMove.removeList().get(i).y();
            if(removeList.get(i).y() != anotherMove.removeList().get(i).y()){
                return -1;
            }
        }
        return 0; //theyre the same
    }
}
