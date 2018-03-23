package component;

import ecs.ID;

/**
 * Created by Jan-Frederik Leißner on 15.02.2018.
 */
public abstract class BasedComponent<T extends Component> extends Component<T> implements Based<T> {

    protected ID base_id;

    @Override
    public void based_on(ID base_id) {
        if(!this.equal_memory(base_id.get((Class<T>) getClass()))){
            this.base_id = base_id;
        }
    }

    @Override
    public boolean has_base() {
        if(base_id == null){
            return false;
        }
        return base_id.get(getClass()) != null;
    }

    @Override
    public T base() {
        if(!has_base()){
            return null;
        }
        return base_id.get((Class<T>) getClass());
    }
}
