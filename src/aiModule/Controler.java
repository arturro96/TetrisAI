package aiModule;

public class Controler {
    private AIController aiController;
    private AIBoard AIBoard;

    Controler(){
        this.AIBoard = new AIBoard();
        this.aiController = new AIController(0.1, this.AIBoard);
    }

    public void nextTetromino(){
        Tetromino t = new Tetromino();
        this.aiController.notifyNextTetromino(t);

    }


}
