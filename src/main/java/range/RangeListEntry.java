package range;

/**
 * Created by Jan-Frederik Leißner on 15.02.2018.
 */
public class RangeListEntry extends Range {
    public RangeListEntry next;

    public RangeListEntry(int from, int to) {
        super(from, to);
    }

    public boolean has_next(){
        return next!=null;
    }
}
