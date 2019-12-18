
public class PolyDriver {

	public static void main(String[] args){
		double[] x = {-3,-1,2,6};
		double[] y = {4,3,2,4};
		double interp = -2;
		
		PolyInterp.lagrange(x, y, interp);
	}
	
}
