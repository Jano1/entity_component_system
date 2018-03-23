package ecs;

import component.BasedComponent;
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

    public <T extends Component> void add(T to_add){
        ecs.add_component_to_id(to_add,this);
    }

    public <T extends Component> void remove(Class<T> with_class){
        ecs.remove_component_from_id(with_class,this);
    }

    public <T extends Component> T get(Class<T> with_class){
        return ecs.get_component_from_id(with_class,this);
    }

    public Blueprint as_blueprint(){
        return new Blueprint(ecs.components_of_id(this));
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
