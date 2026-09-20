package com.algorithms;

public class MergeSort {
    public static void sort(int[] a, Metrics metrics) {
        if (a == null || a.length <= 1) return;
        int[] buffer = new int[a.length];
        metrics.enterRecursion();
        sortInternal(a, 0, a.length - 1, buffer, metrics);
        metrics.exitRecursion();
    }

    private static void sortInternal(int[] a, int left, int right, int[] buffer, Metrics metrics) {
        if (right - left <= 15) {
            insertionSort(a, left, right, metrics);
            return;
        }
        int mid = left + (right - left) / 2;

        metrics.enterRecursion();
        sortInternal(a, left, mid, buffer, metrics);
        metrics.exitRecursion();

        metrics.enterRecursion();
        sortInternal(a, mid + 1, right, buffer, metrics);
        metrics.exitRecursion();

        merge(a, left, mid, right, buffer, metrics);
    }

    private static void insertionSort(int[] a, int left, int right, Metrics metrics) {
        for (int i = left + 1; i <= right; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= left) {
                metrics.incrementComparisons();
                if (a[j] > key) {
                    a[j + 1] = a[j];
                    j--;
                } else {
                    break;
                }
            }
            a[j + 1] = key;
        }
    }

    private static void merge(int[] a, int left, int mid, int right, int[] buffer, Metrics metrics) {
        for (int i = left; i <= right; i++) {
            buffer[i] = a[i];
        }

        int i = left;
        int j = mid + 1;
        for (int k = left; k <= right; k++) {
            if (i > mid) {
                a[k] = buffer[j++];
            } else if (j > right) {
                a[k] = buffer[i++];
            } else {
                metrics.incrementComparisons();
                if (buffer[j] < buffer[i]) {
                    a[k] = buffer[j++];
                } else {
                    a[k] = buffer[i++];
                }
            }
        }
    }
}
