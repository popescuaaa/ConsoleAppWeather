/*
    MAIN METHODS:
        feedforward(input values); - information goes from input to output
        backpropopagtion(target values); -  information goes from output to input ( backwords ) to minimize the error
        getResults() const; - this function gets the results and then print them out
                            - basically it dows not modify the object so is a const member/method


*/

#include <vector>
typedef int Neuron; 
typedef std::vector<Neuron> layer;

class Net{
    private:
        

    public:
        Net(const std::vector<unsigned int> &topology){

        }
        void feedforward( const std::vector<double>& inputVals); // beacause it doesn't make any change in the 
                                                                 // inputVals vector
        void backpropagation( const std::vector<double>& targetVals); 
        void getResults( std::vector<double> results ); // we make changes to the results vector ( we write it )
                                                        // there is no need for the const keyword


};