package aiModule;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MoveMaker implements Runnable, IMoveMaker {
    private final double difficultLevel;
    private AIBoard mainAIBoard;
    private boolean endMove=false;

    public MoveMaker(AIBoard mainAIBoard, double difficultLevel) {
        this.mainAIBoard = mainAIBoard;
        this.difficultLevel = difficultLevel;
    }

    @Override
    public void run() {
        while (!this.endMove) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(){
        this.endMove = true;
    }

    private void findBestMove() {
        List<List<GridField>> originalBoardStatus = this.mainAIBoard.getBoardState();

        int bestEndPosition = 0;
        int bestRotation = 0;
        double bestScore = Double.NEGATIVE_INFINITY;

        for (int endPosition = 0; endPosition < this.mainAIBoard.getWidth(); endPosition++) {
            for (int tetrominoRotation = 0; tetrominoRotation < 4; tetrominoRotation++) {
                AIBoard copiedAIBoard = new AIBoard(originalBoardStatus);

                setRotation(copiedAIBoard, tetrominoRotation);
                setEndPosition(copiedAIBoard, endPosition);
                simulateGravity(copiedAIBoard);
                double currentScore = getScore(copiedAIBoard);

                if (currentScore > bestScore) {
                    bestScore = currentScore;
                    bestEndPosition = endPosition;
                    bestRotation = tetrominoRotation;
                }
            }
        }

        makeMove(bestEndPosition, bestRotation);
    }

    private void simulateGravity(AIBoard copiedAIBoard) {
        for (int i = 0; i < 25; i++) {
            copiedAIBoard.moveDown();
        }
    }

    private void makeMove(int endPosition, int rotation) {
        if(rotation != 0){
            this.mainAIBoard.rotate();
        }else if(endPosition<0){
            this.mainAIBoard.moveLeft();
        }else if(endPosition>0){
            this.mainAIBoard.moveRight();
        }else {
            this.mainAIBoard.moveDown();
        }
    }

    private void setEndPosition(AIBoard copiedAIBoard, int endPosition) {
        int mid = (this.mainAIBoard.getWidth() - 1) / 2;
        int move = endPosition - mid;

        while (move > 0) {
            copiedAIBoard.moveRight();
            move -= 1;
        }

        while (move < 0) {
            copiedAIBoard.moveLeft();
            move += 1;
        }

    }

    private void setRotation(AIBoard copiedAIBoard, int tetrominoRotation) {
        for (int k = 0; k < tetrominoRotation; k++) {
            copiedAIBoard.rotate();
        }
    }

    private double getScore(AIBoard AIBoard) {
        return AIBoard.getAggregateHeight() + AIBoard.getCompleteLines() + AIBoard.getHoles() + AIBoard.getBumpiness();
    }

}
