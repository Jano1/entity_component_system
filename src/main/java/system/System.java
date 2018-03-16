package system;

import java.util.List;

public class System {
    private String identifier;

    private List<String> run_before;
    private List<String> run_after;

    private boolean single_running_instance = true;

    public System(String identifier, List<String> run_after, List<String> run_before, boolean single_running_instance) {
        this.identifier = identifier;
        this.run_after = run_after;
        this.run_before = run_before;
        this.single_running_instance = single_running_instance;
    }

    public String identifier() {
        return identifier;
    }
}
