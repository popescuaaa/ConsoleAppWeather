// Console weather app made with c++11
// Author: Popescu Andrei Gabriel

#include <iostream>
#include <sstream>
#include <fstream>
#include <vector>


std::ifstream data("/home/popescu-andrei/Desktop/rdu-weather-history.csv");
std::ofstream out("/home/popescu-andrei/Desktop/weather.txt");

std::vector<std::string> parse_data(std::string input){
    std::vector<std::string> return_value;
    std::istringstream ss(input);
    std::string token;

    while(std::getline(ss, token, ';')) {
       return_value.push_back(token);
    }

    return return_value;
}   

int main(){
    // Made the tranzition from csv to txt 
        std::string data_to_process;
        data >> data_to_process;
        std::vector<std::string> return_value_for_data_to_process = parse_data(data_to_process);
         
        out << "The data that will be delived in this PDf document will be :" << std::endl;
        for(std::vector<std::string>::iterator i = return_value_for_data_to_process.begin();
                                               i != return_value_for_data_to_process.end(); 
                                               i++){
                 out << *i << std::endl;  

        }
         
        out << "Weather data : " << std::endl;
        
    while(!data.eof()){

        std::string input;
        data >> input;
        std::vector<std::string> return_value = parse_data(input);
        for(std::vector<std::string>::iterator i = return_value.begin(); i != return_value.end(); i++){
            out << *i << " ";

        }
        out << std::endl;
    }

    

    data.close();
    out.close();
return 0;
}