package ua.edu.ucu.stream;

import ua.edu.ucu.function.*;
import ua.edu.ucu.iterable.FilterIterable;
import ua.edu.ucu.iterable.FlatMapIterable;
import ua.edu.ucu.iterable.MapIterable;
import ua.edu.ucu.iterable.OfIterable;

import java.util.Arrays;

public class AsIntStream implements IntStream {

    public Iterable<Integer> iterable;


    private AsIntStream(Iterable<Integer> iterable) {
        this.iterable = iterable;
    }

    public static IntStream of(int... values) {
        return new AsIntStream(new OfIterable(values));
    }

    @Override
    public Double average() {
        return (double) sum() / count();
    }

    @Override
    public Integer max() {
        int maxVal = 0;
        System.out.println(Arrays.toString(toArray()));
        for (Integer i : iterable) {
            if (i > maxVal) {
                maxVal = i;
            }
        }
        return maxVal;
    }

    @Override
    public Integer min() {
        int minVal = Integer.MAX_VALUE;
        for (Integer i : iterable) {
            if (i < minVal) {
                minVal = i;
            }
        }
        return minVal;
    }

    @Override
    public long count() {
        return reduce(0, (sum, x) -> sum += 1);
    }

    @Override
    public Integer sum() {
        int sumVal = 0;
        for (Integer i : iterable) {
            sumVal += i;
        }
        return sumVal;
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        return new AsIntStream(new FilterIterable(iterable, predicate));
    }

    @Override
    public void forEach(IntConsumer action) {
        for (Integer i : iterable) {
            action.accept(i);
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        return new AsIntStream(new MapIterable(iterable, mapper));
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        return new AsIntStream(new FlatMapIterable(iterable, func));
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        int reduceVal = identity;
        for (Integer number : iterable) {
            reduceVal = op.apply(reduceVal, number);
        }

        return reduceVal;
    }

    @Override
    public int[] toArray() {
        int[] arr = new int[(int) count()];
        int index = 0;
        for (Integer i : iterable) {
            arr[index] = i;
            index += 1;
        }
        return arr;

    }

}
