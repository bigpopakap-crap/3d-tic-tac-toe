
package classes;

import java.util.Iterator;

public class Line implements Iterable<Box> {
    private Box[] line;
    public Line(Box a, Box b, Box c) {
        line = new Box[3];
        line[0] = a;
        line[1] = b;
        line[2] = c;
    }
    public Iterator<Box> iterator() {
        return new Iterator() {
            private int index = -1;
            public boolean hasNext() {
                return index < 2;
            }
            public Object next() {
                index++;
                return line[index];
            }
            public void remove() {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };
    }
    public boolean isMonopolized() {
        return (line[0].isOwned()
             && line[0].getOwner() == line[1].getOwner()
             && line[0].getOwner() == line[2].getOwner());
    }
    public boolean isWinDraw(GenericPlayer p) {
        return (line[0].getOwner() == p && line[1].getOwner() == p && !line[2].isOwned())
            || (line[0].getOwner() == p && line[2].getOwner() == p && !line[1].isOwned())
            || (line[2].getOwner() == p && line[1].getOwner() == p && !line[0].isOwned());
    }
        public Box getSoleUnclaimedBox() {
            for (Box b : this) {
                if (!b.isOwned()) {
                    return b;
                }
            }
            return null;
        }
    public boolean contains(Box box) {
        return line[0] == box || line[1] == box || line[2] == box;
    }
    public boolean hasOneOf(GenericPlayer p) {
        return (!line[0].isOwned() && !line[1].isOwned() && line[2].getOwner() == p)
            || (!line[0].isOwned() && line[1].getOwner() == p && !line[2].isOwned())
            || (line[0].getOwner() == p && !line[1].isOwned() && !line[2].isOwned());
    }
}
