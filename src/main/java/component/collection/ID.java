package component.collection;

import range.IDPool;

/**
 * Created by Jan-Frederik Leißner on 16.02.2018.
 */
public class ID {
    private int id;
    private IDPool pool;

    private ID(IDPool pool){
        this.id = -1;
        this.pool = pool;
    }

    public void lease(){
        if (id==-1 || !leased()) {
            this.id = pool.next_id();
        }else{
            System.err.println("leasing from "+pool+" not possible...");
        }
    }

    public void release(){
        pool.release_id(id);
    }

    public boolean leased(){
        return !pool.is_leaseable(id);
    }

    public static ID lease_from(IDPool pool){
        ID id = new ID(pool);
        id.lease();
        return id;
    }

    public static ID invalid_id(){
        return new ID(null);
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "ID:"+id;
    }
}
