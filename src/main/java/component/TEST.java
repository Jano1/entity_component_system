package component;

import component.collection.ID;
import component.implemented.PositionComponent;
import range.IDPool;
import range.Range;

/**
 * Created by Jan-Frederik Leißner on 16.02.2018.
 */
public class TEST {
    public static void main(String[] args){
        PositionComponent test_1 = new PositionComponent(1,0,0);
        PositionComponent test_2 = new PositionComponent(0,1,0);
        PositionComponent test_3 = new PositionComponent(0,0,1);
        PositionComponent test_4 = new PositionComponent(0,1,1);

        IDPool pool = new IDPool(new Range(0,9));

        test_1.bind_to(ID.lease_from(pool));
        test_2.bind_to(ID.lease_from(pool));
        test_3.bind_to(ID.lease_from(pool));
        test_4.bind_to(ID.lease_from(pool));

        test_3.bindend_to().release();
    }
}
