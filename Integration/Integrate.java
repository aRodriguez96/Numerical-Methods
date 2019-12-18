public class Integrate {
	public static double func(double x){
		//return (10 * Math.pow(x, 3)) + (2 * Math.pow(x, 2));
		return Math.pow(x, 2);
		//return x;
	}
	
	public static double Trap(double a, double b, double n){
		double h = (b-a)/n;
		double answer = 0;
		double sum = 0;
		for(int i=1; i<n; i++){
			sum += func(a + (h*i));
		}
		answer = h * ((func(a) + func(b) / 2) + sum);
		return answer;
	}
	
	public static double Mid(double a, double b, double n){
		double h = (b-a)/n;
		double answer = 0;
		double mid = (a + (h/2));
		for(int i=0; i<n; i++){
			answer += h * func(mid);
			mid += h;
		}
		return answer;
	}
	
	public static double Simp(double a, double b, double n){
		double h = (b-a)/n;
		double sum1 = func(a) + func(b);
		double sum2 = 0, sum3 = 0;
		for(int i=1; i<n; i += 2){
			sum2 += func(a + (h*i));
		}
		for(int i=2; i<n-1; i+=2){
			sum3 += func(a + (h*i));
		}
		return (h/3) * (sum1 + (4 * sum2) + (2 * sum3));
	}
	
	public static double[] TrapOpti(double a, double b, int j, double[] T){
		if(T[0] == -1){
			T[0] = (func(a) + func(b))/2.0;
		}
			
		for(int i=1; i<j; i++){
			if(T[i] == -1){
				int n = (int) Math.pow(2, i);
				double h = (b-a)/n;
				double sum = 0;
				for(int odd=1; odd<n; odd+=2){
					sum += func((odd/(double)n));
				}
				T[i] = T[i-1]/2 + (sum * h);
			}	
		}
		return T;
	}
	
	public static double[][] Romberg(double traps[], int n){
		double[][] R = new double[n][n];
		for(int j=0; j<n; j++){
			for(int k=0; k<n; k++){
				if(k==0){
					R[j][k] = traps[j];
					//System.out.println("R[" + j + "][" + k + "] = " + R[j][k]);
				}
				else
					R[j][k] = -1;
			}
		}
		for(int k=1; k<n; k++){
			for(int j=k; j<n; j++){
				R[j][k] = ((Math.pow(4, k) * R[j][k-1]) - (R[j-1][k-1]) )/(double)((Math.pow(4, k)-1));
				/*System.out.println("R["+j+"]["+k+"]: ");
				System.out.println("4^k * R[j][k-1] = " + (Math.pow(4, k) * R[j][k-1]));
				System.out.println("R[j-1][k-1] = " + R[j-1][k-1]);
				System.out.println("4^k-1 = " + (Math.pow(4, k)-1) + "\n");
				System.out.println(((Math.pow(4, k) * R[j][k-1]) - (R[k-1][k-1]))/(double)((Math.pow(4, k)-1)) + "\n");*/
			}
		}
		return R;
	}
	
	public static void PrintIntegral(double a, double b, int n) {
		System.out.println("Integral from " + a + " to " + b + " where n=" + n);
		System.out.print("Mid = ");
		System.out.format("%.16f", Integrate.Mid(a, b, n));
		System.out.print("\t\tTrap = "); 
		System.out.format("%.16f", Integrate.Trap(a, b, n));
		System.out.print("\t\tSimp = "); 
		System.out.format("%.16f", Integrate.Simp(a, b, n));
		System.out.println();			
	}
}