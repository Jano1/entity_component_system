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

    // system - handling
    private SystemDispatcher system_dispatcher;

    public ECS(IDPool pool) {
        this.pool = pool;
        component_collections = new HashedMap<>();
        system_dispatcher = new SystemDispatcher();
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

    public SystemDispatcher edit_system_dispatcher() {
        return system_dispatcher;
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

    Map<String,List<ID>> cached_id_lists = new HashMap<>();
    public void tick() {
        List<List<System>> ordered_system_groups = system_dispatcher.get_system_groups();
        cached_id_lists.clear();
        for(List<System> current_group : ordered_system_groups){
            for(System current_system : current_group){
                current_system.handle(id_list_with(current_system.needed_components()));
            }
        }
    }

    private List<ID> id_list_with(Class<? extends Component>[] classes) {
        String cache_key = Arrays.toString(classes);
        if(!cached_id_lists.containsKey(cache_key)){
            List<ID> to_cache = new ArrayList<>();
            List<ComponentCollection> collections = new ArrayList<>();
            //gather all needed collections
            for(Class needed_class : classes){
                collections.add(component_collections.get(needed_class));
            }
            //test if id is in all
            if(collections.size()>0){
                for(ID current_id : (Set<ID>) collections.get(0).keySet()){
                    for(ComponentCollection current_collection : collections){
                        if(!current_collection.containsKey(current_id)){

                        }
                    }
                }
            }
            cached_id_lists.put(cache_key,to_cache);
        }
        return cached_id_lists.get(cache_key);
    }
}
