package range;

/**
 * Created by Jan-Frederik Leißner on 15.02.2018.
 */
public class RangeList {

    private int lowest, highest;

    public RangeListEntry first;

    public RangeList(int lowest, int highest) {
        this.lowest = lowest;
        this.highest = highest;
        first = new RangeListEntry(lowest, highest);
    }

    public boolean has_first(){
        return first != null;
    }

    public void insert_range(Range insert) {
        RangeListEntry to_insert = RangeListEntry.from_range(insert);
        //inserting
        if (first == null) {
            first = to_insert;
        } else {
            RangeListEntry current = first;
            RangeListEntry last = null;
            while (insert.from > current.from && current.has_next()) {
                last = current;
                current = current.next;
            }
            RangeListEntry tmp = current;
            if (last == null) {
                first = to_insert;
            } else {
                last.next = to_insert;
            }
            to_insert.next = tmp;
            //resolve overlapping right
            resolve_overlapping_right(to_insert, current);
            //resolve overlapping left
            resolve_overlapping_left(to_insert, last);
        }

    }

    private void resolve_overlapping_left(RangeListEntry start, RangeListEntry last) {
        if(last != null && last.mergeable(start)){
            last.merge(start);
            last.next = start.next;
        }
    }

    private void resolve_overlapping_right(RangeListEntry start, RangeListEntry next) {
        while (start.mergeable(next)) {
            start.merge(next);
            start.next = next.next;
            next = next.next;
        }
    }

    @Override
    public String toString() {
        RangeListEntry current = first;
        String content = "{" + current.toString();
        while (current.has_next()) {
            current = current.next;
            content += current.toString();
        }
        content += "}";
        return content;
    }
}
