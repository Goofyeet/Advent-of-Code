package Day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Scanner;

public class WastelandP1 {
    public static void main(String args[]){
        String path = "2023/Day8/input.txt";
        try{
            Scanner fileReader = new Scanner(new File(path));
            long totalSteps = 0;
            int lineNum = 0;
            Hashtable<String, String[]> adjList = new Hashtable<>();
            String line = "";
            String currNode = "AAA";
            String directions = fileReader.nextLine();
            fileReader.nextLine();

            //build adjacency list
            while(fileReader.hasNextLine()){
                line = fileReader.nextLine();

                String[] strArr = line.split("\\s+=\\s+");
                String node = strArr[0];

                String[] connected = strArr[1].substring(1,strArr[1].length() - 1).split(",\\s+");

                adjList.put(node, connected);
            }

            boolean foundEnd = false;
            int loopCount = 0;
            int length = directions.length();

            while(!foundEnd){
                for(int i = 0; i < length; i++){
                    if(currNode.equals("ZZZ")){
                        foundEnd = true;
                        break;
                    }

                    char c = directions.charAt(i);
                    int dirIndex = 0;

                    if(c == 'R'){
                        dirIndex = 1;
                    }

                    currNode = adjList.get(currNode)[dirIndex];
                    totalSteps++;
                }
                //loopCount++;
                //System.out.print("Loop count: " + loopCount + "   total steps: " + totalSteps);
            }

            System.out.println("Total Steps: " + totalSteps);

        } catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }
}
