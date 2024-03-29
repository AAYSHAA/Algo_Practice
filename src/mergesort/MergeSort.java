package mergesort;

import java.util.Random;

/**
 *
 * @author klaus
 */
public class MergeSort {

    // Merge (sorted) ranges values[first]...values[mid] and values[mid+1]...values[last]
    private static void mergeRanges(int[] values, int first, int mid, int last) {
        int n1 = mid - first + 1;
        int n2 = last - mid;

        // Create temporary arrays
        int[] left = new int[n1];
        int[] right = new int[n2];

        // Copy data to temporary arrays left[] and right[]
        for (int i = 0; i < n1; ++i) {
            left[i] = values[first + i];
        }
        for (int j = 0; j < n2; ++j) {
            right[j] = values[mid + 1 + j];
        }

        // Merge the temporary arrays back into values[first]...values[last]
        int i = 0, j = 0;
        int k = first;
        while (i < n1 && j < n2) {
            if (left[i] <= right[j]) {
                values[k] = left[i];
                i++;
            } else {
                values[k] = right[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of left[], if any
        while (i < n1) {
            values[k] = left[i];
            i++;
            k++;
        }

        // Copy remaining elements of right[], if any
        while (j < n2) {
            values[k] = right[j];
            j++;
            k++;
        }
    }

    // Auxiliary recursive function
    // Sorts the range values[first]...values[last]
    private static void sortRange(int[] values, int first, int last, int iteration) {
        if (last > first) { // Otherwise there is nothing to do (single value)
            int mid = (first + last) / 2;
            sortRange(values, first, mid, iteration + 1); // Recursively sort first half
            sortRange(values, mid + 1, last, iteration + 1); // Recursively sort second half
            mergeRanges(values, first, mid, last); // Merge sorted halves
            printIteration(values, first, last, iteration);
        }
    }

    private static void printIteration(int[] values, int first, int last, int iteration) {
        System.out.print("Iteration " + iteration + ": ");
        for (int i = first; i <= last; i++) {
            System.out.print(values[i] + " ");
        }
        System.out.println();
    }

    public static void sort(int[] values) {
        sortRange(values, 0, values.length - 1, 0);
    }

    public static int[] randomValues(int howMany) {
        int[] result = new int[howMany];
        Random random = new Random();
        for (int i = 0; i < howMany; i++)
            result[i] = random.nextInt() % (10 * howMany);
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
            for (int i = 0; i < numValues; i++)
                System.out.print(a[i] + " ");
            System.out.println();
        }
        long start = System.currentTimeMillis();
        sort(a);
        long now = System.currentTimeMillis();
        double elapsed = (now - start) / 1000.0;
        if (printResults) {
            System.out.print("After sorting: ");
            for (int i = 0; i < numValues; i++)
                System.out.print(a[i] + " ");
            System.out.println();
        }
        System.out.println();
        System.out.println("Elapsed time = " + elapsed + " seconds");
    }
}
