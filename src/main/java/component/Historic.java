package component;

/**
 * Created by Jan-Frederik Leißner on 15.02.2018.
 */
public interface Historic<T extends Component> {
    void snap();
    boolean has_snap();
    void reset();
}
