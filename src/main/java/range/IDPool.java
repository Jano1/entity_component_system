package range;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jan-Frederik Leißner on 15.02.2018.
 */
public class IDPool {
    private List<Range> used_ranges;
    private Range overall_range;

    public IDPool(Range overall_range){
        this.overall_range = overall_range;
        used_ranges = new ArrayList<>();
    }

    private int get_id(){
        return 0;
    }
}
