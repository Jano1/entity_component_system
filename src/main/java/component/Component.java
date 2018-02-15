package component;

/**
 * Created by Jan-Frederik Leißner on 15.02.2018.
 */
public abstract class Component<T extends Component> implements Cloneable{

    public abstract boolean equal_values(T test);

    public boolean equal_memory(T test){
        return super.equals(test);
    }

    @Override
    protected abstract T clone();
}
