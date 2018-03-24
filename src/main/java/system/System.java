package system;

import ecs.ECS;
import ecs.ID;

import java.util.List;

public abstract class System implements Runnable {
    private String identifier;

    private List<String> run_before;
    private List<String> run_after;

    private boolean single_running_instance = true;

    public System(){

    }

    public System(String identifier, List<String> run_after, List<String> run_before, boolean single_running_instance) {
        this.identifier = identifier;
        this.run_after = run_after;
        this.run_before = run_before;
        this.single_running_instance = single_running_instance;
    }

    @Override
    public void run() {

    }

    public String identifier() {
        return identifier;
    }

    protected List<String> before(){
        return run_before;
    }

    protected List<String> after(){
        return run_before;
    }

    public abstract void handle(List<ID> to_handle);
}
