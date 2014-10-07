
package classes;

import java.awt.Color;

public abstract class GenericPlayer {
    protected Game game;
        public Game getGame() {
            return game;
        }
    protected Color color;
        public Color getColor() {
            return color;
        }

    public GenericPlayer(Game game, Color color) {
        this.color = color;
        this.game = game;
    }

    public abstract void makeMove();
}
