package Day9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class MirageP1 {
    public static void main(String args[]){
        String path = "2023/Day9/input.txt";
        try{
            Scanner fileReader = new Scanner(new File(path));
            long sum = 0;
            String line = "";

            while(fileReader.hasNextLine()){
                line = fileReader.nextLine();
                int[] history = parseLine(line);

                sum += findNextValue(history);
            }

            System.out.println("Total Sum: " + sum);

        } catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }

    public static int findNextValue(int[] history){
        boolean allZeroes = false;

        for(int i = 0; i < history.length; i++){
            if(history[i] == 0){
                if(i == history.length - 1){
                    allZeroes = true;
                }
            }
            else{
                break;
            }
        }

        if(allZeroes){
            return 0;
        }
        else{
            int[] diff = new int[history.length - 1];

            for(int i = 0; i < diff.length; i++){
                diff[i] = history[i + 1] - history[i];
            }

            return history[history.length - 1] + findNextValue(diff);
        }
    }

    public static int[] parseLine(String line){
        String[] strArr = line.split("\\s+");
        int[] history = new int[strArr.length];

        for(int i = 0; i < history.length; i++){
            history[i] = Integer.parseInt(strArr[i]);
        }

        return history;
    }
}
