import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class taskD {

    static ArrayList<Vertex> vertices;

    static int currentComponent = 1;

    static class Vertex {

        int vertexNum;
        int componentNum;

        public Vertex(int vertexNum) {
            this.vertexNum = vertexNum;
        }

    }

    static class Graph {
        int V;
        ArrayList<ArrayList<Integer>> adjList;

        public Graph(int v) {
            V = v;
            adjList = new ArrayList<>();
            for (int i = 0; i < v; i++) {
                adjList.add(new ArrayList<>());
            }
        }

        void addEdge(int src, int dst) {
            adjList.get(src).add(dst);
            adjList.get(dst).add(src);
        }

        void DFS(int v, boolean[] visited) {
            visited[v] = true;
            vertices.get(v).componentNum = currentComponent;
            for (int x : adjList.get(v)) {
                if (!visited[x]) DFS(x, visited);
            }
        }

        void connectedComponents() {
            boolean[] visited = new boolean[V];
            for (int v = 0; v < V; v++) {
                if (!visited[v]) {
                    DFS(v, visited);
                    currentComponent++;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(new FileReader("components.in"));
        FileWriter writer = new FileWriter("components.out");

        int n = reader.nextInt(), m = reader.nextInt();
        vertices = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            vertices.add(new Vertex(i + 1));
        }

        Graph graph = new Graph(n);

        for (int i = 0; i < m; i++) {
            graph.addEdge(reader.nextInt() - 1, reader.nextInt() - 1);
        }

        graph.connectedComponents();

        writer.write(String.valueOf(currentComponent - 1) + '\n');

        for (int i = 0; i < n; i++) {
            writer.write(vertices.get(i).componentNum + " ");
        }

        reader.close();
        writer.close();
    }
}
