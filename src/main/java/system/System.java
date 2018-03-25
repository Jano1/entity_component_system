package system;

import component.Component;
import ecs.ID;

import java.util.Collections;
import java.util.List;

public abstract class System implements Runnable {

    private Class<? extends Component>[] needed_components;

    public System(Class<? extends Component>[] needed_components) {
        this.needed_components = needed_components;
    }

    public Class<? extends Component>[] needed_components(){
        return needed_components;
    }

    @Override
    public void run() {

    }

    public abstract void handle(List<ID> to_handle);
}
