#include <iostream>
#include <fstream>
#include <string>
#include <regex>
#include <vector>
#include <set>
#include <utility>
#include <deque>

struct monkey;

std::ifstream input("input.txt");
std::string line;
std::smatch match;
unsigned __int64 monkeyBusiness;
int rounds = 10000;
std::vector<monkey *> monkeyArr;
int tempThrow;
int lineNum = 1;
int cycle = 1;

struct monkey
{
    std::deque<unsigned long long int> items;
    enum operation
    {
        add,
        mult,
        square
    } opEnum;
    int opNum = 0;
    int trueThrow;
    int falseThrow;
    int divNum;
    int inspections;
    unsigned long long int updateWorry()
    {
        if (opEnum == add)
        {
            return (items[0] += opNum);
        }
        else if (opEnum == mult)
        {
            return (items[0] *= opNum);
        }
        else
        {
            // return items[0];
            return (items[0] *= items[0]);
        }
    }
};

int main()
{
    while (getline(input, line))
    {
        if (((lineNum - 1) % 7) == 0)
        {
            monkey *p = new monkey();
            monkeyArr.push_back(p);
        }
        else if (((lineNum - 2) % 7) == 0)
        {
            while (std::regex_search(line, match, std::regex("\\d+")))
            {
                monkeyArr.back()->items.push_back(stoi(match[0].str()));
                line = match.suffix();
            }
        }
        else if (((lineNum - 3) % 7) == 0)
        {
            if (std::regex_search(line, match, std::regex("[*+]")))
            {
                std::string tempEnum = match[0].str();
                if (tempEnum == "*")
                {
                    monkeyArr.back()->opEnum = monkey::mult;
                }
                else if (tempEnum == "+")
                {
                    monkeyArr.back()->opEnum = monkey::add;
                }
            }
            if (std::regex_search(line, match, std::regex("\\d+")))
            {
                monkeyArr.back()->opNum = stoi(match[0].str());
            }
            else if (std::regex_search(line, match, std::regex(" old$")))
            {
                monkeyArr.back()->opEnum = monkey::square;
            }
        }
        else if (((lineNum - 4) % 7) == 0)
        {
            if (std::regex_search(line, match, std::regex("\\d+")))
            {
                monkeyArr.back()->divNum = stoi(match[0].str());
                cycle *= monkeyArr.back()->divNum;
            }
        }
        else if (((lineNum - 5) % 7) == 0)
        {
            if (std::regex_search(line, match, std::regex("\\d+")))
            {
                monkeyArr.back()->trueThrow = stoi(match[0].str());
            }
        }
        else if (((lineNum - 6) % 7) == 0)
        {
            if (std::regex_search(line, match, std::regex("\\d+")))
            {
                monkeyArr.back()->falseThrow = stoi(match[0].str());
            }
        }
        lineNum++;
        
    }

    // loop through 20 rounds
    for (auto i = 0; i < rounds; i++)
    {
        // loop through each monkey
        for (auto m = 0; m < monkeyArr.size(); m++)
        {
            // loop each item the monkey has
            while (monkeyArr[m]->items.size() > 0)
            {
                monkeyArr[m]->inspections++;
                unsigned long long int worry = monkeyArr[m]->updateWorry();
                while(worry > cycle)
                {
                    worry -= cycle;
                }

                int remainder = (worry % monkeyArr[m]->divNum);
                if (remainder == 0)
                {
                    tempThrow = monkeyArr[m]->trueThrow;
                }
                else
                {
                    // worry -= remainder;
                    tempThrow = monkeyArr[m]->falseThrow;
                }
                // worry /= monkeyArr[m]->divNum;
                monkeyArr[tempThrow]->items.push_back(worry);

                monkeyArr[m]->items.pop_front();
            }
        }
        /*
                for (auto t = 0; t < monkeyArr.size(); t++)
                {
                    std::cout << "Monkey " << t << ": ";
                    for (auto s = 0; s < monkeyArr[t]->items.size(); s++)
                    {
                        std::cout << monkeyArr[t]->items[s] << ", ";
                    }
                    std::cout << std::endl;
                }
                */
    }

    std::set<int> maxSet;

    for (auto i = 0; i < monkeyArr.size(); i++)
    {
        maxSet.insert(monkeyArr[i]->inspections);
        std::cout << "Monkey " << i << " inspected items " << monkeyArr[i]->inspections << " times. " << monkeyArr[i]->items.size() << std::endl;
    }

    int max = *maxSet.rbegin();
    int max2 = *(++maxSet.rbegin());
    monkeyBusiness = max * max2;

    std::cout << "max: " << max << std::endl;
    std::cout << "max2: " << max2 << std::endl;
    std::cout << "Total monkey business: " << monkeyBusiness << std::endl;

    return 0;
}
