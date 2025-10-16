package P5;

import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

public class MazeSolver {

    public static void main(String args[]) {
        int row = Integer.parseInt(args[0]);
        int col = Integer.parseInt(args[1]);
        
        Queue<Integer> queue = new LinkedList<>();

        char maze[][] = new char[row][col];

        System.out.println(row + " " + col);

        for (int r = 0; r < row; r++) {
            Scanner scan = new Scanner(args[r + 2]);

            String next = scan.next();
            for (int c = 0; c < col; c++) {
                maze[r][c] = next.charAt(c);
                // System.out.println(maze[r][c]);
            }

            scan.close();
        }
    }
    
}