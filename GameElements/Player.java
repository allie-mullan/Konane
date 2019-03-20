/**
 * @author: Francine Dennehy
 * @author: Alexandra Mullan
 * @version: 1.0
 * This class contains the methods to set up the artifical interegents.
 *
 **/
package GameElements;
import java.util.*;

public class Player{

    private MoveGenerator moveGenerator;
    private char myColor;
    private boolean isAgent;
    private boolean isCPU;

    public Player(){
    }

    public boolean isCPU(){
        return isCPU;
    }

    public char myColor(){
        return myColor;
    }

    public ArrayList<Move> takeFirstTurn(GameBoard currentBoardState){
        ArrayList<Move> g = new ArrayList<Move>();
        return g;
    }

    public ArrayList<Move> takeTurn(GameBoard currentBoardState){
        ArrayList<Move> g = new ArrayList<Move>();
        return g;
    }

    public Move takeTurn1(GameBoard currentBoardState){
        Move g = new Move();
        return g;
    }

    public Move playerChooseMove(ArrayList<Move> availableMoves, int i){
        Move g = new Move();
        return g;
    }

    public Move chooseMove(ArrayList<Move> availableMoves, GameBoard currentBoardState, char myColor, boolean firstW){
        Move g = new Move();
        return g;
    }

    public void addStrategySettings(int sp, int mD){
    }

    public void printMove(Move m){
    }
}//end of Agent class
