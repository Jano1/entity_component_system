package component;

/**
 * Created by Jan-Frederik Leißner on 15.02.2018.
 */
public abstract class BasedComponent<T extends Component> extends Component<T> implements Based<T> {
    protected T base;

    @Override
    public boolean has_base() {
        return base != null;
    }

    @Override
    public void based_on(T base) {
        if (!this.equal_memory(base)) {
            this.base = base;
        }
    }
}
