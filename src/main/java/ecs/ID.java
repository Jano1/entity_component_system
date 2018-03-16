package ecs;

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

    public static ID invalid_id(){
        return new ID(-1,null);
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "ID:"+id;
    }

    public int integer_id() {
        return id;
    }
}
