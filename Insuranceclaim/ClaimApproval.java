
package Day2.Insuranceclaim;

 class ClaimApprovalGraph {

    static String[] stages = {
        "Registration", "Assessment", "Surveyor", "Settlement"
    };

    static int[][] graph = new int[4][4];

    // Add connection
    static void addEdge(int from, int to) {
        graph[from][to] = 1;
    }

    // BFS - Shortest Path
    static void BFS(int start, int end) {

        int[] queue = new int[4];
        int[] visited = new int[4];
        int[] parent = new int[4];

        for (int i = 0; i < 4; i++)
            parent[i] = -1;

        int front = 0, rear = 0;

        queue[rear++] = start;
        visited[start] = 1;

        while (front < rear) {

            int current = queue[front++];

            if (current == end) {
                int[] path = new int[4];
                int count = 0;

                while (current != -1) {
                    path[count++] = current;
                    current = parent[current];
                }

                System.out.print("Shortest Path: ");

                for (int i = count - 1; i >= 0; i--)
                    System.out.print(stages[path[i]] + " ");

                return;
            }

            for (int i = 0; i < 4; i++) {

                if (graph[current][i] == 1 && visited[i] == 0) {
                    visited[i] = 1;
                    parent[i] = current;
                    queue[rear++] = i;
                }
            }
        }

        System.out.println("No Path Found");
    }

    // DFS - Cycle Detection
    static boolean DFS(int node, int[] visited, int[] path) {

        visited[node] = 1;
        path[node] = 1;

        for (int i = 0; i < 4; i++) {

            if (graph[node][i] == 1) {

                if (path[i] == 1)
                    return true;

                if (visited[i] == 0 && DFS(i, visited, path))
                    return true;
            }
        }

        path[node] = 0;
        return false;
    }

    public static void main(String[] args) {

        addEdge(0, 1);
        addEdge(1, 2);
        addEdge(2, 3);
        addEdge(1, 3);

        System.out.println("CLAIM APPROVAL PROCESS");

        BFS(0, 3);

        int[] visited = new int[4];
        int[] path = new int[4];

        boolean cycle = false;

        for (int i = 0; i < 4; i++) {
            if (visited[i] == 0 && DFS(i, visited, path)) {
                cycle = true;
                break;
            }
        }

        System.out.println("\nCycle Detected: " + cycle);
    }
}
