package ExamplePack;

public class CounterPlus implements Runnable {

    private Counter counter;

    public CounterPlus(Counter counter) {
        this.counter = counter;
    }

    /* @Override void run()
     * This method overrides abstract void run() method in Runnable interface, to make
     * a new thread while calling this method. In the loop it calls ten times twice
     * ExamplePack.Counter.inc1() and ExamplePack.Counter.inc2().
     * */
    @Override
    public void run() {
        for (var i = 0; i < 10; i++) {
            counter.inc1();
            counter.inc2();
            counter.inc1();
            counter.inc2();
        }
    }
}
