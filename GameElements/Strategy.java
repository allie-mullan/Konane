/**
* @author: Francine Dennehy
* @author: Alexandra Mullan
* @version: 1.5
*
**/

package GameElements;
import java.util.*;
import java.math.*;

public class Strategy{

    int maxDepth;
    MoveGenerator mG;
    int speed;

    public Strategy(MoveGenerator mG, int maxDepth, int speed){
      this.mG = mG;
      this.maxDepth = maxDepth;
      this.speed = speed;
    }

    public Move strategyGo(GameBoard gb, char myColor, ArrayList<Move> c, boolean firstW){
      //return c.get(0);
      if(speed == 1) //SLOW - MINIMAX ONLY
      return minimax_search(gb, myColor, c, firstW);
      else //FAST -- ALPA BETA PRUNING
      return alpha_beta_search(gb, myColor, c, firstW);
    }

    public Move alpha_beta_search(GameBoard gb, char myColor, ArrayList<Move> c, boolean firstW){ //Move currentMove
      Move m = new Move();
      Move v;
      char oppColor;
      if(myColor == 'w'){
        oppColor = 'b';
      }else{
        oppColor = 'w';
      }
      if(firstW){
        v = max(gb.gameState(), m, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, myColor, c, oppColor);
      }else{
        v = max(gb.gameState(), m, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, myColor, c, oppColor);
      }
      return c.get(v.getUtility());
    }

    public Move min(char[][] gb, Move m, int alpha, int beta, int currentDepth, char myColor, char oppColor){ //"returns a utility value" in this case it will return the whole move which contains a utlity value
      ArrayList<Move> availableMoves = mG.getMoves(gb, myColor);
      m.setV(Integer.MAX_VALUE);

      if(terminal_test(m, gb, currentDepth, myColor,availableMoves))
      return m;

      for(int i = 0; i<availableMoves.size(); i++){//for each move in the set of available moves
        char[][] tempBoard = mG.tempBoard(gb,  availableMoves.get(i).futureLocation(),availableMoves.get(i).currentLocation(),myColor, null);
        int oldV = m.getV();
        int result = max(tempBoard, availableMoves.get(i), alpha, beta, currentDepth+1, oppColor, null, myColor).getV();

        if(oldV>result)
          m.setUtility(i);

        m.setV(Math.min(oldV, result));

        if(m.getV() <= alpha){
          return m;
        }
        beta = Math.min(beta, m.getV());
      }
      return m;
    }

    public Move max(char[][] gb, Move m, int alpha, int beta, int currentDepth, char myColor, ArrayList<Move> c, char oppColor){
      m.setV(Integer.MIN_VALUE);

      ArrayList<Move> availableMoves;
      if(c != null){
        availableMoves = c;//mG.first_turn(myColor, gb);
      }else{
        availableMoves = mG.getMoves(gb, myColor);
      }

      //m.setV(Integer.MIN_VALUE);
      if(terminal_test(m, gb, currentDepth, myColor, availableMoves))
      return m;

      for(int i = 0; i<availableMoves.size(); i++){//for each move in the set of available moves
        char[][] tempBoard = mG.tempBoard(gb,  availableMoves.get(i).futureLocation(),availableMoves.get(i).currentLocation(), myColor, availableMoves.get(i).removeList().get(0));
        int oldV = m.getV();
        int result = min(tempBoard, availableMoves.get(i), alpha, beta, currentDepth+1, oppColor, myColor).getV();
        if(oldV<result)
        m.setUtility(i);
        m.setV(Math.max(oldV, result));
        if(m.getV() >= beta){
          return m;
        }
        alpha = Math.max(alpha, m.getV());
      }
      return m;
    }

    public boolean terminal_test(Move m, char[][] gb, int currentDepth, char myColor, ArrayList<Move> availableMoves){
      if(currentDepth == maxDepth){ //either you reach the max depth of the search tree
        m.calculateUtility(gb, mG, myColor);
        return true;
      }
      if(availableMoves.size() == 0){ //either you reach the max depth of the search tree
        m.calculateUtility(gb, mG, myColor);
        return true;
      }
      return false;
    }

    public Move minimax_search(GameBoard gb, char myColor, ArrayList<Move> c, boolean firstW){ //Move currentMove
      Move m = new Move();
      Move v;
      char oppColor;
      if(myColor == 'w'){
        oppColor = 'b';
      }else{
        oppColor = 'w';
      }
      if(firstW){
        v = minimax_max(gb.gameState(), m, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, myColor, c, oppColor);
      }else{
        v = minimax_max(gb.gameState(), m, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, myColor, c, oppColor);
      }
      return c.get(v.getUtility());
    }//end of minimax_search()

    public Move minimax_min(char[][] gb, Move m, int alpha, int beta, int currentDepth, char myColor, ArrayList<Move> c, char oppColor){ //"returns a utility value" in this case it will return the whole move which contains a utlity value
      ArrayList<Move> availableMoves;
      if(c != null){
        availableMoves = c;//mG.first_turn(myColor, gb);
      }else{
        availableMoves = mG.getMoves(gb, myColor);
      }
      m.setV(Integer.MAX_VALUE);
      if(terminal_test(m, gb, currentDepth, myColor, availableMoves))
      return m;

      for(int i = 0; i<availableMoves.size(); i++){//for each move in the set of available moves
        Move q = availableMoves.get(i);
        char[][] tempBoard = mG.tempBoard(gb, q.currentLocation(), q.futureLocation(),myColor, q.removeList().get(0));
        int oldV = m.getV();
        int result = minimax_max(tempBoard, q, alpha, beta, currentDepth+1, oppColor, null, myColor).getV();
        if(oldV>result)
        m.setUtility(i);
        m.setV(Math.min(oldV, result));
      }
      return m;
    }

    public Move minimax_max(char[][] gb, Move m, int alpha, int beta, int currentDepth, char myColor, ArrayList<Move> c, char oppColor){

      ArrayList<Move> availableMoves;

      if(c != null){
        availableMoves = c;//mG.first_turn(myColor, gb);
      }else{
        availableMoves = mG.getMoves(gb, myColor);
      }

      m.setV(Integer.MIN_VALUE);

      if(terminal_test(m, gb, currentDepth, myColor, availableMoves))
        return m;

      for(int i = 0; i<availableMoves.size(); i++){//for each move in the set of available moves
        Move q = availableMoves.get(i);
        char[][] tempBoard = mG.tempBoard(gb,  q.futureLocation(), q.currentLocation(), myColor, q.removeList().get(0));
        int result = minimax_min(tempBoard, q, alpha, beta, currentDepth+1, oppColor, null, myColor).getV();
        int oldV = m.getV();
        if(oldV<result)
        m.setUtility(i);
        m.setV(Math.max(oldV, result));
      }
      return m;
    }
  }
