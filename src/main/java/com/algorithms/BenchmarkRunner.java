package com.algorithms;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class BenchmarkRunner {
    public static void main(String[] args) {
        int[] sizes = {1000, 10000, 100000, 1000000};
        String[] algorithms = {"MergeSort", "QuickSort"};
        String[] inputTypes = {"random", "sorted", "duplicates"};
        int runs = 5;

        try (FileWriter writer = new FileWriter("results.csv")) {
            writer.append("algorithm,input,n,time_ms,comparisons,max_depth\n");

            for (String algo : algorithms) {
                for (String inputType : inputTypes) {
                    for (int n : sizes) {
                        long[] times = new long[runs];
                        long[] comps = new long[runs];
                        long[] depths = new long[runs];

                        for (int r = 0; r < runs; r++) {
                            int[] original = generateInput(n, inputType);
                            Metrics metrics = new Metrics();

                            long startTime = System.nanoTime();
                            if (algo.equals("MergeSort")) {
                                MergeSort.sort(original, metrics);
                            } else if (algo.equals("QuickSort")) {
                                QuickSort.sort(original, metrics);
                            }
                            long endTime = System.nanoTime();

                            times[r] = (endTime - startTime) / 1_000_000;
                            comps[r] = metrics.getComparisons();
                            depths[r] = metrics.getMaxDepth();
                        }

                        Arrays.sort(times);
                        Arrays.sort(comps);
                        Arrays.sort(depths);

                        writer.append(String.format("%s,%s,%d,%d,%d,%d\n",
                                algo, inputType, n, times[runs / 2], comps[runs / 2], depths[runs / 2]));
                    }
                }
            }
            System.out.println("Benchmark finished. results.csv generated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int[] generateInput(int n, String type) {
        int[] arr = new int[n];
        Random rnd = new Random(42);
        for (int i = 0; i < n; i++) {
            if (type.equals("random")) {
                arr[i] = rnd.nextInt();
            } else if (type.equals("sorted")) {
                arr[i] = i;
            } else if (type.equals("duplicates")) {
                arr[i] = rnd.nextInt(10);
            }
        }
        return arr;
    }
}
