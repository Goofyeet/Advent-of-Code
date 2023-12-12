package Day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ScratchcardsP2 {
    public static void main(String args[]){
        String path = "2023/Day4/input.txt";

        try{
            Scanner fileReader = new Scanner(new File(path));
            long totalCards = 0;
            String line = "";
            ArrayList<Integer> copies = new ArrayList<>();
            copies.add(0);
            int cardNum = 1;

            //loop through lines in puzzle input
            while(fileReader.hasNextLine()){
                line = fileReader.nextLine();   //Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
                int wonCopies = 0;
                int cardCopies = copies.get(cardNum - 1);

                String[] numbers = line.split(" \\| "); //["Card 1: 41 48 83 86 17", "83 86  6 31 17  9 48 53"]
                String dirtyWinners = numbers[0];   //"Card 1: 41 48 83 86 17"

                String myNumbers = numbers[1].trim();  //"83 86  6 31 17  9 48 53"
                String winners = dirtyWinners.split(": ")[1].trim();   //"41 48 83 86 17"

                String[] myNumbersStrArr = myNumbers.split("\\s+"); //["83", "86", "6", "31", "17", "9", "48", "53"]
                String[] winnersStrArr = winners.split("\\s+"); //["41", "48", "83", "86", "17"]

                int[] myNumbersArr = new int[myNumbersStrArr.length];
                int[] winnersArr = new int[winnersStrArr.length];

                //initialize myNumbersArr
                for(int i = 0; i < myNumbersArr.length; i++){
                    myNumbersArr[i] = Integer.parseInt(myNumbersStrArr[i]);
                }

                //initialize winnersArr
                for(int i = 0; i < winnersArr.length; i++){
                    winnersArr[i] = Integer.parseInt(winnersStrArr[i]);
                }

                //check if this card has any winners
                for(int i = 0; i < winnersArr.length; i++){
                    for(int j = 0; j < myNumbersArr.length; j++){

                        //there is a match
                        if(winnersArr[i] == myNumbersArr[j]){
                            wonCopies++;

                            break;
                        }
                    }
                }

                //increment number of copies of the cards I won a copy of
                for(int i = 0; i < wonCopies; i++){
                    int cardIndex = cardNum + i;

                    //if entry exists
                    if(copies.size() > cardIndex){
                        //new value equals old value + 1 for original + 1 for each copy
                        int c = copies.get(cardIndex) + (1 + cardCopies);
                        copies.set(cardIndex,c);
                    }
                    else{
                        copies.add(cardIndex, 1 + cardCopies);
                    }
                }

                //if next card doesn't have any copies
                if(copies.size() < cardNum + 1){
                    copies.add(0);
                }

                int numCards = 1 + cardCopies;

                System.out.println(cardNum + ": " + numCards);

                totalCards += numCards;

                cardNum++;
            }

            System.out.println("Total Cards: " + totalCards);

        } catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }
}
