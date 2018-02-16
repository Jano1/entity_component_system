package component.collection;

import range.IDPool;

import javax.swing.text.html.parser.Entity;

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
        if (!leased()) {
            this.id = pool.next_id();
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

    @Override
    public int hashCode() {
        return id;
    }
}
