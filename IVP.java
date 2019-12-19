public class IVP{

   public static void main(String[] args){
      System.out.println("Euler:     " + euler(0, 1, 2, 1000000));
      System.out.println("Midpoint:  " + midpoint(0, 1, 2, 1000000));
      System.out.println("Trapezoid: " + trapezoid(0, 1, 2, 1000000));
      System.out.println("RK4:       " + rk4(0, 1, 2, 1000000));
      System.out.println("Actual:    " + Math.E * Math.E);
   }
   
   public static double euler(double x0, double y0, double xn, int n){  //x0 is value of first x, y0 is value of y at x0, xn is right interval value, n is number of rectangles
      double h = (xn - x0) / n;
      double yi = y0;
      for (int i = 0; i < n; i++){
         yi = yi + h * f(x0 + h * i, yi);
      }
      return yi;
   }
   
   public static double midpoint(double x0, double y0, double xn, int n){
      double h = (xn - x0) / n;
      double yi = y0;
      double xi = x0;
      for (int i = 0; i < n; i++){
         double g1 = f(xi, yi);
         double gmid = f(x0 + h / 2, yi +  h / 2 * g1);
         yi += h * gmid;
         xi += h;
      }
      return yi;
   }
   
   public static double trapezoid(double x0, double y0, double xn, int n){
      double h = (xn - x0) / n;
      double yi = y0;
      double xi = x0;
      for (int i = 0; i < n; i++){
         double g1 = f(xi, yi);
         double g2 = f(x0 + h, yi +  h * g1);
         yi += h / 2 * (g1 + g2);
         xi += h;
      }
      return yi;
   }
   
   public static double rk4(double x0, double y0, double xn, int n){
      double h = (xn - x0) / n;
      double yi = y0;
      double xi = x0;
      for (int i = 0; i < n; i++){
         double g1 = f(xi, yi);
         double g2 = f(x0 + h / 2, yi +  h / 2 * g1);
         double g3 = f(x0 + h / 2, yi +  h / 2 * g2);
         double g4 = f(x0 + h, yi +  h * g3);
         yi += h / 6 * (g1 + 2 * g2 + 2 * g3 + g4);
         xi += h;
      }
      return yi;
   }
   
   public static double f(double x, double y){
      return y;
   }

}