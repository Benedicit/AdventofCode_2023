import java.io.*;
import java.util.*;

public class Day5 {
    public static void main(String[] args) {
        System.out.println(day5_Part2());
    }

    public static long day5_Part1() {
        File file = new File("/Users/benedikt/Documents/Programmieren/AdventOfCode/Inputs/2023/Tag_5.txt");
        BufferedReader br;
        int result = 0;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String strLine;
        Map<long[], long[]> seedToSoil = new HashMap<>();
        Map<long[], long[]> soilToFertilizer = new HashMap<>();
        Map<long[], long[]> fertilizerToWater = new HashMap<>();
        Map<long[], long[]> waterToLight = new HashMap<>();
        Map<long[], long[]> lightToTemperature = new HashMap<>();
        Map<long[], long[]> temperatureToHumidity = new HashMap<>();
        Map<long[], long[]> humidityToLocation = new HashMap<>();
        String[] seeds = new String[0];
        long[] source = new long[2];
        long[] dest = new long[2];
        String[] all;
        int currentMap = 0;
        int currentLine = 1;

        while (true) {
            try {
                if ((strLine = br.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (strLine.isEmpty()) {
                try {
                    for (int i = 0; i < 2; i++) {
                        strLine = br.readLine();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                currentMap++;
                currentLine++;
            }

            if (currentLine == 1) {
                seeds = strLine.substring(7).split(" ");
            } else {
                all = strLine.split(" ");
                dest[0] = Long.parseLong(all[0]);
                dest[1] = Long.parseLong(all[2]);
                source[0] = Long.parseLong(all[1]);
                source[1] = Long.parseLong(all[2]);
                switch (currentMap) {
                    case 1:
                        seedToSoil.put(source, dest);
                        break;
                    case 2:
                        soilToFertilizer.put(source, dest);
                        break;
                    case 3:
                        fertilizerToWater.put(source, dest);
                        break;
                    case 4:
                        waterToLight.put(source, dest);
                        break;
                    case 5:
                        lightToTemperature.put(source, dest);
                        break;
                    case 6:
                        temperatureToHumidity.put(source, dest);
                        break;
                    case 7:
                        humidityToLocation.put(source, dest);
                        break;
                }
                source = new long[2];
                dest = new long[2];
            }
            currentLine++;
        }

        long currentOffset;
        long lowestLocation = 0;
        long current;
        for (int i = 0; i < seeds.length; i++) {
            current = Long.parseLong(seeds[i]);
            for (int j = 1; j < 8; j++) {
                boolean found = false;
                switch (j) {
                    case 1:
                        current = getCurrent(seedToSoil, current, found);
                        break;
                    case 2:
                        current = getCurrent(soilToFertilizer, current, found);
                        break;
                    case 3:
                        current = getCurrent(fertilizerToWater, current, found);
                        break;
                    case 4:
                        current = getCurrent(waterToLight, current, found);
                        break;
                    case 5:
                        current = getCurrent(lightToTemperature, current, found);
                        break;
                    case 6:
                        current = getCurrent(temperatureToHumidity, current, found);
                        break;
                    case 7:
                        current = getCurrent(humidityToLocation, current, found);
                        break;

                }
            }
            if (i==0) {
                lowestLocation = current;
            } else {

                if(current<lowestLocation) {
                    lowestLocation =current;
                }
            }
        }

        return lowestLocation;


    }

    private static long getCurrent(Map<long[], long[]> map, long current, boolean found) {
        long currentOffset;
        for (Map.Entry<long[], long[]> entry : map.entrySet()) {
            long[] ints = entry.getKey();
            long[] ints2 = entry.getValue();
            if (current >= ints[0] && current < ints[0] + ints[1] && !found) {
                currentOffset = current - ints[0];
                current = ints2[0] + currentOffset;
                found = true;
                break;
            }
        }
        return current;
    }

    public static long day5_Part2() {
        //Just Bruteforce the solution

        File file = new File("/Users/benedikt/Documents/Programmieren/AdventOfCode/Inputs/2023/Tag_5.txt");
        BufferedReader br;
        int result = 0;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String strLine;
        Map<long[], long[]> seedToSoil = new HashMap<>();
        Map<long[], long[]> soilToFertilizer = new HashMap<>();
        Map<long[], long[]> fertilizerToWater = new HashMap<>();
        Map<long[], long[]> waterToLight = new HashMap<>();
        Map<long[], long[]> lightToTemperature = new HashMap<>();
        Map<long[], long[]> temperatureToHumidity = new HashMap<>();
        Map<long[], long[]> humidityToLocation = new HashMap<>();
        String[] seeds = new String[0];
        long[] source = new long[2];
        long[] dest = new long[2];
        String[] all;
        int currentMap = 0;
        int currentLine = 1;

        while (true) {
            try {
                if ((strLine = br.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (strLine.isEmpty()) {
                try {
                    for (int i = 0; i < 2; i++) {
                        strLine = br.readLine();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                currentMap++;
                currentLine++;
            }

            if (currentLine == 1) {
                seeds = strLine.substring(7).split(" ");
            } else {
                all = strLine.split(" ");
                dest[0] = Long.parseLong(all[0]);
                dest[1] = Long.parseLong(all[1]);
                source[0] = Long.parseLong(all[1]);
                source[1] = Long.parseLong(all[2]);
                switch (currentMap) {
                    case 1:
                        seedToSoil.put(source, dest);
                        break;
                    case 2:
                        soilToFertilizer.put(source, dest);
                        break;
                    case 3:
                        fertilizerToWater.put(source, dest);
                        break;
                    case 4:
                        waterToLight.put(source, dest);
                        break;
                    case 5:
                        lightToTemperature.put(source, dest);
                        break;
                    case 6:
                        temperatureToHumidity.put(source, dest);
                        break;
                    case 7:
                        humidityToLocation.put(source, dest);
                        break;
                }
                source = new long[2];
                dest = new long[2];
            }
            currentLine++;
        }

        long currentOffset;
        long lowestLocation = 0;
        long current;
        long test =0;
        for (int i = 0; i < seeds.length-1; i+=2) {
            for (long j = Long.parseLong(seeds[i]); j <= Long.parseLong(seeds[i]) +Long.parseLong(seeds[i+1]); j++) {
                current = j;
                for (int h = 1; h < 8; h++) {
                    boolean found = false;
                    switch (h) {
                        case 1:
                            current = getCurrent(seedToSoil, current, found);
                            break;
                        case 2:
                            current = getCurrent(soilToFertilizer, current, found);
                            break;
                        case 3:
                            current = getCurrent(fertilizerToWater, current, found);
                            break;
                        case 4:
                            current = getCurrent(waterToLight, current, found);
                            break;
                        case 5:
                            current = getCurrent(lightToTemperature, current, found);
                            break;
                        case 6:
                            current = getCurrent(temperatureToHumidity, current, found);
                            break;
                        case 7:
                            current = getCurrent(humidityToLocation, current, found);
                            break;

                    }
                }
                if (test==0) {
                    lowestLocation = current;
                } else if(current < lowestLocation){
                    lowestLocation = current;
                }
                test++;
            }
        }
        current = lowestLocation;
        for (int h = 1; h < 8; h++) {
            boolean found = false;
            current = switch (h) {
                case 1 -> getCurrent(seedToSoil, current, found);
                case 2 -> getCurrent(soilToFertilizer, current, found);
                case 3 -> getCurrent(fertilizerToWater, current, found);
                case 4 -> getCurrent(waterToLight, current, found);
                case 5 -> getCurrent(lightToTemperature, current, found);
                case 6 -> getCurrent(temperatureToHumidity, current, found);
                case 7 -> getCurrent(humidityToLocation, current, found);
                default -> current;
            };
        }
        System.out.println(current);
        return lowestLocation;
    }
}