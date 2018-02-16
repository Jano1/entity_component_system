package range;

/**
 * Created by Jan-Frederik Leißner on 15.02.2018.
 */
public class RangeList {

    private int lowest,highest;

    private RangeListEntry first;

    public RangeList(int lowest, int highest){
        this.lowest = lowest;
        this.highest = highest;
        first = new RangeListEntry(lowest,highest);
    }

    public void insert_range(Range insert){
        //inserting
        RangeListEntry current = first;
        RangeListEntry last = null;
        while(insert.from>current.from && current.has_next()){
            last = current;
            current = current.next;
        }
        RangeListEntry tmp = current;
        RangeListEntry to_insert = (RangeListEntry) insert;
        if(last == null){
            first = to_insert;
        }else{
            last.next = to_insert;
        }
        to_insert.next = tmp;
        //resolve overlapping
    }
}
