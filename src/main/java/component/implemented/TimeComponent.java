package component.implemented;

import component.BasedComponent;

/**
 * Created by Jan-Frederik Leißner on 15.02.2018.
 */
public class TimeComponent extends BasedComponent<TimeComponent> {

    long ticks_since_start;
    int ticks_per_second;

    public TimeComponent(long ticks_since_start, int ticks_per_second){
        this.ticks_since_start = ticks_since_start;
        this.ticks_per_second = ticks_per_second;
    }

    public TimeComponent(int ticks_per_second){
        this(0,ticks_per_second);
    }


    @Override
    public TimeComponent absolute() {
        if(has_base()){
            return new TimeComponent(base.ticks_per_second+ticks_per_second,base.ticks_per_second+ticks_per_second);
        }
        return this;
    }

    @Override
    public boolean equal_values(TimeComponent test) {
        return ticks_per_second==test.ticks_per_second && ticks_since_start==test.ticks_since_start;
    }

    @Override
    protected TimeComponent clone() {
        return new TimeComponent(ticks_since_start,ticks_per_second);
    }
}
