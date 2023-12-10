import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day10 {
    public static void main(String[] args) {
        System.out.println(day10_Part2());
    }

    public static long day10_Part1() {
        File file = new File("/Users/benedikt/Documents/Programmieren/AdventOfCode/Inputs/2023/Tag_10.txt");
        BufferedReader br;
        long result = 0;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String strLine;

        int currentLine = 0;
        int startLine = 0;
        int startIndex = 0;
        List<char[]> lines = new ArrayList<>();
        while (true) {
            try {
                if ((strLine = br.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (strLine.indexOf('S') != -1) {
                startLine = currentLine;
                startIndex = strLine.indexOf('S');
            }
            lines.add(strLine.toCharArray());

            currentLine++;
        }
        char[][] grid = new char[lines.size()][];
        for (int i = 0; i < grid.length; i++) {
            grid[i] = lines.get(i);
        }

        char S = grid[startLine][startIndex];
        char currentChar = grid[startLine][++startIndex];

        boolean fromRight = false;
        boolean fromLeft = true;
        boolean fromTop = false;
        boolean fromBottom = false;

        while (currentChar != 'S') {

            switch (currentChar) {
                case '|' -> {
                    fromRight = false;
                    fromLeft = false;
                    if (fromBottom) {
                        fromTop = false;
                        startLine--;
                    } else {
                        fromTop = true;
                        fromBottom = false;
                        startLine++;
                    }
                }
                case '-' -> {

                    fromBottom = false;
                    fromTop = false;
                    if (fromLeft) {
                        startIndex++;
                        fromRight = false;
                    } else {
                        fromRight = true;
                        fromLeft = false;
                        startIndex--;
                    }
                }
                case 'L' -> {

                    fromRight = false;
                    fromBottom = false;
                    if (fromTop) {
                        startIndex++;
                        fromTop = false;
                        fromLeft = true;
                    } else {
                        startLine--;
                        fromLeft = false;
                        fromBottom = true;
                    }

                }
                case 'J' -> {

                    fromLeft = false;
                    fromBottom = false;
                    if (fromTop) {
                        startIndex--;
                        fromRight = true;
                        fromTop = false;
                    } else {
                        startLine--;
                        fromBottom = true;
                        fromRight = false;
                    }
                }
                case '7' -> {

                    fromRight = false;
                    fromBottom = false;
                    if (fromLeft) {
                        startLine++;
                        fromTop = true;
                        fromLeft = false;
                    } else {
                        startIndex--;
                        fromTop = false;
                        fromRight = true;
                    }
                }
                case 'F' -> {
                    fromLeft = false;
                    fromBottom = false;

                    if (fromRight) {
                        startLine++;
                        fromTop = true;
                        fromRight = false;
                    } else {
                        startIndex++;
                        fromTop = false;
                        fromLeft = true;
                    }
                }

            }

            result++;


            currentChar = grid[startLine][startIndex];


        }
        result = result / 2 + 1;

        return result;
    }


    public static long day10_Part2() {
        File file = new File("/Users/benedikt/Documents/Programmieren/AdventOfCode/Inputs/2023/Tag_10.txt");
        BufferedReader br;
        long result = 0;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String strLine;

        int currentLine = 0;
        int startLine = 0;
        int startIndex = 0;
        List<char[]> lines = new ArrayList<>();
        while (true) {
            try {
                if ((strLine = br.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (strLine.indexOf('S') != -1) {
                startLine = currentLine;
                startIndex = strLine.indexOf('S');
            }
            lines.add(strLine.toCharArray());

            currentLine++;
        }
        char[][] grid = new char[lines.size()][];
        for (int i = 0; i < grid.length; i++) {
            grid[i] = lines.get(i);
        }
        lines = null;
        boolean[][] checkIfOnLoop = new boolean[grid.length][grid[0].length];
        char S = grid[startLine][startIndex];
        List<int[]> loop = new ArrayList<>();
        checkIfOnLoop[startLine][startIndex] = true;
        loop.add(new int[]{startLine,startIndex});
        boolean fromRight = false;
        boolean fromLeft = true;
        boolean fromTop = false;
        boolean fromBottom = false;
        int n = 1;



        char currentChar = grid[startLine][++startIndex];

        int area =0;
        while (currentChar != 'S') {
            loop.add(new int[]{startLine,startIndex});
            checkIfOnLoop[startLine][startIndex] = true;
            switch (currentChar) {
                case '|' -> {
                    fromRight = false;
                    fromLeft = false;
                    if (fromBottom) {
                        fromTop = false;
                        startLine--;
                    } else {
                        fromTop = true;
                        fromBottom = false;
                        startLine++;
                    }
                }
                case '-' -> {

                    fromBottom = false;
                    fromTop = false;
                    if (fromLeft) {
                        startIndex++;
                        fromRight = false;
                    } else {
                        fromRight = true;
                        fromLeft = false;
                        startIndex--;
                    }
                }
                case 'L' -> {

                    fromRight = false;
                    fromBottom = false;
                    if (fromTop) {
                        startIndex++;
                        fromTop = false;
                        fromLeft = true;
                    } else {
                        startLine--;
                        fromLeft = false;
                        fromBottom = true;
                    }

                }
                case 'J' -> {

                    fromLeft = false;
                    fromBottom = false;
                    if (fromTop) {
                        startIndex--;
                        fromRight = true;
                        fromTop = false;
                    } else {
                        startLine--;
                        fromBottom = true;
                        fromRight = false;
                    }
                }
                case '7' -> {

                    fromRight = false;
                    fromBottom = false;
                    if (fromLeft) {
                        startLine++;
                        fromTop = true;
                        fromLeft = false;
                    } else {
                        startIndex--;
                        fromTop = false;
                        fromRight = true;
                    }
                }
                case 'F' -> {
                    fromLeft = false;
                    fromBottom = false;

                    if (fromRight) {
                        startLine++;
                        fromTop = true;
                        fromRight = false;
                    } else {
                        startIndex++;
                        fromTop = false;
                        fromLeft = true;
                    }
                }

            }

            currentChar = grid[startLine][startIndex];
            result++;
        }
        //loop.remove(loop.size()-1);
        for (int i = 1; i < loop.size()-1; i++) {
            area += loop.get(i)[0] * (loop.get(i+1)[1] - loop.get(i-1)[1]);
        }
        area += loop.get(0)[0] *(loop.get(1)[1]-loop.get(loop.size()-1)[1]);
        area += loop.get(loop.size()-1)[0] * (loop.get(0)[1]-loop.get(loop.size()-2)[1]);
        area /= 2;
       // area *= -1;
        result = area - day10_Part1();
        return result;
    }

}
