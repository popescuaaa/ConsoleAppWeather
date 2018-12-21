public class Connection {
    private double weight;
    private double deltaWeight;

    public Connection(){
        weight = (double) Math.random();
        deltaWeight = (double) Math.random();


    }
    public double getDeltaWeight() {
        return deltaWeight;
    }

    public double getWeight() {
        return weight;
    }

    public void setDeltaWeight(double deltaWeight) {
        this.deltaWeight = deltaWeight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

}
