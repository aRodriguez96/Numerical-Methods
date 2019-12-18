public class NumericalDiff {

	public static double func(double x){
		//return Math.log(x);
		return (6 * Math.pow(x, 2) + 2)/(2 * x);
	}
	
	public static double forwardDiff(double x, double h){
		return (func(x+h) - func(x))/h;
	}
	public static double backwardsDiff(double x, double h){
		return (func(x)- func(x-h))/h;
	}
	public static double centerDiff(double x, double h){
		return (func(x+h)-func(x-h))/(2*h);
	}
	public static double secondCenter(double x, double h){
		return (func(x+h) + func(x-h) - (2*func(x)))/(Math.pow(h, 2));
	}
	
}