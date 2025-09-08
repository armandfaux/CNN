public class FlattenLayer extends Layer {
    public double[][][] forward(double[][][] input) {
        double[][][] output = new double[1][1][input.length * input[0].length * input[0][0].length];

        for (int z = 0; z < input.length; z++) {
            for (int y = 0; y < input[0].length; y++) {
                for (int x = 0; x < input[0][0].length; x++) {
                    int index = z * (input[0].length * input[0][0].length) + y * input[0][0].length + x;
                    output[0][0][index] = input[z][y][x];
                }
            }
        }

        return output;
    }
}
