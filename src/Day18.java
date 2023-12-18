import java.io.*;
import java.util.*;

public class Day18 {
    public static void main(String[] args) {
        System.out.println(day18_Part2());
    }
    public static long day18_Part1() {
        File file = new File("/Users/benedikt/Documents/Programmieren/AdventOfCode/Inputs/2023/Tag_18.txt");
        BufferedReader br;
        long result = 0;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        String strLine;
        List<String[]> dirToLength = new ArrayList<>();
        long countPointsOutside = 0;
        while (true) {
            try {
                if ((strLine = br.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String[] strings = strLine.split(" ");
            dirToLength.add(new String[] {strings[0],strings[1]});
        }
        long x =0;
        long y = 0;
        Map<String,Integer> upOrDown = Map.of("L",-1,"R",1,"U",1,"D",-1);
        List<long[]> cords = new ArrayList<>();
        for (var color : dirToLength) {
            String a = color[0];
            switch (a) {
                case "L","R" -> {
                    countPointsOutside += Long.parseLong((color)[1]);
                    x += upOrDown.get(a) * Long.parseLong((color)[1]);
                }
                case "U","D" -> {
                    countPointsOutside += Long.parseLong((color)[1]);
                    y += upOrDown.get(a) * Long.parseLong((color)[1]);
                }
            }
            cords.add(new long[]{x,y});
        }
        long temp = area(cords,countPointsOutside);
        result = countPointsOutside + temp;
        return result;
    }

    public static long area(List<long[]> cords, long r) {
        long area =0;
        long result;
        for (int i = 1; i < cords.size()-1; i++) {
            area += cords.get(i)[0] * (cords.get(i+1)[1] - cords.get(i-1)[1]);
        }
        area += cords.get(1)[0] *(cords.get(2)[1]-cords.get(cords.size()-1)[1]);
        area += cords.get(cords.size()-1)[0] * (cords.get(1)[1]-cords.get(cords.size()-2)[1]);
        area /= 2;
        // area *= -1;
        result = Math.abs(area) - (r/2) +1;

        return result;
    }
    public static long day18_Part2() {
        File file = new File("/Users/benedikt/Documents/Programmieren/AdventOfCode/Inputs/2023/Tag_18.txt");
        BufferedReader br;
        long result = 0;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        String strLine;
        List<String[]> dirToLength = new ArrayList<>();
        long countPointsOutside = 0;
        while (true) {
            try {
                if ((strLine = br.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String[] strings = strLine.split(" ");
            String newCode = strings[2];
            newCode = newCode.replaceAll("[()]","").substring(1);
            long length = HexFormat.fromHexDigits(newCode.subSequence(0,newCode.length()-1));
            Map<Character,String> encode = Map.of('0',"R",'1',"D",'2',"L",'3',"U");
            dirToLength.add(new String[] {encode.get(newCode.charAt(newCode.length()-1)),""+length});
        }
        long x =0;
        long y = 0;
        Map<String,Long> upOrDown = Map.of("L",-1L,"R",1L,"U",1L,"D",-1L);
        List<long[]> cords = new ArrayList<>();
        for (var color : dirToLength) {
            String a = color[0];
            switch (a) {
                case "L","R" -> {
                    countPointsOutside += Long.parseLong((color)[1]);
                    x += upOrDown.get(a) * Long.parseLong((color)[1]);
                }
                case "U","D" -> {
                    countPointsOutside += Integer.parseInt((color)[1]);
                    y += upOrDown.get(a) * Integer.parseInt((color)[1]);
                }
            }
            cords.add(new long[]{x,y});
        }
        long temp = area(cords,countPointsOutside);
        result = countPointsOutside + temp;
        return result;
    }

}
