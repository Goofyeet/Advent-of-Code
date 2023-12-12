package Day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FertilizerP1 {
    public static void main(String args[]){
        String path = "2023/Day5/test.txt";

        try{
            Scanner fileReader = new Scanner(new File(path));
            int minLocation = 0;
            String line = "";

            //loop through lines in puzzle input
            while(fileReader.hasNextLine()){
                line = fileReader.nextLine();

                
            }

            System.out.println("Lowest Location: " + minLocation);

        } catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }
}
