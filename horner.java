//evaluates a large polynomial at a value of x 
public class horner {
	// Function that returns value of poly[0]x(n-1) + 
    // poly[1]x(n-2) + .. + poly[n-1] 
    static int horner1(int poly[], int n, int x) 
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
		int[] poly = {2, 0, 2, -6, 2, -1}; 
        int x = 3; 
        int n = poly.length; 
        System.out.println("Value of polynomial is "  
                                        + horner1(poly,n,x)); 
	}

}
