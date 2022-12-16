#include <iostream>
#include <fstream>
#include <string>


int main(){

int sum = 0;
int half;

std::string priority = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
std::string line;
char shared;

std::ifstream input("input.txt");

while(getline(input, line))
{
    half = line.length() / 2;
    std::string left(line.begin(), line.begin() + half);
    std::string right(line.begin() + half, line.end());

    for(char c : left){
        for(char a : right){
            if(c == a){
                shared = c;
                break;
            }
        }
    }

    sum += (priority.find(shared) + 1);

    //std::cout << "left: " << left << " length: " << left.length() << std::endl;
    //std::cout << "right: " << right << " length: " << right.length() << std::endl;
    std::cout << "shared: " << shared << " sum: " << sum << std::endl;
}

std::cout << "sum: " << sum << std::endl;

input.close();

return 0;

}
