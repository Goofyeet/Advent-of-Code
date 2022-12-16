#include <iostream>
#include <fstream>
#include <string>
#include <regex>
#include <set>
#include <utility>

std::ifstream input("input.txt");
std::string line;
int numPositions = 1;
std::smatch match;
std::string tempDir;
int moves;
std::set<std::pair<int, int>> posSet;
int ropeX[10];
int ropeY[10];
int prevX[10];
int prevY[10];
int xDir;
int yDir;

int main()
{
    while (getline(input, line))
    {
        std::regex_search(line, match, std::regex("\\d+"));
        moves = stoi(match[0].str());

        std::regex_search(line, match, std::regex("[A-Z]"));
        tempDir = match[0].str();

        if (tempDir == "L")
        {
            xDir = -1;
            yDir = 0;
        }
        else if (tempDir == "R")
        {
            xDir = 1;
            yDir = 0;
        }
        else if (tempDir == "U")
        {
            xDir = 0;
            yDir = 1;
        }
        else
        {
            xDir = 0;
            yDir = -1;
        }

        for (auto i = 0; i < moves; i++)
        {
            // move head first
            ropeX[0] += xDir;
            ropeY[0] += yDir;

            for (auto k = 0; k < 9; k++)
            {
                // move each tail piece
                if ((abs(ropeX[k] - ropeX[k + 1]) > 1) || (abs(ropeY[k] - ropeY[k + 1]) > 1))
                {
                    if ((ropeX[k] != ropeX[k + 1]) && (ropeY[k] != ropeY[k + 1]))
                    {
                        // tail needs to move diagnally
                        if (ropeY[k] > ropeY[k + 1])
                        {
                            ropeY[k + 1] += 1;
                        }
                        else if (ropeY[k] < ropeY[k + 1])
                        {
                            ropeY[k + 1] -= 1;
                        }
                        if (ropeX[k] > ropeX[k + 1])
                        {
                            ropeX[k + 1] += 1;
                        }
                        else if (ropeX[k] < ropeX[k + 1])
                        {
                            ropeX[k + 1] -= 1;
                        }
                    }

                    else
                    {
                        // move tail towards last rope piece
                        if (ropeX[k] == ropeX[k + 1])
                        {
                            ropeY[k + 1] += ((ropeY[k] - ropeY[k + 1]) / 2);
                        }
                        else if (ropeY[k] == ropeY[k + 1])
                        {
                            ropeX[k + 1] += ((ropeX[k] - ropeX[k + 1]) / 2);
                        }
                    }

                    if (k == 8)
                    {
                        posSet.insert(std::make_pair(ropeX[9], ropeY[9]));
                    }
                }
            }
        }
    }

    numPositions = posSet.size() + 1;
    std::cout << "Number of positions visited: " << numPositions << std::endl;

    return 0;
}
