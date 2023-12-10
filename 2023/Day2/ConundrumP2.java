package Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ConundrumP2 {
    public static void main(String[] args){
        final String path = "2023/Day2/input.txt";
        final int maxRed = 12;
        final int maxGreen = 13;
        final int maxBlue = 14;
        int powerSum = 0;

        try{
            Scanner fileReader = new Scanner(new File(path));

            //loop through lines in puzzle input
            while(fileReader.hasNextLine()){
                String line = fileReader.nextLine();    //"Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"
                String[] gameString = line.split(": "); //["Game 1", "3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"]
                int gameId = Integer.parseInt(gameString[0].substring(5));  //1
                String[] miniGames = gameString[1].split("; ");  //["3 blue, 4 red", "1 red, 2 green, 6 blue", "2 green"]
                int minRed = 0;
                int minGreen = 0;
                int minBlue = 0;

                //loop through each mini game
                for(int i = 0; i < miniGames.length; i++){
                    String game = miniGames[i];     //"3 blue, 4 red"
                    String[] colors = game.split(", ");  //["3 blue", "4 red"]

                    //loop through colors picked in the mini game
                    for(int j = 0; j < colors.length; j++){
                        String c = colors[j];   //"3 blue"
                        int num = Integer.parseInt(String.valueOf(c.split(" ")[0]));    //3

                        if(c.endsWith("red") && num > minRed){
                            minRed = num;
                        }
                        else if(c.endsWith("green") && num > minGreen){
                            minGreen = num;
                        }
                        else if(c.endsWith("blue") && num > minBlue){
                            minBlue = num;
                        }
                    }
                    
                }

                int power = minRed * minGreen * minBlue;
                System.out.println(minRed + " " + minGreen + " " + minBlue + " " + power);
                powerSum += power;
            }

            System.out.println("Total sum: " + powerSum);

        } catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }
}
