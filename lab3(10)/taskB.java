import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class taskB {
    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(new FileReader("spantree.in"));
        FileWriter writer = new FileWriter("spantree.out");
        int points = reader.nextInt();
        int[][] graph = new int[points][points];
        int[] minE = new int[points], x = new int[points], y = new int[points];
        Arrays.fill(minE, Integer.MAX_VALUE);
        boolean[] used = new boolean[points];
        double result = 0;
        for (int i = 0; i < points; i++) {
            x[i] = reader.nextInt();
            y[i] = reader.nextInt();
        }
        for (int i = 0; i < points; i++) {
            for (int j = 0; j < points; j++) {
                graph[i][j] = (x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j]);
            }
        }
        minE[0] = 0;
        for (int i = 0; i < points; i++) {
            int v = -1;
            for (int j = 0; j < points; j++) {
                if (!used[j] && (v == -1 || minE[j] < minE[v])) v = j;
            }
            used[v] = true;
            for (int j = 0; j < points; j++) {
                if (!used[j] && graph[v][j] < minE[j] && v != j) minE[j] = graph[v][j];
            }
        }
        for (int i = 0; i < points; i++) {
            result += Math.sqrt(minE[i]);
        }
        writer.write(String.format("%.10f", result));
        reader.close();
        writer.close();
    }
}
