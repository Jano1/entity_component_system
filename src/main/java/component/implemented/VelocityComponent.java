package component.implemented;

import component.HistoricBasedComponent;

/**
 * Created by jleissner on 19.03.2018.
 */
public class VelocityComponent extends HistoricBasedComponent<VelocityComponent> {

    int x,y,z;

    public VelocityComponent(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public VelocityComponent absolute() {
        if(has_base()){
            return new VelocityComponent(base.x+x,base.y+y,base.z+z);
        }
        return this;
    }

    @Override
    public void reset() {
        if(has_snap()){
            x = snap.x;
            y = snap.y;
            z = snap.z;
            snap = null;
        }
    }

    @Override
    public boolean equal_values(VelocityComponent test) {
        return test.x==x && test.y==y && test.z == z;
    }

    @Override
    public VelocityComponent clone() {
        return new VelocityComponent(x,y,z);
    }

    @Override
    public String toString() {
        return "VEL("+x+","+y+","+z+")";
    }
}
