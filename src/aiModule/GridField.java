package aiModule;

public class GridField {

    private boolean isOccupied;

    public GridField(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    public GridField(){
        isOccupied = false;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }
}
