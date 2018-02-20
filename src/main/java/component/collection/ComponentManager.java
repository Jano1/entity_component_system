package component.collection;

import component.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jan-Frederik Leißner on 20.02.2018.
 */
public class ComponentManager {

    private Map<Class,ComponentCollection> map;

    public ComponentManager(){
        map = new HashMap<>();
    }

    public ComponentCollection collection_for(Class<? extends Component> type) {
        if(!map.containsKey(type)){
            map.put(type,new ComponentCollection());
        }
        return map.get(type);
    }

    @Override
    public String toString() {
        return map.toString();
    }
}
