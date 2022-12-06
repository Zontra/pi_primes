package piCalculator;

public class PiCalculator {

    public static double calcPiWithNThreads(int limit, int threadCnt) {
        double pi = 0;

        if (threadCnt < 1) {
            throw new IllegalArgumentException("Thread count < 1");
        }

        for (int i = 0; i < threadCnt; i++) {
            int min = i * limit / threadCnt;
            int max = (i + 1) * limit / threadCnt;

            if (i != 0) {
                min++;
            }

            PartialSumCalculator psc = new PartialSumCalculator(min, max);
            Thread t = new Thread(psc);
            t.start();

            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread-" + i + ": [" + min + ", " + max + "] => " + (max-min+1));
            pi += psc.getSum();
        }

        System.out.println("Pi: " + pi * 4);
        return pi*4;
    }

    public static void main(String[] args) {
        calcPiWithNThreads(99999, 7);
    }
}
