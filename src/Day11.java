import java.io.*;
import java.util.*;

public class Day11 {
    public static void main(String[] args) {
        System.out.println(day11_Part2());
    }
    public static long day11_Part1() {
        File file = new File("/Users/benedikt/Documents/Programmieren/AdventOfCode/Inputs/2023/Tag_11.txt");
        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String strLine;

        int currentLine = 0;
        int startLine = 0;
        int startIndex = 0;
        int n =1;
        List<String> lines = new ArrayList<>();
        while (true) {
            try {
                if ((strLine = br.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String counter = strLine;

            lines.add(strLine);

            currentLine++;
        }
        char[][] grid = new char[lines.size()][];
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if(line.replace(".", "").isEmpty()) {
                lines.add(i,line);
                i++;
            }
        }

        for (int i = 0; i < lines.get(0).length(); i++) {
            StringBuilder sb = new StringBuilder();

            for (String s: lines) {
                sb.append(s.charAt(i));
            }
            if(sb.toString().replace(".", "").isEmpty()) {
                for (int j=0; j<lines.size(); j++) {
                    StringBuilder newLine = new StringBuilder(lines.remove(j));
                    newLine.insert(i,".");
                    lines.add(j,newLine.toString());
                }
                i++;
            }
        }
        /*
        for (int i = 1; i < n; i++) {
            int indexLine = 0;
            int indexChar1 = lines.get(indexLine).indexOf('#');

            while (indexChar1 == -1) {
                indexLine++;
                indexChar1 = lines.get(indexLine).indexOf('#');
            }
            String test = lines.remove(indexLine).replaceFirst("#",".");
            lines.add(indexLine,test);
            List<String> clone = new ArrayList<>(lines);
            for (int j = i+1; j < n; j++) {
                int indexSecondLine = i;
                int indexChar2 = clone.get(indexSecondLine).indexOf('#');
                while (indexChar2 == -1) {
                    indexSecondLine++;
                    indexChar2 = clone.get(indexSecondLine).indexOf('#');
                }
                test = clone.remove(indexSecondLine).replaceFirst("#",".");
                clone.add(indexSecondLine,test);
                result += indexSecondLine-indexLine + Math.abs(indexChar2-indexChar1);
            }

        }

         */
        n=0;
        long result = 0;
        Map<Integer,int[]> galaxies = new HashMap<>();
        for (int i = 0; i < lines.size(); i++) {
            String current = lines.get(i);
            while (current.indexOf('#') != -1) {
                int index = current.indexOf('#');
                String replace = current.replaceFirst("#", ".");
                n++;
                current = replace;
                galaxies.put(n,new int[]{i,index});
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = i+1; j <= n; j++) {
                result += galaxies.get(j)[0] - galaxies.get(i)[0] + Math.abs(galaxies.get(j)[1] - galaxies.get(i)[1]);
            }
        }

        return result;
    }
    public static long day11_Part2() {
        File file = new File("/Users/benedikt/Documents/Programmieren/AdventOfCode/Inputs/2023/Tag_11.txt");
        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String strLine;

        int currentLine = 0;
        int startLine = 0;
        int startIndex = 0;
        int n =1;
        List<String> lines = new ArrayList<>();
        while (true) {
            try {
                if ((strLine = br.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String counter = strLine;

            lines.add(strLine);

            currentLine++;
        }
        char[][] grid = new char[lines.size()][];


        for (int i = 0; i < lines.get(0).length(); i++) {
            StringBuilder sb = new StringBuilder();

            for (String s: lines) {
                sb.append(s.charAt(i));
            }
            if(sb.toString().replace(".", "").isEmpty()) {
                for (int j=0; j<lines.size(); j++) {
                    StringBuilder newLine = new StringBuilder(lines.remove(j));
                    //newLine.deleteCharAt(i);
                    newLine.insert(i,"|");
                    lines.add(j,newLine.toString());
                }
                i++;
            }
        }
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if(line.replace(".", "").isEmpty()) {
                String temp = line.replace(".","|");
                lines.remove(i);
                lines.add(i,temp);
                i++;
            }
        }
        n=0;
        long result = 0;
        int offset = 0;
        Map<Integer, long[]> galaxies = new HashMap<>();

        for (int i = 0; i < lines.size(); i++) {
            String current = lines.get(i);

            if(current.replaceAll("[.|]","").isEmpty()) {
                offset++;
            }
            while (current.indexOf('#') != -1) {
                int index = current.indexOf('#');
                long multiplier = 0;
                if(current.substring(0,index).contains("|")) {
                    multiplier = current.substring(0,index).chars().filter(x -> x == '|').count();
                }
                String replace = current.replaceFirst("#", ".");
                n++;
                current = replace;
                long ageDistance = 1000000-1;
                long newIndex = index+(ageDistance*multiplier)-multiplier;
                long newLine = i+(ageDistance*offset);
                galaxies.put(n,new long[]{newLine,newIndex});
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = i+1; j <= n; j++) {
                result += Math.abs(galaxies.get(j)[0] - galaxies.get(i)[0]) + Math.abs(galaxies.get(j)[1] - galaxies.get(i)[1]);
            }
        }

        return result;
    }
}
