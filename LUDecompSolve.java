package lu_decomp;

public class LUDecompSolve {

	public static void LUDecomp(double[][] a, int[] indx, double d) throws Exception {
		final double TINY=1.0e-20;
		int i,imax = 0,j,k;
		double big,dum,sum,temp;

		int n = a.length;
		double[] vv = new double[n];
		d=1.0;
		for (i=0;i<n;i++) {
		big=0.0;
		for (j=0;j<n;j++)
		if ((temp=Math.abs(a[i][j])) > big) big=temp;
		if (big == 0.0) throw new Exception("Singular matrix in routine ludcmp");
		vv[i]=1.0/big;
		}
		for (j=0;j<n;j++) {
		for (i=0;i<j;i++) {
		sum=a[i][j];
		for (k=0;k<i;k++) sum -= a[i][k]*a[k][j];
		a[i][j]=sum;
		}
		big=0.0;
		for (i=j;i<n;i++) {
		sum=a[i][j];
		for (k=0;k<j;k++) sum -= a[i][k]*a[k][j];
		a[i][j]=sum;
		if ((dum=vv[i]*Math.abs(sum)) >= big) {
		big=dum;
		imax=i; //imax is largest row
		}
		}
		if (j != imax) {
		for (k=0;k<n;k++) {
		dum=a[imax][k];
		a[imax][k]=a[j][k];
		a[j][k]=dum;
		}
		d = -d;
		vv[imax]=vv[j];
		}
		indx[j]=imax;
		if (a[j][j] == 0.0) a[j][j]=TINY;
		if (j != n-1) {
		dum=1.0/(a[j][j]);
		for (i=j+1;i<n;i++) a[i][j] *= dum;
		}
		}
		}
		public static void LUBackSub(double[][] a, int[] indx, double[] b) {
		int i,ii=0,ip,j;
		double sum;

		int n=a.length;
		for (i=0;i<n;i++) {
		ip=indx[i];
		sum=b[ip];
		b[ip]=b[i];
		if (ii != 0)
		for (j=ii-1;j<i;j++) sum -= a[i][j]*b[j];
		else if (sum != 0.0)
		ii=i+1;
		b[i]=sum;
		}
		for (i=n-1;i>=0;i--) {
		sum=b[i];
		for (j=i+1;j<n;j++) sum -= a[i][j]*b[j];
		b[i]=sum/a[i][i];
		}
		}
		public static void main(String[] args) {
//		int size = 4;
//		double[][] a = //new double[size][size]; - make sure dimensions match
//		{{2,-1,0,0},
//		{-1,2,-1,0},
//		{0,-1,2,-1},
//		{0,0,-1,2}};
//			int size = 3;
//			double[][] a = //new double[size][size]; - make sure dimensions match
//			{{25,5,1},
//			{64,8,1},
//			{144,12,1}};
			int size = 4;
			double[][] a = //new double[size][size]; - make sure dimensions match
			{{1,2,0,0},
			{-1,2,3,0},
			{0,-2,3,4},{0,0,-3,4}};
		//int[] swapAry = new int[size];
		int[] swapInd = new int[] {0,1,2,3};
		double[] b = new double[] {5,15,40,20};
		try {
		LUDecomp(a,swapInd,1.0);
		System.out.println("Matrix: ");
		for(int i = 0; i < size; i++) {
		for(int j = 0; j < size; j++) {
		System.out.print(a[i][j] + " ");
		}
		System.out.println();
		}
		System.out.println();
		LUBackSub(a,swapInd,b);
		System.out.println("Result: ");
		//results in b
		for(double element: b) {
		System.out.println(element);
		}
		} catch (Exception e) {
		e.printStackTrace();
		}
		}
}
