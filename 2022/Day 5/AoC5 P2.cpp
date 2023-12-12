#include <iostream>
#include <fstream>
#include <string>
#include <regex>
#include <vector>
#include <algorithm>
#include <deque>

int main()
{

    std::string line;
    std::string topCrates;
    int lineNum = 1;
    int totalStacks;
    int count = 1;
    int move;
    int from;
    int to;
    int stack;
    std::regex reg("\\d+");
    std::smatch match;
    std::vector<std::deque<int>> crates(9, std::deque<int>());
    std::ifstream input("input.txt");

    while (getline(input, line))
    {
        if (lineNum < 9)
        {
            if (lineNum == 1)
            {
                totalStacks = ((line.length() + 1) / 4);
            }

            for (auto i = 1; i < line.length(); i += 4)
            {
                if (line[i] != ' ')
                {
                    stack = (i + 3) / 4;
                    crates[stack - 1].push_front(line[i]);
                }
            }
        }

        else if (lineNum >= 11)
        {
            while (std::regex_search(line, match, reg))
            {
                switch (count)
                {
                case 1:
                    // quantity to move
                    move = stoi(match[0]);
                    break;
                case 2:
                    // from stack
                    from = stoi(match[0]) - 1;
                    break;
                case 3:
                    // to stack
                    to = stoi(match[0]) - 1;
                    break;
                }
                line = match.suffix().str();
                count++;
            }

            for(auto mov = 0; mov < move; mov++)
            {
                crates[to].push_back(*(crates[from].end() - (move - mov)));
            }

            for(auto m = 1; m <= move; m++){
              crates[from].pop_back();  
            }

            count = 1;
        }

        lineNum++;
    }

    std::cout << "Top Crates: ";

    for (auto stk = 0; stk < 9; stk++)
    {
        topCrates = crates[stk].back();
        std::cout << topCrates;
    }

    std::cout << ": Done" << std::endl;

    input.close();

    return 0;
}
