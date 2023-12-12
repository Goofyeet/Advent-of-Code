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

bool isTreeVisible(int x, int y, std::vector<std::vector<int>> grid)
{
    int treeHeight = grid[x][y];
    for (auto l = (x - 1); l >= 0; l--)
    {
        if (grid[l][y] >= treeHeight)
        {
            break;
        }
        if (l == 0)
        {
            return true;
        }
    }
    for (auto r = (x + 1); r < grid.size(); r++)
    {
        if (grid[r][y] >= treeHeight)
        {
            break;
        }
        if (r == (grid.size() - 1))
        {
            return true;
        }
    }
    for (auto t = (y - 1); t >= 0; t--)
    {
        if (grid[x][t] >= treeHeight)
        {
            break;
        }
        if (t == 0)
        {
            return true;
        }
    }
    for (auto d = (y + 1); d < grid[0].size(); d++)
    {
        if (grid[x][d] >= treeHeight)
        {
            break;
        }
        if (d == (grid[0].size() - 1))
        {
            return true;
        }
    }

    return false;
}

int main()
{
    std::vector<std::vector<int>> treeGrid = createGrid();
    visibleTrees += ((treeGrid[0].size() * 2) + (treeGrid.size() * 2) - 4);

    for (auto x = 1; x < (treeGrid.size() - 1); x++)
    {
        for (auto y = 1; y < (treeGrid[0].size() - 1); y++)
        {
            if (isTreeVisible(x, y, treeGrid))
            {
                visibleTrees++;
            }
        }
    }
    std::cout << "Visible Trees: " << visibleTrees << std::endl;

    return 0;
}
