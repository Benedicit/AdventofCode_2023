import java.io.*;
import java.util.*;

public class Day7 {
    public static void main(String[] args) {
        System.out.println(day7_Part2());
    }
    public static long day7_Part1() {
        File file = new File("/Users/benedikt/Documents/Programmieren/AdventOfCode/Inputs/2023/Tag_7.txt");
        BufferedReader br;
        long result = 0;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Map<Character,Integer> map = new java.util.HashMap<>(Map.of('1', 1,
                '2', 2, '3', 3, '4', 4, '5', 5,
                '6', 6, '7', 7, '8', 8, '9', 9, 'T', 10));
        map.put('J',11);
        map.put('Q',12);
        map.put('K',13);
        map.put('A',14);
        String strLine;


        List<long[]> allSame = new ArrayList<>();
        List<long[]> four = new ArrayList<>();
        List<long[]> fullHouse = new ArrayList<>();
        List<long[]> three = new ArrayList<>();
        List<long[]> twoDoubles = new ArrayList<>();
        List<long[]> doubles = new ArrayList<>();
        List<long[]> card = new ArrayList<>();

        while (true) {
            try {
                if ((strLine = br.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            long value =0;

            String[] input = strLine.split(" ");
            String hand = input[0];
            StringBuilder sb = new StringBuilder();
            hand.chars().distinct().forEach(c -> sb.append((char) c));
            String distinct = sb.toString();
            Map<Character, Long> countChars = new HashMap<>();
            long bet = Long.parseLong(input[1]);

            for (int i = 0; i < distinct.length(); i++) {
                char current = distinct.charAt(i);
                long num = hand.chars().filter(x -> x == current).count();
                countChars.put(current,num);
            }
            long offset = (long) Math.pow(15,5);
            switch (distinct.length()) {
                case 1 -> {
                    value = getValue(value,hand,map,offset);
                    allSame.add(new long[]{value,bet});
                }
                case 2 -> {
                    value = getValue(value,hand,map,offset);
                    long[] counts = new long[3];
                    for (int i = 0; i < 2; i++) {
                        counts[i] = countChars.get(distinct.charAt(i));
                    }
                    Arrays.sort(counts);
                    value = getValue(value,hand,map,offset);
                    if(counts[2] == 4) {

                        four.add(new long[]{value,bet});
                    } else {

                        fullHouse.add(new long[]{value,bet});
                    }
                    }
                case 3 -> {
                    long[] counts = new long[3];
                    for (int i = 0; i < 3; i++) {
                        counts[i] = countChars.get(distinct.charAt(i));
                    }
                    Arrays.sort(counts);
                    value = getValue(value,hand,map,offset);
                    if(counts[2] == 2) {

                        twoDoubles.add(new long[]{value,bet});
                    } else {

                        three.add(new long[]{value,bet});
                    }
                    }
                case 4-> {
                    value = getValue(value,hand,map,offset);
                    doubles.add(new long[]{value,bet});
                    }
                case 5 -> {
                    value = getValue(value, hand, map, offset);
                    card.add(new long[]{value,bet});
                }
            }

        }


        int rank = 1;
        for (List<long[]> longs : Arrays.asList(card, doubles, twoDoubles, three, fullHouse, four, allSame)) {
            result = getRank(longs,rank,result);
            rank += longs.size();
        }

        return result;
    }
    public static long getRank(List<long[]> list,int rank,long result) {
        if (list != null) {
            list.sort(Comparator.comparingLong(value -> value[0]));
            for (long[] l : list) {
                long bet = l[1];
                result += bet * rank;
                rank++;
            }
        }
        return result;
    }
    public static long getValue(long value, String s,Map<Character,Integer> map, long offset) {
        for (int i = 0; i < s.length(); i++) {
            value += map.get(s.charAt(i)) *offset;
            offset /= 15;
            if(offset==0) {
                offset =1;
            }
        }
        return value;
    }
    public static long day7_Part2() {
        File file = new File("/Users/benedikt/Documents/Programmieren/AdventOfCode/Inputs/2023/Tag_7.txt");
        BufferedReader br;
        long result = 0;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Map<Character,Integer> map = new java.util.HashMap<>(Map.of(
                '2', 2, '3', 3, '4', 4, '5', 5,
                '6', 6, '7', 7, '8', 8, '9', 9, 'T', 10));
        map.put('J',0);
        map.put('Q',11);
        map.put('K',12);
        map.put('A',13);
        String strLine;

        List<long[]> allSame = new ArrayList<>();
        List<long[]> four = new ArrayList<>();
        List<long[]> fullHouse = new ArrayList<>();
        List<long[]> three = new ArrayList<>();
        List<long[]> twoDoubles = new ArrayList<>();
        List<long[]> doubles = new ArrayList<>();
        List<long[]> card = new ArrayList<>();
        while (true) {
            try {
                if ((strLine = br.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            long value =0;

            String[] input = strLine.split(" ");
            String hand = input[0];
            StringBuilder sb = new StringBuilder();
            hand.chars().distinct().forEach(c -> sb.append((char) c));
            String distinct = sb.toString();
            Map<Character, Long> countChars = new HashMap<>();
            long bet = Long.parseLong(input[1]);

            for (int i = 0; i < distinct.length(); i++) {
                char current = distinct.charAt(i);
                long num = hand.chars().filter(x -> x == current).count();
                countChars.put(current,num);
            }
            long whatHand =0;
            boolean actuallyThree = false;
            boolean actuallyFourth = false;
            if(distinct.indexOf('J') != -1 && distinct.length() != 1) {
                long jCount = countChars.get('J');
                switch ((int) jCount) {
                    case 1-> {
                        long[] counts = new long[distinct.length()];
                        for (int i = 0; i < distinct.length(); i++) {
                            counts[i] = countChars.get(distinct.charAt(i));
                        }
                        Arrays.sort(counts);
                        if (counts[counts.length-1] == 2 && distinct.length()==4){
                            actuallyThree = true;
                            whatHand = 3;
                        } else if (counts[counts.length-1] == 2 && distinct.length()==3){
                            whatHand = 4;
                        } else if (counts[counts.length-1] == 3){
                            whatHand = 4;
                            actuallyFourth = true;
                        } else {
                            whatHand = counts[counts.length-1] + 1;
                        }
                    }
                    case 2 -> {
                        long[] counts = new long[distinct.length()];
                        for (int i = 0; i < distinct.length(); i++) {
                            counts[i] = countChars.get(distinct.charAt(i));
                        }
                        Arrays.sort(counts);
                        if(distinct.length() == 2) {
                            whatHand = 5;
                        } else if (distinct.length() == 3 &&counts[counts.length-1] ==2) {
                            whatHand = 4;
                            actuallyFourth = true;
                        }else if (distinct.length() == 3 &&counts[counts.length-1] ==3) {
                            whatHand = 5;
                        } else if (distinct.length() == 4) {
                            whatHand = 3;
                            actuallyThree = true;
                        }
                    }
                    case 3-> {
                        if (distinct.length() == 2) {
                            whatHand = 5;
                        } else if (distinct.length() == 3) {
                            whatHand = 4;
                            actuallyFourth = true;
                        }
                    }
                    default -> whatHand = 5;
                }
            } else {
                whatHand = 6-distinct.length();
            }
            long offset = (long) Math.pow(15,5);
            switch ((int) whatHand) {
                case 5 -> {
                    value = getValue(value,hand,map,offset);
                    allSame.add(new long[]{value,bet});
                }
                case 4 -> {
                    value = getValue(value,hand,map,offset);
                    long[] counts = new long[3];
                    for (int i = 0; i < 2; i++) {
                        counts[i] = countChars.get(distinct.charAt(i));
                    }
                    Arrays.sort(counts);
                    value = getValue(value,hand,map,offset);
                    if(counts[counts.length-1] == 4 || actuallyFourth) {

                        four.add(new long[]{value,bet});
                    } else {

                        fullHouse.add(new long[]{value,bet});
                    }
                }
                case 3 -> {
                    long[] counts = new long[distinct.length()];
                    for (int i = 0; i < distinct.length(); i++) {
                        counts[i] = countChars.get(distinct.charAt(i));
                    }
                    Arrays.sort(counts);
                    value = getValue(value,hand,map,offset);
                    if(counts[counts.length-1] == 2 && !actuallyThree) {

                        twoDoubles.add(new long[]{value,bet});
                    } else {

                        three.add(new long[]{value,bet});
                    }
                }
                case 2-> {
                    value = getValue(value,hand,map,offset);
                    doubles.add(new long[]{value,bet});
                }
                case 1 -> {
                    value = getValue(value, hand, map, offset);
                    card.add(new long[]{value,bet});
                }
            }

        }
        int rank = 1;
        for (List<long[]> longs : Arrays.asList(card, doubles, twoDoubles, three, fullHouse, four, allSame)) {
            result = getRank(longs,rank,result);
            rank += longs.size();
        }

        return result;
    }


}
