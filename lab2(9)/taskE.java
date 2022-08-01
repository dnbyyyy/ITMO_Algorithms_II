import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class taskE {
    static ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
    static int[] color;
    static ArrayList<Integer> sort = new ArrayList<>();

    static void DFS(int index) {
        color[index] = 1;

        for (int i = 0; i < adjList.get(index).size(); i++) {
            if (color[adjList.get(index).get(i)] == 0) DFS(adjList.get(index).get(i));
        }

        color[index] = 2;
        sort.add(index);
    }

    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(new FileReader("hamiltonian.in"));
        FileWriter writer = new FileWriter("hamiltonian.out");
        int vertices = reader.nextInt(), edges = reader.nextInt();
        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
        }
        color = new int[vertices];
        for (int i = 0; i < edges; i++) {
            adjList.get(reader.nextInt() - 1).add(reader.nextInt() - 1);
        }
        for (int i = 0; i < vertices; i++) {
            if (color[i] != 2) DFS(i);
        }
        boolean flag = false;
        int tmp1 = 0, tmp2;
        if (!sort.isEmpty()) {
            tmp1 = sort.get(sort.size() - 1);
            sort.remove(sort.size() - 1);
            flag = true;
        }
        while (!sort.isEmpty() && flag) {
            tmp2 = sort.get(sort.size() - 1);
            sort.remove(sort.size() - 1);
            flag = false;
            for (int i = 0; i < adjList.get(tmp1).size(); i++) {
                if (adjList.get(tmp1).get(i) == tmp2) {
                    flag = true;
                    break;
                }
            }
            tmp1 = tmp2;
        }
        if (flag) writer.write("YES");
        else writer.write("NO");
        reader.close();
        writer.close();
    }
}