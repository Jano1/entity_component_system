package component.implemented;

import org.junit.Before;
import org.junit.Test;

import java.sql.Time;

import static org.junit.Assert.*;

/**
 * Created by Jan-Frederik Leißner on 15.02.2018.
 */
public class TimeComponentTest {

    TimeComponent base;
    TimeComponent test;

    @Before
    public void setUp() throws Exception {
        base = new TimeComponent(25);
        test = new TimeComponent(1f,base);
    }

    @Test
    public void test_factor(){
        test.delta_seconds_factor = 5f;
        base.delta_seconds_factor = 5f;

        assertEquals(25f,test.absolute().delta_seconds_factor,1E-10f);
    }

    @Test
    public void increase_ticks(){
        test.ticks_since_start = 0;
        test.tick();

        assertEquals(1,test.ticks_since_start);
    }
}