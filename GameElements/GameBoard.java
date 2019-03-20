package GameElements;

public class GameBoard {

    //instance variables
    private char[][] gameState = new char[8][8];
    private boolean initialState;
    protected final int SIZE = 8;

    //constructor
    public GameBoard(){
        initialState = true;
        newGameBoard();
    }

    public void newGameBoard(){
        for(int i = 0; i < 8; i ++){
            for(int j = 0; j < 8; j ++){
                if((i+j) % 2 == 0){
                    gameState[i][j] = 'b';
                }else{
                    gameState[i][j] = 'w';
                }
            }
        }
    }

    public void replace(Tuple t, char newVal){
        gameState[t.x()][t.y()] = newVal;
    }

    public void update(Move curMov, char myColor){

        if(curMov.numRemoves() > 0){
            for(int i = 0; i<curMov.removeList().size(); i++){
                Tuple t = curMov.removeList().get(i);
                replace(t,'-');
            }
        }

        if(curMov.cLX() != -1){
            replace(curMov.currentLocation(), '-');
        }

        if(curMov.fLX() != -1){
            replace(curMov.futureLocation(), myColor);
        }

    }

    public void printGameBoard(){

        for(int i = 0; i < 8; i ++){
            for(int j = 0; j < 8; j ++){
                System.out.print(gameState[i][j]);
                System.out.print(" ");
            }
            System.out.print("\n");
        }

    }

    public char get_game_state(int x, int y){
        return gameState[x][y];
    }

    public int get_Size(){
        return SIZE;
    }//end of get_Size

    public char[][] gameState(){
        return gameState;
    }
}//end of GameBoard
