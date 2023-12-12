package Day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GearsP1 {
    public static void main(String args[]){
        String path = "2023/Day3/input.txt";

        try{
            Scanner fileReader = new Scanner(new File(path));
            int totalSum = 0;
            int lineCount = 1;
            String top = "";
            String middle = "";
            String bottom = "";

            //loop through lines in puzzle input
            while(fileReader.hasNextLine() || bottom.length() > 0){
                String partNum = "";
                boolean closeToPart = false;

                //check if line is first line of input
                if(lineCount == 1){
                    middle = fileReader.nextLine();
                    bottom = fileReader.nextLine();
                }

                //if line isn't first line and has a bottom line
                else if(fileReader.hasNextLine()){
                    top = middle;
                    middle = bottom;
                    bottom = fileReader.nextLine();
                }

                //line is last line of input
                else{
                    top = middle;
                    middle = bottom;
                    bottom = "";
                }

                //loop through characters in line
                for(int i = 0; i < middle.length(); i++){
                    char c = middle.charAt(i);

                    //check if char is a digit
                    if(Character.isDigit(c)){
                        partNum += String.valueOf(c);

                        //if not close to part yet, check above and below
                        if(!closeToPart){
                            closeToPart = aboveOrBelow(top, bottom, i);
                        }
                    }

                    //if char is a part
                    else if(isPart(c)){
                        closeToPart = true;

                        //part is after a number
                        if(partNum.length() > 0){
                            int num = Integer.parseInt(partNum);
                            totalSum += num;
                            partNum = "";
                        }
                    }

                    //char is a period
                    else{
                        boolean setNow = false;

                        if(aboveOrBelow(top, bottom, i)){
                            closeToPart = true;
                            setNow = true;
                        }

                        if (closeToPart){
                            if(partNum.length() > 0){
                                int num = Integer.parseInt(partNum);
                                totalSum += num;
                            }

                            if(!setNow){
                                closeToPart = false;
                            }
                        }

                        partNum = "";
                    }
                }
                if(partNum.length() > 0 && closeToPart){
                    int num = Integer.parseInt(partNum);
                    totalSum += num;
                }

                lineCount++;
            }

            System.out.println("Total sum: " + totalSum);

        } catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }

    //special chars between 33 and 45 inclusive
    //returns true if c is a special char
    public static boolean isPart(char c){
        //not a digit, period, or whitespace
        String regex = "[^\\d\\.\\s]";
        String s = String.valueOf(c);

        return s.matches(regex);
    }

    public static boolean aboveOrBelow(String top, String bottom, int i){

        if(i < top.length()){
            char topChar = top.charAt(i);

            if(isPart(topChar)){
                return true;
            }
        }
        if(i < bottom.length()){
            char bottomChar = bottom.charAt(i);

            if(isPart(bottomChar)){
                return true;
            }
        }

        return false;
    }
}
