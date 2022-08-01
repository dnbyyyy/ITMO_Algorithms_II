import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.LinkedList;

public class taskD {
    static int currentComponent = 1;
    static int[] verticesList;

    static class Graph {
        private final int V;
        private final LinkedList<Integer>[] adj;

        Graph(int v) {
            V = v;
            adj = new LinkedList[v];
            for (int i = 0; i < v; ++i)
                adj[i] = new LinkedList<>();
        }

        void addEdge(int v, int w) {
            adj[v].add(w);
        }

        void DFSUtil(int v, boolean[] visited) {
            visited[v] = true;
            verticesList[v] = currentComponent;

            int n;

            for (Integer integer : adj[v]) {
                n = integer;
                if (!visited[n])
                    DFSUtil(n, visited);
            }
        }

        Graph getTranspose() {
            Graph g = new Graph(V);
            for (int v = 0; v < V; v++) {
                for (Integer integer : adj[v]) g.adj[integer].add(v);
            }
            return g;
        }

        void fillOrder(int v, boolean[] visited, Stack<Integer> stack) {
            visited[v] = true;

            for (int n : adj[v]) {
                if (!visited[n])
                    fillOrder(n, visited, stack);
            }

            stack.push(v);
        }

        void printSCCs() {
            Stack<Integer> stack = new Stack<>();

            boolean[] visited = new boolean[V];
            for (int i = 0; i < V; i++)
                visited[i] = false;

            for (int i = 0; i < V; i++)
                if (!visited[i])
                    fillOrder(i, visited, stack);

            Graph gr = getTranspose();

            for (int i = 0; i < V; i++)
                visited[i] = false;

            while (!stack.empty()) {
                int v = stack.pop();

                if (!visited[v]) {
                    gr.DFSUtil(v, visited);
                    currentComponent++;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(new FileReader("cond.in"));
        FileWriter writer = new FileWriter("cond.out");
        int vertices = reader.nextInt(), edges = reader.nextInt();
        Graph graph = new Graph(vertices);
        for (int i = 0; i < edges; i++) {
            graph.addEdge(reader.nextInt() - 1, reader.nextInt() - 1);
        }
        verticesList = new int[vertices];
        graph.printSCCs();
        writer.write(String.format("%d\n", currentComponent - 1));
        for (int i = 0; i < vertices; i++) {
            writer.write(verticesList[i] + " ");
        }
        reader.close();
        writer.close();
    }
}

