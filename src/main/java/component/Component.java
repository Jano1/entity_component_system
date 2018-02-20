package component;

import component.collection.ComponentCollection;
import component.collection.ComponentManager;
import component.collection.ID;

/**
 * Created by Jan-Frederik Leißner on 15.02.2018.
 */
public abstract class Component<T extends Component> implements Cloneable {

    private ComponentManager manager;

    public void set_manager(ComponentManager manager){
        this.manager = manager;
    }

    public ComponentCollection collection(){
        return manager.collection_for(getClass());
    }

    public void bind_to(ID id){
        collection().put(id,this);
    }

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
    protected abstract T clone();
}
