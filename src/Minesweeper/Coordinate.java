package Minesweeper;

public class Coordinate {
    private int row;
    private int col;

    public Coordinate(int r, int c){
        row = r;
        col = c;
    }

    public int getRow(){
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean equals(Coordinate c) {
        return row == c.getRow() && col == c.getCol();
    }
}
