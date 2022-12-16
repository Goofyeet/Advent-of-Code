#include <iostream>
#include <fstream>
#include <string>
#include <regex>

std::string line;
std::ifstream input("input.txt");
std::smatch match;
int total = 0;

void test()
{
    while (getline(input, line))
    {
        if(std::regex_search(line,match,std::regex("\\d+"))){
            total += stoi(match[0]);
        }
    };
    std::cout << total << std::endl;
}

int main()
{
    test();

    input.close();

    return 0;
}