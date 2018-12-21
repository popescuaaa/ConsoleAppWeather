import java.util.ArrayList;

public class Layer {
    private ArrayList<Neuron> neurons;
    private static final Integer MAX_NUMBER_OF_NEURONS = 10;

    public Layer(){
        neurons = new ArrayList<>(MAX_NUMBER_OF_NEURONS);
    }
    public void setNeurons(ArrayList<Neuron> neurons) {
        this.neurons = neurons;
    }

    public ArrayList<Neuron> getNeurons() {
        return neurons;
    }
}
