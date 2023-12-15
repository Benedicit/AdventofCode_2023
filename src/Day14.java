import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day14 {
    public static void main(String[] args) {
        System.out.println(day14_Part2());
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
        Map<Integer,List<String>> memo = new HashMap<>();
        int position =0;
        for (int i = 0; i < 1e9; i++) {
            if(i>=1000) {
                memo.put(i, columns);
            }
            List<String> temp = new ArrayList<>();
            lines = new ArrayList<>();
            for (String s : columns) {
                StringBuilder stringBuilder = getStringBuilderNorth(s);
                temp.add(stringBuilder.toString());
            }
            columns = new ArrayList<>(temp);
            temp = new ArrayList<>();

            for (int j = 0; j < columns.get(0).length(); j++) {
                StringBuilder sb = new StringBuilder();
                for (String s : columns) {
                    sb.append(s.charAt(j));
                }
                lines.add(sb.toString());
            }

            for (String s : lines) {
                StringBuilder sb = getStringBuilderNorth(s);
                temp.add(sb.toString());
            }
            lines = new ArrayList<>(temp);
            temp = new ArrayList<>();
            columns = new ArrayList<>();

            for (int j = 0; j < lines.get(0).length(); j++) {
                StringBuilder sb = new StringBuilder();

                for (String s : lines) {
                    sb.append(s.charAt(j));
                }

                columns.add(sb.toString());
            }


            for (String s : columns) {
                StringBuilder stringBuilder = getStringBuilderSouth(s);
                temp.add(stringBuilder.toString());
            }

            columns = new ArrayList<>(temp);
            temp = new ArrayList<>();

            lines = new ArrayList<>();
            for (int j = 0; j < columns.get(0).length(); j++) {
                StringBuilder sb = new StringBuilder();
                for (String s : columns) {
                    sb.append(s.charAt(j));
                }
                lines.add(sb.toString());
            }

            for (String s : lines) {
                StringBuilder sb = getStringBuilderSouth(s);
                temp.add(sb.toString());
            }
            lines = new ArrayList<>(temp);
            temp = new ArrayList<>();
            columns = new ArrayList<>();
            for (int j = 0; j < lines.get(0).length(); j++) {
                StringBuilder sb = new StringBuilder();

                for (String s : lines) {
                    sb.append(s.charAt(j));
                }

                columns.add(sb.toString());
            }
            if(memo.containsValue(columns)) {
                break;
            }
        }

        int howOftenLoop = (int) ((1e9 -1000) % memo.size());
        columns = memo.get(1000+howOftenLoop);

        for (String s : columns) {
            for (int i = 0; i < s.length(); i++) {
                if(s.charAt(i)=='O') {
                    result += s.length()-i;
                }
            }
        }

        return result;
    }

    private static StringBuilder getStringBuilderSouth(String s) {
        StringBuilder stringBuilder = new StringBuilder(s);
        for (int i = s.length()-2; i >=0; i--) {

            char current = stringBuilder.charAt(i);

            if(current == 'O'){
                char charBefore = stringBuilder.charAt(i+1);
                int charAfter = i+1;

                while(charBefore== '.') {
                    stringBuilder.setCharAt(charAfter,'O');
                    stringBuilder.setCharAt(charAfter-1,'.');
                    charAfter++;
                    if(charAfter>=stringBuilder.length()) {
                        break;
                    }
                    charBefore = stringBuilder.charAt(charAfter);
                }
            }
        }
        return stringBuilder;
    }

}
