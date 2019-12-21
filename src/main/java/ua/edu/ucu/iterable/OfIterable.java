package ua.edu.ucu.iterable;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class OfIterable implements Iterable<Integer> {

    private int[] elements;
//    private int i = 0;

    public OfIterable(int[] elements) {
        this.elements = elements;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private int i = 0;

            @Override
            public boolean hasNext() {
                return elements.length > i;
            }

            @Override
            public Integer next() {
                if (hasNext()) {
                    return elements[i++];
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }
}

//
//    @Override
//    public Iterator<Integer> iterator() {
//        return new Iterable<Integer>() {
//            @Override
//            public Iterator<Integer> iterator() {
//                return new OfIterator(elements);
//            }
//        };
//    }
//}
