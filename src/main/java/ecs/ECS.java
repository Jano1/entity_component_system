package ecs;

import component.Component;
import component.collection.ComponentCollection;
import org.apache.commons.collections4.map.HashedMap;
import range.IDPool;
import range.Range;
import system.SystemDispatcher;
import system.System;

import java.util.Map;

public class ECS {
    // id - handling
    private IDPool pool;

    // component - handling
    private Map<String,ComponentCollection> component_collections;

    // system - handling
    private SystemDispatcher system_dispatcher;
    private Map<String,System> systems;

    public ECS(IDPool pool) {
        this.pool = pool;
        component_collections = new HashedMap<>();
        systems = new HashedMap<>();
        system_dispatcher = new SystemDispatcher();
    }

    public ECS(int id_pool_size){
        this(new IDPool(new Range(0,id_pool_size)));
    }

    public void register_system(System to_register){
        systems.put(to_register.identifier(),to_register);
    }

    public void remove_system(System to_remove){
        systems.remove(to_remove.identifier());
    }

    public ID create_entity(Blueprint blueprint){
        ID possible_id = new ID(pool.next_id(),this);
        for (Component component : blueprint){
            add_component_to_id(component.clone(),possible_id);
        }
        return possible_id;
    }

    public void add_component_to_id(Component component, ID id){
        component.create_collection_in(this);
        component_collections.get(component.collection_key()).put(id,component);
    }

    public void remove_entity(ID id) {
        pool.release_id(id.integer_id());
        for(ComponentCollection collection : component_collections.values()){
            collection.remove(id);
        }
    }

    public void remove_component_from_id(Component component, ID id){
        component_collections.get(component.collection_key()).remove(id);
    }

    public Map<String,ComponentCollection> component_collections(){
        return component_collections;
    }

    public void tick(){

    }
}
