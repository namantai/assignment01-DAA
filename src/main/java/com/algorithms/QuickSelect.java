package com.algorithms;

public class QuickSelect {
    public static int select(int[] a, int k, Metrics metrics) {
        if (a == null || a.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty.");
        }
        if (k < 0 || k >= a.length) {
            throw new IllegalArgumentException("Index k is out of range: " + k);
        }

        int low = 0;
        int high = a.length - 1;

        while (low <= high) {
            int[] pivots = QuickSort.partition3Way(a, low, high, metrics);
            int lt = pivots[0];
            int gt = pivots[1];

            if (k < lt) {
                high = lt - 1;
            } else if (k > gt) {
                low = gt + 1;
            } else {
                return a[k];
            }
        }
        return a[k];
    }
}