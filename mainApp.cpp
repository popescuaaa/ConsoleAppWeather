// Popescu Andrei Gabriel September 2018
// Neural Network ( feedforwoard model ) weather-predictor console application

/*
 
    - topology : specify the architecture of the neutral net 
      *for exemple, a topology like {3,2,1} =  specifies a 3 layes = size(toplogy)
        neural net and then, topology[i], i = 1,size(toplogy) neurons / specific layer    

*/


#include <iostream>
#include "NN.h"
#include <fstream>
#include <vector>

using namespace std;


int main(){
    
    vector<double> inputVals;
    vector<double> targetVals;
    vector<double> results;
    vector<unsigned int> topology; // here we specifiy the topology of the neural net 

    Net myNet(topology);
    
    return 0;
}

