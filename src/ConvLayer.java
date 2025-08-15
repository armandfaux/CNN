import java.util.Random;

class ConvLayer {
    private int kernelNum;
    private int kernelHeight;
    private int kernelWidth;
    private double[][][] kernels;

    private int stride;
    private int padding;

    public ConvLayer(int kernelNum, int kernelHeight, int kernelWidth) {
        this.kernelNum = kernelNum;
        this.kernelWidth = kernelWidth;
        this.kernelHeight = kernelHeight;
        this.stride = 1;
        this.padding = 0;

        // Each kernel (filter) is represented by a matrix of weights
        this.kernels = new double[kernelNum][kernelHeight][kernelWidth];

        initKernels();
    }

    private void initKernels() {
        Random rand = new Random();
        for (int k = 0; k < this.kernelNum; k++) {
            for (int y = 0; y < this.kernelHeight; y++) {
                for (int x = 0; x < this.kernelWidth; x++) {
                    this.kernels[k][y][x] = rand.nextGaussian() * 0.01;
                }
            }
        }
    }

    public void forward(double[][] input) {
        int outputHeight = (input.length - this.kernelHeight + 2 * this.padding) / this.stride + 1;
        int outputWidth = (input[0].length - this.kernelWidth + 2 * this.padding) / this.stride + 1;

        double[][][] output = new double[kernelNum][outputHeight][outputWidth];

        // For each kernel
        for (int k = 0; k < this.kernelNum; k++) {

            // Scan the input
            for (int outputY = 0; outputY < outputHeight; outputY++) {
                for (int outputX = 0; outputX < outputWidth; outputX++) {
                    double sum = 0.0;

                    // Compute product of kernel and input region
                    for (int ky = 0; ky < this.kernelHeight; ky++) {
                        for (int kx = 0; kx < this.kernelWidth; kx++) {
                            int inputY = outputY * this.stride + ky - this.padding;
                            int inputX = outputX * this.stride + kx - this.padding;
                            sum += input[inputY][inputX] * this.kernels[k][ky][kx];
                        }
                    }

                    // TODO add RELU activation
                    output[k][outputY][outputX] = sum;
                }
            }
        }
    }

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