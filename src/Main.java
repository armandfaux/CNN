public class Main {
    public static void main(String[] args) {
        // Example usage
        ConvLayer convLayer = new ConvLayer(2, 3, 3);
        PoolLayer poolLayer = new PoolLayer(2, 2);
        DenseLayer denseLayer = new DenseLayer(10, 2);

        // no random 10x10 input
        double[][][] input = new double[1][10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                input[0][i][j] = (i + j) % 2 == 0 ? (i + 20) / 3 + 5 : i + j / 2 + 10;
            }
        }

        // Create a CNN and add layers
        CNN network = new CNN();
        network.addLayer(convLayer);
        network.addLayer(poolLayer);
        network.addLayer(denseLayer);
        // network.addLayer(denseLayer);
        // Add more layers as needed

        // Forward pass
        double[][][] cnnOutput = network.forward(input);
    }
}
