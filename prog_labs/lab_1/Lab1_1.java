public class Lab1_1{
	public static final int MASSIVE_D_SIZE = 8;
	public static final int MASSIVE_X_SIZE = 18;
	public static void try_all(short [] d, float [] x,  double k[][]){
		for(int i = 0; i < MASSIVE_D_SIZE; i++){
			for (int j = 0; j < MASSIVE_X_SIZE; j++){
				if (d[i]==9){
					k[i][j] = Math.cbrt(Math.sin(Math.cbrt(x[j])));
				} else if (d[i]==5 || d[i]==7 || d[i]==13 || d[i]==15){
					k[i][j] = 2.0*Math.pow((x[j]/(x[j]+(3.0/4.0))),3.0);
				}else {
					k[i][j] = Math.asin(1.0/(Math.E*Math.sqrt(Math.pow(Math.tan(Math.pow(Math.pow(3.0/4.0-(2.0*x[j]),2.0)/Math.pow(Math.PI/(x[j]+1.0),2.0),Math.pow(Math.cos(x[j]),2.0))),2.0))));
				}
			}	
		}
	}
	public static short [] massive_d(){
		int y = 19;
		short d [] = new short [MASSIVE_D_SIZE];
		for (int i=0; i < MASSIVE_D_SIZE; i++) {
			y = y-2;
			d[i]=(short) (y);
		}
		return d;
	}
	public static float [] massive_x(){
		float x [] = new float [MASSIVE_X_SIZE];
		float max = 4.0f;
		float min = -13.0f;
		for ( int m = 0; m < MASSIVE_X_SIZE; m++){
			x[m] = (float) ((Math.random() * (max-min))+min);
		}
		return x; 
	}
	public static double [] [] massive_k(short [] d, float [] x){
		double k [][] = new double [MASSIVE_D_SIZE][MASSIVE_X_SIZE];
		try_all(d,x,k);
		return k;
	}
	public static void output(double k[][]){
		for (int o = 0; o < MASSIVE_D_SIZE; o++){
			String outp = "";
			for (int h = 0; h < MASSIVE_X_SIZE; h++){
				outp += " " + String.format("%10.5f",k[o][h]);
			}
			System.out.println(outp);
		}
	}
	public static void main(String[] args) {
		short [] d = massive_d();
		float [] x = massive_x();
		double [] [] k = massive_k(d, x);
		output(k);
	}
}
