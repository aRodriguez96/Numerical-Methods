import java.util.*;
public class BVP{

   public static void main(String[] args){
      double[] y = run(0, 2, 20, 1, Math.E * Math.E);
      System.out.println(Arrays.toString(y));
      
   }
   
   public static double[] run(double start, double finish, int n, double ystart, double yend){ //ystart is yvalue at ystart and yend is y vlaue at yfinish
      double h = (finish - start) / n;
      double[] a = new double[n + 1];
      double[] b = new double[n + 1];
      double[] c = new double[n];
      double[] d = new double[n + 1];
      //a[0] = 0;
      b[0] = 1;
      c[0] = 0;
      a[n] = 0;
      b[n] = 1;
      d[0] = ystart;
      d[n] = yend;
      for (int i = 1; i < n; i++){
         double x = start + i * h;
         a[i] = a(x) - b(x) * h / 2;
         b[i] = -2*a(x) + c(x) * h * h;
         c[i] = a(x) + b(x) * h / 2;
         d[i] = d(x) * h * h;
      }
      double[] y = TriDiagonal.performComputation(a, b, c, d);
      double integral = 0;
      for (int i = 0; i < n; i++){
         integral += y[i] * h;
      }
      System.out.println(integral);
      return y;
   }
   
   public static double a(double x){
      return 1;
   }
   
   public static double b(double x){
      return 1;
   }
   
   public static double c(double x){
      return 1;
   }
   
   public static double d(double x){
      return 3 * Math.pow(Math.E, x);
   }

}