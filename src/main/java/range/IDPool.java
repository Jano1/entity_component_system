package range;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jan-Frederik Leißner on 15.02.2018.
 */
public class IDPool{
    private RangeList ranges;

    public IDPool(Range overall_range){
        ranges = new RangeList(overall_range.from,overall_range.to);
    }

    public int next_id() {
        if (!ranges.has_first()) {
            return -1;
        }
        int id = ranges.first.increase_from();
        if (ranges.first.size() <= 0) {
            ranges.first = ranges.first.next;
        }
        return id;
    }

    public void release_id(int id) {
        ranges.insert_range(new Range(id, id));
    }

    public int useable_id_amount(){
        if(!ranges.has_first()){
            return 0;
        }
        RangeListEntry current = ranges.first;
        int counter = current.size();
        while(current.has_next()){
            counter += current.next.size();
            current = current.next;
        }
        return counter;
    }

}
