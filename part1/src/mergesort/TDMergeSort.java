package part1.mergesort;

import part1.AbstractSort;

public class TDMergeSort extends AbstractSort {

    private static void merge(Comparable[] origin, Comparable[] replica, int lo, int mid, int hi) {
        System.arraycopy(origin, lo, replica, lo, hi - lo + 1);

        int leftIndex = lo;
        int rightIndex = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (leftIndex > mid) origin[k] = replica[rightIndex++]; // 왼쪽 배열의 원소가 모두 들어간 이후에는 오른쪽 배열의 원소를 계속 꺼내도록
            else if (rightIndex > hi) origin[k] = replica[leftIndex++];  // 오른쪽 배열의 원소가 모두 들어간 이후에는 왼쪽 배열의 원소를 계속 꺼내도록
            else if (less(replica[leftIndex], replica[rightIndex])) origin[k] = replica[leftIndex++];
            else origin[k] = replica[rightIndex++];
        }

    }

    public static void sort(Comparable[] list) {
        Comparable[] aux = new Comparable[list.length];
        sort(list, aux, 0, list.length - 1);
    }

    private static void sort(Comparable[] list, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) return;

        int mid = lo + (hi - lo) / 2;
        sort(list, aux, lo, mid);
        sort(list, aux, mid + 1, hi);
        merge(list, aux, lo, mid, hi);
    }

    public static void main(String[] args) {
        Character[] list = {'M','E','R','G','E','S','O','R','T','E','X','A','M','P','L','E'};


        sort(list);
        for(Character i : list)
            System.out.print(i + " ");
    }
}
