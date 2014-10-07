
package classes;

import java.awt.Color;

public class Human extends GenericPlayer {

    public Human(Game game, Color color) {
        super(game, color);
    }

    public void makeMove() {
        while (getGame().getWindow().getCurrentPlayer() == this) {
            System.out.println(".");
        }
    }

}
