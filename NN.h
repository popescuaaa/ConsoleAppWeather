/*
    MAIN METHODS:
        feedforward(input values); - information goes from input to output
        backpropopagtion(target values); -  information goes from output to input ( backwords ) to minimize the error
        getResults() const; - this function gets the results and then print them out
                            - basically it dows not modify the object so is a const member/method


*/

#include <vector>
#include <iostream>

struct Connection{
    double weight;
    double deltaWeight;

};

class Neuron;

typedef std::vector<Neuron> layer;

// ********************** Class Neuron ********************
// we define the class here as we'll use the layer data type 

class Neuron{
    public:
        Neuron();

    private:
        // the most important piece of data in a Neuron
        double m_outputValue;
        // a Neuron also contains a specific weight which is used to feed 
        // the other Neurons, in our case will be specified and fiexd
        // Note: there are specific type of neural nets with specific weights 
        // calculated even during the process of learning in order to minimize the
        // erros.
        std::vector<Connection> m_outputWeights;
        // there is one weight for every Neuron which is connected


};

// ********************** Class Net ***********************
class Net{
    private:
        std::vector<layer> m_layers;

    public:
        Net(const std::vector<unsigned int> &topology);
        void feedforward( const std::vector<double>& inputVals){}; // beacause it doesn't make any change in the 
                                                                 // inputVals vector
        void backpropagation( const std::vector<double>& targetVals){}; 
        void getResults( std::vector<double> results ){}; // we make changes to the results vector ( we write it )
                                                        // there is no need for the const keyword


};

 Net::Net(const std::vector<unsigned int> &topology){
     int numLayers = topology.size();
     
     
     // for every layer in numLayers range we'll add a new layer to  m_layers 
     for( unsigned int i =0;i < numLayers;i++ ){
         m_layers.push_back(layer());

         // We have added a new layer, now we'll fill it with Neurons
         // and we'll add a bias neuron to the layer
         for(unsigned int numNeurons = 0;numNeurons <= topology[i];numNeurons++ ){
             // we go further with one position because we'll have an aditional BIAS neuron 
             // with a fixed value
             m_layers.back().push_back( Neuron() );
        

         } 
     }
 }