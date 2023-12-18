import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day12 {
    public static void main(String[] args) {
        System.out.println(day12_Part1());
    }
    public static long day12_Part1() {
        File file = new File("/Users/benedikt/Documents/Programmieren/AdventOfCode/Inputs/2023/Tag_12.txt");
        BufferedReader br;
        long result = 0;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String strLine;

        while (true) {
            try {
                if ((strLine = br.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String[] splitted = strLine.split(" ");
            String leftPart = splitted[0];
            String rightPart = splitted[1];
            String[] numbers = rightPart.split(",");
            List<String> nums = new ArrayList<>(Arrays.stream(numbers).toList());
            List<Integer> idxOfSequ = new ArrayList<>();
            for (int i=0; i<nums.size();i++) {
                String s = nums.get(i);
                int length = Integer.parseInt(s);
                StringBuilder sb = new StringBuilder();
                if(i==nums.size()-1) {
                    sb.append(".*[^#]");
                    sb.append("#".repeat(Math.max(0, length)));
                } else {
                    sb.append(".*[^#]");
                    sb.append("#".repeat(Math.max(0, length))).append("\\.");
                }
                int index = -1;
                Pattern pattern = Pattern.compile(sb.toString());
                Matcher matcher = pattern.matcher(leftPart);
                if (matcher.find()) {

                    index = leftPart.indexOf("#".repeat(Math.max(0, length)));
                    leftPart = getString(leftPart, index, length);
                    idxOfSequ.add(i);
                }

            }
            List<String> helper = new ArrayList<>();
            for (Integer i: idxOfSequ) {
                helper.add(nums.get(i));
            }
            for (String s: helper) {
                nums.remove(s);
            }
            int permut = 0;
            while(leftPart.contains("?")) {
                int index = 0;
                while (leftPart.charAt(index) != '#' && leftPart.charAt(index) != '?') {
                    index++;
                }
                int start = index;
                StringBuilder sb = new StringBuilder(leftPart);
                StringBuilder checkIfPresent = new StringBuilder();
                if(nums.isEmpty()) {
                    break;
                }
                int l = Integer.parseInt(nums.get(0));
                String currentSequence = checkIfPresent.append("#".repeat(Math.max(0, l))).toString();
                if (leftPart.charAt(index) == '?') {
                    sb.setCharAt(index, '#');
                }
                while (!sb.toString().contains(currentSequence)) {
                    index++;
                    sb.setCharAt(index, '#');
                }
                nums.remove(0);
                int temp = index + 1;
                int questionMarkCount = 0;
                while (temp < leftPart.length() && leftPart.charAt(temp) == '?') {
                    questionMarkCount++;
                    temp++;
                }
                int countDifferentSets = 1;
                int initialLength =0;
                while (!nums.isEmpty() && Integer.parseInt(nums.get(0)) < questionMarkCount) {
                    index++;
                    sb.setCharAt(index, '.');
                    l = Integer.parseInt(nums.get(0));
                    currentSequence = "#".repeat(Math.max(0, l));
                    for (int i = 0; i < currentSequence.length(); i++) {
                        index++;
                        sb.setCharAt(index, '#');
                    }
                    temp = index + 1;
                    questionMarkCount = 0;
                    while (temp < leftPart.length() && leftPart.charAt(temp) == '?') {
                        questionMarkCount++;
                        temp++;
                    }
                    initialLength = temp-start;
                    countDifferentSets++;
                }
                leftPart = getString(leftPart, index+1, l);
                if (questionMarkCount > 0) {
                    int n = countDifferentSets;
                    while (n > 0) {
                        if (permut == 0) {
                            permut = 1;
                        }
                        permut *= n;
                        n--;
                    }
                    permut += initialLength;
                }
            }
            if(permut==0) {
                result++;
            } else {
                result += permut;
            }
        }
       return result;
    }

    public static String getString(String leftPart, int index, int l) {
        String replace;
        StringBuilder sb = new StringBuilder(leftPart);
        if (index > 0 && (index + l + 1) < leftPart.length()) {
            index--;
            for (int i = 0; i < l+1; i++) {
                sb.deleteCharAt(index);
            }
            replace = sb.toString();
           // replace = leftPart.replace(leftPart.substring(index - 1, index + l), "");
        } else if (index > 0 && (index + l +1) >= leftPart.length()) {
            /*
            index--;
            for (int i = 0; i < l; i++) {
                sb.deleteCharAt(index);
            }
            replace = sb.toString();

             */
           replace = leftPart.replace(leftPart.substring(index - 1), "");
        } else {
            index =0;
            for (int i = 0; i < l; i++) {
                sb.deleteCharAt(index);
            }
            replace = sb.toString();
            //replace = leftPart.replace(leftPart.substring(index, index + l + 1), "");
        }
        return replace;
    }

    public static long day12_Part2() {
        File file = new File("/Users/benedikt/Documents/Programmieren/AdventOfCode/Inputs/2023/Tag_12.txt");
        BufferedReader br;
        long result = 0;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String strLine;


        while (true) {
            try {
                if ((strLine = br.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }
}
