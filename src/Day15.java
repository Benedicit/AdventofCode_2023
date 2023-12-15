import java.io.*;
import java.util.*;

public class Day15 {
    public static void main(String[] args) {
        System.out.println(day15_Part2());
    }
    public static long day15_Part1() {
        File file = new File("/Users/benedikt/Documents/Programmieren/AdventOfCode/Inputs/2023/Tag_15.txt");
        BufferedReader br;
        long result = 0;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String strLine;
        String[] subsets = new String[0];
        while (true) {
            try {
                if ((strLine = br.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            subsets = strLine.split(",");
        }

        for (String s: subsets) {
            int value = 0;
            for (int i = 0; i < s.length(); i++) {
                int ascii = s.charAt(i);
                value += ascii;
                value *= 17;
                value %= 256;
            }
            result += value;
        }

        return result;
    }
    public static long day15_Part2() {
        File file = new File("/Users/benedikt/Documents/Programmieren/AdventOfCode/Inputs/2023/Tag_15.txt");
        BufferedReader br;
        long result = 0;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String strLine;
        String[] subsets = new String[0];
        while (true) {
            try {
                if ((strLine = br.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            subsets = strLine.split(",");
        }
        List<ArrayList<String>> boxes = new ArrayList<>();
        for (int i = 0; i < 256; i++) {
            boxes.add(new ArrayList<>());
        }
        Map<String,Integer> v = new HashMap<>();

        for (String s: subsets) {
            int value = 0;
            String[] newString = new String[0];
            boolean remove = false;
            if(s.contains("=")) {
                char lens = s.charAt(s.indexOf('=')+1);
                newString = s.split("=");
                String label = newString[0];
                for (int i = 0; i < label.length(); i++) {
                    int ascii = label.charAt(i);
                    value += ascii;
                    value *= 17;
                    value %= 256;
                }
                v.put(label, Character.getNumericValue(lens));
                if(!boxes.get(value).contains(label)) {
                    boxes.get(value).add(label);
                }

            } else {
                newString = s.split("-");
                for (var box : boxes) {
                    box.remove(newString[0]);
                }
                v.remove(newString[0]);
            }




        }
        for (int j=0; j< boxes.size(); j++) {
            int slot =1;
            for (int i = 0; i < boxes.get(j).size(); i++) {

                result += (long) slot * v.get(boxes.get(j).get(i)) * (j+1);
                slot++;
            }
        }
        return result;
    }
}
