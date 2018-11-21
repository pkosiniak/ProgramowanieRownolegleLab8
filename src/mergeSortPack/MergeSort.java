package mergeSortPack;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

public class MergeSort extends RecursiveTask<int[]> {

    private int[] arrayToSort;

    public MergeSort(int[] arrayToSort) {
        this.arrayToSort = arrayToSort;
    }

    /* @Override int[] compute()
     * This method overrides RecursiveTask<> class abstract method. It divides arrayToSort
     * field in to two mergeSortPack.MergeSort classes, and then, using ForkJoinTask class, creates two
     * new asynchronously execute tasks using fork() method. Due to this fact it waits for
     * the result, by using join() method. After merge in mergeArray() method calls this
     * returns (partly) sorted Integer array.
     * */
    @Override
    protected int[] compute() {

        System.out.println(Arrays.toString(arrayToSort));
        if (arrayToSort.length <= 1)
            return this.arrayToSort;
        var task1 = new MergeSort(Arrays.copyOfRange(arrayToSort, 0, arrayToSort.length / 2));
        var task2 = new MergeSort(Arrays.copyOfRange(arrayToSort, arrayToSort.length / 2, arrayToSort.length));

        task1.fork();
        task2.fork();

        var arr1 = task1.join();
        var arr2 = task2.join();

        mergeArray(arr1, arr2);

        return this.arrayToSort;
    }


    private void mergeArray(int[] arr1, int[] arr2) {

        int i = 0, j = 0, k = 0;

        for (; (i < arr1.length) && (j < arr2.length); k++)
            if (arr1[i] < arr2[j]) arrayToSort[k] = arr1[i++];
            else arrayToSort[k] = arr2[j++];

        if (i == arr1.length)
            for (var a = j; a < arr2.length; a++) arrayToSort[k++] = arr2[a];
        else
            for (var a = i; a < arr1.length; a++) arrayToSort[k++] = arr1[a];
    }
}