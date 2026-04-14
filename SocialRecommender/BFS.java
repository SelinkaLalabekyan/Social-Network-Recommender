import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BFS {
    public static Set<String> twoHop(Graph graph, String start) {
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);

        Set<String> result = new HashSet<>();

        int level = 0;

        while (!queue.isEmpty() && level < 2) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String node = queue.poll();

                for (String neighbor : graph.getFriends(node)) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        result.add(neighbor);
                        queue.add(neighbor);
                    }
                }
            }

            level++;
        }

        return result;
    }
}