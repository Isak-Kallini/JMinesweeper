import Minesweeper.Game;
import Minesweeper.UI;

public class Main {
    public static void main(String[] args) {
        Game game = new Game(15, 15);
        game.generate();
        new UI(game);
    }
}