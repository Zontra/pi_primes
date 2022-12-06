package primeFinder;

public class PrimeChecker implements Runnable {

    private final long candidate;
    private final PrimeFinder finder;

    public PrimeChecker(long candidate, PrimeFinder finder) {
        this.candidate = candidate;
        this.finder = finder;
    }

    @Override
    public void run() {
        if (isPrimeWithDelay()) {
            finder.addprime(candidate);
        }
    }

    public boolean isPrimeWithDelay() {
        boolean isPrime = true;
        int tmp = (int) Math.ceil(Math.sqrt(candidate));

        for (int i = 2; i <= tmp; i++) {
            if (candidate % i == 0) {
                isPrime = false;
                break;
            }
            try {
                Thread.sleep(finder.getDelay());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (candidate <= 1) {
            isPrime = false;
        }

        if (candidate == 2) {
            isPrime = true;
        } else if (candidate % 2 == 0) {
            isPrime = false;
        }



        return isPrime;
    }
}
