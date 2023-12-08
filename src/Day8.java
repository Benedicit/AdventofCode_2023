import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day8 {
    public static void main(String[] args) {
        System.out.println(day8_Part2());
    }
    public static long day8_Part1() {
        File file = new File("/Users/benedikt/Documents/Programmieren/AdventOfCode/Inputs/2023/Tag_8.txt");
        BufferedReader br;
        long result = 0;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String strLine;

        int currentLine = 1;
        String firstWord = "";
        String pathFinding = "";

        Map<String,String> wordToLeft = new HashMap<>();
        Map<String,String> wordToRight = new HashMap<>();
        String currentWord = "";
        while (true) {
            try {
                if ((strLine = br.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (currentLine==1) {
                pathFinding = strLine;
            } else if (strLine.isEmpty()) {
                currentLine++;
                continue;
            } else {
                String[] line = strLine.split(" = ");
                String leftRight = line[1].trim().replaceAll("[()]", "");
                currentWord = line[0].trim();
                String[] temp = leftRight.split(", ");
                String left = temp[0].trim();
                String right = temp[1].trim();
                wordToLeft.put(currentWord, left);
                wordToRight.put(currentWord, right);
            }
            currentLine++;
        }
        boolean found = false;
        firstWord = "AAA";
        while (!found) {
            int i=0;
            if(found) {
                break;
            }
            while (i < pathFinding.length()) {
                char whereTo = pathFinding.charAt(i);
                if (whereTo == 'L') {
                    firstWord = wordToLeft.get(firstWord);
                } else {
                    firstWord = wordToRight.get(firstWord);
                }
                result++;
                if(firstWord.equals("ZZZ")) {
                    found = true;
                    break;
                }
                i++;
            }
        }
        return result;

    }
    public static long day8_Part2() {
        File file = new File("/Users/benedikt/Documents/Programmieren/AdventOfCode/Inputs/2023/Tag_8.txt");
        BufferedReader br;
        long result = 0;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String strLine;

        int currentLine = 1;
        String firstWord;
        String pathFinding = "";
        List<String> ghosts = new ArrayList<>();
        Map<String,String> wordToLeft = new HashMap<>();
        Map<String,String> wordToRight = new HashMap<>();
        String currentWord;
        while (true) {
            try {
                if ((strLine = br.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (currentLine==1) {
                pathFinding = strLine;
            } else if (strLine.isEmpty()) {
                currentLine++;
                continue;
            } else {
                String[] line = strLine.split(" = ");
                String leftRight = line[1].trim().replaceAll("[()]", "");
                currentWord = line[0].trim();
                String[] temp = leftRight.split(", ");
                String left = temp[0].trim();
                String right = temp[1].trim();
                wordToLeft.put(currentWord, left);
                wordToRight.put(currentWord, right);
                if(currentWord.charAt(currentWord.length()-1) == 'A') {
                    ghosts.add(currentWord);
                }
            }
            currentLine++;
        }
        boolean found = false;
        //firstWord = "AAA";
        for (String s: ghosts) {
            result =0;
            firstWord = s;
            found = false;
            while (!found) {
                int i = 0;
                if (found) {
                    break;
                }
                while (i < pathFinding.length()) {
                    char whereTo = pathFinding.charAt(i);
                    if (whereTo == 'L') {
                        firstWord = wordToLeft.get(firstWord);
                    } else {
                        firstWord = wordToRight.get(firstWord);
                    }
                    result++;
                    if (firstWord.charAt(firstWord.length()-1) == 'Z') {
                        found = true;
                        break;
                    }
                    i++;
                }
            }
            System.out.println(result);
        }
        return result;
    }

}
