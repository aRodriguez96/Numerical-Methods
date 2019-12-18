
public class horner {
	// Function that returns value of poly[0]x(n-1) + 
    // poly[1]x(n-2) + .. + poly[n-1] 
    static int horner(int poly[], int n, int x) 
    { 
        // Initialize result 
        int result = poly[0];   
   
        // Evaluate value of polynomial using Horner's method 
        for (int i=1; i<n; i++) 
            result = result*x + poly[i]; 
   
        return result; 
    } 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//
		//goes from highest to lowest left to right
		//
		 // Let us evaluate value of 2x3 - 6x2 + 2x - 1 for x = 3 
      //  int[] poly = {2, -6, 2, -1}; 
		int[] poly = {2, 0, 2, -6, 2, -1}; 
        int x = 3; 
        int n = poly.length; 
        System.out.println("Value of polynomial is "  
                                        + horner(poly,n,x)); 
	}

}