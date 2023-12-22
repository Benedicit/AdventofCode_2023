import java.io.*;
import java.util.*;

public class Day16 {
    public static void main(String[] args) {
        System.out.println(day16_Part2());
    }

    public static long day16_Part1() {
        File file = new File("/Users/benedikt/Documents/Programmieren/AdventOfCode/Inputs/2023/Tag_16.txt");
        BufferedReader br;
        long result = 0;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String strLine;
        List<char[]> lines = new ArrayList<>();

        while (true) {
            try {
                if ((strLine = br.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            lines.add(strLine.toCharArray());
        }
        char[][] grid = new char[lines.size()][];
        for (int i = 0; i < grid.length; i++) {
            grid[i] = lines.get(i);
        }
        Map<String,List<String>> found = new HashMap<>();
        char[][] resultGrid = new char[grid.length][grid[0].length];
        determinePath(grid,resultGrid,found,"Left",0,0);
        for (var arr : resultGrid) {
            for (char c: arr) {
                if(c == '#') {
                    result++;
                }
            }
        }
        return result;
    }


    public static void determinePath(char[][] grid, char[][] resultGrid, Map<String,List<String>> found, String direction, int i, int j) {
        char currentChar;
        //resultGrid[i][j] = '#';
        while (i < grid.length && j < grid[0].length && i >= 0 && j >= 0) {
           int[] cords2 = {i,j};
           String cords = Arrays.toString(cords2);
                currentChar = grid[i][j];
                resultGrid[i][j] = '#';
                switch (currentChar) {
                    case '|' -> {
                        if (direction.equals("Left") || direction.equals("Right")) {
                            determinePath(grid,resultGrid,found,"Up",i,j);
                            direction = "Down";
                        }
                    }

                    case '-' -> {
                        if (direction.equals("Down") || direction.equals("Up")) {
                            determinePath(grid,resultGrid,found,"Left",i,j);
                            direction = "Right";
                        }
                    }

                    case '/' -> {
                        switch (direction) {
                            case "Left" -> direction = "Up";
                            case "Right" -> direction = "Down";
                            case "Up" -> direction = "Left";
                            case "Down" -> direction = "Right";
                            default -> {

                            }
                        }
                    }

                    case '\\' -> {
                        switch (direction) {
                            case "Left" -> direction = "Down";
                            case "Right" -> direction = "Up";
                            case "Up" -> direction = "Right";
                            case "Down" -> direction = "Left";
                            default -> {

                            }
                        }
                    }
                    default -> {

                    }

                }
            if(!found.containsKey(cords)) {
                List<String> temp = new ArrayList<>();
                temp.add(direction);
                found.put(cords,temp);
            } else {
                var list = found.get(cords);
                if(list.contains(direction)) {
                    break;
                } else {
                    list.add(direction);
                }
            }

            switch (direction) {
                case "Left" -> {
                    j++;
                }
                case "Right" -> {
                    j--;
                }
                case "Up" -> {
                    i--;
                }
                case "Down" -> {
                    i++;
                }
                default -> {

                }
            }



        }

    }
        public static long day16_Part2 () {
            File file = new File("/Users/benedikt/Documents/Programmieren/AdventOfCode/Inputs/2023/Tag_16.txt");
            BufferedReader br;
            long result = 0;
            try {
                br = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            String strLine;
            List<char[]> lines = new ArrayList<>();

            while (true) {
                try {
                    if ((strLine = br.readLine()) == null) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                lines.add(strLine.toCharArray());
            }
            char[][] grid = new char[lines.size()][];
            for (int i = 0; i < grid.length; i++) {
                grid[i] = lines.get(i);
            }
            Map<String,List<String>> found = new HashMap<>();
            List<char[][]> resultGrids = new ArrayList<>();
            for (int i = 0; i < grid.length * 4-4; i++) {
                resultGrids.add(new char[grid.length][grid[0].length]);
            }
            determinePath(grid,resultGrids.get(0),found,"Left",0,0);
            found = new HashMap<>();
            determinePath(grid,resultGrids.get(1),found,"Down",0,0);
            found = new HashMap<>();
            determinePath(grid,resultGrids.get(2),found,"Right",0,grid[0].length-1);
            found = new HashMap<>();
            determinePath(grid,resultGrids.get(3),found,"Down",0,grid[0].length-1);
            found = new HashMap<>();
            determinePath(grid,resultGrids.get(4),found,"Left", grid.length-1, 0);
            found = new HashMap<>();
            determinePath(grid,resultGrids.get(5),found,"Up",grid.length-1,0);
            found = new HashMap<>();
            determinePath(grid,resultGrids.get(6),found,"Right",grid.length-1,grid[0].length-1);
            found = new HashMap<>();
            determinePath(grid,resultGrids.get(7),found,"Up",grid.length-1,grid[0].length-1);
            int index = 8;
            for (int i = 1; i < grid.length-2; i++) {
                determinePath(grid,resultGrids.get(index++),found,"Left",i,0);
                found = new HashMap<>();
                determinePath(grid,resultGrids.get(index++),found,"Right",i, grid.length-1);
                found = new HashMap<>();
            }

            for (int i = 1; i < grid.length-2; i++) {
                determinePath(grid,resultGrids.get(index++),found,"Down",0,i);
                found = new HashMap<>();
                determinePath(grid,resultGrids.get(index++),found,"Up",grid.length-1,i);
                found = new HashMap<>();
            }

            for (var charGrid: resultGrids) {
                int temp = 0;
                for (var arr : charGrid) {
                    for (char c : arr) {
                        if (c == '#') {
                            temp++;
                        }
                    }
                }
                if(temp>result) {
                    result = temp;
                }
            }
            return result;
        }
}
