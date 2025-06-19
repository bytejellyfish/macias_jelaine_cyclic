

import java.util.*;

public class cyclic_dfs {
    static int[][] graph = new int[100][100];
    static boolean[] visited = new boolean[100];
    static boolean[] inStack = new boolean[100];
    static int numVertices;
    static int cycleStart = -1;
    static int cycleEnd = -1;

    static boolean dfs(int node) {
        visited[node] = true;
        inStack[node] = true;

        for (int i = 0; i < numVertices; i++) {
            if (graph[node][i] == 1) {
                if (!visited[i]) {
                    if (dfs(i)) return true;
                } else if (inStack[i]) {
                    cycleStart = i;
                    cycleEnd = node;
                    return true;
                }
            }
        }

        inStack[node] = false;
        return false;
    }

    static boolean hasCycle() {
        Arrays.fill(visited, false);
        Arrays.fill(inStack, false);

        for (int i = 0; i < numVertices; i++) {
            if (!visited[i] && dfs(i)) {
                return true;
            }
        }
        return false;
    }

    static void printCycle() {
        if (cycleStart == -1) {
            System.out.println("Engk! No cycle found.");
            return;
        }

        System.out.print("Cycle found: " + cycleStart);
        int current = cycleEnd;
        while (current != cycleStart) {
            System.out.print(" <- " + current);
            for (int i = 0; i < numVertices; i++) {
                if (graph[i][current] == 1 && visited[i]) {
                    current = i;
                    break;
                }
            }
        }
        System.out.println(" <- " + cycleStart);
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

    public static void main(String[] args) {
        System.out.println("Cycle Detection using DFS\n");

        System.out.println("First Example with Graph with a Cycle:");
        numVertices = 4;
        for (int[] row : graph) Arrays.fill(row, 0);
        graph[0][1] = 1;
        graph[1][2] = 1;
        graph[2][3] = 1;
        graph[3][1] = 1; 

        printMatrix();
        if (hasCycle()) {
            System.out.println("Wow CYCLE FOUND!");
            printCycle();
        } else {
            System.out.println("Engk! No cycle found.");
        }

        System.out.println("\n" + "-".repeat(50) + "\n");

        // New Example 2: Graph with No Cycle (4 nodes)
        System.out.println("Second Example with Graph with No Cycle:");
        numVertices = 4;
        for (int[] row : graph) Arrays.fill(row, 0);
        graph[0][1] = 1;
        graph[1][2] = 1;
        graph[2][3] = 1;

        printMatrix();
        cycleStart = -1;
        cycleEnd = -1;
        if (hasCycle()) {
            System.out.println("This graph has a cycle!");
            printCycle();
        } else {
            System.out.println("Engk! No cycle found.");
        }

        System.out.println("\nSayonara ! ~");
    }
}