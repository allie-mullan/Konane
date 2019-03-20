/**
  * @author Francine Dennehy
  * @author Alexandra Mullan
  * This class tests the functionality of the Tuple Class
 **/
package Tests;
import GameElements.Tuple;
import java.util.*;

public class Test_Tuple{

/**
  * @version 1.0
  * This is the constructor of the Test_Tuple class it prints out the results of
  * the only method present in this class.
 **/
  public Test_Tuple(){
    System.out.println("Test tuple result: " + run_test());
  }//endof construtor

/**
  * @version 1.0
  * @return boolean
  * This method tests the functionality of the tuple class's only methods  
 **/
  public boolean run_test(){
    Tuple t = new Tuple(1,1);

    if((t.x() == 1) && (t.y() == 1)){
      return true;
    }
    else {
      return false;
    }
  }//endof run_test

}//endof Test_Tuple
