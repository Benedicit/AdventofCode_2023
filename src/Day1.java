import java.io.*;
import java.util.Map;

public class Day1 {
    public static void main(String[] args) {

        System.out.println(calculate());
    }
        public static long calculate() {
            File file = new File("/Users/benedikt/Documents/Programmieren/AdventOfCode/Inputs/2023/Tag_1.txt");
            BufferedReader br;
            long result = 0;
            Map<Character,Integer> map = Map.of('0',0,'1',1,
                    '2',2,'3',3,'4',4,'5',5,
                    '6',6,'7',7,'8',8,'9',9);
            Map<String,Integer> map2 = Map.of("one",1,
                    "two",2,"three",3,"four",4,"five",5,
                    "six",6,"seven",7,"eight",8,"nine",9);
            try {
                br = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            String strLine;

            //Read File Line By Line
            while (true)   {
                try {
                    if ((strLine = br.readLine()) == null) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                boolean leftFound = false;
                boolean rightFound =false;
                int leftSide = 0;
                int rightSide = strLine.length()-1;
                long temp = 0;
                while (!rightFound || !leftFound) {

                    //Check if a number exists first as a substring starting on the left side
                    if (strLine.length()-leftSide>3 && map2.containsKey(strLine.substring(leftSide,leftSide+3)) && !leftFound) {
                        temp += map2.get(strLine.substring(leftSide,leftSide+3)) * 10;
                        leftFound = true;
                    } else if(strLine.length()-leftSide>4 && map2.containsKey(strLine.substring(leftSide,leftSide+4)) && !leftFound) {
                        temp += map2.get(strLine.substring(leftSide,leftSide+4)) * 10;
                        leftFound = true;
                    } else if(strLine.length()-leftSide>5 && map2.containsKey(strLine.substring(leftSide,leftSide+5)) && !leftFound) {
                        temp += map2.get(strLine.substring(leftSide,leftSide+5)) * 10;
                        leftFound = true;
                    }

                    //Check which digit is the first in the string starting from the left
                    if(map.containsKey(strLine.charAt(leftSide)) && !leftFound) {
                        temp += map.get(strLine.charAt(leftSide)) *10;
                        leftFound =true;

                        //If not found, continue searching
                    } else if(!leftFound) {
                        leftSide++;
                    }

                    //Check if a number exists first as a substring starting on the right side
                    if (rightSide-2>0 && map2.containsKey(strLine.substring(rightSide-2,rightSide+1)) && !rightFound) {
                        temp += map2.get(strLine.substring(rightSide-2,rightSide+1));
                        rightFound =true;
                    } else if (rightSide-3>0 && map2.containsKey(strLine.substring(rightSide-3,rightSide+1)) && !rightFound) {
                        temp += map2.get(strLine.substring(rightSide-3,rightSide+1));
                        rightFound =true;
                    }else if (rightSide-4>0 && map2.containsKey(strLine.substring(rightSide-4,rightSide+1)) && !rightFound) {
                        temp += map2.get(strLine.substring(rightSide-4,rightSide+1));
                        rightFound =true;
                    }

                    //Check which digit is the first in the string starting from the right
                    if(map.containsKey(strLine.charAt(rightSide)) && !rightFound) {
                        temp += map.get(strLine.charAt(rightSide));
                        rightFound =true;

                        //If not found, continue searching
                    } else if (!rightFound) {
                        rightSide--;
                    }
                }
                result += temp;
            }
            return result;

        }

}