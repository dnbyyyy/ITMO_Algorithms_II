import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class taskF {

    static class Node {
        String pathFromStart;
        int upNode;
        int downNode;
        int rightNode;
        int leftNode;

        public Node() {
            pathFromStart = "";
            upNode = -1;
            downNode = -1;
            rightNode = -1;
            leftNode = -1;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(new FileReader("input.txt"));
        FileWriter writer = new FileWriter("output.txt");

        int height = reader.nextInt(), width = reader.nextInt();
        char[][] maze = new char[height][width];

        for (int i = 0; i < height; i++) {
            maze[i] = reader.nextLine().toCharArray();
            System.out.println(Arrays.toString(maze[i]));
        }

        Node[] adjacencyList = new Node[height * width];
        int startNode = 0;
        int finishNode = 0;

        for (int i = 0; i < height; ++i)
        {
            for (int j = 0; j < width; ++j)
            {
                if (maze[i][j] != '#')
                {
                    if (i > 0)
                    {
                        if (maze[i - 1][j] != '#')
                        {
                            adjacencyList[i * width + j].upNode = (i - 1) * width + j;
                        }
                    }
                    if (j < width - 1)
                    {
                        if (maze[i][j + 1] != '#')
                        {
                            adjacencyList[i * width + j].rightNode = i * width + j + 1;
                        }
                    }
                    if (i < height - 1)
                    {
                        if (maze[i + 1][j] != '#')
                        {
                            adjacencyList[i * width + j].downNode = (i + 1) * width + j;
                        }
                    }
                    if (j > 0)
                    {
                        if (maze[i][j - 1] != '#')
                        {
                            adjacencyList[i * width + j].leftNode = i * width + j - 1;
                        }
                    }
                }
                if (maze[i][j] == 'S')
                {
                    startNode = i * width + j;
                }
                if (maze[i][j] == 'T')
                {
                    finishNode = i * width + j;
                }
            }
        }

        boolean[] visited = new boolean[height * width];
        Queue<Integer> connectedNodes = new LinkedList<>();
        connectedNodes.add(startNode);

        while (!connectedNodes.isEmpty()) {
            if (adjacencyList[connectedNodes.peek()].upNode != -1 && !visited[adjacencyList[connectedNodes.peek()].upNode])
            {
                visited[adjacencyList[connectedNodes.peek()].upNode] = true;
                adjacencyList[adjacencyList[connectedNodes.peek()].upNode].pathFromStart = adjacencyList[connectedNodes.peek()].pathFromStart + 'U';
                connectedNodes.add(adjacencyList[connectedNodes.peek()].upNode);
            }
            if (adjacencyList[connectedNodes.peek()].rightNode != -1 && !visited[adjacencyList[connectedNodes.peek()].rightNode])
            {
                visited[adjacencyList[connectedNodes.peek()].rightNode] = true;
                adjacencyList[adjacencyList[connectedNodes.peek()].rightNode].pathFromStart = adjacencyList[connectedNodes.peek()].pathFromStart + 'R';
                connectedNodes.add(adjacencyList[connectedNodes.peek()].rightNode);
            }
            if (adjacencyList[connectedNodes.peek()].downNode != -1 && !visited[adjacencyList[connectedNodes.peek()].downNode])
            {
                visited[adjacencyList[connectedNodes.peek()].downNode] = true;
                adjacencyList[adjacencyList[connectedNodes.peek()].downNode].pathFromStart = adjacencyList[connectedNodes.peek()].pathFromStart + 'D';
                connectedNodes.add(adjacencyList[connectedNodes.peek()].downNode);
            }
            if (adjacencyList[connectedNodes.peek()].leftNode != -1 && !visited[adjacencyList[connectedNodes.peek()].leftNode])
            {
                visited[adjacencyList[connectedNodes.peek()].leftNode] = true;
                adjacencyList[adjacencyList[connectedNodes.peek()].leftNode].pathFromStart = adjacencyList[connectedNodes.peek()].pathFromStart + 'L';
                connectedNodes.add(adjacencyList[connectedNodes.peek()].leftNode);
            }
            connectedNodes.remove();
        }

        if (!adjacencyList[finishNode].pathFromStart.isEmpty()) {
            writer.write(adjacencyList[finishNode].pathFromStart.length() + '\n' + adjacencyList[finishNode].pathFromStart);
        }
        else writer.write("-1");
        reader.close();
        writer.close();
    }
}