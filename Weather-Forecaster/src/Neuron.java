import java.util.ArrayList;

// Responsible for doing the math stuff
// The biggest part of the computations are made here

public class Neuron {

    private static final Integer MAX_NUMBER_OF_OUTPUT_WEIGHTS = 10;
    private static final Double alpha = 0.5; // momentum, or last multiplier of deltaWeight
    private static final Double eta =  0.15; // pretty good learning rate
    private double outputVal = 0;
    private ArrayList<Connection> outputWeights = new ArrayList<>(MAX_NUMBER_OF_OUTPUT_WEIGHTS);
    private Integer index;
    private Double gradient;

    public Neuron(Integer numOutputs, Integer index){
        System.out.println("A neuron has been created!\n");

        for (int c = 0; c < numOutputs; c++) {
            Connection connection = new Connection();
            outputWeights.add(connection);
        }

        this.index = index;
    }

    // also known as the Transfer Function

    private Double activationFunction(final Double input) {

        return Math.tanh(input);
    }

    private Double activationFunctionDerivative(final Double input) {
        return (1 - Math.tanh(input) * Math.tanh(input));

    }

    public ArrayList<Connection> getOutputWeights() {
        return outputWeights;
    }

    public Double getOutputVal() {
        return outputVal;
    }

    public void setOutputVal(final double outputVal) {
        this.outputVal = outputVal;
    }

    public Double feedNeuron(final Integer layerIndex, final Net net){
        Double sum = 0.0;

        // the previous layer outputs are out inputs
        // must include the bias neuron

        for (int n = 0;
                 n < net.getLayers()
                         .get(layerIndex)
                         .getNeurons()
                         .size();
                 n++) {

                 sum +=  net.getLayers()
                            .get(layerIndex)
                            .getNeurons()
                            .get(n).getOutputVal() * net.getLayers()
                                                         .get(layerIndex)
                                                         .getNeurons()
                                                         .get(n)
                                                         .getOutputWeights()
                                                         .get(index)
                                                         .getWeight();

        }

        return sum;
    }

    public void calculateOutputGradient(Double targetValue) {
        // just take a look ( and sum up )  at the difference
        // between the outputValue and targetValue

        Double delta = targetValue - outputVal;
        gradient = delta * activationFunctionDerivative(outputVal);

    }

    public void calculateHiddenGradient(final Layer nextLayer) {

        Double dow = sumDOW(nextLayer);
        gradient = dow * activationFunctionDerivative(outputVal);
    }

    public void updateInputWeights(final Layer previousLayer) {
        for (int n = 0; n < previousLayer.getNeurons().size(); n++) {
            Neuron currentNeuron  = previousLayer.getNeurons().get(n);
            Double oldDelta =  currentNeuron.outputWeights.get(index).getDeltaWeight();

            Double newDeltaWeight  =
                                  eta           // larning rate
                                * currentNeuron.getOutputVal()
                                * gradient
                                + alpha         // momentum rate
                                + oldDelta;
            currentNeuron.getOutputWeights().get(index).setDeltaWeight(newDeltaWeight);
            currentNeuron.getOutputWeights().get(index)
                         .setWeight(currentNeuron
                                    .getOutputWeights()
                                    .get(index)
                                    .getWeight() + newDeltaWeight);
        }
    }

    public Double sumDOW(final Layer nextLayer) {
        Double sum = 0.0;

        // Sum the contribution of errors of the nodes we feed

        for (int n = 0; n < nextLayer.getNeurons().size() - 1; n++) {
            sum += outputWeights.get(n).getWeight() * nextLayer.getNeurons().get(n).gradient;

        }

        return sum;

    }
}
