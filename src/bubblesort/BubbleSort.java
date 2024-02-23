package bubblesort;

import java.util.Random;

/**
 *
 * @author klaus
 */
public class BubbleSort {

    public static void sort(int[] values) {
        boolean swapped;
        int n = values.length;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (values[j] > values[j + 1]) {
                    // Swap values[j] and values[j+1]
                    int temp = values[j];
                    values[j] = values[j + 1];
                    values[j + 1] = temp;
                    swapped = true;
                }
            }
            // If no two elements were swapped in the inner loop, the array is already sorted
            if (!swapped) {
                break;
            }
            // Print the current state of the array after each pass
            System.out.print("Iteration " + (i + 1) + ": ");
            for (int k = 0; k < n; k++) {
                System.out.print(values[k] + " ");
            }
            System.out.println();
        }
    }

    public static int[] randomValues(int howMany) {
        int[] result = new int[howMany];
        Random random = new Random();
        for (int i = 0; i < howMany; i++) {
            result[i] = random.nextInt() % (10 * howMany);
        }
        return result;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // How many values to generate
        int numValues = 30;
        // Whether to print results. Only use with small numbers of values.
        boolean printResults = true;

        int[] a = randomValues(numValues);
        if (printResults) {
            System.out.print("Before sorting: ");
            for (int i = 0; i < numValues; i++) {
                System.out.print(a[i] + " ");
            }
            System.out.println();
        }
        long start = System.currentTimeMillis();
        sort(a);
        long now = System.currentTimeMillis();
        double elapsed = (now - start) / 1000.0;
        if (printResults) {
            System.out.print("After sorting: ");
            for (int i = 0; i < numValues; i++) {
                System.out.print(a[i] + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Elapsed time = " + elapsed + " seconds");
    }
}
