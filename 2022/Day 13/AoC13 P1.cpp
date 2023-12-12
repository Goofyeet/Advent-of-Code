#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <deque>

std::ifstream input("input2.txt");
std::string line;

int findTotalIndexes()
{
    int lineNum = 0;
    int index = 1;
    int total = 0;
    std::string firstPair;
    std::string secondPair;

    while(getline(input, line))
    {
        //check if line is a first pair line
        if(lineNum % 3 == 0)
        {
            firstPair = line;
        }
        //check if line is a second pair line
        else if((lineNum - 1) % 3 == 0)
        {
            secondPair = line;
            comparePairs(firstPair, secondPair, index);
            index++;
        }

        lineNum++;
    }  
}

//didn't think about handling both integers and lists...use templates?
void comparePairs(std::string pair1Str, std::string pair2Str, int index)
{
    //parse line and convert to a list or an integer
    void parseInput(std::string pair1Str, std::string pair2Str)
    {

    }

    parseInput(pair1Str, pair2Str);



}

int main()
{
    int totalIndexes = 0;

    totalIndexes = findTotalIndexes();
    
    std::cout << "Total indexes that are in the right order: " << totalIndexes << std::endl;

    return 0;
}
