package ecs;

import component.Component;
import component.collection.ComponentCollection;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.map.HashedMap;
import range.IDPool;
import range.Range;
import system.System;
import system.SystemDispatcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ECS {
    // id - handling
    private IDPool pool;

    // component - handling
    private Map<Class, ComponentCollection> component_collections;
    private Map<String, List<ID>> cached_id_lists;

    // system - handling
    private SystemDispatcher system_dispatcher;

    public ECS(IDPool pool) {
        this.pool = pool;
        component_collections = new HashedMap<>();
        system_dispatcher = new SystemDispatcher();
        cached_id_lists = new HashedMap<>();
    }

    public ECS(int id_pool_size) {
        this(new IDPool(new Range(0, id_pool_size)));
    }

    public void register_system_group(String key, System[] to_register) {
        system_dispatcher.register_group(key, to_register);
    }

    public void remove_system_group(String key) {
        system_dispatcher.remove_group(key);
    }

    public ID create_entity(Blueprint blueprint) {
        ID possible_id = new ID(pool.next_id(), this);
        for (Component component : blueprint) {
            add_component_to_id(component.clone(), possible_id);
        }
        return possible_id;
    }

    public <T extends Component> void add_component_to_id(T component, ID id) {
        if (!component_collections.containsKey(component.getClass())) {
            component_collections.put(component.getClass(), new ComponentCollection<T>());
        }
        component_collections.get(component.getClass()).put(id, component);
    }

    public void remove_entity(ID id) {
        pool.release_id(id.integer_id());
        for (ComponentCollection collection : component_collections.values()) {
            collection.remove(id);
        }
    }

    public <T extends Component> void remove_component_from_id(Class<T> with_class, ID id) {
        component_collections.get(with_class).remove(id);
    }

    public List<Component> components_of_id(ID id) {
        List<Component> result = new ArrayList<>();
        for (ComponentCollection collection : component_collections.values()) {
            if (collection.containsKey(id)) {
                result.add((Component) collection.get(id));
            }
        }
        return result;
    }

    public <T extends Component> T get_component_from_id(Class<T> with_class, ID id) {
        if(!component_collections.containsKey(with_class)){
            return null;
        }
        return (T) component_collections.get(with_class).get(id);
    }

    public void tick() {
        List<List<System>> ordered_system_groups = system_dispatcher.get_system_groups();
        cached_id_lists.clear();
        for (List<System> current_group : ordered_system_groups) {
            for (System current_system : current_group) {
                List<ID> id_list = new ArrayList<>();
                String cache_key = generate_cache_key(current_system.needed_components());
                if(!cached_id_lists.containsKey(cache_key)){
                    for (Class<? extends Component> needed : current_system.needed_components()) {
                        List<ID> local_list = new ArrayList<>(component_collections.get(needed).keySet());
                        if (id_list.size() == 0) {
                            id_list.addAll(local_list);
                            continue;
                        }
                        id_list = ListUtils.intersection(id_list, local_list);
                    }
                    cached_id_lists.put(cache_key,id_list);
                }
                current_system.handle(cached_id_lists.get(cache_key));
            }
        }
    }

    private String generate_cache_key(Class<? extends Component>[] classes) {
        List<String> cache_key_list = new ArrayList<>();
        for(Class needed : classes){
            cache_key_list.add(needed.getName());
        }
        Collections.sort(cache_key_list);
        return String.join(":",cache_key_list);
    }

    public void render(float interpolation) {

    }
}
