import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MazeSolver {

    // Simple linked-list queue storing Location objects
    private static class LinkedQueue {
        private static class Node {
            Location value;
            Node next;
            Node(Location v) { value = v; }
        }

        private Node head;
        private Node tail;

        public boolean isEmpty() { return head == null; }

        public void enqueue(Location loc) {
            Node n = new Node(loc);
            if (tail == null) {
                head = tail = n;
            } else {
                tail.next = n;
                tail = n;
            }
        }

        public Location dequeue() {
            if (head == null) return null;
            Location v = head.value;
            head = head.next;
            if (head == null) tail = null;
            return v;
        }
    }

    // Recursive BFS: processes one node, enqueues neighbors, then recurses
    private static Location recursiveBFS(LinkedQueue q, boolean[][] visited, char[][] grid, int targetR, int targetC) {
        if (q.isEmpty()) return null;
        Location cur = q.dequeue();
        if (cur.row == targetR && cur.col == targetC) return cur;

        final int[] dr = {-1, 1, 0, 0};
        final int[] dc = {0, 0, -1, 1};

        for (int i = 0; i < 4; i++) {
            int nr = cur.row + dr[i];
            int nc = cur.col + dc[i];
            if (nr < 0 || nr >= grid.length || nc < 0 || nc >= grid[0].length) continue;
            if (visited[nr][nc]) continue;
            if (grid[nr][nc] == 'X') continue;
            visited[nr][nc] = true;
            Location next = new Location(nr, nc, cur);
            q.enqueue(next);
        }

        return recursiveBFS(q, visited, grid, targetR, targetC);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        if (!sc.hasNextInt()) {
            System.err.println("Missing rows/cols");
            return;
        }
        int rows = sc.nextInt();
        int cols = sc.nextInt();
        sc.nextLine(); // consume rest of line

        char[][] grid = new char[rows][cols];
        int startR = -1, startC = -1, targetR = -1, targetC = -1;

        for (int r = 0; r < rows; r++) {
            String line = "";
            // read next non-empty line
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                if (line.length() == 0) continue;
                break;
            }
            if (line == null) line = "";
            // if user provided tokens without spaces we can also accept scanner.next()
            if (line.length() < cols) {
                String token = line.trim();
                if (token.length() < cols && sc.hasNext()) {
                    token = token + sc.next();
                }
                line = token;
            }
            for (int c = 0; c < cols && c < line.length(); c++) {
                char ch = line.charAt(c);
                grid[r][c] = ch;
                if (ch == 'S') { startR = r; startC = c; }
                if (ch == 'T') { targetR = r; targetC = c; }
            }
            // Fill any missing with walls
            for (int c = line.length(); c < cols; c++) grid[r][c] = 'X';
        }

        if (startR < 0 || targetR < 0) {
            System.out.println("Start or target not found");
            return;
        }

        boolean[][] visited = new boolean[rows][cols];
        LinkedQueue q = new LinkedQueue();

        visited[startR][startC] = true;
        q.enqueue(new Location(startR, startC, null));

        Location goal = recursiveBFS(q, visited, grid, targetR, targetC);

        if (goal == null) {
            System.out.println("No path found");
            return;
        }

        // Reconstruct path
        List<Location> path = new ArrayList<>();
        for (Location cur = goal; cur != null; cur = cur.parent) path.add(cur);
        // reverse
        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.println(path.get(i));
        }
        System.out.println("Total distance = " + (path.size() - 1));
    }
}
