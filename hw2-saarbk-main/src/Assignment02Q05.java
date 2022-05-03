
import java.util.Arrays;

public class Assignment02Q05 {

	public static void main(String[] args) {
		// do not change this part below
		int N = Integer.parseInt(args[0]);
		int[][] matrix = new int[N][N]; // the input matrix to be
		for(int i=0;i < N; i++){
			for(int j=0; j < N; j++){
				matrix[i][j] = Integer.parseInt(args[1+(i*N)+j]); // the value at [i][j] is the i*N+j item in args (without considering args[0])
			}
		}
		for(int i=0;i < N; i++)
			System.out.println(Arrays.toString(matrix[i]));
		System.out.println("");
		int[][] rotatedMatrix; // the rotated matrix
		
		// *** __single loop and single matrix sol___ ***
		int l=0,r=N-1;
		int step=0;
		do{
			int temp1=matrix[l][r];
			matrix[l][r]=matrix[N-r-1][l];
			int temp2=matrix[r][N-l-1];
			matrix[r][N-l-1]=temp1;
			matrix[N-r-1][l]=matrix[N-l-1][N-r-1];
			matrix[N-l-1][N-r-1]=temp2;
			if(l==r-1)
			{
				step++;
				r--;
				l=step;
			}else {
			l++;}
		}while (l<=r);

		// do not change this part below
		for(int i=0;i < N; i++){ 
			System.out.println(Arrays.toString(matrix[i])); // this would compile after you would put value in transposedMatrix
		}
	}
}
