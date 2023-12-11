package Day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GearsP2 {
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

                    //if char is a *
                    if(isGear(c)){
                        int ratio = findGearRatio(i, top, middle, bottom);
                        totalSum += ratio;
                    }
                }

                lineCount++;
            }

            System.out.println("Total sum: " + totalSum);

        } catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }

    //returns true if c is a special char
    public static boolean isGear(char c){
        String regex = "\\*";
        String s = String.valueOf(c);

        return s.matches(regex);
    }

    public static int findGearRatio(int index, String top, String middle, String bottom){
        String gearNum = "";
        char c;
        int ratio = 1;
        int found = 0;

        //check left
        int left = index - 1;
        if(left >= 0){
            c = middle.charAt(left);
            if(Character.isDigit(c)){
                found++;
                gearNum = getGearString(left, middle);
                ratio *= Integer.parseInt(gearNum);
            }
        }

        //check right
        int right = index + 1;
        if(right < middle.length()){
            c = middle.charAt(right);
            if(Character.isDigit(c)){
                found++;
                gearNum = getGearString(right, middle);
                ratio *= Integer.parseInt(gearNum);
            }
        }

        //check top
        if(top.length() > 0){
            char tl = ' ';
            char tm = ' ';
            char tr = ' ';

            if(left > 0){
                tl = top.charAt(left);
            }
            tm = top.charAt(index);

            if(right < top.length()){
                tr = top.charAt(right);
            }

            //only tl and tr are digits, these are the 2 numbers
            if(Character.isDigit(tl) && !Character.isDigit(tm) && Character.isDigit(tr)){
                found++;
                gearNum = getGearString(left, top);
                ratio *= Integer.parseInt(gearNum);

                found++;
                gearNum = getGearString(right, top);
                ratio *= Integer.parseInt(gearNum);
            }
            else if(Character.isDigit(tl)){
                found++;
                gearNum = getGearString(left, top);
                ratio *= Integer.parseInt(gearNum);
            }
            else if(Character.isDigit(tm)){
                found++;
                gearNum = getGearString(index, top);
                ratio *= Integer.parseInt(gearNum);
            }
            else if(Character.isDigit(tr)){
                found++;
                gearNum = getGearString(right, top);
                ratio *= Integer.parseInt(gearNum);
            }
        }

        //check bottom
        if(bottom.length() > 0){
            char bl = ' ';
            char bm = ' ';
            char br = ' ';

            if(left > 0){
                bl = bottom.charAt(left);
            }
            bm = bottom.charAt(index);

            if(right < top.length()){
                br = bottom.charAt(right);
            }

            //only tl and tr are digits, these are the 2 numbers
            if(Character.isDigit(bl) && !Character.isDigit(bm) && Character.isDigit(br)){
                found++;
                gearNum = getGearString(left, bottom);
                ratio *= Integer.parseInt(gearNum);

                found++;
                gearNum = getGearString(right, bottom);
                ratio *= Integer.parseInt(gearNum);
            }
            else if(Character.isDigit(bl)){
                found++;
                gearNum = getGearString(left, bottom);
                ratio *= Integer.parseInt(gearNum);
            }
            else if(Character.isDigit(bm)){
                found++;
                gearNum = getGearString(index, bottom);
                ratio *= Integer.parseInt(gearNum);
            }
            else if(Character.isDigit(br)){
                found++;
                gearNum = getGearString(right, bottom);
                ratio *= Integer.parseInt(gearNum);
            }
        }

        if(found != 2){
            ratio = 0;
        }

        return ratio;
    }

    public static String getGearString(int index, String line){
        char c = line.charAt(index);
        String gearString = "";

        //find start of gear string
        while(Character.isDigit(c)){
            index--;

            if(index >= 0){
                c = line.charAt(index);
            }
            else{
                break;
            }
        }

        c = line.charAt(++index);

        //build string
        while(Character.isDigit(c)){
            gearString += String.valueOf(c);
            index++;
            if(index < line.length()){
                c = line.charAt(index);
            }
            else{
                break;
            }
        }

        return gearString;
    }
}
