import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class taskA {
    FastScanner in;
    PrintWriter out;
    int[][] graph;
    boolean[] used;
    int n;

    long short_path(int start, int finish) {
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[start] = 0;

        while (true) {
            int v = -1;
            for (int i = 0; i < n; i++)
                if (!used[i] && dist[i] < Long.MAX_VALUE && (v == -1 || dist[v] > dist[i]))
                    v = i;
            if (v == -1) break;
            used[v] = true;
            for (int to = 0; to < n; to++)
                if (!used[to] && graph[v][to] != -1)
                    dist[to] = min(dist[to], dist[v] + graph[v][to]);

        }
        return dist[finish];
    }

    private void solve() throws IOException {
        n = in.nextInt();
        graph = new int[n][n];
        used = new boolean[n];
        int start = in.nextInt();
        int finish = in.nextInt();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = in.nextInt();
            }
        }
        long ans = short_path(start - 1, finish - 1);
        if (ans != Long.MAX_VALUE)
            out.println(ans);
        else
            out.println(-1);

    }

    static class FastScanner {
        StringTokenizer st;
        BufferedReader br;

        FastScanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        String next() throws IOException {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

    }

    private void run() throws IOException {
        in = new FastScanner(new FileInputStream("pathmgep.in"));
        out = new PrintWriter(new FileOutputStream("pathmgep.out"));
        solve();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new taskA().run();
    }
}