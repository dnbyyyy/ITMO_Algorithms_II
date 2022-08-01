import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class taskA {
    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(new FileReader("input.txt"));
        FileWriter writer = new FileWriter("output.txt");
        int vertices = reader.nextInt(), edges = reader.nextInt();
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int i = 0; i < edges; i++) {
            int src = reader.nextInt() - 1, dst = reader.nextInt() - 1;
            adjList.get(src).add(dst);
            adjList.get(dst).add(src);
        }
        for (int i = 0; i < vertices; i++) {
            writer.write(String.format("%d ", adjList.get(i).size()));
        }
        reader.close();
        writer.close();
    }
}
