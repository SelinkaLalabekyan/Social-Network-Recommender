import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Components {

    public static List<List<String>> find(Graph graph) {
        Set<String> visited = new HashSet<>();
        List<List<String>> result = new ArrayList<>();

        for (String user : graph.getUsers()) {
            if (visited.contains(user)) continue;

            List<String> component = new ArrayList<>();
            Queue<String> queue = new LinkedList<>();

            queue.add(user);
            visited.add(user);

            while (!queue.isEmpty()) {
                String node = queue.poll();
                component.add(node);

                for (String neighbor : graph.getFriends(node)) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.add(neighbor);
                    }
                }
            }

            result.add(component);
        }

        return result;
    }
}