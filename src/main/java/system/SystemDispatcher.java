package system;

import org.apache.commons.collections4.map.HashedMap;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SystemDispatcher {
    private List<Class<? extends System>> registered_systems;

    private List<List<Class<? extends System>>> tick_plan;

    public SystemDispatcher() {
        registered_systems = new ArrayList<>();
        tick_plan = new ArrayList<>();
    }

    public void register(Class<? extends System> to_register) {
        registered_systems.add(to_register);
        rebuild_tick_plan();
    }

    public void remove(Class<? extends System> to_remove) {
        registered_systems.remove(to_remove);
        rebuild_tick_plan();
    }

    public void rebuild_tick_plan() {
        for(Class<? extends System> system_class : registered_systems){
            List<Class<? extends System>> current = new ArrayList<>();
            current.add(system_class);
            tick_plan.add(current);
        }
    }

    public void run_tick_plan() {
        for(List<Class<? extends System>> parrallel_systems : tick_plan){
            java.lang.System.out.println("running: "+parrallel_systems);
            for(Class<? extends System> system_class : parrallel_systems){
                //TODO:Hier gehts weiter!
            }
        }
    }
}
