package component;

/**
 * Created by Jan-Frederik Leißner on 15.02.2018.
 */
public interface Based<T extends Component> {
    T absolute();
    void based_on(T base);
    boolean has_base();
}
