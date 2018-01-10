package aiModule;

import java.util.ArrayList;
import java.util.List;

public class AIBoard implements Board{
    private final static int boardWidth = 10;
    private final static int boardHeight = 20;
    private List<List<GridField>> boardStatus;

    public AIBoard(){
        boardStatus = new ArrayList<>();
    }

    AIBoard(List<List<GridField>> boardStatus){
        this.boardStatus = boardStatus;
    }

    @Override
    public GridField getFieldAtPosition(int x, int y) {
        return null;
    }

    @Override
    public List<List<GridField>> getBoardState() {
        return boardStatus;
    }

    @Override
    public int getWidth() {
        return boardWidth;
    }

    @Override
    public int getHeight() {
        return boardHeight;
    }

    @Override
    public void addBoardStateChangedListener(BoardStateChangedListener newListener) {

    }

    @Override
    public void addLinesClearedListener(LinesClearedListener newListener) {

    }

    public void moveLeft(){

    }

    public void moveRight(){

    }

    public void rotate(){

    }

    public void moveDown(){

    }

    public void register(){

    }

    private boolean isLine(int row){
        for (int i=0; i<boardWidth; i++){
            if (!boardStatus.get(i).get(row).isOccupied())
                return false;
        }
        return true;
    }

    private int getColumnHeight(int column){        //column - [0, 1, ..., 9]
        int r = 0;
        for (; r<boardHeight && !boardStatus.get(column).get(r).isOccupied(); r++);
        return boardHeight - r;
    }

    public int getAggregateHeight(){
        int total = 0;
        for (int c = 0; c<boardWidth; c++)
            total+=this.getColumnHeight(c);
        return total;
    };

    public int getCompleteLines(){
        int total = 0;
        for (int r=0; r<boardHeight; r++)
            if (this.isLine(r))
                total++;
        return total;
    };

    public int getHoles(){
        int total = 0;
        for (int c=0; c<boardWidth; c++){
            boolean check = false;
            for (int r=0; r<boardHeight; r++){
                if(boardStatus.get(c).get(r).isOccupied())
                    check = true;
                else if (!boardStatus.get(c).get(r).isOccupied() && check)
                    total++;
            }
        }
        return total;
    };

    public int getBumpiness(){
        int total = 0;
        for (int c = 0; c<boardWidth-1; c++)
            total+=Math.abs(this.getColumnHeight(c)-this.getColumnHeight(c+1));
        return total;
    };

    public void showBoard(){
        for(int r=0; r<boardHeight; r++){
            for (int c=0; c<boardWidth; c++) {
                if (boardStatus.get(c).get(r).isOccupied())
                    System.out.print(1 + " ");
                else
                    System.out.print(0 + " ");
            }
            System.out.println();
        }
    }

    public void add(List<GridField> row){
        boardStatus.add(row);
    }

}
