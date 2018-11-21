package ExamplePack;

public class Counter {

    private long c1 = 0;
    private long c2 = 0;
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    /*
     * This method increments this class variable, with a synchronized lock Object, witch
     * ensures correct computing while being processed.
     * */
    public void inc1() {
        synchronized (lock1) {
            try {
                c1++;
                Thread.sleep(1);
            } catch (InterruptedException ignored) {
            }
        }
    }

    public void inc2() {
        synchronized (lock2) {
            try {
                c2++;
                Thread.sleep(1);
            } catch (InterruptedException ignored) {
            }
        }
    }

    public long get_c1() {
        return (c1);
    }

    public long get_c2() {
        return (c2);
    }
}






