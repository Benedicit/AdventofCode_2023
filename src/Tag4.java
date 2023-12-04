import java.io.*;
import java.util.*;

public class Tag4 {
    public static void main(String[] args) {
        System.out.println(day4_Part2());
    }
    public static int day4() {
        File file = new File("/Users/benedikt/Documents/Programmieren/AdventOfCode/Inputs/2023/Tag_4.txt");
        BufferedReader br;
        int result = 0;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        String strLine;
        String leftSide;
        String[] strLineNumbers;
        String rightSide;
        int currentLine = 1;
        while (true) {
            try {
                if ((strLine = br.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
          strLineNumbers = strLine.split("\\|");
            leftSide = strLineNumbers[0].substring(10,strLineNumbers[0].length()-1);
            rightSide = strLineNumbers[1].substring(1);

            int index =0;
            List<String> left = new ArrayList<>();
            List<String> right = new ArrayList<>();
            int length =0;
            int linePoints =0;
            while(index+1<leftSide.length()) {
                left.add(leftSide.substring(index,index+2));
                index +=3;
            }
            index =0;
            while(index+1<rightSide.length()) {
                right.add(rightSide.substring(index,index+2));
                index +=3;
            }
            for (String s : left) {
                if (right.contains(s)) {
                    length++;
                }
            }
            if (length>0) {
                linePoints = (int) Math.pow(2, length-1);
            }
            currentLine++;
            result += linePoints;
        }
        return result;
    }
    public static int day4_Part2() {
        File file = new File("/Users/benedikt/Documents/Programmieren/AdventOfCode/Inputs/2023/Tag_4.txt");
        BufferedReader br;
        int result = 0;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Map<Integer, Integer> cards = new HashMap<>();
        Stack<Integer> newCards = new Stack<>();
        String strLine;
        String leftSide;
        String[] strLineNumbers;
        String rightSide;
        int currentLine = 1;
        while (true) {
            try {
                if ((strLine = br.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            strLineNumbers = strLine.split("\\|");
            leftSide = strLineNumbers[0].substring(10, strLineNumbers[0].length() - 1);
            rightSide = strLineNumbers[1].substring(1);

            int index = 0;
            List<String> left = new ArrayList<>();
            List<String> right = new ArrayList<>();
            int length = 0;
            while (index + 1 < leftSide.length()) {
                left.add(leftSide.substring(index, index + 2));
                index += 3;
            }
            index = 0;
            while (index + 1 < rightSide.length()) {
                right.add(rightSide.substring(index, index + 2));
                index += 3;
            }
            for (String s : left) {
                if (right.contains(s)) {
                    length++;
                }
            }
            cards.put(currentLine, length);
            while (length > 0) {
                newCards.push(currentLine + length--);
            }
            currentLine++;
            result++;
        }
        while (!newCards.isEmpty()) {
            int currentCard = newCards.pop();
            int length = cards.get(currentCard);
            while (length > 0) {
                newCards.push(currentCard + length--);
            }
            result++;
        }
        return result;
    }
}
