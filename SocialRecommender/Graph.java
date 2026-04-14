import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph {
    private Map<String, Set<String>> graph = new HashMap<>();

    public void addUser(String user) {
        graph.putIfAbsent(user, new HashSet<>());
    }

    public void addConnection(String u1, String u2) {
        addUser(u1);
        addUser(u2);

        graph.get(u1).add(u2);
        graph.get(u2).add(u1);
    }

    public Set<String> getFriends(String user) {
        return graph.getOrDefault(user, new HashSet<>());
    }

    public boolean hasUser(String user) {
        return graph.containsKey(user);
    }

    public Set<String> getUsers() {
        return graph.keySet();
    }
}