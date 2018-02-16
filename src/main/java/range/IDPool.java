package range;

/**
 * Created by Jan-Frederik Leißner on 15.02.2018.
 */
public class IDPool {
    private RangeList ranges;
    private int minimum,maximum;
    private int useable_id_amount;

    public IDPool(Range overall_range) {
        ranges = new RangeList(overall_range.from, overall_range.to);
        minimum = overall_range.from;
        maximum = overall_range.to;
        useable_id_amount = overall_range.size();
    }

    public int next_id() {
        if (!ranges.has_first()) {
            System.err.println("Pool ran out of free ids!!!");
            return -1;
        }
        int id = ranges.first.increase_from();
        if (ranges.first.size() <= 0) {
            ranges.first = ranges.first.next;
        }
        useable_id_amount--;
        return id;
    }

    public void release_id(int id) {
        if(!(id<minimum) && !(id>maximum)){
            ranges.insert_range(new Range(id, id));
            useable_id_amount++;
        }
    }

    public int useable_id_amount(){
        return useable_id_amount;
    }

    @Override
    public String toString() {
        return "POOL("+useable_id_amount()+"/"+minimum+"-"+maximum+")"+ranges;
    }

    public boolean is_leaseable(int id) {
        if(!ranges.has_first()){
            return false;
        }
        RangeListEntry current = ranges.first;
        while(id < current.from && current.has_next()){
            current = current.next;
        }
        return current.in_range(id);
    }


}
