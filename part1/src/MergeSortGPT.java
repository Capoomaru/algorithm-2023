package src;/*
생성일 : 2023/2/21
요청 사항 : make some code which do merge sort in java

This implementation uses a recursive divide-and-conquer approach to sort the array.

The mergeSort method takes in the array to sort and the left and right indices, and recursively sorts the left and right halves of the array before merging them together using the merge method.
The merge method creates a temporary array to hold the values being merged, and then iterates through the left and right halves, comparing and merging values into the original array.

In the main method, an example array is created and printed, and then the mergeSort method is called on it to sort it.
The sorted array is then printed.
 */

public class MergeSortGPT {

    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(arr, left, middle);
            mergeSort(arr, middle + 1, right);
            merge(arr, left, middle, right);
        }
    }

    private static void merge(int[] arr, int left, int middle, int right) {
        int[] temp = new int[arr.length];
        for (int i = left; i <= right; i++) {
            temp[i] = arr[i];
        }
        int i = left;
        int j = middle + 1;
        int k = left;
        while (i <= middle && j <= right) {
            if (temp[i] <= temp[j]) {
                arr[k] = temp[i];
                i++;
            } else {
                arr[k] = temp[j];
                j++;
            }
            k++;
        }
        while (i <= middle) {
            arr[k] = temp[i];
            k++;
            i++;
        }
    }

    public static void main(String[] args) {
        int[] arr = { 12, 11, 13, 5, 6, 7 };
        System.out.println("Given Array");
        printArray(arr);
        mergeSort(arr, 0, arr.length - 1);
        System.out.println("\nSorted array");
        printArray(arr);
    }

    private static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}