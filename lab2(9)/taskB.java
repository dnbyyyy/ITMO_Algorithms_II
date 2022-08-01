import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class taskB {

    static int[] colors, previous;
    static Stack<Integer> path = new Stack<>();

    static class Graph {
        private final int V;
        private LinkedList<Integer>[] adj;

        Graph(int v) {
            V = v;
            adj = new LinkedList[v];
            for (int i = 0; i < v; ++i)
                adj[i] = new LinkedList<>();
        }

        void addEdge(int v, int w) {
            adj[v].add(w);
        }

        void DFS(int v) {
            path.push(v);
            colors[v] = 1;
            for (int i = 0; i < adj[v].size(); i++) {
                if (colors[adj[v].get(i)] == 0) DFS(adj[v].get(i));
                if (colors[adj[v].get(i)] == 1) {
                    System.out.println("YES");
                    int j = -1;
                    while (!path.empty() || j != adj[v].get(i)) {
                        j = path.pop();
                        System.out.println(j);
                    }
                }
                colors[adj[v].get(i)] = 2;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(new FileReader("cycle.in"));
        FileWriter writer = new FileWriter("cycle.out");
        int vertices = reader.nextInt(), edges = reader.nextInt();
        Graph graph = new Graph(vertices);
        for (int i = 0; i < edges; i++) {
            graph.addEdge(reader.nextInt() - 1, reader.nextInt() - 1);
        }
        colors = new int[vertices];
        for (int i = 0; i < vertices; i++) {
            if (colors[i] == 0) graph.DFS(i);
        }
        reader.close();
        writer.close();
    }
}
