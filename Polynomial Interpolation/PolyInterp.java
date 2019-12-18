
public class PolyInterp {

	public static void lagrange(double[] x, double[] y, double interp){
		double[] L = new double[x.length];
		for(int Lx=0; Lx<x.length; Lx++){
			double numerator = 1;
			for(int term=0; term<x.length; term++){
				if(Lx != term){
					numerator *= (interp - x[term]);
				}
			}
			L[Lx] = numerator;
		}
		for(int Lx=0; Lx<x.length; Lx++){
			double denominator = 1;
			for(int term=0; term<x.length; term++){
				if(Lx != term){
					denominator *= (x[Lx] - x[term]);
				}
			}
			L[Lx] /= denominator;
		}
		double value = 0;
		for(int i=0; i<L.length; i++){
			value += (y[i] * L[i]);
		}
		System.out.println("Interpolated Value at x = " + interp + " = " + value + "\n");
	}
	
}
