public class RootFinding {
	
	public static Object[] rootBisection(double target, double tol_f, double tol_x, int max_iter, 
										 double x_low, double x_high) {
		Object answer[] = {0,0, "In Progress"};
		double x = 0;
		int num_iter = 0;
		double y_low = func(x_low);
		double diff_y_low = y_low - target;
		if(Math.abs(diff_y_low) <= tol_f) {
			x = x_low;
			answer[0] = num_iter; answer[1] = x; answer[2] = "Success";
			return answer;
		}
		double y_high = func(x_high);
		double diff_y_high = y_high - target;
		if(Math.abs(diff_y_high) <= tol_f) {
			x = x_high;
			answer[0] = num_iter; answer[1] = x; answer[2] = "Success";
			return answer;
		}
		if(diff_y_low * diff_y_high > 0.0) {
			answer[0] = num_iter; answer[1] = 0; answer[2] = "Root Not Bracketed";
			return answer;
		}
		for(num_iter = 1; num_iter < max_iter; ++num_iter) {
			x = (x_low + x_high)/2.0;
			double y = func(x);
			double diff_y = y - target;
			if(Math.abs(diff_y) <= tol_f) {
				answer[0] = num_iter; answer[1] = x; answer[2] = "Success";
				return answer;
			}
			if(diff_y * diff_y_low > 0.0) {
				x_low = x;
			}
			else {
				x_high = x;
			}
			if(Math.abs(x_high - x_low) <= tol_x) {
				answer[0] = num_iter; answer[1] = x; answer[2] = "Success";
				return answer;
			}
		}
		answer[0] = max_iter; answer[1] = 0; answer[2] = "Failed, Max iterations reached";
		
		return answer;
	}
	
	public static Object[] newtonRaphson(double target, double tol_f, double tol_x, int max_iter, double x0) {
		
		final double tol_fprime = 1.0e-12;
		Object[] answer = {0,0, "In Progress"};
		int num_iter;
		double x = x0;
		for(num_iter = 1; num_iter < max_iter; ++num_iter) {
			double[] funcVals = func2(x);
			double diff_f = funcVals[0] - target;
			if(Math.abs(diff_f) <= tol_f) {
				answer[0] = num_iter; answer[1] = x; answer[2] = "Success";
				return answer;
			}
			if(Math.abs(funcVals[1]) <= tol_fprime) {
				answer[0] = num_iter; answer[1] = 0; answer[2] = "Fail, Divide By 0 Error";
				return answer;
			}
			double delta_x = diff_f/funcVals[1];
			if(Math.abs(delta_x) <= tol_x) {
				answer[0] = num_iter; answer[1] = x; answer[2] = "Success!";
				return answer;
			}
			x -= delta_x;
		}
		answer[0] = max_iter; answer[1] = 0; answer[2] = "Fail, Reached Max Iterations";
		return answer;
	}
	
	public static double func(double x) {
		
		//return x*x;
		return (2 * Math.pow(x, 3)) + (6 * Math.pow(x, 2)) + (3 * x);   //original
	}
	
	public static double[] func2(double x) {
		double[] answer = {0,0};
		answer[0] = (2 * Math.pow(x, 3)) + (6 * Math.pow(x, 2)) + (3 * x);  //original
		answer[1] = (6 * Math.pow(x, 2)) + (12 * x) + 3; //deriv
		
		return answer;	
	}
		
	public static void main(String[] args) {
		
		Object[] answer1 = rootBisection(0.0, 1.0e-10, 1.0e-10, 100, -3.0, -1.0);     
		System.out.println("Bisection: " + answer1[2] + ", \t\tnum_iter = " + answer1[0] + ", \t\tx = " + answer1[1]);
		Object[] answer2 = newtonRaphson(0.0, 1.0e-10, 1.0e-10, 100, -2.0);   //last parameter is starting x value 
		System.out.println("Newton-Raphson: " + answer2[2] + ", \tnum_iter = " + answer2[0] + ", \t\tx = " + answer2[1]);
	}

}
