package component.implemented;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Jan-Frederik Leißner on 15.02.2018.
 */
public class PositionComponentTest {

    private PositionComponent test;
    PositionComponent base;

    @Before
    public void setUp() throws Exception {
        test = new PositionComponent(1,1,1);
        base = new PositionComponent(10,10,10);
    }

    @Test
    public void test_reset_value_x() {
        test.x = 1;
        test.snap();
        test.x = 100;
        test.reset();
        assertEquals(1,test.x);
    }

    @Test
    public void test_reset_nullified_snap() {
        test.x = 1;
        test.snap();
        test.reset();
        assertEquals(false,test.has_snap());
    }

    @Test
    public void equal_values_true() {
        test.x = 1;
        test.y = 1;
        test.z = 1;
        assertEquals(true,test.equal_values(new PositionComponent(1,1,1)));
    }

    @Test
    public void equal_values_false() {
        test.x = 1;
        test.y = 2;
        test.z = 1;
        assertFalse(test.equal_values(new PositionComponent(1,1,1)));
    }

    @Test
    public void test_clone() {
        PositionComponent clone = test.clone();
        assertEquals(false,clone.equal_memory(test));
    }

    @Test
    public void absolute() {
        test.based_on(base);
        test.x = 1;
        test.y = 1;
        test.z = 1;
        assertEquals(true,test.absolute().equal_values(new PositionComponent(11,11,11)));
    }

    @Test
    public void has_base_true() {
        test.based_on(base);
        assertEquals(true,test.has_base());
    }

    @Test
    public void has_base_false() {
        test.based_on(null);
        assertEquals(false,test.has_base());
    }

    @Test
    public void has_snap_true() {
        test.snap();
        assertTrue(test.has_snap());
    }

    @Test
    public void has_snap_false() {
        test.snap();
        test.reset();
        assertTrue(!test.has_snap());
    }

    @Test
    public void equal_memory() {
        assertTrue(test.equal_memory(test));
    }
}