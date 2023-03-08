package src.countingsort;

import src.AbstractSort;

public class CountingSort extends AbstractSort {

    public static int[] sort(int[] list, int size) {
        int[] indexList = new int[size];
        int[] sortedList = new int[list.length];

        for (int key : list)
            indexList[key] += 1;

        //카운팅된 숫자 출력 구문
        /*System.out.println("각 자리의 빈도수");
        for (int value : indexList)
            System.out.print(value + " ");
        System.out.println();*/

        for (int i = 1; i < size; i++)
            indexList[i] += indexList[i - 1];

        //각 키의 시작 인덱스 출력 구문
        /*System.out.println("각 키의 시작 인덱스");
        for (int value : indexList)
            System.out.print(value + " ");
        System.out.println();*/

        /*
        뒤에서 부터 조회하는 이유는 stable을 유지하기 위함
        indexList에서 index는 해당 value가 마지막으로 등장하는 index임
        => 이 문제를 해결하기 위해서는 count 이후 덧셈작업을 하여 C' 배열을 생성할 때 다른 방향을 적용해야함

        indexList[list.length - 1] = list.length - indexList[list.length - 1];
        for (int i = list.length - 2; i > 0; i--)
            indexList[i] = indexList[i + 1] - indexList[i];

        for (int i = 0; i < list.length; i++)
            sortedList[indexList[list[i]]++] = list[i];
        */
        for (int i = list.length - 1; i >= 0; i--)
            sortedList[--indexList[list[i]]] = list[i];

        return sortedList;
    }

    public static void main(String[] args) {
        int[] list = {10, 4, 5, 8, 1, 8, 3, 6};

        System.out.println("Given Array");
        show(list);

        int[] sortedList = sort(list, 11);

        System.out.println("\nSorted array");
        show(sortedList);
    }

    protected static void show(int[] list) {
        for (int value : list) {
            System.out.print(value + " ");
        }
    }
}
