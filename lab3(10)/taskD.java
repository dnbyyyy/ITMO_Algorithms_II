import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class taskD {

    static class Pair {
        long first, second;

        public Pair(long first, long second) {
            this.first = first;
            this.second = second;
        }
    }

    boolean bfs(ArrayList<ArrayList<Pair>> graph, long start) {
        HashSet<Long> data = new HashSet<>();
        data.add(start);
        Queue<Long> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            long current = queue.peek();
            queue.remove();
            for (long i = 0; i < graph.get((int) current).size(); i++) {
                long to = graph.get((int) current).get((int) i).first;
                //if (data.stream().findFirst(to) == )
            }
        }
        return true;
    }
}
