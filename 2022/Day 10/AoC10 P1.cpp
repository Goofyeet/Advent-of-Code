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
int cycle = 1;
int signalStrength;
int start = 20;
int end = 220;
int gap = 40;
int x = 1;

void checkCycle()
{
    for (auto i = start; i <= end; i += gap)
    {
        if (cycle == i)
        {
            signalStrength = cycle * x;
            sum += signalStrength;
            std::cout << "Signal stength at " << cycle << ", x is " << x << " : " << signalStrength << std::endl;
        }
    }
}

int main()
{
    while (getline(input, line))
    {
        if (std::regex_search(line, match, std::regex("-?\\d+")))
        {
            num = stoi(match[0].str());
            cycle++;
            checkCycle();
            x += num;
        }
        cycle++;
        checkCycle();
    }

    std::cout << "Total sum of signal strengths: " << sum << std::endl;

    return 0;
}
