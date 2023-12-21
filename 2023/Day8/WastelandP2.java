package Day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class WastelandP2 {
    public static void main(String args[]){
        String path = "2023/Day8/input.txt";
        try{
            Scanner fileReader = new Scanner(new File(path));
            long totalSteps = 0;
            Hashtable<String, String[]> adjList = new Hashtable<>();
            ArrayList<String> currNodes = new ArrayList<>();
            Hashtable<String, Integer> zDistance = new Hashtable<>();
            String directions = fileReader.nextLine();
            fileReader.nextLine();

            //build adjacency list
            while(fileReader.hasNextLine()){
                String line = fileReader.nextLine();

                String[] strArr = line.split("\\s+=\\s+");
                String node = strArr[0];

                if(node.charAt(2) == 'A'){
                    currNodes.add(node);
                }

                String[] connected = strArr[1].substring(1,strArr[1].length() - 1).split(",\\s+");

                adjList.put(node, connected);
            }

            int dirLength = directions.length();
            ArrayList<Integer> distances = new ArrayList<>();
            long lcm = 1;

            //loop over directions again if end not found
            for(int n = 0; n < currNodes.size(); n++){
                String node = currNodes.get(n);
                int distance = 0;
                boolean zFound = false;

                while(!zFound){
                    //loop through directions
                    for(int i = 0; i < dirLength; i++){
                        if(node.charAt(2) == 'Z'){
                            distances.add(distance);
                            zFound = true;
                            break;
                        }

                        char c = directions.charAt(i);
                        int dirIndex = 0;

                        if(c == 'R'){
                            dirIndex = 1;
                        }

                        node = adjList.get(node)[dirIndex];

                        distance++;
                        }
                    }
                }

            lcm = distances.get(0);

            while(true){
                lcm += distances.get(0);

                if(lcm % distances.get(1) == 0){
                    if(lcm % distances.get(2) == 0){
                        if(lcm % distances.get(3) == 0){
                            if(lcm % distances.get(4) == 0){
                                if(lcm % distances.get(5) == 0){
                                    break;
                                }
                            }
                        }
                    }
                }
            }

            System.out.println("Total Steps: " + lcm);

        } catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }
}
