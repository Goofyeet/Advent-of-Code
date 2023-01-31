#include <iostream>
#include <fstream>
#include <string>
#include <regex>
#include <vector>
#include <set>
#include <utility>
#include <deque>
#include <memory>
#include <utility>

std::ifstream input("input.txt");
std::string line;
struct node
    {
        char height = 'a';
        bool visited = false;
        int distance = 0;
        int parentX = 0;
        int parentY = 0;
    };
std::deque<node> nodeQueue; // a queue of nodes
int startX = 0;
int startY = 0;

std::vector<std::vector<node>> createGrid()
{
    int lineNum = 0;
    std::vector<std::vector<node>> grid;

    while (getline(input, line))
    {
        if (lineNum == 0)
        {
            std::vector<std::vector<node>> grid(line.length(),std::vector<node>(42));
            std::cout << line.length() << std::endl;  // 163
            std::cout << grid.size() << std::endl;    // 163
            std::cout << grid[0].size() << std::endl; // equal to number of lines
            std::cout << grid[0].max_size() << std::endl;
            std::cout << grid[0].capacity() << std::endl;
        }
        int x = 0;
        for (char c : line)
        {
            //node tempNode = node();
            //tempNode.height = c;

            grid[x].emplace_back(node());
            //grid[x][lineNum].height = c;
            if (c == 'S')
            {
                startX = x;
                startY = lineNum;
            }

            x++;
        }
        lineNum++;
    }

    return grid;
}

void addNeighbors(std::vector<std::vector<node>> grid, int x, int y)
{
    int parentX = x;
    int parentY = y;
    if (y < grid[0].size())
    {
        nodeQueue.push_back(grid[x][y + 1]);
        grid[x][y + 1].parentX = parentX;
        grid[x][y + 1].parentY = parentY;
    }
    if (y > 0)
    {
        nodeQueue.push_back(grid[x][y - 1]);
        grid[x][y - 1].parentX = parentX;
        grid[x][y - 1].parentY = parentY;
    }
    if (x < grid.size())
    {
        nodeQueue.push_back(grid[x + 1][y]);
        grid[x + 1][y].parentX = parentX;
        grid[x + 1][y].parentY = parentY;
    }
    if (x > 1)
    {
        nodeQueue.push_back(grid[x - 1][y]);
        grid[x - 1][y].parentX = parentX;
        grid[x - 1][y].parentY = parentY;
    }
}

int findShortestPath(std::vector<std::vector<node>> grid)
{
    int x = startX;
    int y = startY;
    node neighbor;
    while (true)
    {
        addNeighbors(grid, x, y);
        neighbor = nodeQueue.front();
        if (neighbor.visited == false)
        {
            neighbor.distance = (grid[neighbor.parentX][neighbor.parentY].distance) + 1;
            if (neighbor.height == 'E')
            {
                return neighbor.distance;
            }
        }
        nodeQueue.pop_front();
    }
}

int main()
{
    std::vector<std::vector<node>> grid = createGrid();
    int distance = findShortestPath(grid);

    std::cout << "Fewest Steps possible: " << distance << std::endl;

    return 0;
}
