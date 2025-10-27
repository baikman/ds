package P5;

import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

public class MazeSolver {

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        
        int row = scan.nextInt();
        int col = scan.nextInt();
        int length = 0;
        
        Queue<Location> queue = new LinkedList<>();

        Location maze[][] = new Location[row][col];
        Location start = new Location();
        Location target = new Location();
        Location parent = new Location();

        for (int r = 0; r < row; r++) {
            String next = scan.next();

            for (int c = 0; c < col; c++) {
                char curr = next.charAt(c);
                Coordinate currCoord = new Coordinate(r, c);
                maze[r][c] = new Location(curr, currCoord);
                if (curr == 'S') start = new Location(maze[r][c]);
                if (curr == 'T') target = new Location(maze[r][c]);
            }   
        }

        scan.close();

        Location curr = new Location(start);
        
        queue.add(curr);
        curr.visited = true;
        while ((!queue.isEmpty()) && (parent.coord != target.coord)) {
            parent = queue.remove();
            
            if (parent.coord.row - 1 >= 0) {
                if ((maze[parent.coord.row - 1][parent.coord.col].visited == false) && (maze[parent.coord.row - 1][parent.coord.col].type != 'X')) {
                    queue.add(maze[parent.coord.row - 1][parent.coord.col]);
                    maze[parent.coord.row - 1][parent.coord.col].visited = true;
                    maze[parent.coord.row - 1][parent.coord.col].parent = parent;
                }
            }
            if (parent.coord.col - 1 >= 0) {
                if ((maze[parent.coord.row][parent.coord.col - 1].visited == false) && (maze[parent.coord.row][parent.coord.col - 1].type != 'X')) {
                    queue.add(maze[parent.coord.row][parent.coord.col - 1]);
                    maze[parent.coord.row][parent.coord.col - 1].visited = true;
                    maze[parent.coord.row][parent.coord.col - 1].parent = parent;
                }
            }
            if (parent.coord.row + 1 < row) {
                if ((maze[parent.coord.row + 1][parent.coord.col].visited == false) && (maze[parent.coord.row + 1][parent.coord.col].type != 'X')) {
                    queue.add(maze[parent.coord.row + 1][parent.coord.col]);
                    maze[parent.coord.row + 1][parent.coord.col].visited = true;
                    maze[parent.coord.row + 1][parent.coord.col].parent = parent;
                }
            }
            if (parent.coord.col + 1 < col) {
                if ((maze[parent.coord.row][parent.coord.col + 1].visited == false) && (maze[parent.coord.row][parent.coord.col + 1].type != 'X')) {
                    queue.add(maze[parent.coord.row][parent.coord.col + 1]);
                    maze[parent.coord.row][parent.coord.col + 1].visited = true;
                    maze[parent.coord.row][parent.coord.col + 1].parent = parent;
                }
            }

            // parent.coord.printCoord();
        }

        if (parent.coord == target.coord) {
            curr = new Location(parent);
            while (curr.coord != start.coord) {
                curr = curr.parent;
                length++;
            }
            Coordinate path[] = new Coordinate[length + 1];
            curr = new Location(parent);
            for (int i = length; i >= 0; i--) {
                path[i] = curr.coord;
                curr = curr.parent;
            }
            for (int i = 0; i < length + 1; i++) path[i].printCoord();
            System.out.println("Total distance = " + length);
        } else {
            System.out.println("Maze not solvable.");
        }
    }
}