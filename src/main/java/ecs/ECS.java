package ecs;

import component.Component;
import component.collection.ComponentCollection;
import range.IDPool;
import range.Range;

import java.util.Map;

public class ECS {
    private IDPool pool;
    private Map<String,ComponentCollection> component_collections;

    public ECS(IDPool pool) {
        this.pool = pool;
    }

    public ECS(int id_pool_size){
        this(new IDPool(new Range(0,id_pool_size)));
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
}
