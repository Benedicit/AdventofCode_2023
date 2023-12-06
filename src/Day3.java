import java.io.*;
import java.util.*;

public class Day3 {
    public static void main(String[] args) {
        System.out.println(gearRatio1());
        System.out.println(gearRatio2());
    }

    public static boolean isSymbol(Character c) {
        Map<Character, Integer> map = Map.of('0', 0, '1', 1,
                '2', 2, '3', 3, '4', 4, '5', 5,
                '6', 6, '7', 7, '8', 8, '9', 9);
        return !map.containsKey(c) && c != '.';
    }

    public static int gearRatio1() {
        File file = new File("/Users/benedikt/Documents/Programmieren/AdventOfCode/Inputs/2023/Tag_3.txt");
        BufferedReader br;
        int result = 0;
        Map<Character, Integer> map = Map.of('0', 0, '1', 1,
                '2', 2, '3', 3, '4', 4, '5', 5,
                '6', 6, '7', 7, '8', 8, '9', 9);
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        String strLine;


        int currentLine = 1;
        Map<Integer, Integer> firstRowNumbers = new HashMap<>();
        Map<Integer, Integer> secondRowNumbers = new HashMap<>();
        List<Integer> firstRowSymbol = new ArrayList<>();
        List<Integer> secondRowSymbol = new ArrayList<>();
        while (true) {
            try {
                if ((strLine = br.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            int length = strLine.length();
            for (int i = 0; i < length; i++) {
                char currentChar = strLine.charAt(i);
                if (map.containsKey(currentChar)) {
                    secondRowNumbers.put(i, map.get(currentChar));
                } else if (isSymbol(currentChar)) {
                    secondRowSymbol.add(i);
                }
            }
            int tester;
            int num = 0;
            int num2 = 0;
            int index = 0;
            if (!firstRowSymbol.isEmpty()) {
                tester = firstRowSymbol.get(index);
            } else {
                tester = Integer.MIN_VALUE;
            }
            result = getResult(result, firstRowNumbers, secondRowNumbers, firstRowSymbol, tester, num, num2, index);


            if (!secondRowSymbol.isEmpty()) {
                tester = secondRowSymbol.get(index);
            } else {
                tester = Integer.MIN_VALUE;
            }
            result = getResult(result, firstRowNumbers, secondRowNumbers, secondRowSymbol, tester, num, num2, index);

            firstRowNumbers = secondRowNumbers;
            firstRowSymbol = secondRowSymbol;
            secondRowNumbers = new HashMap<>();
            secondRowSymbol = new ArrayList<>();
            currentLine++;


        }
        return result;
    }

    private static int getResult(int result, Map<Integer, Integer> firstRowNumbers, Map<Integer, Integer> secondRowNumbers, List<Integer> firstRowSymbol, int tester, int num, int num2, int index) {
        while (true) {
            num = addNumber(num,firstRowNumbers,tester);
            num2 = addNumber(num2,secondRowNumbers,tester);
            index++;
            if (index < firstRowSymbol.size()) {
                tester = firstRowSymbol.get(index);
            } else {
                break;
            }

        }
        result += num + num2;
        return result;
    }


    public static int getNumber(int tester, Map<Integer, Integer> numbers) {
        int currentIndex;
        int num;
        currentIndex = tester - 1;
        num = numbers.get(tester);
        numbers.remove(tester);
        int multiplier = 10;
        while (numbers.containsKey(currentIndex)) {
            int temp = numbers.get(currentIndex) * multiplier;
            num += temp;
            numbers.remove(currentIndex--);
            multiplier *= 10;
        }
        currentIndex = tester + 1;
        while (numbers.containsKey(currentIndex)) {
            num *= 10;
            num += numbers.get(currentIndex);
            numbers.remove(currentIndex++);
        }

        return num;
    }
    public static int addNumber(int num, Map<Integer, Integer> RowNumbers,int tester) {
        while (RowNumbers.containsKey(tester - 1) || RowNumbers.containsKey(tester) || RowNumbers.containsKey(tester + 1)) {

            if (RowNumbers.containsKey(tester - 1)) {
                num += getNumber(tester - 1, RowNumbers);
            } else if (RowNumbers.containsKey(tester)) {
                num += getNumber(tester, RowNumbers);
            } else if (RowNumbers.containsKey(tester + 1)) {
                num += getNumber(tester + 1, RowNumbers);
            }

        }
        return num;
    }





    public static long gearRatio2() {
        File file = new File("/Users/benedikt/Documents/Programmieren/AdventOfCode/Inputs/2023/Tag_3.txt");
        BufferedReader br;
        long result = 0;
        Map<Character, Integer> map = Map.of('0', 0, '1', 1,
                '2', 2, '3', 3, '4', 4, '5', 5,
                '6', 6, '7', 7, '8', 8, '9', 9);
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {

            throw new RuntimeException(e);
        }

        String strLine;


        int currentLine = 1;

        Map<Integer, Integer> firstRowNumbers = new HashMap<>();
        Map<Integer, Integer> secondRowNumbers = new HashMap<>();
        Map<Integer, Integer> thirdRowNumbers = new HashMap<>();
        List<Integer> firstRowSymbol = new ArrayList<>();
        List<Integer> secondRowSymbol = new ArrayList<>();

        while (true) {
            try {
                if ((strLine = br.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            int length = strLine.length();
            for (int i = 0; i < length; i++) {
                char currentChar = strLine.charAt(i);
                if (map.containsKey(currentChar)) {
                    thirdRowNumbers.put(i, map.get(currentChar));
                } else if (isStar(currentChar)) {
                    secondRowSymbol.add(i);
                }
            }
            Map<Integer, Integer> secondRowNumbersCopy = new HashMap<>(secondRowNumbers);
            Map<Integer, Integer> thirdRowNumbersCopy = new HashMap<>(thirdRowNumbers);
            int tester;
            List<Integer> num = new ArrayList<>();
            int index = 0;


            if (!firstRowSymbol.isEmpty()) {
                tester = firstRowSymbol.get(index);
            } else {
                tester = Integer.MIN_VALUE;
            }
            while (true) {
                while (firstRowNumbers.containsKey(tester - 1) || firstRowNumbers.containsKey(tester) || firstRowNumbers.containsKey(tester + 1)) {
                    addNumber(num, firstRowNumbers, tester);
                }
                while(secondRowNumbersCopy.containsKey(tester - 1) || secondRowNumbersCopy.containsKey(tester) || secondRowNumbersCopy.containsKey(tester + 1)) {
                    addNumber(num, secondRowNumbersCopy, tester);
                }
                while(thirdRowNumbersCopy.containsKey(tester - 1)  || thirdRowNumbersCopy.containsKey(tester)  || thirdRowNumbersCopy.containsKey(tester + 1)) {
                    addNumber(num, thirdRowNumbersCopy, tester);
                }

                if (num.size()== 2) {
                    result += (long) num.get(0) *num.get(1);
                }

                num = new ArrayList<>();
                index++;

                if (index < firstRowSymbol.size()) {
                    tester = firstRowSymbol.get(index);
                } else {
                    break;
                }

            }
            firstRowNumbers = secondRowNumbers;
            firstRowSymbol = secondRowSymbol;
            secondRowNumbers = thirdRowNumbers;
            secondRowSymbol = new ArrayList<>();
            thirdRowNumbers = new HashMap<>();
            currentLine++;
        }
        return result;
    }

    public static void addNumber(List<Integer> num, Map<Integer, Integer> RowNumbers,int tester) {
        if (RowNumbers.containsKey(tester - 1)) {
            num.add(getNumber(tester - 1, RowNumbers));
        } else if (RowNumbers.containsKey(tester)) {
            num.add(getNumber(tester, RowNumbers));
        } else if (RowNumbers.containsKey(tester + 1)) {
            num.add(getNumber(tester + 1, RowNumbers));
        }
    }

    public static boolean isStar(char c) {
        return c =='*';
    }

}





