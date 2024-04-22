
import java.util.*;

public class Game{
    Deque<Player> players;
    Board gameBoard;
     Game(){
        players=new  LinkedList<Player>();
        CellX cellX=new CellX();
        players.add(new Player("Player1",cellX));
        CellO cellY=new CellO();
        players.add(new Player("player2",cellY));
        gameBoard=new Board(3);
    }
    public String StartGame(){
        boolean isWinner=true;
        while(isWinner){
            Player playturn=players.removeFirst();
            gameBoard.printBoard();
            boolean isFree=gameBoard.getFreeCells();
            if(!isFree){
                isWinner=false;
                continue;
            }
            System.out.println(playturn.getName()+" "+"enter the position");
            Scanner inputS=new Scanner(System.in);
            String s=inputS.nextLine();
            String[] values=s.split(",");
              int inputRow=Integer.valueOf(values[0]);
              int inputCol=Integer.valueOf(values[1]);

              boolean isValid=gameBoard.addAtPositions(inputRow, inputCol,playturn.cellType);
              if(!isValid){
                players.addFirst(playturn);
                System.out.println("Invalid Positon");
                continue;
              }
              players.addLast(playturn);

              boolean iswin=isWinner(inputRow,inputCol,playturn.cellType.cellType);
              if(iswin){
                return playturn.name;
              }
        }
        return " Match Tied";
    }
    public boolean isWinner(int row,int col,CellType cellType){
        boolean isRow=true;
        boolean isCol=true;
        boolean isDiagonal=true;
        boolean isRevDiagonal=true;
        for(int i=0;i<gameBoard.size;i++){
            if(gameBoard.board[row][i]==null || gameBoard.board[row][i].cellType!=cellType){
                isRow=false;
            } 
        }
        for(int i=0;i<gameBoard.size;i++){
            if(gameBoard.board[i][col]==null || gameBoard.board[i][col].cellType!=cellType){
                isCol=false;
            }
        }
        for(int i=0,j=0;i<gameBoard.size;i++,j++){
            if(gameBoard.board[i][j]==null || gameBoard.board[i][j].cellType!=cellType){
                isDiagonal=false;
            } 
        }
        for(int i=0,j=gameBoard.size-1;i<gameBoard.size;i++,j--){
            if(gameBoard.board[i][j]==null || gameBoard.board[i][j].cellType!=cellType){
                isRevDiagonal=false;
            } 
        }
        return isRow || isCol || isDiagonal || isRevDiagonal;
    }
}