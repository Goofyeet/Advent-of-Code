#include <iostream>
#include <fstream>
#include <string>
#include <regex>
#include <vector>
#include <set>
#include <utility>
#include <deque>

std::ifstream input("input.txt");
std::string line;
struct node
{
    char height;
    bool visited;
    int distance = 0;
    node* parent = nullptr;
};
std::deque<node*> nodeQueue;
int startX = 0;
int startY = 0;
std::vector<std::vector<node*>> grid;


void createGrid()
{
    int lineNum = 0;

    while(getline(input,line))
    {
        if (lineNum == 0)
            {
                std::vector<std::vector<node*>> grid(line.length());
                std::cout << line.length() << std::endl;    //163
                std::cout << grid.size() << std::endl;      //163
                std::cout << grid[0].size() << std::endl;   //equal to number of lines
            }
        int x = 0;
        for(char c:line)
        {
            grid[x].emplace_back(new node);
            grid[x][lineNum]->height = c;
            if(c == 'S')
            {
                startX = x;
                startY = lineNum;
            }
            
            x++;
        }
        lineNum++;
    }
}


void addNeighbors(int x, int y)
{
    node* parent = grid[x][y];
    if(y < grid[0].size())
    {
        nodeQueue.push_back(grid[x][y + 1]);
        grid[x][y + 1]->parent = parent;
    }
    if(y > 0)
    {
        nodeQueue.push_back(grid[x][y - 1]);
        grid[x][y - 1]->parent = parent;
    }
    if(x < grid.size())
    {
        nodeQueue.push_back(grid[x + 1][y]);
        grid[x + 1][y]->parent = parent;
    }
    if(x > 1)
    {
        nodeQueue.push_back(grid[x - 1][y]);
        grid[x - 1][y]->parent = parent;
    }
}


int findShortestPath()
{
    int x = startX;
    int y = startY;
    node* neighbor;
    while(true)
    {
        addNeighbors(x,y);
        neighbor = nodeQueue.front();
        if(neighbor->visited == false)
        {
            neighbor->distance = neighbor->parent->distance + 1;
            if(neighbor->height == 'E')
            {
                return neighbor->distance;
            }
        }
        nodeQueue.pop_front();
    }
}


int main()
{
    createGrid();
    int distance = findShortestPath();

    std::cout << "Fewest Steps possible: " << distance << std::endl;

    return 0;
}
