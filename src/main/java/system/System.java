package system;

import ecs.ID;

import java.util.List;

public abstract class System implements Runnable {

    @Override
    public void run() {

    }

    public abstract void handle(List<ID> to_handle);
}
