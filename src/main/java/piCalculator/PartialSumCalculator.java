package piCalculator;

import java.util.stream.IntStream;

public class PartialSumCalculator implements Runnable {

    private int min;
    private int max;
    private double sum;

    public PartialSumCalculator(int min, int max) {
        if (min < 0) {
            throw new IllegalArgumentException("Minimum < 0");
        }
        this.min = min;
        this.max = max;
        ;
    }

    @Override
    public void run() {
        for (int i = min; i <= max; i++) {
            sum += Math.pow(-1, i) / (2 * i + 1);
        }
    }

    public double getSum() {
        return sum;
    }
}