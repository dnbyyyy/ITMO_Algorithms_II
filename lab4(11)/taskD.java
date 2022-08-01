import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class taskD {
    FastScanner in;
    PrintWriter out;
    ArrayList<Pair>[] graph;
    boolean[] used;
    int n;
    long[] dist;
    ArrayList<Edge> edgeList;

    static class Pair {
        int to;
        int weight;

        Pair(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static class Edge {
        int from;
        int to;
        long weight;

        Edge(int from, int to, long weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    void short_path(int s) {
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[s] = 0;
        for (int i = 0; i < n - 1; i++) {
            for (Edge edge : edgeList) {
                int from = edge.from;
                int to = edge.to;
                long weight = edge.weight;
                if (dist[from] != Long.MAX_VALUE && dist[from] + weight < dist[to]) {
                    dist[to] = dist[from] + weight;
                }
            }
        }
        String[] ans = new String[n];
        for (int i = 0; i < n - 1; i++) {
            for (Edge edge : edgeList) {
                int from = edge.from;
                int to = edge.to;
                long weight = edge.weight;
                if (dist[from] != Long.MAX_VALUE && dist[from] + weight < dist[to]) {
                    dist[to] = (long) (-9 * 1e18);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (dist[i] <= -9*1e18) {
                ans[i] = "-";
            } else if (dist[i] == Long.MAX_VALUE) {
                ans[i] = "*";
            } else {
                ans[i] = String.valueOf(dist[i]);
            }
        }
        for (int i = 0; i < n; i++) {
            out.println(ans[i]);
        }
    }

    private void solve() throws IOException {
        n = in.nextInt();
        int m = in.nextInt();
        int s = in.nextInt();
        graph = new ArrayList[n];
        used = new boolean[n];
        dist = new long[n];
        edgeList = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            long weight = in.nextLong();
            edgeList.add(new Edge(from, to, weight));
        }
        short_path(s - 1);
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

        long nextLong() throws IOException {
            return Long.parseLong(next());
        }

    }

    private void run() throws IOException {
        in = new FastScanner(new FileInputStream("path.in"));
        out = new PrintWriter(new FileOutputStream("path.out"));
        solve();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new taskD().run();
    }
}