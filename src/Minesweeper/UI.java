package Minesweeper;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UI {
    public UI(Game game){
        SwingUtilities.invokeLater(() -> createWindow(game, "Minesweeper", 200, 200));
    }

    private void createWindow(Game game, String title, int width, int height) {
        JFrame frame = new JFrame(title);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = frame.getContentPane();
        JPanel gamePanel = new JPanel(new GridLayout(game.getRow(), game.getCol()));

        ArrayList<UICell> cells = new ArrayList<>();
        for(int r = 0; r < game.getRow(); r++){
            for(int c = 0; c < game.getCol(); c++){
                cells.add(new UICell(game.get(new Coordinate(r, c)).getValue(), new Coordinate(r, c), cells, game));
            }
        }
        cells.forEach(c -> gamePanel.add(c.getButton()));
        pane.add(gamePanel);

        frame.setMinimumSize(new Dimension(width, height));
        frame.pack();
        frame.setVisible(true);
    }
}
