package Day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

public class PipeMazeP1 {
    private static ArrayList<String> map;

    public static void main(String args[]){
        String path = "2023/Day10/input.txt";
        try{
            Scanner fileReader = new Scanner(new File(path));
            String line = "";
            ArrayList<String> metalIsland = new ArrayList<>();

            //build metal island map
            while(fileReader.hasNextLine()){
                line = fileReader.nextLine();
                metalIsland.add(line);
            }

            map = metalIsland;

            int farthestDist = findDistance();

            System.out.println("Farthest Distance: " + farthestDist);

        } catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }

    public static int findDistance(){
        Integer[] start = findStart();
        Integer[][] nextMoves = moveFromStart(start);
        Integer[] coords1 = nextMoves[0];
        Integer[] coords2 = nextMoves[1];
        int distance = 1;
        boolean samePos = false;
        Integer[] past1 = start;
        Integer[] past2 = start;

        while(!samePos){
            Integer[] tempPos = coords1;
            coords1 = increment(coords1, past1);
            past1 = tempPos;

            tempPos = coords2;
            coords2 = increment(coords2, past2);
            past2 = tempPos;

            distance++;

            if(Objects.equals(coords1[0], coords2[0]) && Objects.equals(coords1[1], coords2[1])){
                samePos = true;
            }
        }

        return distance;
    }

    //return the 2 pipe positions connected to start
    private static Integer[][] moveFromStart(Integer[] startPos){
        int startX = startPos[1];
        int startY = startPos[0];
        Integer[][] nextMoves = new Integer[2][2];
        Integer[] northPos = {startY - 1, startX};
        Integer[] eastPos = {startY, startX + 1};
        Integer[] southPos = {startY + 1, startX};
        Integer[] westPos = {startY, startX - 1};
        int move = 0;

        if(checkPos(0, northPos)){
            nextMoves[move] = northPos;
            move++;
        }
        if(checkPos(1, eastPos)){
            nextMoves[move] = eastPos;
            move++;
        }
        if(move < 2 && checkPos(2, southPos)){
            nextMoves[move] = southPos;
            move++;
        }
        if(move < 2 && checkPos(3, westPos)){
            nextMoves[move] = westPos;
        }

        return nextMoves;
    }

    private static boolean checkPos(int dir, Integer[] pos){
        int posX = pos[1];
        int posY = pos[0];
        int height = map.size();
        int width = map.get(0).length();

        if(posX < 0 || posX >= width || posY < 0 || posY >= height){
            return false;
        }

        char c = map.get(posY).charAt(posX);

        switch(dir){
            case 0: //north
                if(c == '|' || c == '7' || c == 'F'){
                    return true;
                }
                break;
            case 1: //east
                if(c == '-' || c == '7' || c == 'J'){
                    return true;
                }
                break;
            case 2: //south
                if(c == '|' || c == 'L' || c == 'J'){
                    return true;
                }
                break;
            case 3: //west
                if(c == '-' || c == 'L' || c == 'F'){
                    return true;
                }
                break;
        }

        return false;
    }

    //returns the next position that hasn't been visited
    private static Integer[] increment(Integer[] pos, Integer[] past){
        //get current char
        char c = map.get(pos[0]).charAt(pos[1]);

        Integer[] northPos = {pos[0] - 1, pos[1]};
        Integer[] eastPos = {pos[0], pos[1] + 1};
        Integer[] southPos = {pos[0] + 1, pos[1]};
        Integer[] westPos = {pos[0], pos[1] - 1};

        switch(c){
            case 'F':
                //check south and east
                if(Objects.equals(past[0], southPos[0]) && Objects.equals(past[1], southPos[1])){
                    pos = eastPos;
                }
                else{
                    pos = southPos;
                }
                break;
            case '7':
                //check south and west
                if(Objects.equals(past[0], southPos[0]) && Objects.equals(past[1], southPos[1])){
                    pos = westPos;
                }
                else{
                    pos = southPos;
                }
                break;
            case 'L':
                //check north and east
                if(Objects.equals(past[0], northPos[0]) && Objects.equals(past[1], northPos[1])){
                    pos = eastPos;
                }
                else{
                    pos = northPos;
                }
                break;
            case 'J':
                //check north and west
                if(Objects.equals(past[0], northPos[0]) && Objects.equals(past[1], northPos[1])){
                    pos = westPos;
                }
                else{
                    pos = northPos;
                }
                break;
            case '|':
                if(Objects.equals(past[0], northPos[0]) && Objects.equals(past[1], northPos[1])){
                    pos = southPos;
                }
                else{
                    pos = northPos;
                }
                break;
            case '-':
                if(Objects.equals(past[0], westPos[0]) && Objects.equals(past[1], westPos[1])){
                    pos = eastPos;
                }
                else{
                    pos = westPos;
                }
                break;
        }

        return pos;
    }

    private static Integer[] findStart(){
        Integer[] coords = new Integer[2];
        int lineLen = map.get(0).length();

        for(int i = 0; i < map.size(); i++){
            String line = map.get(i);

            for(int j = 0; j < lineLen; j++){
                if(line.charAt(j) == 'S'){
                    coords[0] = i;
                    coords[1] = j;
                }
            }
        }

        return coords;
    }
}
