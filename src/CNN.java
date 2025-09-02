import java.util.ArrayList;
import java.util.List;

public class CNN {
    private List<Layer> layers;

    // TODO implement "predict" and "train" methods
    // Current implementation is made for testing the different layers and structure of the network
    public CNN() {
        this.layers = new ArrayList<>();
    }

    public void addLayer(Layer layer) {
        this.layers.add(layer);
    }

    public double[][][] forward(double[][][] input) {
        double[][][] output = input;
        for (Layer layer : layers) {
            output = layer.forward(output);
        }
        return output;
    }

    public double[][][] backward(double[][][] gradient) {
        double[][][] output = gradient;
        for (int i = layers.size() - 1; i >= 0; i--) {
            output = layers.get(i).backward(output);
        }
        return output;
    }
}
