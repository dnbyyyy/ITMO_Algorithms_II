import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class taskE{

    static int MAXS = 500;

    static int MAXC = 26;

    static int []out = new int[MAXS];

    static int []f = new int[MAXS];

    static int [][]g = new int[MAXS][MAXC];


    static void buildMatchingMachine(String[] arr, int k) {
        Arrays.fill(out, 0);

        for(int i = 0; i < MAXS; i++)
            Arrays.fill(g[i], -1);

        int states = 1;

        for(int i = 0; i < k; ++i) {
            String word = arr[i];
            int currentState = 0;

            for(int j = 0; j < word.length(); ++j) {
                int ch = word.charAt(j) - 'a';

                if (g[currentState][ch] == -1)
                    g[currentState][ch] = states++;

                currentState = g[currentState][ch];
            }

            out[currentState] |= (1 << i);
        }

        for(int ch = 0; ch < MAXC; ++ch)
            if (g[0][ch] == -1)
                g[0][ch] = 0;

        Arrays.fill(f, -1);

        Queue<Integer> q = new LinkedList<>();

        for(int ch = 0; ch < MAXC; ++ch) {

            if (g[0][ch] != 0) {
                f[g[0][ch]] = 0;
                q.add(g[0][ch]);
            }
        }

        while (!q.isEmpty()) {

            int state = q.peek();
            q.remove();

            for(int ch = 0; ch < MAXC; ++ch) {

                if (g[state][ch] != -1) {

                    int failure = f[state];

                    while (g[failure][ch] == -1)
                        failure = f[failure];

                    failure = g[failure][ch];
                    f[g[state][ch]] = failure;

                    out[g[state][ch]] |= out[failure];

                    q.add(g[state][ch]);
                }
            }
        }
    }

    static int findNextState(int currentState, char nextInput) {
        int answer = currentState;
        int ch = nextInput - 'a';

        while (g[answer][ch] == -1)
            answer = f[answer];

        return g[answer][ch];
    }

    static void searchWords(String[] arr, int k, String text) throws IOException {

        FileWriter writer = new FileWriter("search4.out");

        buildMatchingMachine(arr, k);

        int currentState = 0;

        for(int i = 0; i < text.length(); ++i) {
            currentState = findNextState(currentState, text.charAt(i));

            if (out[currentState] == 0)
                continue;

            for(int j = 0; j < k; ++j) {
                if ((out[currentState] & (1 << j)) > 0) {
                    writer.write("YES\n");
                    System.out.print("Word " + arr[j] +
                            " appears from " +
                            (i - arr[j].length() + 1) +
                            " to " + i + "\n");
                }
                else writer.write("NO\n");
            }
        }
        writer.close();
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new FileReader("search4.in"));
        int n = in.nextInt();
        String[] data = new String[n];
        for (int i = 0; i < n; i++) {
            data[i] = in.nextLine();
        }
        String text = in.nextLine();
        int k = data.length;

        searchWords(data, k, text);
    }
}
