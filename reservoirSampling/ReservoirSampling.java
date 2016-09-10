import java.util.Random;

/**
 * Fill up the k slots in a reservoir with even distribution.
 */

public class ReservoirSampling {
    private int[] reservoir;

    public ReservoirSampling(int k, int[] input) {
        reservoir = new int[k];
        Random random = new Random();

        for (int i = 0; i < input.length; i++) {
            if (i < k) {
                reservoir[i] = input[i];
            } else {
                int n = random.nextInt(i + 1);
                if (n < k) {
                    reservoir[n] = input[i];
                }
            }
        }
    }
}
