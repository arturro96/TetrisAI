import aiModule.AIBoard;
import aiModule.GridField;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Tetris");
        AIBoard AIBoardStatus = new AIBoard();
        int boardWidth = AIBoardStatus.getWidth();
        int boardHeight = AIBoardStatus.getHeight();
        for(int c=0; c<boardWidth; c++){
            List<GridField> row = new ArrayList<>();
            for (int r=0; r<boardHeight; r++){
                GridField gridField = new GridField();
                if (r>10) {
                    Random random = new Random();
                    gridField.setOccupied(random.nextBoolean());
                    if (r == 13 || r == 14)
                        gridField.setOccupied(true);
                }
                row.add(gridField);
            }
            AIBoardStatus.add(row);
        }
        AIBoardStatus.showBoard();
        System.out.println();
        System.out.println("AggregateHeight: " + AIBoardStatus.getAggregateHeight() +
                " CompleteLines: " + AIBoardStatus.getCompleteLines() +
                " Holes: " + AIBoardStatus.getHoles() +
                " Bumpiness: " + AIBoardStatus.getBumpiness());
    }
}
