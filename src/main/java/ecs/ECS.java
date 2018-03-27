package ecs;

import component.Component;
import component.collection.ComponentCollection;
import org.apache.commons.collections4.map.HashedMap;
import range.IDPool;
import range.Range;
import system.SystemDispatcher;
import system.System;

import java.util.*;

public class ECS {
    // id - handling
    private IDPool pool;

    // component - handling
    private Map<Class, ComponentCollection> component_collections;
    private Map<List<Class>,List<ID>> cached_id_lists;

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

    public void register_system_group(String key, System[] to_register){
        system_dispatcher.register_group(key,to_register);
    }

    public void remove_system_group(String key){
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
        if(!component_collections.containsKey(component.getClass())){
            component_collections.put(component.getClass(),new ComponentCollection<T>());
        }
        component_collections.get(component.getClass()).put(id,component);
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
        return (T) component_collections.get(with_class).get(id);
    }

    public void tick() {
        List<List<System>> ordered_system_groups = system_dispatcher.get_system_groups();
        for(List<System> current_group : ordered_system_groups){
            for(System current_system : current_group){

            }
        }
    }

    public void render(float interpolation){

    }
}
