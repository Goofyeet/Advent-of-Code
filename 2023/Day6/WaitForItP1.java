package Day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WaitForItP1 {
    public static void main(String args[]){
        String path = "2023/Day6/input.txt";

        try{
            Scanner fileReader = new Scanner(new File(path));
            int total = 1;

            String timeLine = fileReader.nextLine();    //Time:      7  15   30
            String distLine = fileReader.nextLine();    //Distance:  9  40  200

            int[] timeArr = parseLine(timeLine);
            int[] distArr = parseLine(distLine);

            //loop through races
            for(int i = 0; i < timeArr.length; i++){
                int time = timeArr[i];
                int record = distArr[i];
                int wins = 0;

                //loop through timing
                for(int j = 1; j < time; j++){
                    int timeLeft = time - j;
                    int distance = timeLeft * j;

                    if(distance > record){
                        wins++;
                    }
                }

                total *= wins;
            }

            System.out.println("Total product: " + total);

        } catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }

    public static int[] parseLine(String line){
        String numStr = line.split(":\\s+")[1];   //"7  15   30"
        String[] numStrArr = numStr.split("\\s+");   //["7", "15", "30"]
        int[] lineArr = new int[numStrArr.length];

        for(int i = 0; i < lineArr.length; i++){
            lineArr[i] = Integer.parseInt(numStrArr[i]);
        }

        return lineArr;
    }
}
