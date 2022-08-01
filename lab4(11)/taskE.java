import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class taskE {
    FastScanner in;
    PrintWriter out;
    int[][] graph;
    boolean[] used;
    int n;
    int[] prev;
    long[] dist;
    ArrayList<Edge> edgeList;


    static class Edge {
        int from;
        int to;
        int weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    void cycle() {
        Arrays.fill(dist, Long.MAX_VALUE);
        Arrays.fill(prev, -1);
        dist[0] = 0;
        int start = -1;
        for (int i = 0; i < n - 1; i++) {
            start = -1;
            for (Edge edge : edgeList) {
                int from = edge.from;
                int to = edge.to;
                int weight = edge.weight;
                if (dist[from] + weight < dist[to]) {
                    dist[to] = dist[from] + weight;
                    prev[to] = from;
                    start = to;
                }
            }
        }
        if (start == -1) {
            out.print("NO");
        } else {
            out.println("YES");
            ArrayList<Integer> path = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                start = prev[start];
            }
            int cur = start;
            while (true) {
                path.add(cur + 1);
                if (cur == start && path.size() != 1) {
                    break;
                }
                cur = prev[cur];
            }
            out.println(path.size());
            Collections.reverse(path);
            for (Integer integer : path) {
                out.print(integer + " ");
            }

        }

    }


    private void solve() throws IOException {
        n = in.nextInt();
        prev = new int[n];
        graph = new int[n][n];
        used = new boolean[n];
        dist = new long[n];
        edgeList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = in.nextInt();
                edgeList.add(new Edge(i, j, graph[i][j]));
                if (i == j && graph[i][j] < 0) {
                    out.println("YES");
                    out.println("2");
                    int k = i + 1;
                    out.print(k + " " + k);
                    return;
                }
            }
        }
        cycle();

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
        in = new FastScanner(new FileInputStream("negcycle.in"));
        out = new PrintWriter(new FileOutputStream("negcycle.out"));
        solve();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new taskE().run();
    }
}