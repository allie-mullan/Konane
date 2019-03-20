/**
 * @author: Francine Dennehy
 * @author: Alexandra Mullan
 * @version: 1.0
 * This class contains the methods to generate the legal moves on Konane.
 *
 **/
package GameElements;
import java.util.*;

public class MoveGenerator{

    public MoveGenerator(){
    }//end of Constructor

    public ArrayList<Move> first_turn(char c, GameBoard currentBoardState){

        ArrayList<Move> legal_moves = new ArrayList<Move>();

        if(c == 'b'){
            Move m1 = new Move();
            m1.removeList().add(new Tuple(7,7));
            Move m2 = new Move();
            m2.removeList().add(new Tuple(0,0));
            Move m3 = new Move();
            m3.removeList().add(new Tuple(4,4));
            Move m4 = new Move();
            m4.removeList().add(new Tuple(3,3));

            legal_moves.add(m1); /* the numbers are changed because the   */
            legal_moves.add(m2); /* game board is from 0 - 7 not 1 - 8    */
            legal_moves.add(m3); /* also the numebers are stored as (y,x) */
            legal_moves.add(m4);

        }
        if(c == 'w'){

            if(currentBoardState.get_game_state(7,7) == '-'){
                Move m1 = new Move();
                m1.removeList().add(new Tuple(6,7));
                Move m2 = new Move();
                m2.removeList().add(new Tuple(7,6));

                legal_moves.add(m1);
                legal_moves.add(m2);
            }
            else if(currentBoardState.get_game_state(0,0) == '-'){
                Move m1 = new Move();
                m1.removeList().add(new Tuple(0,1));
                Move m2 = new Move();
                m2.removeList().add(new Tuple(1,0));

                legal_moves.add(m1);
                legal_moves.add(m2);
            }
            else if(currentBoardState.get_game_state(4,4) == '-'){
                Move m1 = new Move();
                m1.removeList().add(new Tuple(3,4));
                Move m2 = new Move();
                m2.removeList().add(new Tuple(4,3));
                Move m3 = new Move();
                m3.removeList().add(new Tuple(5,4));
                Move m4 = new Move();
                m4.removeList().add(new Tuple(4,5));

                legal_moves.add(m1);
                legal_moves.add(m2);
                legal_moves.add(m3);
                legal_moves.add(m4);
            }
            else if(currentBoardState.get_game_state(3,3) == '-'){
                Move m1 = new Move();
                m1.removeList().add(new Tuple(2,3));
                Move m2 = new Move();
                m2.removeList().add(new Tuple(3,2));
                Move m3 = new Move();
                m3.removeList().add(new Tuple(4,3));
                Move m4 = new Move();
                m4.removeList().add(new Tuple(3,4));

                legal_moves.add(m1);
                legal_moves.add(m2);
                legal_moves.add(m3);
                legal_moves.add(m4);
            }

        }//end of if c == 'w'
        return legal_moves;
    }//end of first_turn

    public ArrayList<Move> getMoves(char[][] gameState, char myColor){

        ArrayList<Tree> moveSequences = new ArrayList<Tree>();

        //get all moveable pieces
        ArrayList<Tuple> moveablePieces = getMoveablePieces(gameState, myColor);

        //create a tree for each moveable piece
        for(int i = 0; i < moveablePieces.size(); i++){
            moveSequences.add(new Tree(moveablePieces.get(i).x(), moveablePieces.get(i).y()));
        }
        //System.out.println(moveablePieces.size() + " cosas pueden mover en al menos de una manera");
        //build each tree
        for(int i = 0; i<moveSequences.size(); i++){
            Tree tr = moveSequences.get(i);
            Node r = moveSequences.get(i).getRoot();
            buildMoveTree(moveSequences.get(i), gameState, r, myColor);
        }
        return sequencesToMoves(moveSequences);
    }

    public ArrayList<Move> sequencesToMoves(ArrayList<Tree> t){

        ArrayList<Move> alm = new ArrayList<Move>();
        ArrayList<Node> leaves = new ArrayList<Node>();

        //System.out.println("there are " + t.size() + " trees");
        //for each Tree
        for(int x = 0; x < t.size(); x++){
            //create a set of leaf nodes
            //for each node in the tree
            for(int i = 0; i<t.get(x).size(); i ++){
                if(t.get(x).get(i).isLeaf() == true){
                    leaves.add(t.get(x).get(i));
                }
            }

        }

        //System.out.println("There are " + leaves.size() +" leaves, so there should be " + leaves.size() + " moves");

        for(int i = 0; i< leaves.size(); i++){
            Node r = leaves.get(i); //set r = to a leaf

            int lX = r.x(); //save the leaf's coordinates
            int lY = r.y();

            while(!r.isRoot()){ //set r = this leaf's root
                r = r.parent();
            }

            int rX = r.x();
            int rY = r.y();

            Move m = new Move(rX, rY, lX, lY);

            createRemoveList(m, leaves.get(i));

            alm.add(m);
        }

        return expandMoves(alm);
    }

    public void expand(int lSX, int lSY, int lRX, int lRY, int noJumps, Move fullMove, ArrayList<Move> alm){
        int newLastStepX = -1;
        int newLastStepY = -1;
        //get the new end tuple
         if(lSX-lRX < 0){
            newLastStepX = lRX+1;
            newLastStepY = lRY;
        }
        else if(lSX-lRX > 0){
            newLastStepX = lRX-1;
            newLastStepY = lRY;
        }
        else if(lSY-lRY < 0){
            newLastStepX = lRX;
            newLastStepY = lRY+1;
        }
        else if(lSY-lRY>0){
            newLastStepX = lRX;
            newLastStepY = lRY-1;
        }
        int size = fullMove.removeList().size();
        Move m = new Move(fullMove.cLX(),fullMove.cLY(),newLastStepX,newLastStepY);
        for(int i = (size-noJumps+1); i < size; i++){
            m.addRemoval(fullMove.removeList().get(i));
        }
        noJumps--;
        alm.add(m);
        if(noJumps == 1){
            return;
        }
        int newLastRemoveX = fullMove.removeList().get(size - noJumps).x();
        int newLastRemoveY = fullMove.removeList().get(size - noJumps).y();
        expand(newLastStepX,newLastStepY,newLastRemoveX,newLastRemoveY,noJumps,fullMove,alm);
    }

    public ArrayList<Move> expandMoves(ArrayList<Move> alm){
        for(int i = 0; i < alm.size(); i++){
            Move fullMove = alm.get(i);
            int noSteps = fullMove.removeList().size();
            if(noSteps > 1){

                int lastStepX = fullMove.fLX();
                int lastStepY = fullMove.fLY();
                int lastRemoveX = fullMove.removeList().get(0).x();
                int lastRemoveY = fullMove.removeList().get(0).y();
                expand(lastStepX,lastStepY,lastRemoveX,lastRemoveY,noSteps,fullMove, alm);

            }
        }
        removeDuplicates(alm);
        return alm;
    }

    public void removeDuplicates(ArrayList<Move> alm){
        for(int i = 0; i < alm.size()-1; i++){ //for each move in arraylist
            for(int j = i+1; j<alm.size(); j ++){
                if(alm.get(i).compareTo(alm.get(j)) == 0){//if theyre equal
                    //System.out.println("Remove " + alm.get(j));
                    alm.remove(j);

                }
            }
        }
    }

    public void createRemoveList(Move m, Node n){

        if(n.isRoot()){
            return;
        }

        int nX = n.x();
        int nY = n.y();

        int pX = n.parent().x();
        int pY = n.parent().y();

        int rmvX = (nX + pX)/2;
        int rmvY = (nY + pY)/2;

        m.addRemoval(rmvX, rmvY);

        createRemoveList(m, n.parent());
    }

    public void buildMoveTree(Tree t, char[][] state, Node parent, char myColor){

        ArrayList<Tuple> jumps;

        if(parent.isRoot()){
            jumps = getJumps(state, parent.t(), new Tuple(-1,-1));
        }else{
            jumps = getJumps(state, parent.t(), parent.parent().t()); //HERE
        }

        if(jumps.size() == 0){
            return;
        }

        for(int i = 0; i < jumps.size(); i++){
            Node n = new Node(jumps.get(i), parent);

            t.addNode(n);
            char[][] newState = tempBoard(state, jumps.get(i), parent.t(), myColor, null);

            buildMoveTree(t, newState, n, myColor);

        }
    }

    public char[][] tempBoard(char[][] oldState, Tuple to, Tuple from, char myColor, Tuple firstRemove){
        char[][] newState = new char[8][8];
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                newState[i][j] = oldState[i][j];
            }
        }

        if(to.x() == -1){
            newState[firstRemove.x()][firstRemove.y()] = '-';
        }else{
            if(from.x()== 8 || from.y() == 8){
                System.out.println("");
            }
            newState[from.x()][from.y()] = '-';
            newState[to.x()][to.y()] = myColor;
            newState[(to.x()+from.x())/2][(to.y()+from.y())/2] = '-';
        }

        return newState;
    }

    public ArrayList<Tuple> getJumps(char[][] state, Tuple t, Tuple previous){
        int x = t.x();
        int y = t.y();
        int px = previous.x();
        int py = previous.y();

        ArrayList<Tuple> jumps = new ArrayList<Tuple>();

        if(x+2 < 8 && state[x+2][y] == '-' && state[x+1][y] != '-' && ((x+2) != px && y != py)){
            jumps.add(new Tuple(x+2,y));
        }
        if(x-2 >= 0 && state[x-2][y] == '-' && state[x-1][y] != '-' && ((x-2) != px && y != py)){
            jumps.add(new Tuple(x-2,y));
        }
        if(y+2 < 8 && state[x][y+2] == '-' && state[x][y+1] != '-' && (x != px && (y+2) != py)){
            jumps.add(new Tuple(x,y+2));
        }
        if(y-2 >= 0 && state[x][y-2] == '-' && state[x][y-1] != '-' && (x != px && (y-2) != py)){
            jumps.add(new Tuple(x,y-2));
        }

        return jumps;
    }

    public ArrayList<Tuple> getMoveablePieces(char[][] state, char myColor){
        ArrayList<Tuple> moveablePieces = new ArrayList<Tuple>();

        for(int i = 0; i < 8; i++ ){
            for(int j = 0; j < 8; j++){

                if(state[i][j] == myColor)//is it my piece?
                    if(moveable(i, j, state)) //can it move?

                        moveablePieces.add(new Tuple(i,j));
            }
        }
        return moveablePieces;
    }

    public boolean moveable(int x, int y, char[][] state){
        if(x+2 < 8 && state[x+2][y] == '-' && state[x+1][y] != '-'){
            return true;
        }
        if(x-2 >= 0 && state[x-2][y] == '-' && state[x-1][y] != '-'){
            return true;
        }
        if(y+2 < 8 && state[x][y+2] == '-' && state[x][y+1] != '-'){
            return true;
        }
        if(y-2 >= 0 && state[x][y-2] == '-' && state[x][y-1] != '-'){
            return true;
        }
        return false;
    }

}//end of MoveGenerator class
