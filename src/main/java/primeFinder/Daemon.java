package primeFinder;

public class Daemon extends Thread {

    private final PrimeFinder finder;

    public Daemon(PrimeFinder finder) {
        this.finder = finder;
        setDaemon(true);
        setPriority(MAX_PRIORITY);
    }

    @Override
    public void run() {
        synchronized (this) {
            while (finder.countRunningCheckers() > 0) {
                System.out.println("Active Checkers: " + finder.countRunningCheckers());
                System.out.print("[");
                finder.getPrimes().forEach(n -> System.out.print(n + " "));
                System.out.println("]");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
