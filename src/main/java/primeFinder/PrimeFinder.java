package primeFinder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;
import java.util.stream.Stream;

public class PrimeFinder {

    private final Collection<Long> primes = new Vector<>();
    private List<Thread> threads = new Vector<>();
    private int delay;
    private int from;
    private int to;

    public PrimeFinder() {
    }

    public PrimeFinder(int delay) {
        this.delay = delay;
    }

    public PrimeFinder(int delay, int from, int to) {
        this.delay = delay;
        this.from = from;
        this.to = to;
    }

    public int countRunningCheckers() {
        int counter = 0;
        for (Thread thread : threads) {
            if (thread.isAlive()) {
                counter++;
            }
        }

        return counter;
    }

    /**
     * Teilt alle Zahlen in [from, to[ auf Threads auf,
     * welche die Primalität der jeweiligen Zahl bestimmen
     */
    public void findPrimes() {
        for (int i = from; i < to; i++) {
            Thread t = new Thread(new PrimeChecker(i, this));
            threads.add(t);
            t.start();
        }
        new Daemon(this).start();
    }

    public Stream<Long> getPrimes() {
        return primes.stream().sorted();
    }

    public void addprime(long prime) {
        primes.add(prime);
    }

    public int getDelay() {
        return delay;
    }

    public static void main(String[] args) {
        PrimeFinder finder = new PrimeFinder(100, 1, 1000);
        finder.findPrimes();
    }
}
