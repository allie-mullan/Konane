/**
 * @author: Francine Dennehy
 * @author: Alexandra Mullan
 * @version 1.0
 * This class contains the methods to set up the Human Agent. This class is similar to
 * that of Agent expect the inputs from this class should be provided for by the human player.
 **/
package GameElements;
import java.io.*;
import java.util.Scanner;
import java.util.*;

public class Human extends Player{
  //Declarations
  private char myColor;
  private MoveGenerator moveGenerator;
  private boolean isCPU;
  private Strategy strategy;

/**
  * @version 1.5
  * This is the constructor for the Human class. It takes in a char denoting its color and a
  * boolean saying if it is the CPU or not.
 **/
 public Human(char myColor, boolean isCPU){
     this.myColor = myColor;
     this.isCPU = isCPU;
     moveGenerator = new MoveGenerator();
  }// end of  Human(char myColor, boolean isCPU)

/**
  * @version 1.0
  * @return boolean isCPU
  * This is a getter method that returns the value of isCPU
 **/
  public boolean isCPU(){
      return isCPU;
  }//end of isCPU()

/**
  * @version 1.0
  * @return char myColor
  * This is a getter method that returns the char denoting the agent's color
 **/
  public char myColor(){
      return myColor;
  }//end myColor()

/**
  * @version 1.0
  * @return ArrayList<Move> availableMoves
  * This method is called to have the Human take the first turn.
  * It returns an ArrayList<Move> of all the available moves at the start of the game.
  * It calls a method found within the MoveGenerator class to find the available moves
  * of the first turn.
 **/
 public ArrayList<Move> takeFirstTurn(GameBoard currentBoardState){
     ArrayList<Move> availableMoves = moveGenerator.first_turn(myColor,currentBoardState);
     return availableMoves;
  }// end if takeFirstTurn(GameBoard currentBoardState)

/**
  * @version 1.0
  * @return ArrayList<Move> availableMoves
  * This method is called to have the human take its first turn which relays on
  * a different method found within the MoveGenerator class. This like the above method
  * returns an ArrayList<Move> of all the available moves the agent has at that time.
  *
 **/
 public ArrayList<Move> takeTurn(GameBoard currentBoardState){
     ArrayList<Move> availableMoves = moveGenerator.getMoves(currentBoardState.gameState(), myColor);
     return availableMoves;
  }//end of takeTurn(GameBoard currentBoardState)

/**
  * @version 1.0
  * @return Move m
 **/
 public Move playerChooseMove(ArrayList<Move> availableMoves, int i){
     if(availableMoves.size() > 0){
         return availableMoves.get(i);
     }else{
         Move m = new Move(true);
         return m;
     }
  }//end of playerChooseMove(ArrayList<Move> availableMoves, int i)

}//end of Human class
