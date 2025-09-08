import java.util.Random;

class ConvLayer extends Layer {
    private int kernelNum;
    private int kernelHeight;
    private int kernelWidth;
    private double[][][] kernels;
    private double[] biases;

    private int stride;
    private int padding;

    public ConvLayer(int kernelNum, int kernelHeight, int kernelWidth) {
        this.type = Type.CONV;

        this.kernelNum = kernelNum;
        System.out.println(kernelNum + " kernels");
        this.kernelWidth = kernelWidth;
        this.kernelHeight = kernelHeight;
        this.stride = 1;
        this.padding = 0;

        // Each kernel (filter) is represented by a matrix of weights
        this.kernels = new double[kernelNum][kernelHeight][kernelWidth];
        this.biases = new double[kernelNum];

        init();
    }

    private void init() {
        Random rand = new Random();
        for (int k = 0; k < this.kernelNum; k++) {
            this.biases[k] = 0; // Initialize biases
            for (int y = 0; y < this.kernelHeight; y++) {
                for (int x = 0; x < this.kernelWidth; x++) {
                    this.kernels[k][y][x] = rand.nextGaussian() * 0.01;
                }
            }
        }
    }

    public double[][][] forward(double[][][] input) {

        // print input
        System.out.println("Input feature maps:");
        for (double[][] featureMap : input) {
            for (double[] row : featureMap) {
                for (double value : row) {
                    System.out.print(value + " ");
                }
                System.out.println();
            }
            System.out.println();
        }

        int outputHeight = (input[0].length - this.kernelHeight + 2 * this.padding) / this.stride + 1;
        int outputWidth = (input[0][1].length - this.kernelWidth + 2 * this.padding) / this.stride + 1;

        System.out.println("outputHeight = " + outputHeight);
        System.out.println("outputWidth = " + outputWidth);

        if (outputHeight < 1 || outputWidth < 1 || this.kernelNum < 1) {
            throw new IllegalArgumentException("Invalid output dimensions");
        }

        double[][][] output = new double[this.kernelNum][outputHeight][outputWidth];

        // For each kernel
        for (int k = 0; k < this.kernelNum; k++) {

            // Scan the input
            for (int channel = 0; channel < input.length; channel++) {
                for (int outputY = 0; outputY < outputHeight; outputY++) {
                    for (int outputX = 0; outputX < outputWidth; outputX++) {
                        double sum = 0.0;

                        // Compute product of kernel and input region
                        for (int ky = 0; ky < this.kernelHeight; ky++) {
                            for (int kx = 0; kx < this.kernelWidth; kx++) {
                                int inputY = outputY * this.stride + ky - this.padding;
                                int inputX = outputX * this.stride + kx - this.padding;
                                sum += input[channel][inputY][inputX] * this.kernels[k][ky][kx];
                            }
                        }

                        output[k][outputY][outputX] = Activation.relu(sum + this.biases[k]);
                    }
                }
            }
        }

        // PRINT FEATURE MAPS
        System.out.println("*****************");
        System.out.println(output.length + " feature maps:");
        System.out.println("*****************");
        for (double[][] featureMap : output) {
            for (double[] row : featureMap) {
                for (double value : row) {
                    System.out.print(String.format("%.3f", value) + " ");
                }
                System.out.println();
            }
            System.out.println();
        }

        return output;
    }

    // Setters and getters for layer properties
    public void setStride(int stride) {
        this.stride = stride;
    }

    public void setPadding(int padding) {
        this.padding = padding;
    }

    public int getStride() {
        return stride;
    }

    public int getPadding() {
        return padding;
    }
}