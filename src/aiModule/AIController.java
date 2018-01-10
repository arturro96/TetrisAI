package aiModule;


public class AIController implements  IAIController{
    private double difficultLevel;
    private AIBoard mainAIBoard;

    private Thread moveThread = null;

    public AIController(double difficultLevel, AIBoard mainAIBoard) {
        this.difficultLevel = difficultLevel;
        this.mainAIBoard = mainAIBoard;
    }

    public void notifyNextTetromino(Tetromino tetromino) {
        MoveMaker moveMaker = new MoveMaker(this.mainAIBoard, this.difficultLevel);
        this.moveThread = new Thread(moveMaker);
        this.moveThread.start();
    }

}