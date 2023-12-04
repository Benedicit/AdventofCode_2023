import java.io.*;
import java.util.*;

public class Tag3 {
    public static void main(String[] args) {
        System.out.println(gearRatio2());
        char s = '3';
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

        int idx = 0;
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
            while (true) {
                while (firstRowNumbers.containsKey(tester - 1) || firstRowNumbers.containsKey(tester) || firstRowNumbers.containsKey(tester + 1)
                        || secondRowNumbers.containsKey(tester - 1) || secondRowNumbers.containsKey(tester) || secondRowNumbers.containsKey(tester + 1)) {
                    if (!firstRowNumbers.isEmpty()) {
                        if (firstRowNumbers.containsKey(tester - 1)) {
                            num += getNumber(tester - 1, firstRowNumbers);
                        } else if (firstRowNumbers.containsKey(tester)) {
                            num += getNumber(tester, firstRowNumbers);
                        } else if (firstRowNumbers.containsKey(tester + 1)) {
                            num += getNumber(tester + 1, firstRowNumbers);
                        }
                    }
                    if (!secondRowNumbers.isEmpty()) {
                        if (secondRowNumbers.containsKey(tester - 1)) {
                            num += getNumber(tester - 1, secondRowNumbers);
                        } else if (secondRowNumbers.containsKey(tester + 1)) {
                            num += getNumber(tester + 1, secondRowNumbers);
                        }
                    }
                }
                index++;
                if (index < firstRowSymbol.size()) {
                    tester = firstRowSymbol.get(index);
                } else {
                    break;
                }

            }
            result += num + num2;
            num = 0;
            num2 = 0;
            index = 0;
            if (!secondRowSymbol.isEmpty()) {
                tester = secondRowSymbol.get(index);
            } else {
                tester = Integer.MIN_VALUE;
            }
            while (true) {
                while (firstRowNumbers.containsKey(tester - 1) || firstRowNumbers.containsKey(tester) || firstRowNumbers.containsKey(tester + 1)
                        || secondRowNumbers.containsKey(tester - 1) || secondRowNumbers.containsKey(tester) || secondRowNumbers.containsKey(tester + 1)) {

                    if (!firstRowNumbers.isEmpty()) {
                        if (firstRowNumbers.containsKey(tester - 1)) {
                            num += getNumber(tester - 1, firstRowNumbers);
                        } else if (firstRowNumbers.containsKey(tester)) {
                            num += getNumber(tester, firstRowNumbers);
                        } else if (firstRowNumbers.containsKey(tester + 1)) {
                            num += getNumber(tester + 1, firstRowNumbers);
                        }
                    }
                    if (!secondRowNumbers.isEmpty()) {

                        if (secondRowNumbers.containsKey(tester - 1)) {
                            num2 += getNumber(tester - 1, secondRowNumbers);
                        } else if (secondRowNumbers.containsKey(tester)) {
                            num2 += getNumber(tester, secondRowNumbers);
                        } else if (secondRowNumbers.containsKey(tester + 1)) {
                            num2 += getNumber(tester + 1, secondRowNumbers);
                        }
                    }
                }
                index++;
                if (index < secondRowSymbol.size()) {
                    tester = secondRowSymbol.get(index);
                } else {
                    break;
                }
            }

            result += num + num2;

            firstRowNumbers = secondRowNumbers;
            firstRowSymbol = secondRowSymbol;
            secondRowNumbers = new HashMap<>();
            secondRowSymbol = new ArrayList<>();
            currentLine++;


        }
        return result;
    }

    public static int getNumber(int tester, Map<Integer, Integer> numbers) {
        int currentIndex;
        int num = 0;
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

        int idx = 0;
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
            int countAdjNumbers =0;
            if (!firstRowSymbol.isEmpty()) {
                tester = firstRowSymbol.get(index);
            } else {
                tester = Integer.MIN_VALUE;
            }
            while (true) {
                while (firstRowNumbers.containsKey(tester - 1) || firstRowNumbers.containsKey(tester) || firstRowNumbers.containsKey(tester + 1)
                        || secondRowNumbersCopy.containsKey(tester - 1) || secondRowNumbersCopy.containsKey(tester) || secondRowNumbersCopy.containsKey(tester + 1)
                 || thirdRowNumbersCopy.containsKey(tester - 1)  || thirdRowNumbersCopy.containsKey(tester)  || thirdRowNumbersCopy.containsKey(tester + 1) ) {
                    if (!firstRowNumbers.isEmpty()) {
                        if (firstRowNumbers.containsKey(tester - 1)) {
                            num.add(getNumber(tester - 1, firstRowNumbers));
                        } else if (firstRowNumbers.containsKey(tester)) {
                            num.add(getNumber(tester, firstRowNumbers));
                        } else if (firstRowNumbers.containsKey(tester + 1)) {
                            num.add(getNumber(tester + 1, firstRowNumbers));
                        }
                    }
                    if (!secondRowNumbersCopy.isEmpty()) {
                        if (secondRowNumbersCopy.containsKey(tester - 1)) {
                            num.add(getNumber(tester - 1, secondRowNumbersCopy));
                        } else if (secondRowNumbersCopy.containsKey(tester + 1)) {
                            num.add(getNumber(tester + 1, secondRowNumbersCopy));
                        }
                    }
                    if (!thirdRowNumbersCopy.isEmpty()) {
                        if (thirdRowNumbersCopy.containsKey(tester - 1)) {
                            num.add(getNumber(tester - 1, thirdRowNumbersCopy));
                        } else if( thirdRowNumbersCopy.containsKey(tester)) {
                            num.add(getNumber(tester, thirdRowNumbersCopy));
                        } else if (thirdRowNumbersCopy.containsKey(tester + 1)) {
                            num.add(getNumber(tester + 1, thirdRowNumbersCopy));
                        }
                    }
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
    public static boolean isStar(char c) {
        return c =='*';
    }
}





