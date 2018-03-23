package component.implemented;

import component.HistoricBasedComponent;

/**
 * Created by jleissner on 19.03.2018.
 */
public class VelocityComponent extends HistoricBasedComponent<VelocityComponent> {

    float x,y,z;
    int in_ticks;

    public VelocityComponent(float x, float y, float z, int in_ticks) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.in_ticks = in_ticks;
    }

    public void normalize(){
        if(!is_normalized()){
            x = x/in_ticks;
            y = y/in_ticks;
            z = z/in_ticks;
            in_ticks = 1;
        }
    }

    public boolean is_normalized(){
        return in_ticks == 1;
    }

    @Override
    public VelocityComponent absolute() {
        if(has_base()){
            base().normalize(); normalize();
            return new VelocityComponent(
                    base().x + x,
                    base().y + y,
                    base().z + z,
                    1);
        }
        return this;
    }

    @Override
    public void reset() {
        if(has_snap()){
            x = snap.x;
            y = snap.y;
            z = snap.z;
            in_ticks = snap.in_ticks;
            snap = null;
        }
    }

    @Override
    public boolean equal_values(VelocityComponent test) {
        return test.x==x && test.y==y && test.z == z && test.in_ticks == in_ticks;
    }

    @Override
    public VelocityComponent clone() {
        return new VelocityComponent(x,y,z, in_ticks);
    }

    @Override
    public String toString() {
        return "VEL("+x+","+y+","+z+"/"+ in_ticks +")";
    }
}
