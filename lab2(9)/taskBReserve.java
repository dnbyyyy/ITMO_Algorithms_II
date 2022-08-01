import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class taskBReserve {
    static int vertices, edges;
    static ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
    static int[] color, previous;
    static int start, end;

    static boolean DFS(int u) {
        color[u] = 1;
        for (int i = 0; i < adjList.get(u).size(); i++) {
            int v = adjList.get(u).get(i);
            if (color[v] == 0) {
                previous[v] = u;
                if (DFS(v)) return true;
            }
            if (color[v] == 1) {
                start = v;
                end = u;
                return true;
            }
        }
        color[u] = 2;
        return false;
    }

    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(new FileReader("cycle.in"));
        FileWriter writer = new FileWriter("cycle.out");
        vertices = reader.nextInt();
        edges = reader.nextInt();
        color = new int[vertices];
        previous = new int[vertices];
        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int i = 0; i < edges; i++) {
            adjList.get(reader.nextInt() - 1).add(reader.nextInt() - 1);
        }
        start = -1;
        for (int i = 0; i < vertices; i++) {
            if (DFS(i)) break;
        }
        if (start == -1) {
            writer.write("NO");
        } else {
            writer.write("YES\n");
            ArrayList<Integer> cycle = new ArrayList<>();
            cycle.add(start);
            for (int i = end; i != start; i = previous[i]) {
                cycle.add(i);
            }
            for (int i = cycle.size() - 1; i >= 0; i--) {
                writer.write(String.format("%d ", cycle.get(i) + 1));
            }
        }
        reader.close();
        writer.close();
    }
}
