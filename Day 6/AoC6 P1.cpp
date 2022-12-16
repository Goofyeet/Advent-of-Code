#include <iostream>
#include <fstream>
#include <string>

int main()
{
    std::string line;
    int charNum;

    //change num to consecutive characters needed
    const int num = 14;
  
    std::ifstream input("input.txt");

    while (getline(input, line))
    {
          for(auto i = 0; i < line.length(); i++){
            for(auto count = 0; count <= (num - 2); count++){
                for(auto loop = 1; (loop + count) <= (num - 1); loop++){
                    if(line[i + count] == line[i + count + loop]){
                        //chars match, break from loops, check next char
                        count = num;
                    }
                    else if((count == (num - 2))){
                        //consecutive characters found
                        charNum = (i + num);
                        std::cout << "Number of Chars: " << charNum << std::endl;
                        i = line.length();  
                    }
                }
            }
          }  
    }

    input.close();

    return 0;
}
