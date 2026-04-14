import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        // 🔧 параметры
        String user = args.length > 0 ? args[0] : "alex_king";

        // 📥 загрузка графа
        Graph graph = CSVLoader.load("data/network.csv");

        // 🔍 рекомендации
        List<String> recs = Recommender.recommend(graph, user);

        System.out.println("\n🔍 Recommendations for: " + user);
        System.out.println("--------------------------------------------------");
        System.out.printf("%-15s %-10s %-10s\n", "User", "Mutual", "Jaccard");
        System.out.println("--------------------------------------------------");

        for (String u : recs) {
            int mutual = Recommender.mutualFriends(graph, user, u);
            double jaccard = Recommender.jaccard(graph, user, u);

            System.out.printf("%-15s %-10d %-10.2f\n", u, mutual, jaccard);
        }

        System.out.println("--------------------------------------------------");

        // 🌐 компоненты
        System.out.println("\n🌐 Connected Components:");

        List<List<String>> comps = Components.find(graph);

        int i = 1;
        for (List<String> comp : comps) {
            System.out.println("Group " + i + ": " + comp);
            i++;
        }

        // 🌐 граф
        System.out.println("\n🌐 Graph:");
        for (String u : graph.getUsers()) {
            System.out.println(u + " -> " + graph.getFriends(u));
        }
    }
}