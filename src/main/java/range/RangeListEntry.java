package range;

/**
 * Created by Jan-Frederik Leißner on 15.02.2018.
 */
public class RangeListEntry extends Range {
    protected RangeListEntry next;

    public RangeListEntry(int from, int to) {
        super(from, to);
    }

    public boolean has_next() {
        return next != null;
    }

    @Override
    public String toString() {
        return "[" + from + "-" + to + "]";
    }

    public static RangeListEntry from_range(Range source) {
        return new RangeListEntry(source.from, source.to);
    }
}
