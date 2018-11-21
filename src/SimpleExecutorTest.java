
import ExamplePack.Counter;
import ExamplePack.CounterPlus;
import IntegralPack.Integral;
import mergeSortPack.MergeSort;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

public class SimpleExecutorTest {

    /*
     * Constant Integer numberOfThreads should be equal processor core number.
     * Here it is named as NOFTHREADS, and it is the same for all examples.
     * */
    private static final int NOFTHREADS = Runtime.getRuntime().availableProcessors();

    /*
     * Constant Integer numberOfTasksToEnqueue should (but not have) be equal or greater,
     * then numberOfThreads. Here it is named as NOFTASKS, and it is 25 times greater,
     * then numberOfThreads.
     * */
    private static final int NOFTASKS = 5 * NOFTHREADS;

    /*
     * Main method. Contains all examples calls.
     * */
    public static void main(String[] args) {
        simpleExample();
        integralExample();
        mergeSortExample();
    }

    /*
     * mergeSortExample() method creates, and fills integer array with random values.
     * Then, it creates the new instance of ForkJoinPool() class, with numberOfThreads and
     * invokes mergeSortPack.MergeSort() class as a result returns to a console sorted array of Integers.
     * */
    private static void mergeSortExample() {
        var array = new int[NOFTASKS];
        var rand = new Random();
        for (var i = 0; i < NOFTASKS; i++) array[i] = rand.nextInt(NOFTASKS);

        System.out.println(Arrays.toString(
                new ForkJoinPool(NOFTHREADS).invoke(new MergeSort(array))
        ));
        System.out.println("Finished all threads in mergeSortExample()");
    }

    /*
     * integralExample() method creates thread pools using
     * Executors.newFixedThreadPool(NOFTHREADS) method and sets integral parameters then
     * executes enqueued in IntegralPack.Integral.callableTasks tasks by using Executors.submit()
     * method and sum partial integral results into local variable. After that, the
     * Executors.shutdown() method is called. This method makes the executor accept no new
     * threads and finishes all existing threads in the queue. Next, the while loop
     * waits for Executors.isTerminated() to be true and then the result is printed in a
     * console.
     * */
    private static void integralExample() {
        var result = 0.;
        var executor = Executors.newFixedThreadPool(NOFTHREADS);
        var integral = new Integral(0., Math.PI, Math.PI / NOFTASKS, NOFTASKS * 100);

        for (var element : integral.getCallableTasks())
            try {
                result += executor.submit(element).get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        executor.shutdown();

        while (!executor.isTerminated()) {
        }
        System.out.println(result);
        System.out.println("Finished all threads in integralExample()");
    }

    /*
     * simpleExample() method is almost the same as integralExample() is, except that
     * the called ExamplePack.CounterPlus class uses Runnable interface, so Executors.execute() is
     * called, witch accepts only single Runnable command instead of a
     * ArrayList<IntegralPack.IntegralCallable> class.
     * */
    private static void simpleExample() {
        var executor = Executors.newFixedThreadPool(NOFTHREADS);
        var counter = new Counter();

        for (var i = 0; i < 50; i++)
            executor.execute(new CounterPlus(counter));

        executor.shutdown();
        while (!executor.isTerminated()) {
        }

        System.out.println("Finished all threads in simpleExample()");
        System.out.format("\nCounter_1: %d, Counter_2 %d\n\n",
                counter.get_c1(), counter.get_c2());
    }
} 
