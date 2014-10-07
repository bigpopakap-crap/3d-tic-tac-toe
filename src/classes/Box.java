
package classes;

public class Box {
    private GenericPlayer owner;
    private final int layer;
    private final int row;
    private final int col;

    public Box(int layer, int row, int col) {
        this.layer = layer;
        this.row = row;
        this.col = col;
    }

    public int[] getCoordinates() {
        int[] out = new int[3];
        out[0] = layer;
        out[1] = row;
        out[2] = col;
        return out;
    }

    public void setOwner(GenericPlayer owner) {
        this.owner = owner;
    }

    public GenericPlayer getOwner() {
        return owner;
    }

    public boolean isOwned() {
        return owner != null;
    }

}
