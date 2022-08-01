import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class taskF {
    static ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
    static int[] parity;
    static boolean[] visited;

    static void DFS(int index) {
        boolean status = false;
        visited[index] = true;

        for (int i : adjList.get(index)) {
            if (!visited[i]) DFS(i);
            if (parity[i] == 0) status = true;
        }
        parity[index] = status ? 1 : 0;
    }

    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(new FileReader("game.in"));
        FileWriter writer = new FileWriter("game.out");

        int vertices = reader.nextInt(), edges = reader.nextInt(), start = reader.nextInt();
        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
        }
        parity = new int[vertices];
        visited = new boolean[vertices];
        for (int i = 0; i < edges; i++) {
            adjList.get(reader.nextInt() - 1).add(reader.nextInt() - 1);
        }
        DFS(start - 1);
        if (parity[start - 1] == 1) writer.write("First player wins");
        else writer.write("Second player wins");
        reader.close();
        writer.close();
    }
}
