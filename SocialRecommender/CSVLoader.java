import java.io.BufferedReader;
import java.io.FileReader;

public class CSVLoader {

    public static Graph load(String path) throws Exception {
        Graph graph = new Graph();

        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;

        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");

            if (parts.length == 2) {
                graph.addConnection(parts[0].trim(), parts[1].trim());
            }
        }

        br.close();
        return graph;
    }
}