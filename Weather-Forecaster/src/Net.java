import java.util.ArrayList;

public class Net {
    private static final Integer MAX_NUMBER_OF_LAYERS = 10;
    private ArrayList<Layer> layers = new ArrayList<>(MAX_NUMBER_OF_LAYERS);
    private Double error;
    private ArrayList<Double> resultVal = new ArrayList<>(10);

    public Net(final ArrayList<Integer> topology) {
        for (int layerNum = 0; layerNum < topology.size(); layerNum++){

            Layer l = new Layer();
            Integer numOutputs;
            if (layerNum == topology.size() - 1) {
                numOutputs = topology.size() - 1;
            } else {
                numOutputs = topology.get(layerNum + 1);
            }

            // a neuron has output for every other neuron from the next layer
            // 0 - N => there is an extra BIAS neuron
            for (int numNeurons = 0; numNeurons <= topology.get(layerNum); numNeurons++) {
                l.getNeurons().add(new Neuron(numOutputs, 1));
            }

            layers.add(l);
        }
    }

    public void feedForward(final ArrayList<Double> inputVals){
        System.out.println("feed me!\n");
        if(entrance_assert(inputVals) == true) {

            for (int i  = 0; i < inputVals.size() - 1; i++ ) {
                layers.get(0)
                        .getNeurons()
                        .get(i)
                        .setOutputVal(inputVals.get(i));
            }

            // Propagate -> forward-> from 1 to last hidden layer

            for (int layerNum = 1; layerNum < layers.size(); layerNum++) {
                for (int neuronNum = 0;
                     neuronNum < layers.get(layerNum).getNeurons().size();
                     neuronNum++ ) {

                    // feedForward every neuron

                    layers.get(layerNum)
                            .getNeurons()
                            .get(neuronNum)
                            .feedNeuron(layerNum - 1, this);
                }
            }

        } else {
            System.out.println(Error.INPUT_ERROR);
        }

    }



    public void backPropagation(final ArrayList<Double> targetVals) {
        System.out.println("back baby \n");

        Layer outputLayer = layers.get(layers.size() - 1);

        // set error on 0.0 because this is called ( as a method ) for a new training set

        error = 0.0;

        for (int n = 0; n < outputLayer.getNeurons().size() - 1; n++) {
            Double delta = targetVals.get(n) - outputLayer.getNeurons().get(n).getOutputVal();
            error += delta*delta;
        }

        error /= outputLayer.getNeurons().size() - 1;

        error = Math.sqrt(error); // RMS

        // Calculate the outputs gradients

        for (int n = 0; n < outputLayer.getNeurons().size() - 1; n++) {
           outputLayer.getNeurons().get(n).calculateOutputGradient(targetVals.get(n));
        }

        // Calculate the hidden layer gradients

        for (int h = layers.size() - 2; h > 0; h--) {

            Layer hiddenLayer = layers.get(h);
            Layer nextLayer =  layers.get(h + 1);

            for (int n = 0; n < hiddenLayer.getNeurons().size(); n++) {
                hiddenLayer.getNeurons().get(n).calculateHiddenGradient(nextLayer);
            }
        }

        // Now we can update the connection weights to fit for the target answer
        // we can exclude the input layer

        for (int layerNum = layers.size() -1; layerNum > 0; layerNum--) {

            Layer currentLayer = layers.get(layerNum);
            Layer previousLayer = layers.get(layerNum - 1);

            for (int n = 0; n < currentLayer.getNeurons().size() - 1; n++) {
                currentLayer.getNeurons().get(n).updateInputWeights(previousLayer);
            }
        }

    }

    public ArrayList<Double> getResults(final ArrayList<Double> results) {
        System.out.println("rez baby\n");

        resultVal.clear();

        for (int n = 0; n < layers.get(layers.size() - 1).getNeurons().size(); n++) {
            resultVal.add(layers.get(layers.size() - 1)
                                .getNeurons().get(n).getOutputVal());
        }

        return resultVal;

    }

    // Utils

    public boolean entrance_assert(final ArrayList<Double> inputVals){
        // we have an extra neuron for BIAS
        if(inputVals.size() == layers.get(0).getNeurons().size() -1) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Layer> getLayers() {
        return layers;
    }
}
