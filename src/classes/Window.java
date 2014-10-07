
package classes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Window extends javax.swing.JFrame {
    private Game game;
    private MyPanel panel;

    private GenericPlayer currentPlayer;
        public GenericPlayer getCurrentPlayer() {
            return currentPlayer;
        }
        public void setCurrentPlayer(GenericPlayer player) {
            currentPlayer = player;
        }

        //SET SIZE/SPACING PARAMETERS
    private int boxSize = 75;
    private int boxSpacing = 25;
    private int layerShift = 15;

    private Box bordered;
    class MyPanel extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            for (int i=0; i<27; i++) {
                if (game.getCube().getBox(i/9, i/3-3*ind(i>8)-3*ind(i>17), i%3).isOwned()) {
                    g2.setColor(game.getCube().getBox(i/9, i/3-3*ind(i>8)-3*ind(i>17), i%3).getOwner().getColor());
                } else {
                    g2.setColor(Color.lightGray);
                }
                g2.fill(new Rectangle(35 + (boxSize + boxSpacing)*(i%3) - layerShift*ind(i>8) - layerShift*ind(i>17), 15 + (boxSize + boxSpacing)*(i/3-3*ind(i>8)-3*ind(i>17)) + layerShift*ind(i>8) + layerShift*ind(i>17), boxSize, boxSize));
                g2.setStroke(new BasicStroke(1));
                if (!game.isOver() && game.getCube().getBox(i/9, i/3-3*ind(i>8)-3*ind(i>17), i%3) == bordered) {
                    g2.setStroke(new BasicStroke(4));
                }
                g2.setColor(Color.black);
                g2.draw(new Rectangle(35 + (boxSize + boxSpacing)*(i%3) - layerShift*ind(i>8) - layerShift*ind(i>17), 15 + (boxSize + boxSpacing)*(i/3-3*ind(i>8)-3*ind(i>17)) + layerShift*ind(i>8) + layerShift*ind(i>17), boxSize, boxSize));
                
                if (game.isOver()) {
                    int[][] indeces = game.getWinningBoxes();
                    for (int[] index : indeces) {
                        if (index[0] == i/9
                         && index[1] == i/3-3*ind(i>8)-3*ind(i>17)
                         && index[2] == i%3) {
                            g2.setStroke(new BasicStroke(4));
                            g2.draw(new Rectangle(35 + (boxSize + boxSpacing)*(i%3) - layerShift*ind(i>8) - layerShift*ind(i>17), 15 + (boxSize + boxSpacing)*(i/3-3*ind(i>8)-3*ind(i>17)) + layerShift*ind(i>8) + layerShift*ind(i>17), boxSize, boxSize));
                        }
                    }
                }
                //new ImageIcon(getClass().getResource("/classes/Darkfalse2Knight.jpg")).paintIcon(this, g2, 0, 0);
            }
        }
            private int ind(boolean b) {
                if (b) {
                    return 1;
                } else {
                    return 0;
                }
            }
    }

    public static void main(String [] args) {
        new Game(Human.class, Computer.class, Color.cyan, Color.blue).play();
    }

    private Box getFocusedBox(int x, int y) {
        for (int i=26; i>=0; i--) {
            if (x >= 35 + (boxSize + boxSpacing)*(i%3) - layerShift*ind(i>8) - layerShift*ind(i>17)
             && x <= 35 + (boxSize + boxSpacing)*(i%3) - layerShift*ind(i>8) - layerShift*ind(i>17) + boxSize
             && y >= 15 + (boxSize + boxSpacing)*(i/3-3*ind(i>8)-3*ind(i>17)) + layerShift*ind(i>8) + layerShift*ind(i>17)
             && y <= 15 + (boxSize + boxSpacing)*(i/3-3*ind(i>8)-3*ind(i>17)) + layerShift*ind(i>8) + layerShift*ind(i>17) + boxSize) {
//                System.out.println("yes: [" + (35 + (boxSize + boxSpacing)*(i%3) - layerShift*ind(i>8) - layerShift*ind(i>17)) + ", " + (35 + (boxSize + boxSpacing)*(i%3) - layerShift*ind(i>8) - layerShift*ind(i>17) + boxSize) + "]," +
//                        "[" + (15 + (boxSize + boxSpacing)*(i/3-3*ind(i>8)-3*ind(i>17)) + layerShift*ind(i>8) + layerShift*ind(i>17)) + ", " + (15 + (boxSize + boxSpacing)*(i/3-3*ind(i>8)-3*ind(i>17)) + layerShift*ind(i>8) + layerShift*ind(i>17) + boxSize) + "]");
                return game.getCube().getBox(i/9, i/3-3*ind(i>8)-3*ind(i>17), i%3);
             } else {
//                System.out.println("not: [" + (35 + (boxSize + boxSpacing)*(i%3) - layerShift*ind(i>8) - layerShift*ind(i>17)) + ", " + (35 + (boxSize + boxSpacing)*(i%3) - layerShift*ind(i>8) - layerShift*ind(i>17) + boxSize) + "]," +
//                        "[" + (15 + (boxSize + boxSpacing)*(i/3-3*ind(i>8)-3*ind(i>17)) + layerShift*ind(i>8) + layerShift*ind(i>17)) + ", " + (15 + (boxSize + boxSpacing)*(i/3-3*ind(i>8)-3*ind(i>17)) + layerShift*ind(i>8) + layerShift*ind(i>17) + boxSize) + "]");
             }
        }
        return null;
    }
        private int ind(boolean b) {
            if (b) {
                return 1;
            } else {
                return 0;
            }
        }

    public Window(Game game) {
        this.game = game;
        initComponents();
        setCurrentPlayer(null);

        panel = new MyPanel();
        panel.setBounds(10, 10, 350, 350);
        this.setSize(365, 390);
        panel.setBackground(Color.white);
        this.add(panel);
        this.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        try {
            Box b = getFocusedBox(evt.getX(), evt.getY());
            if (!b.isOwned()) {
                b.setOwner(currentPlayer);
                setCurrentPlayer(null);
                repaint();
            }
        } catch (NullPointerException e) {

        }
    }//GEN-LAST:event_formMouseClicked

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        bordered = game.isOver() ? null : getFocusedBox(evt.getX(), evt.getY());
        repaint();
    }//GEN-LAST:event_formMouseMoved

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}