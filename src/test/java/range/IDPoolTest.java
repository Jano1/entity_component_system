package range;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Jan-Frederik Leißner on 16.02.2018.
 */
public class IDPoolTest {

    IDPool test;

    @Before
    public void setUp() throws Exception {
        test = new IDPool(new Range(0,100));
    }

    @Test
    public void next_id() {
        assertEquals(0,test.next_id());
    }

    @Test
    public void release_id() {
        test.next_id();
        test.release_id(0);
        assertEquals("POOL(0-100){[0-100]}",test.toString());
    }

    @Test
    public void useable_id_amount() {
        assertEquals(101,test.useable_id_amount());
    }
}