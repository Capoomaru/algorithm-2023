package part1.shellsort;

import part1.AbstractSort;

public class ShellSort extends AbstractSort {

    public static void sort(Comparable[] list) {
        int size = list.length;
        int h = 1;

        while (h < size / 3) // 해당 식이 나온 이유는 h가 size 보다 커지는 것을 방지하기 위함
            h = h * 3 + 1; // 리스트 크기에 적당한 크기를 생성

        while (h >= 1) {
            for (int i = h; i < size; i++) {
                for (int j = i; j >= h && less(list[j], list[j - h]); j-=h) {
                    exchange(list, j, j - h);
                }
            }
            h/=3; //근사치 활용 => 실제로는 h = (h-1) / 3
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

