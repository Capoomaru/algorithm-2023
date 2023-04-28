package part1.mergesort;

import part1.AbstractSort;

public class BUMergeSort extends AbstractSort {

    private static void merge(Comparable[] dst, Comparable[] src, int lo, int mid, int hi) {

        int leftIndex = lo;
        int rightIndex = mid + 1;

        for (int k = lo; k <= hi; k++) {
            if (leftIndex > mid) dst[k] = src[rightIndex++]; // 왼쪽 배열의 원소가 모두 들어간 이후에는 오른쪽 배열의 원소를 계속 꺼내도록
            else if (rightIndex > hi) dst[k] = src[leftIndex++];  // 오른쪽 배열의 원소가 모두 들어간 이후에는 왼쪽 배열의 원소를 계속 꺼내도록
            else if (less(src[leftIndex], src[rightIndex])) dst[k] = src[leftIndex++];
            else dst[k] = src[rightIndex++];
        }

    }

    public static void sort(Comparable[] origin) {
        Comparable[] list = origin;
        Comparable[] result = new Comparable[list.length];
        for (int step = 2; (step / 2) < list.length; step *= 2) {
            for (int i = 0; i < list.length; i += step)
                merge(result, list, i, i + step / 2 - 1, Math.min(i + step - 1, list.length - 1));
            Comparable[] tmp = list;
            list = result;
            result = tmp;
        }
        if (list != origin) System.arraycopy(list, 0, origin, 0, origin.length);

    }

    public static void main(String[] args) {
        Character[] list = {'M', 'E', 'R', 'G', 'E', 'S', 'O', 'R', 'T', 'E', 'X', 'A', 'M', 'P', 'L', 'E'};

        sort(list);
        for (Character i : list)
            System.out.print(i + " ");

    }

}
