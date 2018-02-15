package component;

/**
 * Created by Jan-Frederik Leißner on 15.02.2018.
 */
public abstract class HistoricBasedComponent<T extends Component> extends HistoricComponent<T> implements Based<T> {
    protected T base;

    @Override
    public boolean has_base() {
        return base!=null;
    }

    @Override
    public void based_on(T base) {
        this.base = base;
    }
}
