import java.util.*;

public class taskA{

    static void printLIS(Vector<Integer> arr) {
        System.out.println(arr.size());
        for (int x : arr)
            System.out.print(x + " ");
        System.out.println();
    }

    static void constructPrintLIS(int[] arr, int n) {
        Vector<Integer>[] L = new Vector[n];
        for (int i = 0; i < L.length; i++)
            L[i] = new Vector<>();

        L[0].add(arr[0]);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if ((arr[i] > arr[j]) && (L[i].size() < L[j].size() + 1))
                    L[i] = (Vector<Integer>) L[j].clone();
            }

            L[i].add(arr[i]);
        }

        Vector<Integer> max = L[0];

        for (Vector<Integer> x : L)
            if (x.size() > max.size())
                max = x;

        printLIS(max);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        constructPrintLIS(arr, n);
    }
}

