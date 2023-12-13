import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day13 {

    public static void main(String[] args) {
        System.out.println(day13_Part2());
    }
    public static long day13_Part1() {
        File file = new File("/Users/benedikt/Documents/Programmieren/AdventOfCode/Inputs/2023/Tag_13.txt");
        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String strLine;

        int currentLine = 0;
        long result =0;
        List<String> lines = new ArrayList<>();
        while (true) {
            try {
                if ((strLine = br.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if(!strLine.isEmpty()) {
                lines.add(strLine);
            } else {
                currentLine++;
                result += helper(lines);
                lines = new ArrayList<>();
            }
        }
        result += helper(lines);
        return result;
    }

    public static long helper(List<String> lines) {
        long result = 0;
        List<Integer> allHorizontal = new ArrayList<>();
        for (int i = 0; i < lines.size() - 1; i++) {
            String first = lines.get(i);
            String second = lines.get(i + 1);
            if (first.equals(second)) {
                allHorizontal.add(i+1);
            }
        }
        boolean done = false;
        for (Integer index: allHorizontal) {
            int left = index-2;
            int right = index+1;
            boolean equal = true;
            while(left>=0 && right<lines.size()) {
                String leftSide = lines.get(left);
                String rightSide = lines.get(right);
                if(!leftSide.equals(rightSide)) {
                    equal = false;
                    break;
                }
                left--;
                right++;
            }
            if(equal) {
                result += 100L * index;
                done = true;
            }

        }

        List<String> columns = new ArrayList<>();

            for (int i = 0; i < lines.get(0).length(); i++) {
                StringBuilder sb = new StringBuilder();

                for (String s : lines) {
                    sb.append(s.charAt(i));
                }
                columns.add(sb.toString());
            }

            List<Integer> allVertical = new ArrayList<>();
            for (int i = 0; i < columns.size() - 1; i++) {
                String first = columns.get(i);
                String second = columns.get(i + 1);
                if (first.equals(second)) {
                    allVertical.add(i + 1);
                }
            }
            if(!done) {
                for (Integer index : allVertical) {
                    int left = index - 2;
                    int right = index + 1;
                    boolean equal = true;
                    while (left >= 0 && right < columns.size()) {
                        String leftSide = columns.get(left);
                        String rightSide = columns.get(right);
                        if (!leftSide.equals(rightSide)) {
                            equal = false;
                            break;
                        }
                        left--;
                        right++;
                    }
                    if (equal) {
                        result += index;
                    }
                }
            }
        return result;
    }
    public static long day13_Part2() {
        File file = new File("/Users/benedikt/Documents/Programmieren/AdventOfCode/Inputs/2023/Tag_13.txt");
        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String strLine;

        int currentLine = 0;
        long result =0;
        List<String> lines = new ArrayList<>();
        while (true) {
            try {
                if ((strLine = br.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if(!strLine.isEmpty()) {
                lines.add(strLine);
            } else {
                currentLine++;
                result = getResult(result, lines);
                lines = new ArrayList<>();
            }
        }
        result = getResult(result, lines);
        return result;
    }

    public static long getResult(long result, List<String> lines) {
        String smudgedLine = "";
        int indexOfSmudgedLine =0;
        for (int i =0; i<lines.size()-1; i++) {
            boolean noSecond = true;
            String current = lines.get(i);
            for (int j = i+1; j < lines.size(); j++) {
                String second = lines.get(j);
                if(current.equals(second)) {
                    noSecond = false;
                    break;
                }
            }
            if(noSecond) {
                smudgedLine = current;
                indexOfSmudgedLine = i;
                break;
            }
        }
        boolean lineWithOneWrong = false;
        for (int i = indexOfSmudgedLine; i >= 0 ; i--) {
            String leftSide = lines.get(i);
            String temp = compare(smudgedLine,leftSide);
            if(!temp.equals(smudgedLine)) {
                lineWithOneWrong = true;
                smudgedLine = temp;
                break;
            }
        }
        if(!lineWithOneWrong) {
            for (int i = indexOfSmudgedLine; i <lines.size() ; i++) {
                String rightSide = lines.get(i);
                String temp = compare(smudgedLine,rightSide);
                if(!temp.equals(smudgedLine)) {
                    smudgedLine = temp;
                    break;
                }
            }
        }
        lines.remove(indexOfSmudgedLine);
        lines.add(indexOfSmudgedLine,smudgedLine);

        result += helper(lines);
        return result;
    }

    public static String compare(String s1,String s2) {
        int matchCount =0;
        int indexOfWrongChar = 0;
        for (int i = 0; i < s1.length(); i++) {
            if(s1.charAt(i) == s2.charAt(i)) {
                matchCount++;
            } else {
                indexOfWrongChar = i;
            }
        }
        if(s1.length()-matchCount == 1) {
            StringBuilder sb = new StringBuilder(s1);
            if(s1.charAt(indexOfWrongChar) == '.') {
               sb.setCharAt(indexOfWrongChar,'#');


            } else {
                sb.setCharAt(indexOfWrongChar,'.');
            }
            s1 = sb.toString();
        }
        return s1;
    }
}
