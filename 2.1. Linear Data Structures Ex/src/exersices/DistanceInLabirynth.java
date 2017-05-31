package exersices;

import javax.swing.*;
import java.util.*;

/**
 * Created by Simona Simeonova on 5/23/2017.
 */
public class DistanceInLabirynth {
    private static  int startRow;
    private static int startCol;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = Integer.parseInt(scan.nextLine());

        String[][] matrix = readMatrix(n, scan);

        Queue<Cell> queue = new LinkedList<>();
        queue.add(new Cell(startRow,startCol, true,0));

        while (!queue.isEmpty()){
            Cell currentCell = queue.poll();

            int row = currentCell.row;
            int col = currentCell.col;

            if(!matrix[row][col].equals("*")){
                matrix[row][col] = String.valueOf(currentCell.moves);
            }

            if(row-1>=0 && matrix[row-1][col]!="x" && )
        }


        String debug = "";
    }
    private static class Cell{
        public int row;
        public int col;
        public boolean visited;
        public int moves;

        public Cell(int row, int col, boolean visited, int moves){
            this.row = row;
            this.col = col;
            this.visited = visited;
            this.moves = moves;
        }
    }
    public static String[][] readMatrix(int n, Scanner scan){
        String[][] matrix = new String[n][n];
        for(int row = 0; row<n; row++){
            String[] arr = scan.nextLine().split("(?!^)");
            for(int col = 0; col<n; col++){
                matrix[row][col] = arr[col];
                if(matrix[row][col].equals("*")){
                    startRow = row;
                    startCol = col;
                }
            }
        }
        return matrix;
    }
}
