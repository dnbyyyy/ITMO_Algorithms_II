import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class taskC {
    FastScanner in;
    PrintWriter out;
    ArrayList<Pair>[] graph;
    boolean[] used;
    int n;
    long[] dist;
    PriorityQueue queue = new PriorityQueue(1000000);

    static class Pair {
        int to;
        int weight;

        Pair(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    void short_path() {
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;
        queue.push(0, 0);
        Pair p;
        while (!queue.isEmpty()) {
            p = queue.extractMin();
            int v = p.to;
            int weight = p.weight;
            if (weight > dist[v])
                continue;
            for (int i = 0; i < graph[v].size(); i++) {
                int to = graph[v].get(i).to;
                int weight_to = graph[v].get(i).weight;
                if (dist[v] + weight_to < dist[to]) {
                    dist[to] = dist[v] + weight_to;
                    queue.push(to, weight_to);
                }
            }

        }
    }

    private void solve() throws IOException {
        n = in.nextInt();
        int m = in.nextInt();
        graph = new ArrayList[n];
        used = new boolean[n];
        dist = new long[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        int a, b, weight;
        for (int i = 0; i < m; i++) {
            a = in.nextInt() - 1;
            b = in.nextInt() - 1;
            weight = in.nextInt();
            graph[a].add(new Pair(b, weight));
            graph[b].add(new Pair(a, weight));
        }
        short_path();
        for (int i = 0; i < n; i++) {
            out.print(dist[i] + " ");
        }

    }

    static class PriorityQueue {
        Pair[] arr;
        int t = -1;

        PriorityQueue(int size) {
            this.arr = new Pair[size];
        }

        void sift_down(Pair[] arr, int n, int i) {
            int largest = i;
            int l = 2 * i + 1;
            int r = 2 * i + 2;
            if (l < n && arr[l].weight < arr[largest].weight)
                largest = l;

            if (r < n && arr[r].weight < arr[largest].weight)
                largest = r;
            if (largest != i) {
                Pair swap = arr[i];
                arr[i] = arr[largest];
                arr[largest] = swap;
                sift_down(arr, n, largest);
            }
        }

        void sift_up(int i) {
            while (i > 0 && arr[i].weight < arr[(i - 1) / 2].weight) {
                Pair swap = arr[i];
                arr[i] = arr[(i - 1) / 2];
                arr[(i - 1) / 2] = swap;
                i = (i - 1) / 2;
            }
        }



        Pair extractMin() {
            Pair out = arr[0];
            arr[0] = new Pair(arr[t].to, arr[t].weight);
            arr[t] = null;
            t--;
            if (t >= 0) {
                sift_down(arr, t + 1, 0);
            }
            return out;
        }

        void push(int to, int weight) {
            t++;
            arr[t] = new Pair(to, weight);
            sift_up(t);
        }

        boolean isEmpty() {
            return t < 0;
        }
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
        in = new FastScanner(new FileInputStream("pathbgep.in"));
        out = new PrintWriter(new FileOutputStream("pathbgep.out"));
        solve();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new taskC().run();
    }
}