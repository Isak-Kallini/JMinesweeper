package Minesweeper;

import java.util.ArrayList;

public class Game {
    private Cell[][] board;

    public Game(int row, int col){
        board = new Cell[row][col];
        for(int r = 0; r < getRow(); r++){
            for(int c = 0; c < getCol(); c++){
                board[r][c] = new Cell();
            }
        }
    }

    public void generate(){
        clear();
        for(int r = 0; r < getRow(); r++){
            for(int c = 0; c < getCol(); c++){
                if(Math.random() > 0.8){
                    board[r][c].setValue(-1);
                }
            }
        }
        for(int r = 0; r < getRow(); r++){
            for(int c = 0; c < getCol(); c++){
                int value = board[r][c].getValue();
                if(value != -1){
                    int count = 0;
                    for(int i = -1; i < 2; i++){
                        for(int j = -1; j < 2; j++){
                            if((i + r) >= 0 && (i + r) < getRow() && (j + c) >= 0 && (j + c) < getCol()){
                                if(board[r + i][c + j].getValue() == -1){
                                    count += 1;
                                }
                            }
                        }
                    }
                    board[r][c].setValue(count);
                }
            }
        }
    }

    public Cell get(Coordinate c){
        return board[c.getRow()][c.getCol()];
    }

    public ArrayList<Coordinate> reveal(Coordinate c){
        ArrayList<Coordinate> result = new ArrayList<>();
        result.add(c);
        if(board[c.getRow()][c.getCol()].reveal()){
            for(int i = -1; i < 2; i++){
                for(int j = -1; j < 2; j++){
                    if((i + c.getRow()) >= 0 && (i + c.getRow()) < getRow() && (j + c.getCol()) >= 0 && (j + c.getCol()) < getCol()){
                        if(board[i + c.getRow()][j + c.getCol()].getValue() == 0){
                            result.addAll(reveal(new Coordinate(i + c.getRow(), j + c.getCol())));
                            for(int h = -1; h < 2; h++) {
                                for (int k = -1; k < 2; k++) {
                                    if((h + i + c.getRow()) >= 0 && (h + i + c.getRow()) < getRow() && (k + j + c.getCol()) >= 0 && (k + j + c.getCol()) < getCol()){
                                        if(board[h + i + c.getRow()][k + j + c.getCol()].getValue() != 0 && !board[h + i + c.getRow()][k + j + c.getCol()].isRevealed()){
                                            result.addAll(reveal(new Coordinate(h + i + c.getRow(), k + j + c.getCol())));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    public int getRow(){
        return board[0].length;
    }
    public int getCol(){
        return board.length;
    }

    public void clear(){
        board = new Cell[getRow()][getCol()];
        for(int r = 0; r < getRow(); r++){
            for(int c = 0; c < getCol(); c++){
                board[r][c] = new Cell();
            }
        }
    }
}
