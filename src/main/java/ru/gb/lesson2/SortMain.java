package ru.gb.lesson2;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class SortMain {

    public static void main(String[] args) {
        int[] array = IntStream.generate(() -> ThreadLocalRandom.current().nextInt(1000))
                .limit(100_000)
                .toArray();

        timer("Bubble sort random array", array, SortMain::bubbleSort);
        timer("Selection sort random array", array, SortMain::selectionSort);
        timer("Insertion sort random array", array, SortMain::insertionSort);
        System.out.println();

        array = IntStream.range(0, 100_000).toArray();
        timer("Bubble sort sorted array", array, SortMain::bubbleSort);
        timer("Selection sort sorted array", array, SortMain::selectionSort);
        timer("Insertion sort sorted array", array, SortMain::insertionSort);
        System.out.println();

        array = IntStream.range(0, 100_000)
                .boxed()
                .sorted(Collections.reverseOrder())
                .mapToInt(Integer::valueOf)
                .toArray();
        timer("Bubble sort reversed array", array, SortMain::bubbleSort);
        timer("Selection sort reversed array", array, SortMain::selectionSort);
        timer("Insertion sort reversed array", array, SortMain::insertionSort);
    }

    private static void timer(String algorithmName, int[] array, Consumer<int[]> algorithm) {
        long start = System.currentTimeMillis();
        algorithm.accept(Arrays.copyOf(array, array.length));
        long end = System.currentTimeMillis();
        System.out.println(algorithmName + ": " + (end - start));
    }

    private static void bubbleSort(int[] array) {
        boolean sorted = false;
        for (int i = 0; i < array.length && !sorted; i++) {
            sorted = true;
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j + 1] < array[j]) {
                    sorted = false;

                    int tmp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = tmp;
                }
            }
        }
    }

    private static void selectionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int min = array[i];
            int minPosition = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < min) {
                    min = array[j];
                    minPosition = j;
                }
            }

            array[minPosition] = array[i];
            array[i] = min;
        }
    }

    private static void insertionSort(int[] array) {
        // 1 3 4 5 7 4 -> 1 3 4 5 5 7 -> 1 3 4 5 7
        // i = 4
        //
        for (int i = 1; i < array.length; i++) {
            int curr = array[i];
            int j = i;
            while (j > 0 && curr < array[j - 1]) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = curr;
        }
    }

}