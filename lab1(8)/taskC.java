import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class taskC {
    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(new FileReader("input.txt"));
        FileWriter writer = new FileWriter("output.txt");

        int n = reader.nextInt(), m = reader.nextInt();
        int[][] matrix = new int[n][n];

        for (int i = 0; i < m; i++) {
            int pointA = reader.nextInt(), pointB = reader.nextInt();
            if (matrix[pointA - 1][pointB - 1] != 1 && matrix[pointB - 1][pointA - 1] != 1) matrix[pointA - 1][pointB - 1] = 1;
            else {
                writer.write("YES");
                reader.close();
                writer.close();
                return;
            }
        }

        writer.write("NO");

        reader.close();
        writer.close();
    }
}
