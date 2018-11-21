package IntegralPack;

import java.util.concurrent.Callable;


public class IntegralCallable implements Callable<Double> {

    private double dx;
    private double x0;
    private int N;

    IntegralCallable(double x0, double xk, double dx) {
        this.x0 = x0;
        this.N = (int) Math.ceil((xk - x0) / dx);
        this.dx = (xk - x0) / N;
        System.out.println("Creating an instance of IntegralPack.IntegralCallable");
        System.out.println("x0 = " + x0 + ", xk = " + xk + ", N = " + N);
        System.out.println("dx requested = " + dx + ", dx final = " + this.dx);
    }

    private double getFunction(double x) {
        return Math.sin(x);
    }

    private double compute() {
        double integral = 0;
        int i;
        for (i = 0; i < N; i++) {
            var x1 = x0 + i * dx;
            var x2 = x1 + dx;
            integral += ((getFunction(x1) + getFunction(x2)) / 2.) * dx;
        }
        return integral;
    }

    /*@Override Double call()
     * This method overrides Callable<> call() method. As a single thread it computes
     * partial IntegralPack.Integral and returns result to the caller.
     * */
    @Override
    public Double call() {
        var result = compute();
        System.out.println("Calka czastkowa: " + result);
        return result;
    }
}