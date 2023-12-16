package Day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class CamelCardsP1 {
    public static void main(String args[]){
        String path = "2023/Day7/input.txt";

        try{
            Scanner fileReader = new Scanner(new File(path));
            int total = 0;
            ArrayList<String[]> handList = new ArrayList<>();
            String line = "";

            while(fileReader.hasNextLine()){
                line = fileReader.nextLine();
                String[] strArr = line.split("\\s+");

                handList.add(strArr);
            }

            Comparator<String[]> compareHands = (String[] hand1, String[] hand2)-> {
                int first = classifyHand(hand1[0]);
                int next = classifyHand(hand2[0]);

                if(first > next){
                    return 1;
                }
                else if(first < next){
                    return -1;
                }
                else{
                    return evalSameHand(hand1[0], hand2[0]);
                }

            };

            handList.sort(compareHands);

            for(int i = 0; i < handList.size(); i++){
                int product = (i + 1) * Integer.parseInt(handList.get(i)[1]);

                total += product;
            }


            System.out.println("Total product: " + total);

        } catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }

    public static int classifyHand(String hand){
        ArrayList<Character> distinctChars = new ArrayList<>();
        ArrayList<Integer> numChars = new ArrayList<>();

        //loop through chars of first hand
        for(int i = 0; i < hand.length(); i++){
            char c = hand.charAt(i);

            int charIndex = distinctChars.indexOf(c);

            if(charIndex == -1){
                distinctChars.add(c);
                numChars.add(1);
            }
            else {
                numChars.set(charIndex,numChars.get(charIndex) + 1);
            }
        }

        int numPairs = 0;
        int numTriple = 0;
        int handNum = 1;
        for(Integer num : numChars){
            switch(num){
                case 1:
                    break;
                case 2:
                    numPairs++;
                    break;
                case 3:
                    numTriple++;
                    break;
                default:
                    handNum = num + 2;
            }
            if(handNum > 5){
                break;
            }
        }
        if(numTriple == 1){
            if(numPairs == 1){
                handNum = 5;
            }
            else{
                handNum = 4;
            }
        }
        else{
            handNum += numPairs;
        }

        return handNum;
    }

    public static int evalSameHand(String hand1, String hand2){
        char[][] letters = {{'A', '5'}, {'K', '4'}, {'Q', '3'}, {'J', '2'}, {'T', '1'}};
        char[] chars = new char[2];

        //hands are the same
        for(int i = 0; i < 5; i++){
            chars[0] = hand1.charAt(i);
            chars[1] = hand2.charAt(i);

            //at least one of them is a digit
            if(Character.isDigit(chars[0]) || Character.isDigit(chars[1])){
                if(chars[0] < chars[1]){
                    return -1;
                }
                else if(chars[0] > chars[1]){
                    return 1;
                }
            }
            //both are letters
            else{
                for(int j = 0; j < letters.length; j++){
                    if(chars[0] == letters[j][0]){
                        chars[0] = letters[j][1];
                    }
                    if(chars[1] == letters[j][0]){
                        chars[1] = letters[j][1];
                    }
                }

                if(chars[0] < chars[1]){
                    return -1;
                }
                else if(chars[0] > chars[1]){
                    return 1;
                }
            }
        }

        return 0;
    }
}
