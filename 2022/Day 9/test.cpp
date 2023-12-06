#include <iostream>
#include <fstream>
#include <string>
#include <regex>

std::string line;
std::ifstream input("input.txt");
std::smatch match;
int total = 0;

void test()
{
    int test = 4;
    std::vector<int> testVec;
    testVec.push_back(test);

    test = 10;

    std::cout << "testVec test: " << testVec[0] << " Test: " << test << std::endl;
}

int main()
{
    test();

    input.close();

    return 0;
}