import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class taskB {
    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(new FileReader("input.txt"));
        FileWriter writer = new FileWriter("output.txt");

        int n = reader.nextInt();
        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = reader.nextInt();
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j && matrix[i][j] == 1) {
                    writer.write("NO");
                    reader.close();
                    writer.close();
                    return;
                }
                if (matrix[i][j] != matrix[j][i]) {
                    writer.write("NO");
                    reader.close();
                    writer.close();
                    return;
                }
            }
        }

        writer.write("YES");

        reader.close();
        writer.close();
    }
}
