
package classes;

import java.awt.Color;

public class Computer extends GenericPlayer {

    public Computer(Game game, Color color) {
        super(game, color);
    }

    public void makeMove() {
        bestMove().setOwner(this);
        getGame().getWindow().setCurrentPlayer(null);
        getGame().getWindow().repaint();
    }
        private Box bestMove() {
            for (Line line : getGame().getCube().getLineCollection()) {
                if (line.isWinDraw(this)) {
                    return line.getSoleUnclaimedBox();
                }
            }
            for (Line line : getGame().getCube().getLineCollection()) {
                if (line.isWinDraw(getGame().getPlayer1() != this ? getGame().getPlayer1() : getGame().getPlayer2())) {
                    return line.getSoleUnclaimedBox();
                }
            }
            int num;
            for (int k=0; k<3; k++) {
                for (int i=0; i<3; i++) {
                    for (int j=0; j<3; j++) {
                        if (!getGame().getCube().getBox(k, i, j).isOwned()) {
                            num = 0;
                            for (Line line : getGame().getCube().getLineCollection()) {
                                if (line.contains(getGame().getCube().getBox(k, i, j))
                                 && line.hasOneOf(this)) {
                                    num++;
                                }
                            }
                            if (num > 1) {
                                return getGame().getCube().getBox(k, i, j);
                            }
                        }
                    }
                }
            }
            Box b;
            do {
                b = getGame().getCube().getBox(rand012(), rand012(), rand012());
            } while (b.isOwned());
            return b;
        }
            private static int rand012() {
                return (int) (Math.random()*3);
            }

}
