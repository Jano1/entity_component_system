package component.collection;

import component.implemented.PositionComponent;
import range.IDPool;
import range.Range;

/**
 * Created by Jan-Frederik Leißner on 20.02.2018.
 */
public class TEST {
    public static void main(String[] args){
        ComponentManager manager = new ComponentManager();
        IDPool pool = new IDPool(new Range(0,10));

        PositionComponent p_001 = new PositionComponent(1,1,1);
        p_001.set_manager(manager);
        p_001.bind_to(ID.lease_from(pool));

        PositionComponent p_002 = new PositionComponent(1,1,1);
        p_002.set_manager(manager);
        p_002.bind_to(ID.lease_from(pool));

        PositionComponent p_003 = new PositionComponent(1,1,1);
        p_003.set_manager(manager);
        p_003.bind_to(ID.lease_from(pool));

        PositionComponent p_004 = new PositionComponent(1,1,1);
        p_004.set_manager(manager);
        p_004.bind_to(ID.lease_from(pool));

        System.out.println(manager);
    }
}
