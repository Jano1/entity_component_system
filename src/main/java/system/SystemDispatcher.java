package system;

import java.util.*;

public class SystemDispatcher {

    private Map<String,List<System>> registered_systems;
    private List<String> order;

    public SystemDispatcher() {
        registered_systems = new HashMap<>();
        order = new ArrayList<>();
    }

    public void register_group(String key, System[] to_register) {
        if(!order.contains(key)){
            order.add(key);
            registered_systems.put(key,Arrays.asList(to_register));
        }else{
            java.lang.System.err.println("Eintrag '"+key+"' bereits vorhanden...");
        }
    }

    public void remove_group(String key) {
        order.remove(key);
        registered_systems.remove(key);
    }

    public List<List<System>> get_system_groups(){
        List<List<System>> result = new ArrayList<>();
        for(String index : order){
            result.add(registered_systems.get(index));
        }
        return result;
    }
}
