#include <iostream>
#include <fstream>
#include <string>
#include <regex>
#include <vector>
#include <set>
#include <utility>

std::ifstream input("input.txt");
std::string line;
std::smatch match;
int sum;
int num;
int cycle = 0;
int signalStrength;
int position;
int x = 1;

void drawPixel()
{
    position = cycle % 40;
    int xl = x - 1;
    int xr = x + 1;
    xl %= 40; 
    x %= 40;
    xr %= 40;
    if ((xl == position) || (x == position) || (xr == position))
    {
        std::cout << "#";
    }
    else
    {
        std::cout << ".";
    }
    if (position == 39)
    {
        std::cout << std::endl;
    }
}

void doCycle()
{
    cycle++;
    drawPixel();
}

int main()
{
    drawPixel();
    while (getline(input, line))
    {
        if (std::regex_search(line, match, std::regex("-?\\d+")))
        {
            num = stoi(match[0].str());
            doCycle();
            x += num;
        }
        doCycle();
    }

    return 0;
}
