package component.implemented;

import component.BasedComponent;

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

    public void tick(){
        ticks_since_start++;
    }

    @Override
    public TimeComponent absolute() {
        if (has_base()) {
            return new TimeComponent(
                    base().absolute().ticks_per_second + ticks_per_second,
                    base().absolute().ticks_per_second + ticks_per_second,
                    base().absolute().delta_seconds_factor * delta_seconds_factor);
        }
        return this;
    }

    @Override
    public boolean equal_values(TimeComponent test) {
        return ticks_per_second == test.ticks_per_second && ticks_since_start == test.ticks_since_start;
    }

    @Override
    public TimeComponent clone() {
        return new TimeComponent(ticks_since_start, ticks_per_second, delta_seconds_factor);
    }

    @Override
    public String toString() {
        return "TimeComponent{" +
                "ticks_since_start=" + ticks_since_start +
                ", ticks_per_second=" + ticks_per_second +
                ", delta_seconds_factor=" + delta_seconds_factor +
                ", base=" + base() +
                '}';
    }
}
