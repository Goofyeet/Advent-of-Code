#include <iostream>
#include <fstream>
#include <string>
#include <regex>
#include <set>

std::string line;
int dirSum = 0;
std::smatch match;
int total;
int fileSystem = 70000000;
int space;
int needed;
std::set<int> dirSizes; 

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
    dirSizes.insert(size);
    return size;
}

int main()
{
    total = dirSize();

    space = fileSystem - total;
    needed = 30000000 - space;

    //std::cout << "Total: " << total << std::endl;

    for(std::set<int>::iterator it = dirSizes.begin(); it != dirSizes.end(); it++){
        if(*it >= needed){
            std::cout << "Directory: " << *it << " needed: " << needed << std::endl;
        }
    }

    input.close();

    return 0;
}
