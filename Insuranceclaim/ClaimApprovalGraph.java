package Day2.Insuranceclaim;


import java.util.*;

public class ClaimApprovalGraph {

    static class Graph {

        HashMap<String, ArrayList<String>> graph = new HashMap<>();

        void addEdge(String from, String to) {

            graph.putIfAbsent(from, new ArrayList<>());
            graph.putIfAbsent(to, new ArrayList<>());

            graph.get(from).add(to);
        }

        // BFS - Shortest Path

        void BFS(String start, String end) {

            if (!graph.containsKey(start) || !graph.containsKey(end)) {
                System.out.println("Stage not found");
                return;
            }

            Queue<String> queue = new LinkedList<>();

            HashSet<String> visited = new HashSet<>();

            HashMap<String, String> parent = new HashMap<>();

            queue.add(start);
            visited.add(start);

            while (!queue.isEmpty()) {

                String current = queue.poll();

                if (current.equals(end)) {

                    ArrayList<String> path = new ArrayList<>();

                    String temp = end;

                    while (temp != null) {
                        path.add(temp);
                        temp = parent.get(temp);
                    }

                    Collections.reverse(path);

                    System.out.println("Shortest path: " + path);

                    return;
                }

                for (String next : graph.get(current)) {

                    if (!visited.contains(next)) {

                        visited.add(next);
                        parent.put(next, current);
                        queue.add(next);
                    }
                }
            }

            System.out.println("No path found");
        }

        // DFS - Cycle Detection

        boolean DFS(String node, Set<String> visited,
                    Set<String> path) {

            visited.add(node);
            path.add(node);

            for (String next : graph.get(node)) {

                if (!visited.contains(next)) {

                    if (DFS(next, visited, path)) {
                        return true;
                    }

                } else if (path.contains(next)) {

                    return true;
                }
            }

            path.remove(node);

            return false;
        }

        boolean detectCycle() {

            Set<String> visited = new HashSet<>();
            Set<String> path = new HashSet<>();

            for (String node : graph.keySet()) {

                if (!visited.contains(node)) {

                    if (DFS(node, visited, path)) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    public static void main(String[] args) {

        Graph g = new Graph();

        g.addEdge("Registration", "Assessment");
        g.addEdge("Assessment", "Surveyor");
        g.addEdge("Surveyor", "Settlement");
        g.addEdge("Assessment", "Settlement");

        System.out.println("CLAIM APPROVAL PROCESS");

        g.BFS("Registration", "Settlement");

        System.out.println("Cycle detected: " + g.detectCycle());
    }
}
