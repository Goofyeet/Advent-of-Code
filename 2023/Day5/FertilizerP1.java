package Day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class FertilizerP1 {
    public static void main(String args[]){
        String path = "2023/Day5/input.txt";

        try{
            Scanner fileReader = new Scanner(new File(path));
            long minLocation = 0;
            String line = "";
            int data = 0;
            ArrayList<Long> seeds = new ArrayList<>();
            ArrayList<Long[]> seedSoil = new ArrayList<>();
            ArrayList<Long[]> soilFert = new ArrayList<>();
            ArrayList<Long[]> fertWater = new ArrayList<>();
            ArrayList<Long[]> waterLight = new ArrayList<>();
            ArrayList<Long[]> lightTemp = new ArrayList<>();
            ArrayList<Long[]> tempHumid = new ArrayList<>();
            ArrayList<Long[]> humidLoc = new ArrayList<>();

            //loop through lines in puzzle input
            while(fileReader.hasNextLine()){
                line = fileReader.nextLine();

                if(line.isEmpty()){
                    continue;
                }

                //line does not contain numbers
                else if(line.matches("^.+ map:$")){
                    data++;
                    continue;
                }

                switch(data){
                    case 0:
                        seeds = parseSeeds(line);
                        break;
                    case 1:
                        Long[] soilMap = parseMaps(line);
                        seedSoil.add(soilMap);
                        break;
                    case 2:
                        Long[] fertMap = parseMaps(line);
                        soilFert.add(fertMap);
                        break;
                    case 3:
                        Long[] waterMap = parseMaps(line);
                        fertWater.add(waterMap);
                        break;
                    case 4:
                        Long[] lightMap = parseMaps(line);
                        waterLight.add(lightMap);
                        break;
                    case 5:
                        Long[] tempMap = parseMaps(line);
                        lightTemp.add(tempMap);
                        break;
                    case 6:
                        Long[] humidMap = parseMaps(line);
                        tempHumid.add(humidMap);
                        break;
                    case 7:
                        Long[] locMap = parseMaps(line);
                        humidLoc.add(locMap);
                        break;
                }
            }

            for(int i = 0; i < seeds.size(); i++){
                long mappedNum = mapNum(seeds.get(i), seedSoil);
                mappedNum = mapNum(mappedNum, soilFert);
                mappedNum = mapNum(mappedNum, fertWater);
                mappedNum = mapNum(mappedNum, waterLight);
                mappedNum = mapNum(mappedNum, lightTemp);
                mappedNum = mapNum(mappedNum, tempHumid);
                mappedNum = mapNum(mappedNum, humidLoc);

                if(i == 0){
                    minLocation = mappedNum;
                }
                else if(mappedNum < minLocation){
                    minLocation = mappedNum;
                }
            }

            System.out.println("Lowest Location: " + minLocation);

        } catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }

    public static ArrayList<Long> parseSeeds(String line){
        String seedNums = line.split(": ")[1];  //79 14 55 13
        String[] seedStrArr = seedNums.split("\\s+");   //["79", "14", "55", "13"]
        ArrayList<Long> seeds = new ArrayList<>();

        for(int i = 0; i < seedStrArr.length; i++){
            seeds.add(Long.parseLong(seedStrArr[i]));
        }

        return seeds;
    }

    public static Long[] parseMaps(String line){
        Long[] map = new Long[3];
        String[] mapStrArr = line.split("\\s"); //["52", "50", "48"]

        for(int i = 0; i < 3; i++){
            map[i] = Long.parseLong(mapStrArr[i]);
        }

        return map;
    }

    public static long mapNum(long num, ArrayList<Long[]> map){
        long mapped = num;

        for(Long[] mapArr : map){
            long destStart = mapArr[0];
            long sourceStart = mapArr[1];
            long range = mapArr[2];

            if(num >= sourceStart && num <= (sourceStart + range)){
                long diff = num - sourceStart;
                mapped = destStart + diff;
            }
        }

        return mapped;
    }
}
