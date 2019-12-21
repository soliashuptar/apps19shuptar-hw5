package ua.edu.ucu.iterable;

import ua.edu.ucu.function.IntToIntStreamFunction;
import ua.edu.ucu.stream.AsIntStream;

import java.util.Iterator;

public class FlatMapIterable implements Iterable<Integer> {
    private Iterable<Integer> iterable;
    private IntToIntStreamFunction func;

    public FlatMapIterable(Iterable<Integer> iterable,
                           IntToIntStreamFunction func) {
        this.iterable = iterable;
        this.func = func;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new FlatMapIterator();
    }

    private class FlatMapIterator implements Iterator<Integer> {
        private Iterator<Integer> parent = iterable.iterator();
        private Iterator<Integer> tempIter;

        @Override
        public boolean hasNext() {
            if (tempIter != null && tempIter.hasNext()) {
                return true;
            }

            while (parent.hasNext()) {
                tempIter = ((AsIntStream) func.applyAsIntStream(
                        parent.next())).iterable.iterator();
                return tempIter.hasNext();
            }
            return false;
        }

        @Override
        public Integer next() {
            return tempIter.next();
        }
    }
}
