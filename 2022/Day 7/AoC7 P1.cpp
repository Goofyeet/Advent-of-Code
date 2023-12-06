#include <iostream>
#include <fstream>
#include <string>
#include <regex>

std::string line;
int dirSum = 0;
std::smatch match;
int total;

std::ifstream input("input.txt");

int dirSize()
{
    int fileSize = 0;
    int numKids = 0;
    int size = 0;
    int childSize = 0;
    std::string dirName;

    while (getline(input, line))
    {
        // xxxxx fileName
        if (std::regex_search(line, match, std::regex("\\d+")))
        {
            fileSize += stoi(match[0]);
        }
        //$ cd dirName
        else if (std::regex_search(line, match, std::regex("\\$\\scd\\s[a-zA-Z]+")))
        {
            //std::regex_search(line,match,std::regex("[a-zA-Z]+$"));
            //dirName = match[0].str();
            break;
        }
        // dir dirName
        else if (std::regex_search(line, match, std::regex("dir\\s")))
        {
            numKids += 1;
        }
    }

    size += fileSize;
    for (auto i = 1; i <= numKids; i++)
    {
        childSize = dirSize();
        size += childSize;
    }
    if (size <= 100000)
    {
        dirSum += size;
        std::cout << "Dir Sum: " << dirSum << std::endl;
    }
    
    return size;
}

int main()
{
    total = dirSize();

    std::cout << "Total: " << total << std::endl;

    input.close();

    return 0;
}
