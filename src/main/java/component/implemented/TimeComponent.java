package component.implemented;

import component.BasedComponent;

import java.sql.Time;

/**
 * Created by Jan-Frederik Leißner on 15.02.2018.
 */
public class TimeComponent extends BasedComponent<TimeComponent> {

    long ticks_since_start;
    int ticks_per_second;

    float delta_seconds_factor;

    public TimeComponent(long ticks_since_start, int ticks_per_second, float delta_seconds_factor) {
        this.ticks_since_start = ticks_since_start;
        this.ticks_per_second = ticks_per_second;
        this.delta_seconds_factor = delta_seconds_factor;
    }

    /**
     * Assert ticks_since_start = 0
     * @param ticks_per_second
     * @param delta_seconds_factor
     */
    public TimeComponent(int ticks_per_second, float delta_seconds_factor) {
        this(0, ticks_per_second, delta_seconds_factor);
    }

    /**
     * Assert ticks_since_start = 0 and delta_seconds_factor = 1
     * @param ticks_per_second
     */
    public TimeComponent(int ticks_per_second) {
        this(ticks_per_second, 1f);
    }

    /**
     * If someone just wants to give a entity a time-factor
     * @param delta_seconds_factor
     * @param base
     */
    public TimeComponent(float delta_seconds_factor, TimeComponent base){
        this(0,delta_seconds_factor);
        based_on(base);
    }

    public float delta_seconds() {
        return delta_seconds_plain() * delta_seconds_factor;
    }

    public float delta_seconds_plain() {
        return (1f / ticks_per_second);
    }

    public void tick(){
        ticks_since_start++;
    }

    @Override
    public TimeComponent absolute() {
        if (has_base()) {
            return new TimeComponent(
                    base.absolute().ticks_per_second + ticks_per_second,
                    base.absolute().ticks_per_second + ticks_per_second,
                    base.absolute().delta_seconds_factor * delta_seconds_factor);
        }
        return this;
    }

    @Override
    public boolean equal_values(TimeComponent test) {
        return ticks_per_second == test.ticks_per_second && ticks_since_start == test.ticks_since_start;
    }

    @Override
    protected TimeComponent clone() {
        return new TimeComponent(ticks_since_start, ticks_per_second, delta_seconds_factor);
    }
}
