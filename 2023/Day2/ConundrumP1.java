package Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ConundrumP1 {
    public static void main(String[] args){
        final String path = "2023/Day2/input.txt";
        final int maxRed = 12;
        final int maxGreen = 13;
        final int maxBlue = 14;
        int idSum = 0;

        try{
            Scanner fileReader = new Scanner(new File(path));

            //loop through lines in puzzle input
            while(fileReader.hasNextLine()){
                String line = fileReader.nextLine();    //"Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"
                String[] gameString = line.split(": "); //["Game 1", "3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"]
                int gameId = Integer.parseInt(gameString[0].substring(5));  //1
                String[] miniGames = gameString[1].split("; ");  //["3 blue, 4 red", "1 red, 2 green, 6 blue", "2 green"]

                //loop through each mini game
                for(int i = 0; i < miniGames.length; i++){
                    String game = miniGames[i];     //"3 blue, 4 red"
                    String[] colors = game.split(", ");  //["3 blue", "4 red"]
                    int pickedRed = 0;
                    int pickedGreen = 0;
                    int pickedBlue = 0;

                    //loop through colors picked in the mini game
                    for(int j = 0; j < colors.length; j++){
                        String c = colors[j];   //"3 blue"
                        int num = Integer.parseInt(String.valueOf(c.split(" ")[0]));    //3

                        if(c.endsWith("red")){
                            pickedRed = num;
                        }
                        else if(c.endsWith("green")){
                            pickedGreen = num;
                        }
                        else if(c.endsWith("blue")){
                            pickedBlue = num;
                        }
                    }

                    if(pickedRed > maxRed || pickedGreen > maxGreen || pickedBlue > maxBlue){
                        break;
                    }
                    if(i == (miniGames.length - 1)){
                        idSum += gameId;
                    }
                }
            }

            System.out.println("Total sum: " + idSum);

        } catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }
}
