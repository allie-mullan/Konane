/**
  * @author: Francine Dennehy
  * @author: Alexandra Mullan
  * @version: 1.0
  * This class tests the funtionality of the move class
  *
 **/
package Tests;
import GameElements.Move;
import GameElements.Tuple;
import java.util.*;

public class Test_Move{

/**
  * @version 1.0
  * This is the constuctor for the test_move class.
  * It calls all of the methods within the class.
 **/
  public Test_Move(){
    System.out.println("Test setUtility and getUtility result: " + Test_Utility());
    System.out.println("Test setV and getV result: " + Test_V());
    System.out.println("Test cLX and cLY result: " + Test_cLX_cLY());
    System.out.println("Test fLX and fLY result: " + Test_fLX_fLY());
    System.out.println("Test currentLocation result: " + Test_currentLocation());
    System.out.println("Test futureLocation result: " + Test_futureLocation());
    System.out.println("Test numRemoves result: " + Test_numRemoves());
    System.out.println("Test addRemoval both methods result: " + Test_addRemoval_both());
    System.out.println("Test removeList result: " + Test_removeList());
    System.out.println("Test noMove result: " + Test_noMove());
  }

/**
  * @version 1.0
  * @return boolean
  * This method tests the funtionality of the set and get Utility functions.
 **/
  public boolean Test_Utility(){
    Move move = new Move(0,0, 0,1);
    move.setUtility(8);

    if(move.getUtility() == 8){
      return true;
    }
    else{
      return false;
    }
  }//endof  Test_Utility()

/**
  * @version 1.0
  * @return boolean
  * This method tests the funtionality of the set and get v functions.
 **/
  public boolean Test_V(){
    Move move = new Move(0,0, 0,1);
    move.setV(4);
    if(move.getV() == 4){
      return true;
    }
    else{
      return false;
    }
  }//endof Test_V

/**
  * @version 1.0
  * @return boolean
  * This method tests the funtionality of the cLX and cLY functions.
 **/
  public boolean Test_cLX_cLY(){
    Move move = new Move(0,0, 0,1);
    if((move.cLX() == 0) && (move.cLY() == 0) ){
      return true;
    }
    else{
      return false;
    }
  }//endof Test_cLX_cLY

/**
  * @version 1.0
  * @return boolean
  * This method tests the funtionality of the fLX and fLY functions.
 **/
  public boolean Test_fLX_fLY(){
    Move move = new Move(0,0, 0,1);
    if((move.fLX() == 0) && (move.fLY() == 1) ){
      return true;
    }
    else{
      return false;
    }
  }//endof Test_cLX_cLY

/**
  * @version 1.0
  * @return boolean
  * This method tests the funtionality currentLocation function.
 **/
  public boolean Test_currentLocation(){
    Move move = new Move(0,0, 0,1);
    Tuple temp = move.currentLocation();

    if((temp.x() == 0) && (temp.y() == 0) ){
      return true;
    }
    else{
      return false;
    }
  }//endof Test_currentLocation

/**
  * @version 1.0
  * @return boolean
  * This method tests the funtionality futureLocation function.
 **/
  public boolean Test_futureLocation(){
    Move move = new Move(0,0, 0,1);
    Tuple temp = move.futureLocation();

    if((temp.x() == 0) && (temp.y() == 1) ){
      return true;
    }
    else{
      return false;
    }
  }//endof Test_futureLocation

/**
  * @version 1.0
  * @return boolean
  * This method tests the funtionality numRemoves function.
 **/
  public boolean Test_numRemoves(){
    Move move = new Move(0,0, 0,1);
    Tuple temp = new Tuple(3,3);

    move.addRemoval(temp);
    move.addRemoval(2,2);
    move.addRemoval(1,1);

    if(move.numRemoves() == 3){
      return true;
    }
    else{
      return false;
    }
  }//endof Test_numRemoves

/**
  * @version 1.0
  * @return boolean
  * This method tests the funtionality of both addRemoval functions.
 **/
  public boolean Test_addRemoval_both(){
    Move move = new Move(0,0, 0,1);
    Tuple temp = new Tuple(3,3);

    move.addRemoval(temp);
    move.addRemoval(2,2);

    if(move.numRemoves() == 2){
      return true;
    }
    else{
      return false;
    }
  }//endof Test_addRemoval_both

/**
  * @version 1.0
  * @return boolean
  * This method tests the funtionality removeList function.
 **/
  public boolean Test_removeList(){
    Move move = new Move(0,0, 0,1);
    Tuple temp = new Tuple(3,3);

    move.addRemoval(temp);
    move.addRemoval(2,2);

    ArrayList<Tuple> list = move.removeList();

    if((list.get(0).x() == 3) && (list.get(0).y() == 3) && (list.size() == 2)){
      return true;
    }
    else{
      return false;
    }
  }//endof Test_removeList

/**
  * @version 1.0
  * @return boolean
  * This method tests the funtionality noMove function.
 **/
  public boolean Test_noMove(){
    Move move = new Move(0,0, 0,1);
    if(move.noMove() == false){
      return true;
    }
    else{
      return false;
    }
  }//endof Test_noMove

}//endof Test_Move
