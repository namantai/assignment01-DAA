package com.algorithms;

import java.util.Random;

public class QuickSort {
    private static final Random RANDOM = new Random();

    public static void sort(int[] a, Metrics metrics) {
        if (a == null || a.length <= 1) return;
        quickSort(a, 0, a.length - 1, metrics);
    }

    private static void quickSort(int[] a, int low, int high, Metrics metrics) {
        while (low < high) {
            int[] pivots = partition3Way(a, low, high, metrics);
            int lt = pivots[0];
            int gt = pivots[1];

            if (lt - low < high - gt) {
                if (lt - low > 0) {
                    metrics.enterRecursion();
                    quickSort(a, low, lt - 1, metrics);
                    metrics.exitRecursion();
                }
                low = gt + 1;
            } else {
                if (high - gt > 0) {
                    metrics.enterRecursion();
                    quickSort(a, gt + 1, high, metrics);
                    metrics.exitRecursion();
                }
                high = lt - 1;
            }
        }
    }

    public static int[] partition3Way(int[] a, int low, int high, Metrics metrics) {
        int randomIndex = low + RANDOM.nextInt(high - low + 1);
        swap(a, low, randomIndex);
        int pivot = a[low];

        int lt = low;
        int gt = high;
        int i = low + 1;

        while (i <= gt) {
            metrics.incrementComparisons();
            if (a[i] < pivot) {
                swap(a, lt++, i++);
            } else {
                metrics.incrementComparisons();
                if (a[i] > pivot) {
                    swap(a, i, gt--);
                } else {
                    i++;
                }
            }
        }
        return new int[]{lt, gt};
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}