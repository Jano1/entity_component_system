package component;

/**
 * Created by Jan-Frederik Leißner on 15.02.2018.
 */
public abstract class HistoricComponent<T extends Component> extends Component<T> implements Historic<T>{

    protected T snap;

    @Override
    public void snap() {
        snap = this.clone();
    }

    @Override
    public boolean has_snap() {
        return snap!=null;
    }

    @Override
    public abstract void reset();
}
