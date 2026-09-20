package com.algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Random;

public class SortingTest {

    @Test
    void testMergeSortCorrectness() {
        Random rnd = new Random();
        for (int i = 0; i < 100; i++) {
            int[] arr = rnd.ints(500, -1000, 1000).toArray();
            int[] expected = arr.clone();
            Arrays.sort(expected);

            Metrics metrics = new Metrics();
            MergeSort.sort(arr, metrics);
            assertArrayEquals(expected, arr);
        }
    }

    @Test
    void testQuickSortCorrectness() {
        Random rnd = new Random();
        for (int i = 0; i < 100; i++) {
            int[] arr = rnd.ints(500, -1000, 1000).toArray();
            int[] expected = arr.clone();
            Arrays.sort(expected);

            Metrics metrics = new Metrics();
            QuickSort.sort(arr, metrics);
            assertArrayEquals(expected, arr);
        }
    }

    @Test
    void testQuickSortDepthOnSorted() {
        int n = 100000;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = i;

        Metrics metrics = new Metrics();
        QuickSort.sort(arr, metrics);

        int maxAllowedDepth = (int) (2 * Math.ceil(Math.log(n) / Math.log(2)));
        assertTrue(metrics.getMaxDepth() <= maxAllowedDepth,
                "Depth exceeded limit: " + metrics.getMaxDepth());
    }

    @Test
    void testQuickSelect() {
        Random rnd = new Random();
        for (int i = 0; i < 100; i++) {
            int[] arr = rnd.ints(200, -500, 500).toArray();
            int[] sorted = arr.clone();
            Arrays.sort(sorted);

            int k = rnd.nextInt(arr.length);
            Metrics metrics = new Metrics();
            int result = QuickSelect.select(arr, k, metrics);
            assertEquals(sorted[k], result);
        }
    }

    @Test
    void testEdgeCases() {
        Metrics metrics = new Metrics();

        int[] empty = {};
        assertDoesNotThrow(() -> MergeSort.sort(empty, metrics));
        assertDoesNotThrow(() -> QuickSort.sort(empty, metrics));
        assertThrows(IllegalArgumentException.class, () -> QuickSelect.select(empty, 0, metrics));

        int[] single = {42};
        MergeSort.sort(single, metrics);
        assertArrayEquals(new int[]{42}, single);

        int[] equal = {5, 5, 5, 5, 5};
        QuickSort.sort(equal, metrics);
        assertArrayEquals(new int[]{5, 5, 5, 5, 5}, equal);
    }
}