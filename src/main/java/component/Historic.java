package component;

/**
 * Created by Jan-Frederik Leißner on 15.02.2018.
 */
public interface Historic<T extends Component> {

    /**
     * Make a snap of the current state of the component
     */
    void snap();

    /**
     * Checks for a snap
     * @return true if snap!=null
     */
    boolean has_snap();

    /**
     * Resets the Component to a possibly snapped state ( shpuld set snap to null in the process)
     */
    void reset();
}
