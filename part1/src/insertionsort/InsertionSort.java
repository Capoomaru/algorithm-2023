package src.insertionsort;


import src.AbstractSort;

public class InsertionSort extends AbstractSort {

    public static void sort(Comparable[] list) {
        for (int i = 1; i < list.length; i++) {
            for (int j = i; j > 0 && InsertionSort.less(list[j], list[j - 1]); j--) {
                exchange(list, j, j - 1);
            }
        }
    }

    public static void main(String[] args) {
        Integer[] list = {12, 11, 13, 5, 6, 7};

        System.out.println("Given Array");
        show(list);

        sort(list);

        System.out.println("\nSorted array");
        show(list);
    }
}
