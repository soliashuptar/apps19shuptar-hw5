package ua.edu.ucu.iterable;

import ua.edu.ucu.function.IntPredicate;

import java.util.Iterator;

public class FilterIterable implements Iterable<Integer> {
    private Iterable<Integer> iterable;
    private IntPredicate predicate;

    public FilterIterable(Iterable<Integer> iterable, IntPredicate predicate) {
        this.iterable = iterable;
        this.predicate = predicate;

    }

    @Override
    public Iterator<Integer> iterator() {
        Iterator<Integer> parent = iterable.iterator();
        return new Iterator<Integer>() {
            private Integer nextVal;

            @Override
            public boolean hasNext() {
                if (parent.hasNext()) {
                    nextVal = parent.next();
                    if (predicate.test(nextVal)) {
                        return true;
                    }
                }
                while (parent.hasNext()) {
                    nextVal = parent.next();

                    if (predicate.test(nextVal)) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public Integer next() {
                return nextVal;
            }
        };
    }
}

