package component;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import component.collection.ComponentCollection;
import ecs.ECS;

/**
 * Created by Jan-Frederik Leißner on 15.02.2018.
 */
public abstract class Component<T extends Component> implements Cloneable {

    /**
     * Checks the values of the components against each other
     * @param test the component to test against
     * @return true if values are equal
     */
    public abstract boolean equal_values(T test);

    /**
     * Checks if the memory-address of components is equal
     * @param test the component to test against
     * @return true if memory-addresses are equal
     */
    public boolean equal_memory(T test) {
        return super.equals(test);
    }

    /**
     * Creates a clone of the current component-state as a new component
     * @return the cloned component
     */
    @Override
    public abstract T clone();

    /**
     * Creates a json string representation of this object
     * @return
     */
    public String as_json(){
        Gson compiler = new Gson();
        return compiler.toJson(this);
    }

    @Override
    public String toString() {
        return as_json();
    }
}
