package IntegralPack;

import java.util.ArrayList;
import java.util.List;

public class Integral {
    private List<IntegralCallable> callableTasks = new ArrayList<>();

    /*
     * The constructor adds to the ArrayList<IntegralPack.IntegralCallable> callableTasks list, integral
     * parts to be computed. This also contains additional parameter (beside points if
     * integral (x0, xk) and step (dx) ) witch allows to get more accurate result.
     * */
    public Integral(double x0, double xk, double dx, double acc) {
        for (var i = x0; i < xk - dx; i += dx)
            this.callableTasks.add(new IntegralCallable(i, i + dx, dx / acc));
    }

    public List<IntegralCallable> getCallableTasks() {
        return callableTasks;
    }
}

