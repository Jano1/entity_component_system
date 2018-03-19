package ecs;

import component.Component;
import component.collection.ComponentCollection;
import org.apache.commons.collections4.map.HashedMap;
import range.IDPool;
import range.Range;
import system.SystemDispatcher;
import system.System;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ECS {
    // id - handling
    private IDPool pool;

    // component - handling
    private Map<String, ComponentCollection> component_collections;

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

    public void register_system(System to_register) {
        system_dispatcher.register(to_register);
    }

    public void remove_system(System to_remove) {
        system_dispatcher.remove(to_remove);
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

    public void add_component_to_id(Component component, ID id) {
        component.create_collection_in(this);
        component_collections.get(component.type()).put(id, component);
    }

    public void remove_entity(ID id) {
        pool.release_id(id.integer_id());
        for (ComponentCollection collection : component_collections.values()) {
            collection.remove(id);
        }
    }

    public void remove_component_from_id(String type, ID id) {
        component_collections.get(type).remove(id);
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

    public Map<String, ComponentCollection> component_collections() {
        return component_collections;
    }

    public Component get_component_from_id(String type, ID id) {
        return (Component) component_collections.get(type).get(id);
    }

    public void tick() {

    }
}
