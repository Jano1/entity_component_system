package ecs;

import component.Component;

/**
 * Created by Jan-Frederik Leißner on 16.02.2018.
 */
public class ID {
    private int id;
    private ECS ecs;

    protected ID(int id, ECS ecs){
        this.id = id;
        this.ecs = ecs;
    }

    public void release(){
        ecs.remove_entity(this);
    }

    public void add_component(Component to_add){
        ecs.add_component_to_id(to_add,this);
    }

    public void remove_component(String type){
        ecs.remove_component_from_id(type,this);
    }

    public Component get_component(String type){
        return ecs.get_component_from_id(type,this);
    }

    public static ID invalid_id(){
        return new ID(-1,null);
    }

    public Blueprint as_blueprint(){
        Blueprint blueprint = new Blueprint();
        blueprint.addAll(ecs.components_of_id(this));
        return blueprint;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "id:"+integer_id()+","+as_blueprint();
    }

    public int integer_id() {
        return id;
    }
}
