package Minesweeper;

public class Cell {
    private int value = 0;
    private boolean isRevealed = false;
    public Cell(){}

    public int getValue(){
        return value;
    }

    public void setValue(int v){
        value = v;
    }

    public boolean isRevealed(){
        return isRevealed;
    }

    public boolean reveal(){
        if(!isRevealed){
            isRevealed = true;
            return true;
        }else{
            return false;
        }
    }
}
