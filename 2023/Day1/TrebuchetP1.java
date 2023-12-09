package Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TrebuchetP1{
    public static void main(String[] args){
        final String path = "2023/Day1/input.txt";
        int sum = 0;

        try{
            Scanner fileReader = new Scanner(new File(path));

            while(fileReader.hasNextLine()){
                String line = fileReader.nextLine();
                int digitCount = 0;
                String digit1 = "";
                String digit2 = "";

                //loop through each character in the line
                for(int i = 0; i < line.length(); i++){
                    char c = line.charAt(i);

                    //check if c is a digit
                    if(Character.isDigit(c)){
                        digitCount++;

                        //check if this is the first digit
                        if(digitCount == 1){
                            digit1 = String.valueOf(c);
                        }
                        digit2 = String.valueOf(c);
                    }
                }
                //combine first and last digit

                String combined = String.valueOf(digit1 + digit2);
                sum += Integer.parseInt(combined);
            }

        } catch(FileNotFoundException e){
            System.out.println("File not found");
        }

        System.out.println("Total Sum: " + sum);
    }
}