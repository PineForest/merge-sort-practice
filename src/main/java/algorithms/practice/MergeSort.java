package algorithms.practice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * Merge sort solution that allocates a second array. O(n log n) performance, but O(2n) space.
 */
public class MergeSort {
    public static void main(String[] args) throws IOException {
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        while ((line = buffer.readLine()) != null) {
            line = line.trim();
            String[] stringArray = line.split(",");
            int[] array = convert(stringArray);
            System.out.println(Arrays.toString(mergeSort(array)));
        }
    }

    private static int[] mergeSort(int[] array) {
        int[] workingArray = new int[array.length];
        update(workingArray, 0, array.length, array);
        mergeSort(array, 0, array.length, workingArray);
        return array;
    }

    // start is inclusive. end is exclusive
    private static void mergeSort(int[] array, int start, int end, int[] workingArray) {
        int size = end - start;
        if (size >= 2) {
            int middle = size / 2 + start;
            if (size != 2) {
                mergeSort(array, start, middle, workingArray);
                mergeSort(array, middle, end, workingArray);
            }
            merge(array, start, middle, end, workingArray);
        }
        update(array, start, end, workingArray);
    }

    private static void update(int[] array, int start, int end, int[] workingArray) {
        for (int i = start; i < end; ++i) {
            array[i] = workingArray[i];
        }
    }

    private static void merge(int[] array, int start, int middle, int end, int[] workingArray) {
        int left = start;
        int right = middle;
        for (int i = start; i < end; ++i) {
            if (left >= middle && right >= end) {
                break;
            }
            if (left >= middle) {
                workingArray[i] = array[right++];
            } else if (right >= end) {
                workingArray[i] = array[left++];
            } else if (array[left] > array[right]) {
                workingArray[i] = array[right++];
            } else {
                workingArray[i] = array[left++];
            }
        }
    }

    private static int[] convert(String[] stringArray) {
        int[] result = new int[stringArray.length];
        for (int i = 0; i < stringArray.length; ++i) {
            result[i] = Integer.parseInt(stringArray[i]);
        }
        return result;
    }
}
