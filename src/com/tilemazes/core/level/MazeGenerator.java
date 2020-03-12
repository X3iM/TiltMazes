package com.tilemazes.core.level;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;

public class MazeGenerator {

    public void generate() {

        while (true) {
            char[][] maze = initialization();
            fillMaze(maze);
            //printMaze(maze);

            for (int i = 3; i < maze.length-1; i+=2) {
                Random random = new Random();
                for (int j = 1; j < maze[i].length-1; j+=2) {
                    int cell_size = random.nextInt((j < maze[i].length-3) ? 3 : 1) + 1;
                    deleteWall(maze, i, j+1, j+cell_size);
                    int rand_cell = random.nextInt(cell_size) * 2;

                    System.out.println("i " + i + " j " + j + "  cell_size " + cell_size + "  rand_cell " + rand_cell);
                    maze[i-1][cell_size+rand_cell] = ' ';

                    j += cell_size*2;
                }
            }
            printMaze(maze);
        }

    }

    private char[][] initialization() {
        String input = "";
        int row = 0;
        int col = 0;
        try {
            System.out.print("Enter quantity of row(> 1) and col(> 1): ");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            input = bufferedReader.readLine();
            String[] strings = input.split("\\s+");
            row = Integer.parseInt(strings[0]);
            col = Integer.parseInt(strings[1]);

            while (row < 2 && col < 2) {
                System.out.print("Enter quantity of row(> 1) and col(> 1): ");
                String[] string = input.split("\\s+");
                row = Integer.parseInt(string[0]);
                col = Integer.parseInt(string[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new char[row*2+1][col*2+1];
    }

    private void printMaze(char[][] maze) {
        for (char[] chars : maze) {
            for (char aChar : chars) {
                System.out.print(' ');
                System.out.print(aChar);
            }
            System.out.println();
        }
    }

    private void fillMaze(char[][] maze) {
        Arrays.fill(maze[0], 'X');
        for (int i = 0; i < maze.length; i++)
            maze[i][0] = 'X';

        maze[1][maze[0].length-1] = 'X';
        for (int i = 2; i < maze.length; i++) {
            for (int j = 2; j < maze[i].length; j+=2) {
                maze[i][j] = 'X';
            }
        }

        for (int i = 2; i < maze.length; i+=2) {
            Arrays.fill(maze[i], 'X');
        }
    }

    private void deleteWall(char[][] maze, int i, int j_start, int j_finish) {
        for (int j = j_start; j < j_finish; j+=2) {
            maze[i][j] = ' ';
        }
    }
}