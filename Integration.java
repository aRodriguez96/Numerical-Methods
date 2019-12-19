public class Integration{

   public static void main(String[] args){
      System.out.println(rectangle(0, 10, 1000));
      System.out.println(trapezoid(0, 10, 1000));
      System.out.println(simpson(0, 10, 1000));
      System.out.println(simpson2(0, 10, 1000));
   }
   
   public static double rectangle(double left, double right, double n){
      double interval = (right - left) / n;
      double sum = 0;
      for(int i = 0; i < n; i++){
         sum += f(left + i * interval + interval / 2);
      }
      return sum * interval;
   }
   
   public static double trapezoid(double left, double right, double n){
      double interval = (right - left) / n;
      double sum = (f(left) + f(right)) / 2;
      for(int i = 1; i < n; i++){
         sum += f(left + i * interval);
      }
      return sum * interval;
   }
   
   public static double simpson(double left, double right, double n){
      return (2 * rectangle(left, right, n) + trapezoid(left, right, n)) / 3;
   }
   
   public static double simpson2(double left, double right, double n){
      double interval = (right - left) / n;
      double sum = (f(left) + f(right));
      for(int i = 1; i < n; i++){
         if(i % 2 == 1){
            sum += 4 * f(left + i * interval);
         }else{
            sum += 2 * f(left + i * interval);
         }
      }
      return sum * interval / 3;
   }
   
   public static double romberg(double left, double right, double n, int m){
      if (n % Math.pow(2, m) != 0){
         System.out.println("Error: Invalid m and n");
      }
      double[][] matrix = new double[m + 1][m + 1];
      int div = 1;
      for (int i = m; i >= 0; i--){
         matrix[i][0] = trapezoid(left, right, n / div);
         div *= 2;
      }
      for (int i = 1; i <= m; i++){
         double mult = Math.pow(4, i);
         for (int j = 0; j <= m - i; j++){
            matrix[j][i] = (mult * matrix[j + 1][i - 1] - matrix [j][i - 1]) / (mult - 1);
         }
      }
      return matrix[0][m];
   }
   
   public static double f(double x){
      //return x * x * x * x * x;
      return (6 * Math.pow(x, 2) + 2);
   }

}