import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Day14 {
    public static void main(String[] args) {
        System.out.println(day14_Part1());
    }
    public static long day14_Part1() {
        File file = new File("/Users/benedikt/Documents/Programmieren/AdventOfCode/Inputs/2023/Tag_14.txt");
        BufferedReader br;
        long result = 0;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String strLine;

        List<String> lines  =  new ArrayList<>();
        while (true) {
            try {
                if ((strLine = br.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            lines.add(strLine);
        }
        List<String> columns = new ArrayList<>();

        for (int i = 0; i < lines.get(0).length(); i++) {
            StringBuilder sb = new StringBuilder();

            for (String s : lines) {
                sb.append(s.charAt(i));
            }

            columns.add(sb.toString());
        }
        List<String> temp = new ArrayList<>();
        for(String s: columns) {
            StringBuilder stringBuilder = getStringBuilderNorth(s);
            temp.add(stringBuilder.toString());
        }


        for (String s : temp) {
            for (int i = 0; i < s.length(); i++) {
                if(s.charAt(i)=='O') {
                    result += s.length()-i;
                }
            }
        }

        return result;
    }

    private static StringBuilder getStringBuilderNorth(String s) {
        StringBuilder stringBuilder = new StringBuilder(s);
        for (int i = 1; i < s.length(); i++) {

            char current = stringBuilder.charAt(i);

            if(current == 'O'){
                char charBefore = stringBuilder.charAt(i-1);
                int indexCharBefore = i-1;

                while(charBefore == '.') {
                    stringBuilder.setCharAt(indexCharBefore,'O');
                    stringBuilder.setCharAt(indexCharBefore+1,'.');
                    indexCharBefore--;
                    if(indexCharBefore<0) {
                        break;
                    }
                    charBefore = stringBuilder.charAt(indexCharBefore);
                }
            }
        }
        return stringBuilder;
    }

    public static long day14_Part2() {
        File file = new File("/Users/benedikt/Documents/Programmieren/AdventOfCode/Inputs/2023/Tag_14.txt");
        BufferedReader br;
        long result = 0;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String strLine;

        List<String> lines  =  new ArrayList<>();
        while (true) {
            try {
                if ((strLine = br.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            lines.add(strLine);
        }
        List<String> columns = new ArrayList<>();

        for (int i = 0; i < lines.get(0).length(); i++) {
            StringBuilder sb = new StringBuilder();

            for (String s : lines) {
                sb.append(s.charAt(i));
            }

            columns.add(sb.toString());
        }
        List<String> temp = new ArrayList<>();
        for(String s: columns) {
            StringBuilder stringBuilder = getStringBuilderNorth(s);
            temp.add(stringBuilder.toString());
        }


        for (String s : temp) {
            for (int i = 0; i < s.length(); i++) {
                if(s.charAt(i)=='O') {
                    result += s.length()-i;
                }
            }
        }

        return result;
    }

}
