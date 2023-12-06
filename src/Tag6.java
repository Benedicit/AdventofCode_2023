import java.io.*;
import java.util.Arrays;

public class Tag6 {
    public static void main(String[] args) {
        System.out.println(day6_Part2());
    }

    public static long day6_Part1() {
        File file = new File("/Users/benedikt/Documents/Programmieren/AdventOfCode/Inputs/2023/Tag_6.txt");
        BufferedReader br;
        long result = 1;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String strLine;
        String[][] input = new String[2][];
        int idx =0;
        while (true) {
            try {
                if ((strLine = br.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String[] current = strLine.split(" {3}");
            String[] line = new String[current.length];
            for (int j=0; j<current.length;j++) {
                line[j] = current[j].trim();
            }
            if (idx==0) {
                current = Arrays.copyOfRange(line, 2, current.length);
            } else {
                current = Arrays.copyOfRange(line, 1, current.length);
            }
            input[idx++] = current;
        }
        long[] possible = new long[4];
        for (int i = 0; i < 4; i++) {
            double time = Long.parseLong(input[0][i]);
            double distance = Long.parseLong(input[1][i]);
            double sqrt = (time*time)/4 - distance;
            double minTime1 = time/2 + Math.sqrt(sqrt);
            double minTime2 = time/2 - Math.sqrt(sqrt);
            long x1 = (long) (Math.ceil(minTime1));
            long x2 = (long) (Math.ceil(minTime2));

            long temp = Math.abs(x1-x2);
            boolean x1Whole = isWholeNumber(minTime1);
            boolean x2Whole = isWholeNumber(minTime2);
            if (x1Whole && x2Whole) {
                temp--;
            }
            possible[i] = temp;
        }
        for (long l: possible) {
            result *= l;
        }
        return result;
    }
    public static boolean isWholeNumber(double num) {
        // Check if the difference between the number and its rounded value is very small
        return Math.abs(num - Math.round(num)) < 1e-10;
    }
    public static long day6_Part2() {
        File file = new File("/Users/benedikt/Documents/Programmieren/AdventOfCode/Inputs/2023/Tag_6.txt");
        BufferedReader br;
        long result = 1;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String strLine;
        String[][] input = new String[2][];
        int idx = 0;
        while (true) {
            try {
                if ((strLine = br.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String[] current = strLine.split(" {3}");
            String[] line = new String[current.length];
            for (int j = 0; j < current.length; j++) {
                line[j] = current[j].trim();
            }
            if (idx == 0) {
                current = Arrays.copyOfRange(line, 2, current.length);
            } else {
                current = Arrays.copyOfRange(line, 1, current.length);
            }
            input[idx++] = current;
        }
        StringBuilder timeString = new StringBuilder();
        StringBuilder distanceString = new StringBuilder();
        String[] timeStrings = input[0];
        String[] distanceStrings = input[1];
        for (String s: timeStrings) {
            timeString.append(s);
        }
        for (String s: distanceStrings) {
            distanceString.append(s);
        }
            double time = Long.parseLong(timeString.toString());
            double distance = Long.parseLong(distanceString.toString());
            double sqrt = (time * time) / 4 - distance;
            double minTime1 = time / 2 + Math.sqrt(sqrt);
            double minTime2 = time / 2 - Math.sqrt(sqrt);
            long x1 = (long) (Math.ceil(minTime1));
            long x2 = (long) (Math.ceil(minTime2));

            long temp = Math.abs(x1 - x2);
            boolean x1Whole = isWholeNumber(minTime1);
            boolean x2Whole = isWholeNumber(minTime2);
            if (x1Whole && x2Whole) {
                temp--;
            }
            result = temp;
        return result;
    }
}
