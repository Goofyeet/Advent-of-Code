#include <iostream>
#include <fstream>
#include <string>


int main(){

int score = 0;

std::string line;

std::ifstream input("input.txt");

while(getline(input, line))
{
    if(line[2] == 'Y')
        score += 3;
    else if(line[2] == 'Z')
        score += 6;

    if((line == "A Y") | (line == "B X") | (line == "C Z"))
        score += 1;
    else if((line == "B Y") | (line == "C X") | (line == "A Z"))
        score += 2;
    else
        score += 3;
}

std::cout << "Score: " << score << std::endl;

input.close();

return 0;

}
