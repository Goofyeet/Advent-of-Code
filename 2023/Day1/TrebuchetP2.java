package Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TrebuchetP2 {
    public static void main(String[] args){
        final String path = "2023/Day1/input.txt";
        int sum = 0;
        String[] numbers = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

        try{
            Scanner fileReader = new Scanner(new File(path));

            while(fileReader.hasNextLine()){
                String line = fileReader.nextLine();
                int digitCount = 0;
                String digit1 = "";
                String digit2 = "";
                String number = "";

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
                        number = "";
                    }
                    else{
                        number += String.valueOf(c);

                        //check if number could actually be a valid string
                        if(number.length() > 2){

                            //loop through number strings
                            for(int n = 0; n < numbers.length; n++){
                                if(number.endsWith(numbers[n])){
                                    digitCount++;

                                    //check if this is the first digit
                                    if(digitCount == 1){
                                        digit1 = String.valueOf(n);
                                    }
                                    digit2 = String.valueOf(n);

                                    //set number to the last char of number
                                    number = number.substring(number.length() - 1);
                                    break;
                                }
                            }
                        }
                    }
                }
                //combine first and last digit

                String combined = String.valueOf(digit1 + digit2);
                System.out.println(combined);
                sum += Integer.parseInt(combined);
            }

        } catch(FileNotFoundException e){
            System.out.println("File not found");
        }

        System.out.println("Total Sum: " + sum);
    }
}