import java.util.*;

public class TriDiagonalO{

   static double[] sampleA = {0, 3, 6, 9};
   static double[] sampleB = {1, 4, 7, 10};
   static double[] sampleC = {2, 5, 8};
   static double[] sampleD = {3, 5, 7, 9};
   
   public static void main(String[] args){
      double[] x = performComputation(sampleA, sampleB, sampleC, sampleD);
   }
   
   public static double[] performComputation(double[] a, double[] b, double[] c, double[] d){
      if(a.length != b.length || b.length != d.length || b.length != c.length + 1){
         System.out.println("ERROR: Input Sizes");
      }
      double[] coeff = new double[c.length];
      double[] cons = new double[c.length];
      double[] result = new double[a.length];
      cons[0] = d[0] / b[0];
      coeff[0] = -c[0] / b[0];
      int i;
      for (i = 1; i < c.length; i++){
         cons[i] = (d[i] - cons[i - 1] * a[i]) / (b[i] + coeff[i - 1] * a[i]);
         coeff[i] = -c[i] / (b[i] + coeff[i - 1] * a[i]);
      }
      System.out.println("Cons: " + Arrays.toString(cons));
      System.out.println("Coeff: " + Arrays.toString(coeff));
      result[i] = (d[i] - cons[i - 1] * a[i]) / (b[i] + coeff[i - 1] * a[i]);
      for (i--; i >= 0; i--){
         result[i] = cons[i] + coeff[i] * result[i + 1];
      }
      System.out.println("Result: " + Arrays.toString(result));
      check(a, b, c, result);
      return result;
   }
   
   public static void check(double[] a, double[] b, double[] c, double[] x){
      double[] d = new double[x.length];
      d[0] = b[0] * x[0] + c[0] * x[1];
      int i;
      for(i = 1; i < c.length; i++){
         d[i] = a[i] * x[i - 1] + b[i] * x[i] + c[i] * x[i + 1];
      }
      d[i] = a[i] * x[i - 1] + b[i] * x[i];
      System.out.println("Check: " + Arrays.toString(d));
   }

}