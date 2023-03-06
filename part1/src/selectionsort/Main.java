package src.selectionsort;

public class Main {
    public static void main(String[] args) {
            Integer[] arr = { 12, 11, 13, 5, 6, 7 };
            System.out.println("Given Array");
            printArray(arr);
            SelectionSort.sort(arr);
            System.out.println("\nSorted array");
            printArray(arr);


    }
    private static void printArray(Integer[] arr) {
        for (Integer integer : arr) {
            System.out.print(integer + " ");
        }
    }
}
