import java.io.*;
import java.util.*;

public class RelicsOfFailedAttemps {
    public static long gearRatios() {
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
        // String strLine2;
        List<Integer> indexSymbols = new ArrayList<>();
        Map<Integer, Integer> indexNumbers = new HashMap<>();
        boolean firstLine = true;
        //Read File Line By Line
        int currentLine = 1;
        int length = 12;
        int[] line = new int[length];
        boolean[] checkLine = new boolean[length];

        while (true) {

            int idx = 0;

            try {
                if ((strLine = br.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            boolean[] checkLineBefore;

            int[] lineBefore;
            if (firstLine) {
                checkLineBefore = new boolean[strLine.length()];
                lineBefore = new int[strLine.length()];
                Arrays.fill(lineBefore, -1);
            } else {
                lineBefore = Arrays.copyOf(line,length);
                checkLineBefore = Arrays.copyOf(checkLine,length);
                line = new int[length];
                checkLine = new boolean[length];
            }

            while (idx<strLine.length()) {
                int num =0;
                boolean canAdd = false;

                char currentChar = strLine.charAt(idx);
                if (isSymbol(currentChar)) {
                    int newNum =0;
                    canAdd = true;
                    if (lineBefore[idx] != -1 && !checkLineBefore[idx] &&lineBefore[idx] !=Integer.MIN_VALUE) {
                        newNum =numInLineBefore(idx,lineBefore,checkLineBefore);
                    } else if (idx-1>=0 && lineBefore[idx-1] !=-1 && !checkLineBefore[idx-1]&& lineBefore[idx-1] !=Integer.MIN_VALUE) {
                        newNum =numInLineBefore(idx,lineBefore,checkLineBefore);
                    } else if(idx+1<lineBefore.length && lineBefore[idx+1] !=-1 && !checkLineBefore[idx+1]&& lineBefore[idx+1] !=Integer.MIN_VALUE) {
                        newNum =numInLineBefore(idx,lineBefore,checkLineBefore);
                    }
                    result += newNum;
                    line[idx] = Integer.MIN_VALUE;
                    idx++;
                    if (idx<strLine.length()) {
                        currentChar = strLine.charAt(idx);
                    }
                }
                if (map.containsKey(currentChar)) {
                    num = map.get(currentChar);
                    if (idx-1 >0 && lineBefore[idx-1]==Integer.MIN_VALUE && !checkLineBefore[idx-1]) {
                        canAdd = true;
                    }
                    int lengthNumber = idx+1;
                    line[idx] = map.get(strLine.charAt(idx));
                    while (lengthNumber<strLine.length() && map.containsKey(strLine.charAt(lengthNumber))) {
                        if (lengthNumber-1 >0 && lineBefore[lengthNumber-1]==Integer.MIN_VALUE  && !checkLineBefore[lengthNumber-1]) {
                            canAdd = true;
                        } else if(lineBefore[lengthNumber]==Integer.MIN_VALUE  && !checkLineBefore[lengthNumber]){
                            canAdd =true;
                        } else if(lengthNumber+1 <strLine.length() && lineBefore[lengthNumber+1]==Integer.MIN_VALUE  && !checkLineBefore[lengthNumber+1]) {
                            canAdd =true;
                        }
                        num *= 10;
                        num += map.get(strLine.charAt(lengthNumber));
                        line[lengthNumber] = map.get(strLine.charAt(lengthNumber));
                        lengthNumber++;
                    }
                    idx = lengthNumber-1;
                    if (lengthNumber<strLine.length() && isSymbol(strLine.charAt(lengthNumber))) {
                        canAdd = true;
                        result += numInLineBefore(lengthNumber,lineBefore,checkLineBefore);
                        //idx++;
                    }
                    if(canAdd) {
                        int temp = idx;
                        while (temp >=0 && line[temp] != -1 && line[temp] != Integer.MIN_VALUE) {
                            checkLine[temp--] = true;
                        }
                    }
                }
                if (idx<strLine.length() && !isSymbol(strLine.charAt(idx)) && !map.containsKey(strLine.charAt(idx))) {
                    line[idx] = -1;
                } else if(isSymbol(strLine.charAt(idx))) {
                    line[idx] = Integer.MIN_VALUE;
                }
                if (canAdd) {

                    result += num;
                }
                idx++;
                firstLine =false;
            }
            currentLine++;
        }
        return result;

    }
    public static boolean isSymbol(Character c) {
        Map<Character, Integer> map = Map.of('0', 0, '1', 1,
                '2', 2, '3', 3, '4', 4, '5', 5,
                '6', 6, '7', 7, '8', 8, '9', 9);
        return !map.containsKey(c) && c != '.';
    }
    public static int numInLineBefore (int idx, int[] lineBefore, boolean[] checkLineBefore) {
        boolean numberBefore = false;
        int indexLineBefore = idx;
        int result = 0;
        StringBuilder newNum = new StringBuilder();
        if (lineBefore[idx] !=-1 && !checkLineBefore[idx]&&lineBefore[idx] !=Integer.MIN_VALUE) {
            numberBefore = true;
        } else if (idx-1>=0 && lineBefore[idx-1] !=-1 && !checkLineBefore[idx-1] &&lineBefore[idx-1] !=Integer.MIN_VALUE) {
            numberBefore = true;
            indexLineBefore--;
        } else if(idx+1<lineBefore.length && lineBefore[idx+1] !=-1 && !checkLineBefore[idx+1]&&lineBefore[idx+1] !=Integer.MIN_VALUE) {
            numberBefore = true;
            indexLineBefore++;
        }
        if (numberBefore) {
            newNum.append(lineBefore[indexLineBefore]);
            int left = indexLineBefore;
            int right = indexLineBefore;
            checkLineBefore[left] = true;
            while (left-1>=0 && lineBefore[left-1] !=-1 && lineBefore[left-1] !=Integer.MIN_VALUE) {
                left--;
                checkLineBefore[left] = true;
                newNum.insert(0,lineBefore[left]);
            }
            while (right+1<checkLineBefore.length && lineBefore[right+1] !=-1 && lineBefore[right+1] !=Integer.MIN_VALUE) {
                right++;
                checkLineBefore[right] = true;
                newNum.append(lineBefore[right]);
            }
        }
        if (!newNum.toString().isEmpty()) {
            result = Integer.parseInt(newNum.toString());
        }
        return result;
    }

}
