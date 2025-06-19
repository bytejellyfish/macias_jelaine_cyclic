import java.util.*;

public class cyclic_bfs {
    static int[][] graph = new int[100][100];
    static int[] inDegree = new int[100];
    static int numVertices;
    static Queue<Integer> q = new LinkedList<>();

    static void calculateInDegree() {
        Arrays.fill(inDegree, 0);
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (graph[i][j] == 1) {
                    inDegree[j]++;
                }
            }
        }
    }

    static boolean detectCycle() {
        calculateInDegree();
        q.clear();

        for (int i = 0; i < numVertices; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }

        int processed = 0;

        while (!q.isEmpty()) {
            int current = q.poll();
            processed++;
            for (int i = 0; i < numVertices; i++) {
                if (graph[current][i] == 1) {
                    inDegree[i]--;
                    if (inDegree[i] == 0) {
                        q.offer(i);
                    }
                }
            }
        }

        return processed != numVertices;
    }

    static void showCycleVertices() {
        System.out.print("Vertices that are part of cycles: ");
        boolean foundAny = false;
        for (int i = 0; i < numVertices; i++) {
            if (inDegree[i] > 0) {
                System.out.print(i + " ");
                foundAny = true;
            }
        }
        if (!foundAny) {
            System.out.print("None");
        }
        System.out.println();
    }

    static void printMatrix() {
        System.out.println("\nAdjacency Matrix:");
        System.out.print("  ");
        for (int i = 0; i < numVertices; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < numVertices; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < numVertices; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void showInDegrees() {
        System.out.print("In-degrees of vertices: ");
        for (int i = 0; i < numVertices; i++) {
            System.out.print("(" + i + ":" + inDegree[i] + ") ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("Cycle Detection using BFS\n");

        // Example 1
        System.out.println("First Example with Graph with a Cycle");
        numVertices = 4;
        for (int[] row : graph) Arrays.fill(row, 0);
        graph[1][2] = 1;
        graph[2][3] = 1;
        graph[3][1] = 1;

        printMatrix();
        calculateInDegree();
        System.out.print("Before processing: ");
        showInDegrees();
        if (detectCycle()) {
            System.out.println("Cycle found!");
            showCycleVertices();
        } else {
            System.out.println("Engk! No cycle found.");
        }

        System.out.println("\n" + "-".repeat(50) + "\n");

        // Example 2
        System.out.println("Second Example with Graph with No Cycles");
        numVertices = 4;
        for (int[] row : graph) Arrays.fill(row, 0);
        graph[0][1] = 1;
        graph[1][2] = 1;
        graph[2][3] = 1;

        printMatrix();
        calculateInDegree();
        System.out.print("Before processing: ");
        showInDegrees();
        if (detectCycle()) {
            System.out.println("Cycle Found!");
            showCycleVertices();
        } else {
            System.out.println("Engk! No cycle found.");
        }

        System.out.println("\n" + "-".repeat(50) + "\n");

        // Example 3
        System.out.println("Third Example with Bigger Graph with Cycles");
        numVertices = 6;
        for (int[] row : graph) Arrays.fill(row, 0);
        graph[0][1] = 1;
        graph[1][2] = 1;
        graph[2][0] = 1;
        graph[3][4] = 1;
        graph[4][5] = 1;
        graph[5][3] = 1;

        printMatrix();
        calculateInDegree();
        System.out.print("Before processing: ");
        showInDegrees();
        if (detectCycle()) {
            System.out.println("Cycle Found!");
            showCycleVertices();
        } else {
            System.out.println("Engk! No cycle found.");
        }

        System.out.println("\nSayonara :)");
    }
}

