#include <iostream>
#include <fstream>
#include <string>
#include <regex>


int main(){

std::string line;
int overlapped = 0;
int first;
int second;
int third;
int fourth;
int count = 1;

std::ifstream input("input.txt");

while(getline(input, line))
{
    const std::string linestr(line);
    std::smatch match;

    std::regex reg("\\d+");

    while((std::regex_search(line, match, reg)) && (count <= 4)){
        
        switch(count){
            case 1:
                first = stoi(match.str(0));
            case 2:
                second = stoi(match.str(0));
            case 3:
                third = stoi(match.str(0));
            case 4:
                fourth = stoi(match.str(0));
        }
        
        count++;

        line = match.suffix().str();

    }
    count = 1;

    if(((third >= first) && (fourth <= second)) | ((first >= third) && (second <= fourth))){
        overlapped++;
    }
    
}

std::cout << "overlapped: " << overlapped << std::endl;

input.close();

return 0;

}
