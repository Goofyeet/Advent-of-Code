package Day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FertilizerP2 {
    public static void main(String args[]){
        String path = "2023/Day5/test.txt";

        try{
            Scanner fileReader = new Scanner(new File(path));
            long minLocation = 0;
            String line = "";
            int data = 0;
            ArrayList<Long[]> seeds = new ArrayList<>();
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
                long currSeed = seeds.get(i)[0];
                long range = seeds.get(i)[1];

                for(long j = 0; j < range; j++){
                    long mappedNum = mapNum(currSeed + j, seedSoil);
                    mappedNum = mapNum(mappedNum, soilFert);
                    mappedNum = mapNum(mappedNum, fertWater);
                    mappedNum = mapNum(mappedNum, waterLight);
                    mappedNum = mapNum(mappedNum, lightTemp);
                    mappedNum = mapNum(mappedNum, tempHumid);
                    mappedNum = mapNum(mappedNum, humidLoc);

                    if(i == 0 && j == 0){
                        minLocation = mappedNum;
                    }
                    else if(mappedNum < minLocation){
                        minLocation = mappedNum;
                    }
                }
                System.out.println(minLocation);
            }

            System.out.println("Lowest Location: " + minLocation);

        } catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }

    public static ArrayList<Long[]> parseSeeds(String line){
        String seedNums = line.split(": ")[1];  //79 14 55 13
        String[] seedStrArr = seedNums.split("\\s");   //["79", "14", "55", "13"]
        ArrayList<Long[]> seeds = new ArrayList<>();

        for(int i = 0; i < seedStrArr.length; i += 2){
            Long[] seedRange = new Long[2];
            seedRange[0] = Long.parseLong(seedStrArr[i]);
            seedRange[1] = Long.parseLong(seedStrArr[i + 1]);
            seeds.add(seedRange);
        }

        return seeds;
    }

    //not working correctly yet
    //should combine overlapped seed ranges into one
    public static ArrayList<Long[]> optimizeSeeds(ArrayList<Long[]> seeds){
        int i = 0;

        while(i < seeds.size()){
            long start = seeds.get(i)[0];
            long end = start + seeds.get(i)[1] - 1;

            int next = 1;
            while(next < seeds.size()){
                long nextStart = seeds.get(next)[0];
                long nextEnd = nextStart + seeds.get(next)[1] - 1;

                //first range overlaps with next range
                if(start >= nextStart && start <= nextEnd){
                    //first range fully contained in next range
                    //remove first range
                    if(end <= nextEnd){
                        seeds.remove(i);
                    }
                    else{
                        Long[] newRange = new Long[2];
                        newRange[0] = nextStart;
                        newRange[1] = end - start + 1;
                        seeds.set(i, newRange);
                        seeds.remove(next);
                    }
                }

                //next range overlaps with first range
                else if(nextStart >= start && nextStart <= end){
                    //next range fully contained in first range
                    //remove next range
                    if(nextEnd <= end){
                        seeds.remove(next);
                    }
                    else{
                        Long[] newRange = new Long[2];
                        newRange[0] = start;
                        newRange[1] = nextEnd - nextStart + 1;
                        seeds.set(i, newRange);
                        seeds.remove(next);
                    }
                }
                else{
                    next++;
                }
            }
            i++;
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
                break;
            }
        }

        return mapped;
    }
}
