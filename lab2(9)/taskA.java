import java.io.*;
import java.util.*;

public class taskA {
    static class Graph {
        private final int V;
        private LinkedList[] adj;

        Graph(int v) {
            V = v;
            adj = new LinkedList[v];
            for (int i = 0; i < v; ++i)
                adj[i] = new LinkedList();
        }

        void addEdge(int v, int w) {
            adj[v].add(w);
        }

        private boolean isCyclicUtil(int i, boolean[] visited, boolean[] recStack) {
            if (recStack[i])
                return true;

            if (visited[i])
                return false;

            visited[i] = true;

            recStack[i] = true;
            List<Integer> children = adj[i];

            for (Integer c: children)
                if (isCyclicUtil(c, visited, recStack))
                    return true;

            recStack[i] = false;

            return false;
        }

        private boolean isCyclic() {

            boolean[] visited = new boolean[V];
            boolean[] recStack = new boolean[V];

            for (int i = 0; i < V; i++)
                if (isCyclicUtil(i, visited, recStack))
                    return true;

            return false;
        }

        void topologicalSortUtil(int v, boolean visited[], Stack<Integer> stack) {
            visited[v] = true;
            Integer i;

            Iterator<Integer> it = adj[v].iterator();
            while (it.hasNext()) {
                i = it.next();
                if (!visited[i])
                    topologicalSortUtil(i, visited, stack);
            }

            stack.push(v);
        }

        void topologicalSort() throws IOException {

            FileWriter writer = new FileWriter("topsort.out");
            if (isCyclic()) {
                writer.write(String.valueOf(-1));
                writer.close();
                return;
            }

            Stack<Integer> stack = new Stack<>();

            boolean[] visited = new boolean[V];
            for (int i = 0; i < V; i++)
                visited[i] = false;

            for (int i = 0; i < V; i++)
                if (!visited[i])
                    topologicalSortUtil(i, visited, stack);

            while (!stack.empty())
                writer.write(stack.pop() + 1 + " ");

            writer.close();
        }
    }

    public static void main(String args[]) throws IOException {
        Scanner reader = new Scanner(new FileReader("topsort.in"));

        int vertices = reader.nextInt(), edges = reader.nextInt();

        Graph graph = new Graph(vertices);

        for (int i = 0; i < edges; i++) {
            graph.addEdge(reader.nextInt() - 1, reader.nextInt() - 1);
        }

        graph.topologicalSort();
        reader.close();
    }
}
