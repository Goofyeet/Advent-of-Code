#include <iostream>
#include <fstream>
#include <string>
#include <regex>
#include <vector>
#include <set>
#include <utility>

std::ifstream input("input.txt");
std::string line;
int numPositions = 1;
std::smatch match;
std::string tempDir;
int moves;
std::set<std::pair<int,int>> posSet;

enum direction
{
    up,
    right,
    down,
    left
} dir;

int main()
{
    struct knot
    {
        int x = 0;
        int y = 0;
        
        void move(direction dir)
        {
            switch (dir)
            {
            case up:
                y += 1;
                break;
            case right:
                x += 1;
                break;
            case down:
                y -= 1;
                break;
            case left:
                x -= 1;
                break;
            }
        }
    } head, tail, prev;

    while (getline(input, line))
    {
        bool moved = false;
        std::regex_search(line, match, std::regex("\\d+"));
        moves = stoi(match[0].str());

        std::regex_search(line, match, std::regex("[A-Z]"));
        tempDir = match[0].str();

        if (tempDir == "L")
        {
            dir = left;
        }
        else if (tempDir == "R")
        {
            dir = right;
        }
        else if (tempDir == "U")
        {
            dir = up;
        }
        else
        {
            dir = down;
        }

        for (auto i = 0; i < moves; i++)
        {
            head.move(dir);

            if ((abs(head.x - tail.x) > 1) || (abs(head.y - tail.y) > 1))
            {
                if ((tail.x != head.x) && (tail.y != head.y))
                {
                    // tail needs to move diagnally
                    tail.x = prev.x;
                    tail.y = prev.y;
                }
                else
                {
                    tail.move(dir);
                }
                int tempX = tail.x;
                int tempY = tail.y;
                posSet.insert(std::make_pair(tempX, tempY));
            }
            prev.x = head.x;
            prev.y = head.y;
        }
    }

    numPositions = posSet.size() + 1;
    std::cout << "Number of positions visited: " << numPositions << std::endl;

    return 0;
}
