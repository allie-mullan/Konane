/**
  * @author Francine Dennehy
  * @author Alexandra Mullan
  * This class tests the functionality of the moveGenerator Class
 **/
package Tests;
import GameElements.MoveGenerator;
import GameElements.GameBoard;
import GameElements.Move;
import GameElements.Tuple;
import java.util.*;

/**
  getMoves
  sequencesToMoves
  expand
  expandMoves
  createRemoveList
  buildMoveTree
 **/
public class Test_MoveGenerator{

/**
  * @version 1.0
  * This is the constructor class and it calls all of the methods
  * found withing this class.
 **/
  public Test_MoveGenerator(){
    System.out.println("Test first_turn_black result: " + Test_firstTurn_black());
    System.out.println("Test first_turn_white result: " + Test_firstTurn_white());
    System.out.println("Test removeDuplicates result: " + Test_removeDuplicates());
    System.out.println("Test getMoveablePieces result: " + Test_getMoveablePieces());
    System.out.println("Test getMoveablePieces test two result: " + Test_getMoveablePieces_testTwo());
    System.out.println("Test getJumps result: " + Test_getJumps_oneHop());
  }//endof constructor

/**
  * @version 1.0
  * @return boolean
  * This method tests the firstTurn method with player as black.
 **/
  public boolean Test_firstTurn_black(){
    GameBoard gb = new GameBoard();
    MoveGenerator mg = new MoveGenerator();
    ArrayList<Move> temp = mg.first_turn('b', gb);

    if(temp.size() == 4){
      return true;
    }
    else{
      return false;
    }
  }//endof Test_firstTurn_black

/**
  * @version 1.0
  * @return boolean
  * This method tests the firstTurn method with player as white.
 **/
  public boolean Test_firstTurn_white(){
    GameBoard gb = new GameBoard();
    gb.replace(new Tuple(0,0), '-');
    MoveGenerator mg = new MoveGenerator();
    ArrayList<Move> temp = mg.first_turn('w', gb);

    if(temp.size() == 2){
      return true;
    }
    else{
      return false;
    }
  }//endof Test_firstTurn_white

/**
  * @version 1.0
  * @return boolean
  * This method tests the removeDuplicates function.
 **/
  public boolean Test_removeDuplicates(){

   ArrayList<Move> temp = new ArrayList<Move>();
   temp.add(new Move(new Tuple(0,0), new Tuple(1,1)));
   temp.add(new Move(new Tuple(0,0), new Tuple(1,1)));
   temp.add(new Move(new Tuple(0,1), new Tuple(1,1)));
   temp.add(new Move(new Tuple(0,1), new Tuple(1,2)));

   MoveGenerator mg = new MoveGenerator();

   mg.removeDuplicates(temp);

   if(temp.size() == 3){
     return true;
   }
   else{
     return false;
   }
  }//endof Test_getMoves

/**
  * @version 1.0
  * @return boolean
  * This method tests the getMoveablePieces and the moveable functions.
 **/
public boolean Test_getMoveablePieces(){
  GameBoard gb = new GameBoard();
  MoveGenerator mg = new MoveGenerator();

  gb.replace(new Tuple(0,0), '-');
  gb.replace(new Tuple(1,0), '-');
//  gb.printGameBoard();

  ArrayList<Tuple> temp = mg.getMoveablePieces(gb.gameState(), 'b');

  // for(Tuple t : temp){
  //   System.out.println(t.toString());
  // }


  if((temp.get(0).x() == 0) && (temp.get(0).y() == 2) && (temp.size() == 1)){
    return true;
  }
  else{
    return false;
  }

}//endof Test_getMoveablePieces

/**
  * @version 1.0
  * @return boolean
  * This method tests the getMoveablePieces and the moveable functions.
 **/
public boolean Test_getMoveablePieces_testTwo(){
  GameBoard gb = new GameBoard();
  MoveGenerator mg = new MoveGenerator();

  gb.replace(new Tuple(0,0), '-');
  gb.replace(new Tuple(1,0), '-');
//  gb.printGameBoard();

  ArrayList<Tuple> temp = mg.getMoveablePieces(gb.gameState(), 'w');

  // for(Tuple t : temp){
  //   System.out.println(t.toString());
  // }

  if((temp.get(0).x() == 1) && (temp.get(0).y() == 2) && (temp.size() == 2)){
    return true;
  }
  else{
    return false;
  }
}//endof Test_getMoveablePieces_testTwo

/**
  * @version 1.0
  * @return boolean
  * This method tests the getjumps function.
 **/
public boolean Test_getJumps_oneHop(){
  GameBoard gb = new GameBoard();
  MoveGenerator mg = new MoveGenerator();

  gb.replace(new Tuple(0,0), '-');
  gb.replace(new Tuple(1,0), '-');
  gb.replace(new Tuple(1,2), '-');
  //gb.printGameBoard();

  ArrayList<Tuple> temp = mg.getJumps(gb.gameState(), new Tuple(1,4), new Tuple(-1,-1));

  // for(Tuple t : temp){
  //   System.out.println(t.toString());
  // }

  if((temp.get(0).x() == 1) && (temp.get(0).y() == 2)){
    return true;
  }
  else{
    return false;
  }
}//endof Test_getJumps()

/**
  * @version 1.0
  * @return boolean
  * This method tests the getjumps function.
  * NOT COMPLETE WITH TESTING 
 **/
public boolean Test_buildMoveTree(){
  GameBoard gb = new GameBoard();
  MoveGenerator mg = new MoveGenerator();

  gb.replace(new Tuple(0,0), '-');
  gb.replace(new Tuple(1,0), '-');
  gb.replace(new Tuple(1,2), '-');
  gb.printGameBoard();


  return false;
}


}//endof Test_MoveGenerator
