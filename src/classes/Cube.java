
package classes;

import collection.Collection;

public class Cube {
    private Box[][][] cube;
        //cube[layer][row][col]
    private Collection<Line> lines;
        public Collection<Line> getLineCollection() {
            return lines;
        }

    public Cube() {
        cube = new Box[3][3][3];
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                for (int k=0; k<3; k++) {
                    cube[i][j][k] = new Box(i, j, k);
                }
            }
        }
        lines = new Collection<Line>();
        for (int k=0; k<3; k++) {
            for (int j=0; j<3; j++) {
                lines.addObjects(new Line(getBox(k, j, 0), getBox(k, j, 1), getBox(k, j, 2)));
                lines.addObjects(new Line(getBox(k, 0, j), getBox(k, 1, j), getBox(k, 2, j)));
            }
            lines.addObjects(new Line(getBox(k, 0, 0), getBox(k, 1, 1), getBox(k, 2, 2)));
            lines.addObjects(new Line(getBox(k, 2, 0), getBox(k, 1, 1), getBox(k, 0, 2)));
        }
        for (int k=0; k<3; k++) {
            for (int j=0; j<3; j++) {
                lines.addObjects(new Line(getBox(j, k, 0), getBox(j, k, 1), getBox(j, k, 2)));
                lines.addObjects(new Line(getBox(0, k, j), getBox(1, k, j), getBox(2, k, j)));
            }
            lines.addObjects(new Line(getBox(0, k, 0), getBox(1, k, 1), getBox(2, k, 2)));
            lines.addObjects(new Line(getBox(2, k, 0), getBox(1, k, 1), getBox(0, k, 2)));
        }
        for (int k=0; k<3; k++) {
            for (int j=0; j<3; j++) {
                lines.addObjects(new Line(getBox(j, 0, k), getBox(j, 1, k), getBox(j, 2, k)));
                lines.addObjects(new Line(getBox(0, j, k), getBox(1, j, k), getBox(2, j, k)));
            }
            lines.addObjects(new Line(getBox(0, 0, k), getBox(1, 1, k), getBox(2, 2, k)));
            lines.addObjects(new Line(getBox(2, 0, k), getBox(1, 1, k), getBox(0, 2, k)));
        }
        lines.addObjects(new Line(getBox(0, 0, 0), getBox(1, 1, 1), getBox(2, 2, 2)));
        lines.addObjects(new Line(getBox(0, 2, 0), getBox(1, 1, 1), getBox(2, 0, 2)));
        lines.addObjects(new Line(getBox(2, 0, 0), getBox(1, 1, 1), getBox(0, 2, 2)));
        lines.addObjects(new Line(getBox(2, 2, 0), getBox(1, 1, 1), getBox(0, 0, 2)));
    }

    public Box getBox(int layer, int row, int col) {
        return cube[layer][row][col];
    }

}
