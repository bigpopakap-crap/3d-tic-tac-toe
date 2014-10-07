
package classes;

import collection.Collection;
import java.awt.Color;

public class Game {
    private final GenericPlayer player1;
        public GenericPlayer getPlayer1() {
            return player1;
        }
    private final GenericPlayer player2;
        public GenericPlayer getPlayer2() {
            return player2;
        }
    private final Cube cube;
        public Cube getCube() {
            return cube;
        }
    private final Window window;
        public Window getWindow() {
            return window;
        }

    public Game(Class<? extends GenericPlayer> p1, Class<? extends GenericPlayer> p2, Color color1, Color color2) {
        cube = new Cube();
        if (p1.equals(Human.class)) {
            player1 = new Human(this, color1);
        } else {
            player1 = new Computer(this, color1);
        }
        if (p2.equals(Computer.class)) {
            player2 = new Computer(this, color2);
        } else {
            player2 = new Human(this, color2);
        }
        window = new Window(this);
    }

    public void play() {
        loop: while (true) {
            window.setCurrentPlayer(player1);
            player1.makeMove();
            if (isOver()) {
                break loop;
            }
            window.setCurrentPlayer(player2);
            player2.makeMove();
            if (isOver()) {
                break loop;
            }
        }
        window.repaint();
    }

    public boolean isOver() {
        for (Line line : getCube().getLineCollection()) {
            if (line.isMonopolized()) {
                return true;
            }
        }
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                for (int k=0; k<3; k++) {
                    if (!getCube().getBox(i, j, k).isOwned()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public int[][] getWinningBoxes() {
        Collection<int[]> c = new Collection<int[]>();
        for (Line line : getCube().getLineCollection()) {
            if (line.isMonopolized()) {
                for (Box b : line) {
                    c.addObjects(b.getCoordinates());
                }
            }
        }
        int[][] out = new int[c.getNumObjects()][3];
        int index = 0;
        for (int[] coord : c) {
            out[index] = coord;
            index++;
        }
        return out;
    }
}
