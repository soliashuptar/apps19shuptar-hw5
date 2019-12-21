package ua.edu.ucu.iterable;
import ua.edu.ucu.function.IntUnaryOperator;

import java.util.Iterator;

public class MapIterable implements Iterable<Integer>{
    private Iterable<Integer> iterable;
    private IntUnaryOperator mapper;

    public MapIterable(Iterable<Integer> iterable, IntUnaryOperator mapper) {
        this.iterable = iterable;
        this.mapper = mapper;

    }

    @Override
    public Iterator<Integer> iterator() {
        return new MapIterator();
    }

    private class MapIterator implements Iterator<Integer> {
        Iterator<Integer> parent = iterable.iterator();
        private Integer nextVal;

        @Override
        public boolean hasNext() {
            return parent.hasNext();
        }

        @Override
        public Integer next() {
            nextVal = parent.next();
            return mapper.apply(nextVal);
        }
    }
}
