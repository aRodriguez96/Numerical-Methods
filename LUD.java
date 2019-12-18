import java.util.Arrays;

public class LUD{

   static double[][] sample = {{ 1, 1, 2, 4, 1},
                     { 1,-2, 4, 1, 4},
                     { 2,-2, 1,-1, 3},
                     {-1, 2, 1, 1, 2}};
                     
   static double[][] sample2 = {{ 1, 1, 3.5, 4},
                     { 1,-2,-1, 1},
                     { 2,-2, 1,-1},
                     {-1, 2, 1, 1}};
                     
   static double[][] sample3 = {{ 1, 2, 3},
                     { 3, 2, 1},
                     { 2, 2, 1}};                 
                     
   static double[] sampleB = {1, 2, 4, 8, 6};
   
   static double[] sample3B = {14,10, 9};
   
   public static void main(String[] args){
      /*double[][] testA = new double[4][4];
      double[] xT = {0, 8, 12, 30};
      double lambda = .23820534;
      double[] yT = {0, Math.pow(4,lambda), Math.pow(6,lambda), Math.pow(8,lambda)};
      for(int i = 0; i < 3; i++){
         testA[i][0] = yT[i] * yT[i];
         testA[i][1] = xT[i] * yT[i];
         testA[i][2] = xT[i];
         testA[i][3] = yT[i];
      }
      performComputation(testA, new double[]{xT[0]*xT[0],xT[1]*xT[1],xT[2]*xT[2],xT[3]*xT[3]});*/
      performComputation(sample, sampleB);
   }
   
   public static void performComputation(double[][] a, double[] b){
      int n = a.length;
      if(n != a[0].length || n != b.length){
         System.out.println("ERROR: Array Sizes");
         return;
      }
      double[][] aCopy = new double[n][n];
      for(int i = 0; i < n; i++){
         for(int j = 0; j < n; j++){
            aCopy[i][j] = a[i][j];
         }
      }
      int[] swaps = new int[n];
      for(int i = 0; i < n; i++){
         swaps[i] = i;
      }
      for(int row = 0; row < n; row++){
         double maxHat = 0;
         int maxHatIndex = row;
         for(int i = row; i < n; i++){
            double rowHat = a[i][row];
            for(int j = row + 1; j < n; j++){
               rowHat = Math.max(a[i][j], rowHat);
            }
            rowHat = a[i][row] / rowHat;
            if(rowHat > maxHat){
               maxHat = rowHat;
               maxHatIndex = i;
            }
         }
         if(maxHat == 0){
            System.out.println("ERROR: Degenerate Array");
            return;
         }
         int save = swaps[maxHatIndex];
         swaps[maxHatIndex] = swaps[row];
         swaps[row] = save;
         double[] saveArray = a[maxHatIndex];
         a[maxHatIndex] = a[row];
         a[row] = saveArray;
         for(int i = row + 1; i < n; i++){
            a[i][row] = a[i][row] / a[row][row];
            for(int j = row + 1; j < n; j++){
               a[i][j] -= a[i][row] * a[row][j];
            }
         }
      }
      System.out.println("LU:    " + Arrays.deepToString(a));
      System.out.println("Swaps: " + Arrays.toString(swaps));
      double[] bAdj = new double[n];
      for(int i = 0; i < n; i++){
         bAdj[i] = b[swaps[i]];
      }
      System.out.println("BAdj:  " + Arrays.toString(bAdj));
      double[] y = new double[n];
      for(int i = 0; i < n; i++){
         double cons = 0;
         for(int j = 0; j < i; j++){
            cons += a[i][j] * y[j];
         }
         y[i] = bAdj[i] - cons;
      }
      System.out.println("Y:     " + Arrays.toString(y));
      double[] x = new double[n];
      for(int i = n - 1; i >= 0; i--){
         double cons = 0;
         for(int j = n - 1; j > i; j--){
            cons += a[i][j] * x[j];
         }
         x[i] = (y[i] - cons) / a[i][i];
      }
      System.out.println("X:     " + Arrays.toString(x));
      check(aCopy, x);
   }
   
   public static void check(double[][] a, double[] x){
      int n = a.length;
      double[] checkArr = new double[n];
      for(int i = 0; i < n; i++){
         for (int j = 0; j < n; j++){
            checkArr[i] += a[i][j] * x[j];
         }
      }
      System.out.println("Check: " + Arrays.toString(checkArr));
   }

}