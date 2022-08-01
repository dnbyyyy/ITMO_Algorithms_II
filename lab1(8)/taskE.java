import java.io.*;
import java.util.*;

public class taskE {
    BufferedReader reader;
    StringTokenizer tokenizer;
    PrintWriter writer;

    class Graph {
        private final int vertices;
        private int edges;
        private ArrayList<Integer>[] adjList;

        public Graph(int V) {
            this.vertices = V; this.edges = 0;
            adjList = (ArrayList<Integer>[]) new ArrayList[V];
            for (int v = 0; v < V; v++) {
                adjList[v] = new ArrayList<>();
            }
        }

        public Graph() throws IOException {
            this(nextInt());
            int E = nextInt();

            for (int i = 0; i < E; i++) {
                int v = nextInt() - 1, w = nextInt() - 1;
                addEdge(v, w);
            }
        }


        public int getVertices() {
            return vertices;
        }

        public void addEdge(int v, int w) {
            adjList[v].add(w);
            adjList[w].add(v);
            edges++;
        }

        public Iterable<Integer> adj(int v) {
            return adjList[v];
        }

        @Override
        public String toString() {
            StringBuilder s = new StringBuilder(String.format("%d vertices, %d edges\n", vertices, edges));
            for (int v = 0; v < vertices; v++) {
                s.append(v).append(": ");
                for (int w : this.adj(v)) {
                    s.append(w).append(" ");
                }
                s.append("\n");
            }
            return s.toString();
        }
    }

    class BreadthFirstClass {
        private boolean[] marked;
        private int[] path;

        public BreadthFirstClass(Graph G) {
            marked = new boolean[G.getVertices()];
            path = new int[G.getVertices()];
            bfs(G, 0);
        }

        private void bfs(Graph G, int s) {
            Queue<Integer> queue = new ArrayDeque<>();
            marked[s] = true;
            path[0] = 0;
            queue.add(s);
            while (!queue.isEmpty()) {
                int v = queue.poll();
                for (int w : G.adj(v)) {
                    if (!marked[w]) {
                        path[w] = path[v] + 1;
                        marked[w] = true;
                        queue.add(w);
                    }
                }
            }
        }

        public String printDistance() {
            StringBuilder builder = new StringBuilder();
            for (int distance : path) {
                builder.append(distance);
                builder.append(' ');
            }
            return builder.toString();
        }
    }

    public static void main(String[] args) {
        String fileName = "pathbge1";
        new taskE().run(String.format("%s.in", fileName), String.format("%s.out", fileName));
    }

    public String nextToken() throws IOException {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            String inputString = reader.readLine();
            if (inputString != null) {
                tokenizer = new StringTokenizer(inputString);
            } else {
                return null;
            }
        }
        return tokenizer.nextToken();
    }

    public int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    public void solve() throws IOException {
        Graph graph = new Graph();

        BreadthFirstClass bfs = new BreadthFirstClass(graph);

        writer.println(bfs.printDistance());
    }

    public void run(String inputFile, String outputFile) {
        try {
            reader = new BufferedReader(new FileReader(inputFile));
            writer = new PrintWriter(outputFile);
            solve();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}