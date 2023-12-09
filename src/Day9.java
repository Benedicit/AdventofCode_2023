import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day9 {
    public static void main(String[] args) {
        System.out.println(day9_Part2());
    }
    public static long day9_Part1() {
        File file = new File("/Users/benedikt/Documents/Programmieren/AdventOfCode/Inputs/2023/Tag_9.txt");
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
            List<Long> numbers = new ArrayList<>();

            String[] nums = strLine.split(" ");
            for (String s:nums) {
                numbers.add(Long.parseLong(s.trim()));
            }
            List<Long> resultList = helper(numbers);
            result += resultList.get(resultList.size()-1);
        }
        return result;
    }
    public static List<Long> helper(List<Long> list) {
        if(list.stream().distinct().count() ==1) {
            list.add(list.get(list.size()-1));
        } else {
            List<Long> after = new ArrayList<>();
            for (int i = 0; i < list.size()-1; i++) {
                long first = list.get(i);
                long second = list.get(i+1);
                long diff = -first+second;
                after.add(diff);
            }
            helper(after);
            long lastDigitDiff = after.get(after.size()-1)+list.get(list.size()-1);
            list.add(lastDigitDiff);
        }
        return list;
    }


    public static long day9_Part2() {
        File file = new File("/Users/benedikt/Documents/Programmieren/AdventOfCode/Inputs/2023/Tag_9.txt");
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
            List<Long> numbers = new ArrayList<>();

            String[] nums = strLine.split(" ");
            for (String s:nums) {
                numbers.add(Long.parseLong(s.trim()));
            }
            List<Long> resultList = helperPart2(numbers);
            result += resultList.get(0);
        }
        return result;
    }

    public static List<Long> helperPart2(List<Long> list) {
        if(list.stream().distinct().count() ==1) {
            list.add(list.get(list.size()-1));
        } else {
            List<Long> after = new ArrayList<>();
            for (int i = 0; i < list.size()-1; i++) {
                long first = list.get(i);
                long second = list.get(i+1);
                long diff = second-first;
                after.add(diff);
            }
            helperPart2(after);
            long lastDigitDiff = list.get(0)-after.get(0);
            list.add(0, lastDigitDiff);
        }
        return list;
    }

}
