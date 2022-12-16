#include <iostream>
#include <fstream>
#include <string>

int main()
{
    int sum = 0;
    int count = 1;

    std::string priority = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    std::string line;
    std::string shared;
    std::string sharedTemp;

    std::ifstream input("input.txt");

    while (getline(input, line))
    {
        if (count == 1)
        {
            shared += line;
        }

        else
        {
            sharedTemp += shared;
            for (char c : sharedTemp)
            {
                if (line.find(c) == std::string::npos)
                {
                    std::cout << "c: " << c << std::endl;
                    shared.erase(shared.begin() + shared.find(c));
                    std::cout << "shared: " << shared << std::endl;
                }
            }

            if (count == 3)
            {
                sum += (priority.find(shared[0]) + 1);
                count = 1;
                shared.clear();
                continue;
            }
        }
        sharedTemp.clear();
        count++;
    }

    std::cout << "sum: " << sum << std::endl;

    input.close();

    return 0;
}
