package Day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WaitForItP2 {
    public static void main(String args[]){
        String path = "2023/Day6/input.txt";

        try{
            Scanner fileReader = new Scanner(new File(path));
            int total = 1;

            String timeLine = fileReader.nextLine();    //Time:      7  15   30
            String distLine = fileReader.nextLine();    //Distance:  9  40  200

            long time = parseLine(timeLine);
            long record = parseLine(distLine);
            int wins = 0;

            //loop through timing
            for(int j = 1; j < time; j++){
                long timeLeft = time - j;
                long distance = timeLeft * j;

                if(distance > record){
                    wins++;
                }
            }

                total *= wins;

            System.out.println("Total product: " + total);

        } catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }

    public static long parseLine(String line){
        String dirtyNumStr = line.split(":\\s+")[1];   //"7  15   30"
        String[] numStrArr = dirtyNumStr.split("\\s+");   //["7", "15", "30"]
        String numStr = "";
        long num;

        for(int i = 0; i < numStrArr.length; i++){
            numStr += numStrArr[i];
        }

        num = Long.parseLong(numStr);

        return num;
    }
}
