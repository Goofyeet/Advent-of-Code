package Day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScratchcardsP1 {
    public static void main(String args[]){
        String path = "2023/Day4/input.txt";

        try{
            Scanner fileReader = new Scanner(new File(path));
            int totalPoints = 0;
            String line = "";

            //loop through lines in puzzle input
            while(fileReader.hasNextLine()){
                line = fileReader.nextLine();   //Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
                int points = 0;
                int matches = 0;

                String[] numbers = line.split(" \\| "); //["Card 1: 41 48 83 86 17", "83 86  6 31 17  9 48 53"]
                String dirtyWinners = numbers[0];   //"Card 1: 41 48 83 86 17"

                String myNumbers = numbers[1].trim();  //"83 86  6 31 17  9 48 53"
                String winners = dirtyWinners.split(": ")[1].trim();   //"41 48 83 86 17"

                String[] myNumbersStrArr = myNumbers.split("\\s+"); //["83", "86", "6", "31", "17", "9", "48", "53"]
                String[] winnersStrArr = winners.split("\\s+"); //["41", "48", "83", "86", "17"]

                int[] myNumbersArr = new int[myNumbersStrArr.length];
                int[] winnersArr = new int[winnersStrArr.length];

                //initialize myNumbersArr
                for(int i = 0; i < myNumbersArr.length; i++){
                    myNumbersArr[i] = Integer.parseInt(myNumbersStrArr[i]);
                }

                //initialize winnersArr
                for(int i = 0; i < winnersArr.length; i++){
                    winnersArr[i] = Integer.parseInt(winnersStrArr[i]);
                }

                for(int i = 0; i < winnersArr.length; i++){
                    for(int j = 0; j < myNumbersArr.length; j++){
                        if(winnersArr[i] == myNumbersArr[j]){
                            matches++;

                            if(matches == 1){
                                points = 1;
                            }
                            else{
                                points *= 2;
                            }

                            break;
                        }
                    }
                }

                totalPoints += points;
            }

            System.out.println("Total points: " + totalPoints);

        } catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }
}
