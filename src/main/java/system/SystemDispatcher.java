package system;

import java.util.*;

public class SystemDispatcher {
    private Map<String,List<System>> registered_systems;

    public SystemDispatcher() {
        registered_systems = new HashMap<>();
    }

    public <T extends System> void register_group(String key,T... to_register) {
        List<System> list = Arrays.asList(to_register);
        registered_systems.put(key,list);
    }

    public void remove_group(String key) {
        registered_systems.remove(key);
    }
}
