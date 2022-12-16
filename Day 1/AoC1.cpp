#include <iostream>
#include <fstream>
#include <string>


int main(){

int large = 0;
int mid = 0;
int low = 0;
int local = 0;
std::string line;

std::ifstream input("input.txt");

while(getline(input, line))
{
    
    if (line == "")
    {
        if (local > large)
        {
            low = mid;
            mid = large;
            large = local;
        }
        else if(local > mid){
            low = mid;
            mid = local;
        }
        else if(local > low){
            low = local;
        }
        local = 0;
        continue;
    }

    local += std::stoi(line);
   //std::cout << line << std::endl;

}

int total = large + mid + low;

std::cout << "first: " << large << std::endl;
std::cout << "second: " << mid << std::endl;
std::cout << "third: " << low << std::endl;
std::cout << "total: " << total << std::endl;

input.close();

return 1;

}
