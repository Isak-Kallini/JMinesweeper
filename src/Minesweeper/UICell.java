package Minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class UICell {
    JButton button;
    ArrayList<UICell> cells;
    Coordinate pos;
    int value;
    boolean isRevealed = false;
    Game game;

    public UICell(int v, Coordinate c, ArrayList<UICell> k, Game g){
        button = new JButton();
        button.addMouseListener(mouseListener);
        button.setPreferredSize(new Dimension(40, 40));
        button.setMargin(new Insets(2, 2, 2, 2));
        button.setFont(button.getFont().deriveFont(Font.BOLD, 20F));
        button.setBackground(new Color(23,42,58));
        pos = c;
        cells = k;
        value = v;
        game = g;
    }

    public JButton getButton(){
        return button;
    }

    public Coordinate getPos() {
        return pos;
    }

    public int getValue(){
        return value;
    }

    public void setCells(ArrayList<UICell> c){
        cells = c;
    }

    public void reset(){
        isRevealed = false;
        button.setBackground(new Color(23,42,58));
        button.setLabel("");
        value = game.get(pos).getValue();
    }

    public void reveal(){
        ArrayList<Coordinate> revealed = game.reveal(pos);
        if (value == -1) {
            button.setBackground(new Color(96, 23, 0));
        } else {
            revealed.forEach(c -> {
                UICell cell = cells.stream().filter(e -> e.getPos().equals(c)).collect(Collectors.toList()).get(0);
                if(cell.getValue() != 0){
                    cell.getButton().setLabel(Integer.toString(cell.getValue()));
                }
                cell.getButton().setBackground(new Color(157,197,187));
                cell.getButton().repaint();
            });
        }
    }

    MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getButton() == MouseEvent.BUTTON1) {
                if (value == -1) {
                    button.setBackground(new Color(96, 23, 0));
                    JOptionPane.showMessageDialog(null, "Boom");
                    game.clear();
                    game.generate();
                    cells.forEach(c -> c.reset());
                } else {
                    reveal();
                }
            }else if(e.getButton() == MouseEvent.BUTTON3){
                button.setBackground(new Color(242,158,76));
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    };
}
