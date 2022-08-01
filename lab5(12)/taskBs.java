import java.io.*;
import java.util.*;

public class taskBs {
    FastScanner in;
    PrintWriter out;
    int n;
    int m;
    int[] match;
    ArrayList<Integer>[] graph;
    boolean[] visited;
    boolean[] used;

    boolean kyn(int from) {
        if (visited[from])
            return false;
        visited[from] = true;
        for (int i = 0; i < graph[from].size(); i++) {
            int to = graph[from].get(i);
            if (match[to] == -1 || kyn(match[to])) {
                match[to] = from;
                return true;
            }
        }
        return false;
    }

    private void solve() throws IOException {
        n = in.nextInt();
        m = in.nextInt();
        int k = in.nextInt();
        graph = new ArrayList[n];
        visited = new boolean[n];
        used = new boolean[n];
        match = new int[m];
        Arrays.fill(match, -1);
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < k; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            graph[from].add(to);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < graph[i].size(); j++) {
                if (match[graph[i].get(j)] == -1) {
                    match[graph[i].get(j)] = i;
                    used[i]=true;
                    break;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if(used[i]){
                continue;
            }
            Arrays.fill(visited, false);
            kyn(i);
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            if (match[i] != -1)
                ans++;
        }
        out.print(ans);
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
        in = new FastScanner(new FileInputStream("matching.in"));
        out = new PrintWriter(new FileOutputStream("matching.out"));
        solve();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new taskBs().run();
    }
}