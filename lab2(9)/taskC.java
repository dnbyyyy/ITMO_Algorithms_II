import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class taskC{

    static class Edge {
        int src, dst;

        Edge(int s, int d){
            src = s;
            dst = d;
        }
    }

    static boolean isBipartite(int V, ArrayList<ArrayList<Integer>> adj) {
        int[] col = new int[V];
        Arrays.fill(col, -1);

        Queue<Edge> q = new LinkedList<>();

        for (int i = 0; i < V; i++) {

            if (col[i] == -1) {

                q.add(new Edge(i, 0));
                col[i] = 0;

                while (!q.isEmpty()) {
                    Edge p = q.peek();
                    q.poll();

                    int v = p.src;

                    int c = p.dst;

                    for (int j : adj.get(v)) {
                        if (col[j] == c)
                            return false;

                        if (col[j] == -1) {
                            col[j] = (c==1) ? 0 : 1;
                            q.add(new Edge(j, col[j]));
                        }
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {

        Scanner reader = new Scanner(new FileReader("bipartite.in"));
        FileWriter writer = new FileWriter("bipartite.out");

        int V = reader.nextInt(), E = reader.nextInt();

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for(int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            int src = reader.nextInt() - 1, dst = reader.nextInt() - 1;
            adj.get(src).add(dst);
            adj.get(dst).add(src);
        }

        if (isBipartite(V, adj))
            writer.write("YES");
        else
            writer.write("NO");

        reader.close();
        writer.close();
    }
}

