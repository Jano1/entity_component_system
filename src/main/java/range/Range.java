package range;

/**
 * Created by Jan-Frederik Leißner on 15.02.2018.
 */
public class Range {
    int from;
    int to;

    public Range(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public boolean in_range(int test) {
        return !(test < from) && !(test > to);
    }

    public int size() {
        return to - from + 1;
    }

    public boolean mergeable(Range other) {
        return overlapping(other) || neighbouring(other);
    }

    public boolean overlapping(Range other) {
        return in_range(other.from) || in_range(other.to);
    }

    public boolean neighbouring(Range other) {
        Range test = new Range(other.from - 1, other.to + 1);
        return overlapping(test);
    }

    public void merge(Range other) {
        if (mergeable(other)) {
            from = (from <= other.from ? from : other.from);
            to = (to >= other.to ? to : other.to);
        }
    }

    public int increase_from() {
        int old_from = from;
        from += 1;
        return old_from;
    }
}
