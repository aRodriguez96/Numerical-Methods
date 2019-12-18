//NEEDS TOL_Y?
public class SecantMethod {
	public static double func(double x) { //change to accept param fx target in this case 10
		return x*x - 10;
	}
	public static double secantRootFind(double guess1, double guess2, int numIter, double tol_x) throws Exception {
		double prevRootApprox = guess1; //initial guesses
		double rootApprox = guess2; 
		System.out.println("First iterate: " + prevRootApprox);
		System.out.println("Second iterate: " + rootApprox);
		if(Math.abs(rootApprox - prevRootApprox) <= tol_x) {
			return rootApprox;
		}
		System.out.println("xi\tx(i-1)\tf(xi-1)\tdx");
		for(int i = 0; i <= numIter; i++) {
			double fx = func(rootApprox);
			double denominator = (fx - func(prevRootApprox));
			if(denominator == 0) 
				throw new Exception("Zero denominator");
			double temp = rootApprox;
			rootApprox = rootApprox - fx * (rootApprox - prevRootApprox) / denominator;
			prevRootApprox = temp;
			double dF = Math.abs(rootApprox - prevRootApprox);
			if(dF <= tol_x) {
				return rootApprox;
			}
			System.out.format("%.4f\t%.4f\t%.4f\t%.4f\n", rootApprox, prevRootApprox, fx, dF);
		}
		return rootApprox;
	}
	public static void main(String[] args) {
		try {
			System.out.println("Root: " + secantRootFind(1,2,100,.0000005)); //point 1, point 2, iterations, tolerance
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
