#include <iostream>
#include <fstream>
#include <string>
#include <regex>
#include <vector>

int visibleTrees = 0;

std::vector<std::vector<int>> createGrid()
{
    std::ifstream input("input.txt");
    std::string line;
    std::vector<std::vector<int>> treeGrid;
    int y = 0;
    int tree;
    int lineNum = 1;

    while (getline(input, line))
    {
        int x = 0;
        for (char t : line)
        {
            if (lineNum == 1)
            {
                std::vector<int> tempVec;
                treeGrid.push_back(tempVec);
            }
            tree = t - '0';
            treeGrid[x].push_back(tree);
            // std::cout << treeGrid.at(x).at(y);
            x++;
        }
        // std::cout << std::endl;
        y++;
        lineNum++;
    }

    input.close();

    return treeGrid;
}

int treeScore(int x, int y, std::vector<std::vector<int>> grid)
{
    int treeHeight = grid[x][y];
    int left = 0;
    int right = 0;
    int top = 0;
    int down = 0;

    if (x == 0)
    {
        left = 0;
    }
    else if (x == (grid.size() - 1))
    {
        right = 0;
    }
    else
    {
        // tree is between left and right edges
        for (auto l = (x - 1); l >= 0; l--)
        {
            if (grid[l][y] >= treeHeight)
            {
                left++;
                break;
            }
            else if(grid[l][y] < treeHeight)
            {
                left++;
            }
    
        }
        for (auto r = (x + 1); r < grid.size(); r++)
        {
            if (grid[r][y] >= treeHeight)
            {
                right++;
                break;
            }
            else if(grid[r][y] < treeHeight)
            {
                right++;
            }
        }
    }
    if (y == 0)
    {
        top = 0;
    }
    else if (y == (grid[0].size() - 1))
    {
        down = 0;
    }
    else
    {
        // tree is between top and bottom borders
        for (auto t = (y - 1); t >= 0; t--)
        {
            if (grid[x][t] >= treeHeight)
            {
                top++;
                break;
            }
            else if(grid[x][t] < treeHeight)
            {
                top++;
            }
        }
        for (auto d = (y + 1); d < grid[0].size(); d++)
        {
            if (grid[x][d] >= treeHeight)
            {
                down++;
                break;
            }
            else if(grid[x][d] < treeHeight)
            {
                down++;
            }
        }
    }

    return (left * right * top * down);
}

int main()
{
    std::vector<std::vector<int>> treeGrid = createGrid();
    int score = 0;

    for (auto x = 0; x < treeGrid.size(); x++)
    {
        for (auto y = 0; y < treeGrid[0].size(); y++)
        {
            int tempScore = treeScore(x, y, treeGrid);
            if (tempScore > score)
            {
                score = tempScore;
            }
        }
    }
    std::cout << "Highest score: " << score << std::endl;

    return 0;
}
