package src;

public abstract class AbstractSort {
    public static void sort(Comparable[] list) {

    }

    protected static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    protected static void exchange(Comparable[] list, int i, int j) {
        Comparable t = list[i];
        list[i] = list[j];
        list[j] = t;
    }

    protected static void compareTo(Comparable o1) {

    }

    protected static void show(Comparable[] list) {
        for (Comparable a : list) // == for (int i=0;i<list.length;i++)
            System.out.print(a + " "); // == System.out.print(list[i] + " ")
        System.out.println();
    }

    protected static boolean isSorted(Comparable[] list) {
        for (int i=1;i<list.length;i++)
            if (less(list[i], list[i-1])) return false;
        return true;
    }
}