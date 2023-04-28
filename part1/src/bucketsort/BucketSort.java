package part1.bucketsort;

import part1.AbstractSort;
import part1.countingsort.CountingSort;

import java.util.Arrays;

public class BucketSort extends AbstractSort {

    private static int[][] makeBucket(int[] originList, int bucketSize, int bucketCnt) {
        int[][] bucketList = new int[bucketCnt][originList.length];
        int[] itemCnt = new int[bucketCnt];
        int maxRange = bucketCnt * bucketSize;
        for (int[] bucket : bucketList)
            Arrays.fill(bucket, maxRange);

        for (int item : originList) {
            int index = item / bucketSize;
            bucketList[index][itemCnt[index]++] = item;
        }

        for(int[] bucket : bucketList) {
            for(int item : bucket) {
                System.out.print(item + " ");
            }
            System.out.println();
        }

        return bucketList;
    }


    public static int[] sort(int[] list, int bucketSize, int bucketCnt) {
        int[][] bucketList = makeBucket(list, bucketSize, bucketCnt);

        for (int i=0;i<bucketCnt;i++) {
            bucketList[i] = CountingSort.sort(bucketList[i], (i+1) * bucketSize);
            for(int item : bucketList[i]) {
                System.out.print(item + " ");
            }
            System.out.println();
        }


        return new int[]{0};

    }

    public static void main(String[] args) {
        int[] list = {10, 4, 5, 8, 1, 8, 3, 6};
        sort(list, 10, 5);
    }
}
