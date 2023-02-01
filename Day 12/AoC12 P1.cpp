#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <deque>

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
std::deque<int> xQueue;
std::deque<int> yQueue;
int startX = 0;
int startY = 0;

std::vector<std::vector<node>> createGrid()
{
    int lineNum = 0;
    std::vector<std::vector<node>> grid;

    while (getline(input, line))
    {
        int x = 0;

        for (char c : line)
        {
           if (lineNum == 0)
        {
            grid.emplace_back(std::vector<node>());
        } 
            grid.at(x).emplace_back(node());
            
            if (c == 'S')
            {
                startX = x;
                startY = lineNum;
                grid.at(x).at(lineNum).height = 'a';
                grid.at(x).at(lineNum).visited = true;
            }
            else if (c == 'E')
            {
                grid.at(x).at(lineNum).height = '{';
            }
            else
            {
                grid.at(x).at(lineNum).height = c;
            }

            x++;
        }
        lineNum++;
    }
    std::cout << grid[0].size() << std::endl;
    std::cout << grid.size() << std::endl;
    return grid;
}

void addNeighbors(std::vector<std::vector<node>>& grid, int x, int y)
{
    int parentX = x;
    int parentY = y;
    if ((y < (grid[0].size() - 1)) && (!grid[x][y + 1].visited) && (grid[x][y + 1].height <= (grid[x][y].height + 1)))
    {
        grid[x][y + 1].parentX = parentX;
        grid[x][y + 1].parentY = parentY;
        xQueue.push_back(x);
        yQueue.push_back(y + 1);
    }
    if ((y > 0) && (!grid[x][y - 1].visited) && (grid[x][y - 1].height <= (grid[x][y].height + 1)))
    {
        grid[x][y - 1].parentX = parentX;
        grid[x][y - 1].parentY = parentY;
        xQueue.push_back(x);
        yQueue.push_back(y - 1);
    }
    if ((x < (grid.size() - 1)) && (!grid[x + 1][y].visited) && (grid[x + 1][y].height <= (grid[x][y].height + 1)))
    {
        grid[x + 1][y].parentX = parentX;
        grid[x + 1][y].parentY = parentY;
        xQueue.push_back(x + 1);
        yQueue.push_back(y);
    }
    if ((x > 0) && (!grid[x - 1][y].visited) && (grid[x - 1][y].height <= (grid[x][y].height + 1)))
    {
        grid[x - 1][y].parentX = parentX;
        grid[x - 1][y].parentY = parentY;
        xQueue.push_back(x - 1);
        yQueue.push_back(y);
    }
}

int findShortestPath(std::vector<std::vector<node>> grid)
{
    int x = startX;
    int y = startY;

    while (true)
    {
        char height = grid[x][y].height;
        addNeighbors(grid, x, y);
        node& neighbor = grid[x][y];
        node parentNode = grid[neighbor.parentX][neighbor.parentY];
            
        //std::cout << "x: " << x << " y: " << y << " height: " << height << " neighbor.height: " << neighbor.height << " ";

        neighbor.distance = parentNode.distance + 1;
        //std::cout << neighbor.distance << std::endl;
        neighbor.visited = true;
        if (neighbor.height == '{')
        {
            return neighbor.distance - 1;
        }
            
        x = xQueue.front();
        y = yQueue.front();
        xQueue.pop_front();
        yQueue.pop_front();
    }
}

int main()
{
    std::vector<std::vector<node>> grid = createGrid();
    int distance = findShortestPath(grid);

    std::cout << "Fewest Steps possible: " << distance << std::endl;

    return 0;
}
