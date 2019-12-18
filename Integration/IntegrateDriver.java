import java.io.*;

public class IntegrateDriver {

	public static void main(String[] args) throws IOException{
		
		double[] traps = new double[100];
		for(int i=0; i<100; i++) traps[i] = -1;
				
		readFile(traps);
		
		double a = 0, b = 2;
		int n = 2048;
		int j = 16;

		traps = Integrate.TrapOpti(a, b, 32, traps);
		//System.out.println(traps[31]);
		
		double[][] R = Integrate.Romberg(traps, 6);
		for(int i=0; i<6; i++){
			for(int k=0; k<6; k++){
				if(R[i][k] == -1){
					System.out.print("x.xxxxxxxxxxxxxxxx\t");
				}
				else{
					System.out.format("%.16f", R[i][k]);
					System.out.print(" \t");
				}
			}
			System.out.println();
		}
		
		//Integrate.PrintIntegral(a,b,n);
		
		if(!writeFile(traps)){
			System.out.println("Error writing to file.");
			System.exit(1);
		}
	}
	public static double[] readFile(double[] traps) throws NumberFormatException, IOException{
		BufferedReader fr = null;
		try {
			fr = new BufferedReader(new FileReader("/Users/albertrodriguez/Documents/VS/Integration/traps.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("File Not found");
			System.exit(1);
		}
		int count = 0;
		String line;
		while((line = fr.readLine()) != null){
			traps[count] = Double.parseDouble(line);
			count++;
		}
		fr.close();
		return traps;
	}

	public static boolean writeFile(double[] traps) throws IOException{
		BufferedWriter fw;
		try {
			fw = new BufferedWriter(new FileWriter("/Users/albertrodriguez/Documents/VS/Integration/traps.txt"));
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		int writeCount = 0;
		while(traps[writeCount] != -1){
			fw.write(Double.toString(traps[writeCount]));
			if(traps[writeCount+1] != -1){
				fw.newLine();
			}
			writeCount++;
		}
		fw.close();
		return true;
	}
}