import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Tag2 {
    public static void main(String[] args) {
        System.out.println(gamesSuccess());
        System.out.println(powerOfSets());
    }

    public static int gamesSuccess() {
        File file = new File("/Users/benedikt/Documents/Programmieren/AdventOfCode/Inputs/2023/Tag_2.txt");
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String strLine;
        Map<Character,Integer> map = Map.of('0',0,'1',1,
                '2',2,'3',3,'4',4,'5',5,
                '6',6,'7',7,'8',8,'9',9);
        Map<Character,Integer> maxCubes = Map.of('r',12,'g',13,'b',14);
        Map<Character,Integer> length = Map.of('r',3,'g',5,'b',4);
        int numberGames =0;
        Character char1;
        Character char2;
        int id =1;
        while (true) {
            try {
                if ((strLine = br.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            boolean possible = true;
            int index =8;
            while (index<strLine.length()-1) {
                int currentCubes =0;
                char1 = strLine.charAt(index);
                char2 = strLine.charAt(index+1);
                if(!map.containsKey(char1)) {
                    index++;
                } else {
                    if (map.containsKey(char1) && map.containsKey(char2)) {
                        currentCubes = map.get(char1) * 10 + map.get(char2);
                        index += 3;
                    } else if (map.containsKey(char1) && !map.containsKey(char2)) {
                        currentCubes = map.get(char1);
                        index += 2;
                    }
                    if (maxCubes.containsKey(strLine.charAt(index)) && currentCubes > maxCubes.get(strLine.charAt(index))) {
                        possible = false;
                        break;
                    } else {
                        index += length.get(strLine.charAt(index));
                    }
                }
            }
            if (possible) {
                numberGames += id;
            }
            id++;
        }
        return numberGames;
    }
    public static long powerOfSets() {
        File file = new File("/Users/benedikt/Documents/Programmieren/AdventOfCode/Inputs/2023/Tag_2.txt");
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String strLine;
        Map<Character,Integer> map = Map.of('0',0,'1',1,
                '2',2,'3',3,'4',4,'5',5,
                '6',6,'7',7,'8',8,'9',9);
        Map<Character,Integer> maxCubes = Map.of('r',12,'g',13,'b',14);
        Map<Character,Integer> length = Map.of('r',3,'g',5,'b',4);

        Character char1;
        Character char2;
        long powerOfSets =0;

        while (true) {
            try {
                if ((strLine = br.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            boolean possible = true;
            int index =8;
            List<Integer> reds = new ArrayList<>();
            List<Integer> greens = new ArrayList<>();
            List<Integer> blues = new ArrayList<>();
            while (index<strLine.length()-1) {
                int currentCubes =0;
                char1 = strLine.charAt(index);
                char2 = strLine.charAt(index+1);
                if(!map.containsKey(char1)) {
                    index++;
                } else {
                    if (map.containsKey(char1) && map.containsKey(char2)) {
                        currentCubes = map.get(char1) * 10 + map.get(char2);
                        index += 3;
                    } else if (map.containsKey(char1) && !map.containsKey(char2)) {
                        currentCubes = map.get(char1);
                        index += 2;
                    }
                    if (maxCubes.get(strLine.charAt(index)) == 12) {
                        reds.add(currentCubes);
                    } else if (maxCubes.get(strLine.charAt(index)) == 13) {
                        greens.add(currentCubes);
                    } else if (maxCubes.get(strLine.charAt(index)) == 14){
                        blues.add(currentCubes);
                    }
                    index += length.get(strLine.charAt(index));
                }
            }
            Integer redMin = reds.stream().max(Integer::compare).get();
            Integer greenMin = greens.stream().max(Integer::compare).get();
            Integer blueMin = blues.stream().max(Integer::compare).get();
            powerOfSets += (long) redMin * greenMin * blueMin;
        }
        return powerOfSets;
    }


}
