package ua.edu.ucu;

import org.junit.Test;
import ua.edu.ucu.stream.AsIntStream;
import ua.edu.ucu.stream.IntStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

//import static junit.framework.TestCase.assertEquals;

public class AsIntStreamTest {

    @Test
    public void testOf() {
        int[] intArr = {-1, 0, 1, 2, 3};
        IntStream intStream = AsIntStream.of(intArr);
        assertArrayEquals(intStream.toArray(), intArr);
    }

    @Test
    public void testAverage() {
        int[] intArr = {-1, 0, 1, 2, 3};
        IntStream intStream = AsIntStream.of(intArr);
        assertEquals(java.util.Optional.of(1.0), java.util.Optional.of(intStream.average()));
    }

    @Test
    public void testMax() {
        int[] intArr = {-1, 0, 1, 2, 3};
        IntStream intStream = AsIntStream.of(intArr);
        assertEquals(java.util.Optional.of(3), java.util.Optional.of(intStream.max()));
    }

    @Test
    public void testMin() {
        int[] intArr = {-1, 0, 1, 2, 3};
        IntStream intStream = AsIntStream.of(intArr);
        assertEquals(java.util.Optional.of(-1), java.util.Optional.of(intStream.min()));
    }

    @Test
    public void testCount() {
        int[] intArr = {-1, 0, 1, 2, 3};
        IntStream intStream = AsIntStream.of(intArr);
        assertEquals(5, intStream.count());
    }

    @Test
    public void testSum() {
        int[] intArr = {-1, 0, 1, 2, 3};
        IntStream intStream = AsIntStream.of(intArr);
        assertEquals(java.util.Optional.of(5), java.util.Optional.of(intStream.sum()));
    }

    @Test
    public void testFilter() {
        int[] intArr = {-1, 0, 1, 2, 3};
        IntStream intStream = AsIntStream.of(intArr);

        assertEquals(java.util.Optional.of(2), java.util.Optional.of(intStream.filter(x -> x <= 2).max()));
    }

    @Test
    public void testFilter_2() {
        int[] intArr = {-1, 0, 1, 2, 3};
        IntStream intStream = AsIntStream.of(intArr);

        assertEquals(java.util.Optional.of(0), java.util.Optional.of(intStream.filter(x -> x <= 2 && x >= 0).min()));
    }

    @Test
    public void testMap() {
        int[] intArr = {-1, 0, 1, 2, 3};
        IntStream intStream = AsIntStream.of(intArr);

        assertEquals(java.util.Optional.of(-2), java.util.Optional.of(intStream.map(x -> x * 2).min()));
    }

    @Test
    public void testFlatMap() {
        int[] intArr = {-1, 0, 1, 2, 3};
        IntStream intStream = AsIntStream.of(intArr);

        assertEquals(java.util.Optional.of(5), java.util.Optional.of(intStream.flatMap(x -> AsIntStream.of(x - 1, x, x + 2)).max()));
    }

    @Test
    public void testReduce() {
        int[] intArr = {-1, 0, 1, 2, 3};
        IntStream intStream = AsIntStream.of(intArr);

        assertEquals(java.util.Optional.of(10), java.util.Optional.of(intStream.map(x -> x * 2).reduce(0, (sum, x) -> sum += x)));

    }

    @Test
    public void testToArray() {
        int[] intArr = {-1, 0, 1, 2, 3};
        IntStream intStream = AsIntStream.of(intArr);

        assertArrayEquals(new int[]{2, 4, 6}, intStream.map(x -> x * 2).filter(x -> x > 0).toArray());
    }

    @Test
    public void testForEach() {
        StringBuilder str = new StringBuilder();
        int[] intArr = {-1, 0, 1, 2, 3};
        IntStream intStream = AsIntStream.of(intArr);
        intStream.map(x -> x * 2).filter(x -> x > 0).forEach(x -> str.append(x));

        assertEquals("246", str.toString());
    }

}
