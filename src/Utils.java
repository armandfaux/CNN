public class Utils {
    public static double matrixDotProduct(double[][] A, double[][] B) {
    int rows = A.length;
    int cols = A[0].length;
    double sum = 0.0;

    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            sum += A[i][j] * B[i][j];
        }
    }

    return sum;
}
}
