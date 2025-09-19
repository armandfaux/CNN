package cnn;
public class Main {
    public static void main(String[] args) {
        // Example usage
        // ConvLayer convLayer = new ConvLayer(1, 2, 2);
        // PoolLayer poolLayer = new PoolLayer(2, 2);
        // FlattenLayer flattenLayer = new FlattenLayer();
        // DenseLayer denseLayer = new DenseLayer(10, 2);
        // Config.setVerbose(true);

        // double[][][] input = new double[1][3][3];
        // for (int i = 0; i < 3; i++) {
        //     for (int j = 0; j < 3; j++) {
        //         input[0][i][j] = i + j;
        //     }
        // }

        // // Create a CNN and add layers
        // CNN network = new CNN();
        // network.addLayer(convLayer);
        // network.addLayer(poolLayer);
        // network.addLayer(flattenLayer);
        // network.addLayer(denseLayer);

        // // Forward pass
        // double[][][] cnnOutput = network.forward(input);
    
        // CONVOLUTIONAL BACKWARD TEST
        double[][][] input = new double[2][3][3];
        input[0][0][0] = 1;
        input[0][0][1] = 2;
        input[0][0][2] = 0;
        input[0][1][0] = 0;
        input[0][1][1] = 1;
        input[0][1][2] = 3;
        input[0][2][0] = 2;
        input[0][2][1] = 2;
        input[0][2][2] = 1;

        input[1][0][0] = 2;
        input[1][0][1] = 1;
        input[1][0][2] = 1;
        input[1][1][0] = 0;
        input[1][1][1] = 2;
        input[1][1][2] = 1;
        input[1][2][0] = 1;
        input[1][2][1] = 0;
        input[1][2][2] = 3;

        double[][][] gradient = new double[3][2][2];
        gradient[0][0][0] = 1;
        gradient[0][0][1] = 2;
        gradient[0][1][0] = 3;
        gradient[0][1][1] = 4;

        gradient[1][0][0] = 0;
        gradient[1][0][1] = 1;
        gradient[1][1][0] = 0;
        gradient[1][1][1] = 1;

        gradient[2][0][0] = 2;
        gradient[2][0][1] = 0;
        gradient[2][1][0] = 1;
        gradient[2][1][1] = 3;

        Config.setVerbose(true);

        ConvLayer convLayer1 = new ConvLayer(3, 2, 2, 2);
        ConvLayer convLayer2 = new ConvLayer(2, 3, 2, 2);
        FlattenLayer flattenLayer = new FlattenLayer();
        DenseLayer denseLayer = new DenseLayer(10, 2);

        CNN network = new CNN();

        network.addLayer(convLayer1);
        network.addLayer(convLayer2);
        network.addLayer(flattenLayer);
        network.addLayer(denseLayer);
        network.backward(network.forward(input));
    }
}
