package src.selectionsort;

import src.AbstractSort;

public class SelectionSort extends AbstractSort {
    public static void sort(Comparable[] list) {
        for (int i = 0; i < list.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < list.length; j++) {
                if (AbstractSort.less(list[j], list[minIndex]))
                    minIndex = j;
            }
            AbstractSort.exchange(list, i, minIndex);
        }

        assert AbstractSort.isSorted(list);
    }

}
