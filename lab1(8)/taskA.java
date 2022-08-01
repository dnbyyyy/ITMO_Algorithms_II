import java.io.*;
import java.util.Scanner;

public class taskA {
    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(new FileReader("input.txt"));
        FileWriter writer = new FileWriter("output.txt");

        int n = reader.nextInt(), m = reader.nextInt();
        int[][] matrix = new int[n][n];

        for (int i = 0; i < m; i++) {
            int pointA = reader.nextInt(), pointB = reader.nextInt();
            matrix[pointA - 1][pointB - 1] = 1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                writer.write(matrix[i][j] + " ");
            }
            writer.write('\n');
        }

        reader.close();
        writer.close();
    }
}
