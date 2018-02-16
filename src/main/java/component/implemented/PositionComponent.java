package component.implemented;

import component.HistoricBasedComponent;

/**
 * Created by Jan-Frederik Leißner on 15.02.2018.
 */
public class PositionComponent extends HistoricBasedComponent<PositionComponent> {

    int x, y, z;

    public PositionComponent(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void reset() {
        if (has_snap()) {
            x = snap.x;
            y = snap.y;
            z = snap.z;
            snap = null;
        }
    }

    @Override
    public boolean equal_values(PositionComponent test) {
        return x == test.x && y == test.y && z == test.z;
    }

    @Override
    protected PositionComponent clone() {
        return new PositionComponent(x, y, z);
    }

    @Override
    public PositionComponent absolute() {
        if (has_base()) {
            return new PositionComponent(base.absolute().x + x, base.absolute().y + y, base.absolute().z + z);
        }
        return this;
    }

    @Override
    public String toString() {
        return "POS("+x+","+y+","+z+")";
    }
}
