package component;

/**
 * Created by Jan-Frederik Leißner on 15.02.2018.
 */
public interface Based<T extends Component> {

    /**
     * This gives the component in it's absolute state (possibly recursive)
     * @return the component, absolute in the game world
     */
    T absolute();

    /**
     * Sets the base for this component
     * @param base the base for this component
     */
    void based_on(T base);

    /**
     * Check if a base is existent
     * @return true if base!=null
     */
    boolean has_base();
}
