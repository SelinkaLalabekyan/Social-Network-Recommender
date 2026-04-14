import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Recommender {

    public static int mutualFriends(Graph graph, String u1, String u2) {
        Set<String> f1 = graph.getFriends(u1);
        Set<String> f2 = graph.getFriends(u2);

        int count = 0;
        for (String f : f1) {
            if (f2.contains(f)) count++;
        }
        return count;
    }

    public static double jaccard(Graph graph, String u1, String u2) {
        Set<String> f1 = graph.getFriends(u1);
        Set<String> f2 = graph.getFriends(u2);

        Set<String> intersection = new HashSet<>(f1);
        intersection.retainAll(f2);

        Set<String> union = new HashSet<>(f1);
        union.addAll(f2);

        if (union.size() == 0) return 0;

        return (double) intersection.size() / union.size();
    }

    public static List<String> recommend(Graph graph, String user) {

        if (!graph.hasUser(user)) return new ArrayList<>();

        Set<String> direct = graph.getFriends(user);
        Set<String> candidates = BFS.twoHop(graph, user);

        Map<String, Integer> scores = new HashMap<>();

        for (String c : candidates) {
            if (c.equals(user) || direct.contains(c)) continue;

            int mutual = mutualFriends(graph, user, c);

            if (mutual > 0) {
                scores.put(c, mutual);
            }
        }

        List<String> result = new ArrayList<>(scores.keySet());

        result.sort((a, b) -> scores.get(b) - scores.get(a));

        return result.subList(0, Math.min(5, result.size()));
    }
}