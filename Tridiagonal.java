
public class Tridiagonal {
	public static final double PLACEHOLDER = 0;
	public static double[] tridag(double[] a, double[] b, double[] c, double[] r) throws Exception {
		if(a.length != b.length || b.length != c.length || c.length != r.length)
			throw new Exception("Arrays dont have the same length");
			
		double[] u = new double[b.length];
		int j;
		double bet;

		int n = a.length;
		double[] gam = new double[a.length];
		if (b[0] == 0.0) throw new Exception("0 in diagonal");
		u[0] = r[0] / (bet = b[0]);
		for (j = 1; j < n; j++) {
			gam[j] = c[j-1]/bet;
			bet = b[j] - a[j]*gam[j];
			if (bet == 0.0) throw new Exception("Divide by 0 error");
			u[j] = (r[j] - a[j]*u[j-1])/bet;
		}
		for (j= (n-2); j >= 0; j--)
			u[j] -= gam[j+1]*u[j+1];
		return u;
	}
	public static void main(String[] args) {

		//Lower, main, and upper diagonal respectively
		double[] a = {PLACEHOLDER,1,1};
		double[] b = {-2.175,-2.150,-2.125};
		double[] c = {1,1,PLACEHOLDER};
		
		//Right hand side values
		double[] r = {-1.625,0.5,1.625};
		
		try {
			double[] x = tridag(a,b,c,r);
			System.out.println("x");
			for(double element: x) {
				System.out.println(element);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
