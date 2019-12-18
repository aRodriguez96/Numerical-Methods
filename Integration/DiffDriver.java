public class DiffDriver {

	public static void main(String[] args) {
		double h[] = new double[15];
		for(int i=0; i<h.length; i++){
			h[i] = Math.pow(10, -(i+1));
		}
		PrintDiff(h, 1);	
	}
	
	public static void PrintDiff(double[] h, double x){
		System.out.println("Forward \t \tBackwards \t \tCenter \t \t\tSecondCenter");
		for(int i=0; i<h.length; i++){
			System.out.print(NumericalDiff.forwardDiff(x,h[i]) + "\t");
			System.out.print(NumericalDiff.backwardsDiff(x,h[i]) + "\t");
			System.out.print(NumericalDiff.centerDiff(x,h[i]) + "\t");
			System.out.print(NumericalDiff.secondCenter(x,h[i]) + "\n");
		}
		System.out.println();
	}
}