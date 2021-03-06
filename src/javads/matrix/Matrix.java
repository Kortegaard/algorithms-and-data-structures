package javads.matrix;

import java.util.function.*;
import java.util.Arrays;

/**
 * This class is a simple class representing a Matrix, and operations which can be done on a matrix.
 * 
 */


public class Matrix{

	public int m,n;
	double[][] A;

	/**
	 * Initializing the a Matrix based on a 2d-array
	 *
	 * @param mtrx	2d-array to become Matrix
	 */
	public Matrix(double[][] mtrx){
		m = mtrx.length;
		n = mtrx[0].length;
		A = new double[m][n];
		for(int i = 0; i < mtrx.length; i++){
			for(int j = 0; j < mtrx[0].length; j++){
				A[i][j] = mtrx[i][j];
			}
		}
	}

	/**
	 * Initializing the a vector (m x 1) matrix
	 *
	 * @param mtrx	2d-array to become Matrix
	 */
	public Matrix(double[] mtrx){
		m = mtrx.length;
		n = 1;
		A = new double[m][n];
		for(int i = 0; i < mtrx.length; i++){
			A[i][0] = mtrx[i];
		}
	}

	/**
	 * Initializing the a Matrix based on a 2d-array
	 *
	 * @param mtrx	2d-array to become Matrix
	 */
	public Matrix(int[][] mtrx){
		m = mtrx.length;
		n = mtrx[0].length;
		A = new double[m][n];
		for(int i = 0; i < mtrx.length; i++){
			for(int j = 0; j < mtrx[0].length; j++){
				A[i][j] = (double) mtrx[i][j];
			}
		}
	}

	/**
	 * Initializing the a vector (m x 1) matrix
	 *
	 * @param mtrx	2d-array to become Matrix
	 */
	public Matrix(int[] mtrx){
		m = mtrx.length;
		n = 1;
		A = new double[m][n];
		for(int i = 0; i < mtrx.length; i++){
			A[i][0] = (double) mtrx[i];
		}
	}



	/**
	 * Initializing (m x n) matrix with all values 'val'. (a_ij) = val
	 *
	 * @param m 	number of rows
	 * @param n		number of columns
	 * @param val	value to fill matrix
	 */
	public Matrix(int m, int n, double val){
		this.m = m;
		this.n = n;
		A = new double[m][n];
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				A[i][j] = val;
			}
		}
	}

	/**
	 * Initializing (m x n) matrix,
	 */
	public Matrix(int m, int n){
		this.m = m;
		this.n = n;
		A = new double[m][n];
	}


	/**
	 * Matrix multiplications, C = A@B
	 *
	 * @param B 	Matrix to multiply A with
	 * @return 		Matrix A@B (AB)
	 */
	public Matrix times(Matrix B){
		Matrix C = new Matrix(m,B.n);
		for(int i = 0; i < B.n; i++){
			for(int j = 0; j < m; j++){
				double temp = 0;
				for(int k = 0; k < n; k++){
					temp += get(j,k) * B.get(k,i);
				}
				C.set(j,i,temp);
			}
		}
		return C;
	}

	/**
	 * Scalar multiplication with a double a. 
	 *
	 * @param a 	scalar to multiply with A
	 * @return		matrix aA
	 */
	public Matrix times(double a){
		Matrix C = new Matrix(m,n);
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				C.set(i,j,get(i,j)*a);
			}
		}
		return C;
	}

	/**
	 * Adding matrices together (entrywise).
	 *
	 * @param B 	matrix to add to A
	 * @return		A+B
	 */
	public Matrix add(Matrix B){
		assert n == B.n && m == B.m;
		Matrix C = new Matrix(m,n);
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				C.set(i,j,this.get(i,j)+B.get(i,j));
			}
		}
		return C;
	}

	/**
	 * Adding variable a to every entrance in A.
	 *
	 * @param  a 	Value to every entrance
	 * @return 		(a_ij + a)
	 */
	public Matrix add(double a){
		Matrix C = new Matrix(m,n);
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				C.set(i,j,this.get(i,j)+a);
			}
		}
		return C;
	}


	/**
	 * Transpose the matrix
	 *
	 * @return A^T
	 */
	public Matrix transpose(){
		Matrix C = new Matrix(n,m);
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				C.set(j,i,this.get(i,j));
			}
		}
		return C;
	}

	/**
	 * Transpose the matrix
	 * 
	 * @return A^T
	 */
	public Matrix T(){
		return transpose();
	}

	/**
	 * set a entrance in the matrix
	 *
	 * @param i 	i'th row
	 * @param j 	j'th column
	 * @param val 	value to put into A[i][j]
	 */
	public void set(int i, int j, double val){
		this.A[i][j] = val;
	}

	/**
	 * returning a entrance from matrix.
	 *
	 * @param i 	i'th row
	 * @param j 	j'th column
	 * @return 		A[i][j]
	 */
	public double get(int i, int j){
		return this.A[i][j];
	}

	/**
	 * finding maximum element in the matrix.
	 *
	 * @return max(a_ij)
	 */
	public double max(){
		double maxsofar = get(0,0);
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				maxsofar = ( (get(i,j) > maxsofar) ? get(i,j) : maxsofar );
			}
		}
		return maxsofar;
	}

	/**
	 * Fining max in each row, and making it to a column vector.
	 *
	 * @return (max(a_ij))_i
	 */
	public Matrix maxInRow(){
		Matrix maxsofar = new Matrix(m,1,0);
	     	
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				double val = ( (get(i,j) > maxsofar.get(i,0)) ? get(i,j) : maxsofar.get(i,0) );
				maxsofar.set(i, 0, val);
			}
		}
		return maxsofar;
	}


	public Matrix argmax(int axis){
		switch(axis){
			case 0:
				return argmaxAxis0();
			case 1:
				return argmaxAxis1();
			default:
				return null;
		}
	}

	
	private Matrix argmaxAxis1(){
		Matrix argmaxsofar = new Matrix(m,1,0);	
		Matrix maxsofar = new Matrix(m,1,0);	

		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				if(get(i,j) > maxsofar.get(i,0)){
					maxsofar.set(i, 0, get(i,j));
					argmaxsofar.set(i,0,j);
				}
			}
		}
		return argmaxsofar;
	}

	
	private Matrix argmaxAxis0(){
		Matrix argmaxsofar = new Matrix(1,n,0);	
		Matrix maxsofar = new Matrix(1,n,0);	

		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				if(get(i,j) > maxsofar.get(0,j)){
					maxsofar.set(0, j, get(i,j));
					argmaxsofar.set(0,j,i);
				}
			}
		}
		return argmaxsofar;
	}


	/**
	 * finding minimum element in the matrix
	 *
	 * @return min(a_ij)
	 */
	public double min(){
		double minsofar = get(0,0);
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				minsofar = ( (get(i,j) < minsofar) ? get(i,j) : minsofar );
			}
		}
		return minsofar;
	}

	/**
	 * return the average/mean of the entrances in the A.
	 *
	 * @return Sum(a_ij)/(n*m)
	 */
	public double mean(){
		double sum = 0;
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				sum += get(i,j);
			}
		}
		return sum/(n*m);
	}

	public double sum(){
		double sumsofar = 0;
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				sumsofar += get(i,j);
			}
		}
		return sumsofar;
	}
	
	public Matrix sum(int axis){
		switch(axis){
			case 0:
				return sumInColumn();
			case 1:
				return sumInRow();
			default:
				return null;
		}
	}

	private Matrix sumInColumn(){
		Matrix sumsofar = new Matrix(0,n,0);
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				sumsofar.set(0, j, sumsofar.get(0,j)+get(i,j));
			}
		}
		return sumsofar;

	}

	private Matrix sumInRow(){
		Matrix sumsofar = new Matrix(m,1,0);
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				sumsofar.set(i, 0, sumsofar.get(i,0)+get(i,j));
			}
		}
		return sumsofar;

	}

	/**
	 * printing the matrix:
	 */
	public void print(){
		for(double[] row: A){
			System.out.print("[");
			for(double v: row){
				System.out.print(v + " ");
			}
			System.out.print("]");
			System.out.println();
		}
	}

	/**
	 * Identity matrix
	 *
	 * @return	identity matrix
	 */
	public static Matrix identity(int n){
		return identity(n,n);
	}


	/**
	 * Identity matrix
	 *
	 * @return	identity matrix
	 */
	public static Matrix identity(int m, int n){
		Matrix C = new Matrix(m,n);
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				C.set(i,j, (( i == j ) ? 1 : 0));
			}
		}

		return C;
	}

	/**
	 * Swapping two rows in the matrix
	 *
	 * @param i 	index of one of the rows to swap
	 * @param j 	index of the other row to swap
	 */
	public void swapRow(int i, int j){
		double[] tempRow = A[i];
		A[i] = A[j];
		A[j] = tempRow;
	}

	/**
	 * Adding a row to matrix
	 *
	 * <p>
	 * Creating an array with height one higher than before, then moving
	 * all elements from old array to new array
	 *
	 * TODO: 
	 * </P>
	 *
	 * @param row 	array of doubles to add as row
	 */
	public void addRow(double[] row){
		double[][] newA = new double[m+1][n];
		for(int i = 0; i < m; i++){
			newA[i] = A[i];
		}
		newA[m] = row;
		A = newA;
		m++;
	}
	
	/**
	 * Adding all the rows of matrix row, to the matrix A.
	 *
	 * @param row 	Matrix of which rows to add to current matrix
	 */
	public void addRow(Matrix row){
		double[][] newA = new double[m+row.m][n];
		for(int i = 0; i < m; i++){
			newA[i] = A[i];
		}
		for(int j = 0; j < row.m; j++){
			newA[m+j] = row.row(j).getArray()[0];
		}
		A = newA;
		m += row.m;
	}

	/**
	 * 
	 *
	 * @param col 	Matrix of which column to add to current matrix
	 */
	public void addColumnLeft(Matrix col){
		double[][] newA = new double[m][n+col.n];
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n+col.n; j++){
				if (j < col.n){
					newA[i][j] = col.get(i,j);
				}else{
					newA[i][j] = get(i,j-col.n);
				}
			}
		}
		A = newA;
		n += col.n;
	}
	/**
	 * Creating a new matrix A[i..j][:] (i included, and j excluded), 
	 * consisting of the rows specified from the parameters.
	 *
	 * @param i 	Beginning of range.
	 * @param j 	End of range (not included).
	 * @return 		((j-i)  x n) matrix, consisting of row as described above.
	 */
	public Matrix rowRange(int i, int j){
		double[][] rowSub	= Arrays.copyOfRange(A, i,j);
		return new Matrix(rowSub);
	}

	/**
	 * Creating a new matrix A[:][i..j] (i included and j excluded)
	 * consisting of a range of the columns.
	 * 
	 * <p>
	 *	Expample of use
	 *
	 *		A '=' [[ 1  2  3 ],
	 *			   [ 4  5  6 ],
	 *			   [ 7  8  9 ]]
	 *
	 * Then 
	 * 		A.colRange(0,2) = [[ 1  2 ],
	 *	 					   [ 4  5 ],
	 *						   [ 7  8 ]]
	 *
	 * </p>
	 *
	 * @param i 	Beginning of range.
	 * @param j 	End of range (not included).
	 * @return 		( m x (j-i)) matrix, consisting of cols as described above.
	 */
	public Matrix colRange(int i, int j){
		return T().rowRange(i,j).T();
	}

	/**
	 * Creating a row vector ( Matrix 1 x n ), consisting of the i'th row
	 *
	 * @param i  	the row to 'vectorize'
	 * @return 		( 1 x n ) matrix, consisting of the i'th row.
	 */
	public Matrix row(int i){
		return new Matrix(A[i]).T();
	}

	/**
	 * getting the array that the matrix is build of
	 *
	 * @return 	dobule[m][n] array of the matrix.
	 */
	public double[][] getArray(){
		return A;
	}
	
	/**
	 * using function on every entry on the matrix.
	 *
	 * <p>
	 *	Goes through each of the entries in matrix C, which has the same shape as A, and uses function
	 *	h on entrance a_ij, and sets c_ij = d(a_ij), and this we will end up with matrix
	 *
	 *						  C = (c_ij)_ij = (d(a_ij))_ij
	 * </p>
	 *
	 * @param d 	function to use on the entries
	 * @return 		matrix C = (c_ij) = (d(a_ij))_ij
	 */
	public Matrix entranceWise(Function<Double, Double> d){
		Matrix C = new Matrix(m,n);
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				C.set(i,j,(double) d.apply(new Double(get(i,j))));
			}
		}
		return C;
	}
	
	/**
	 * Using a function on the two matricies A, B entrance wise, to create the retuning matrix C.
	 * 
	 * <p>
	 *	This function simply goes thorugh all the entries in matrix C (same shape as matrix A),
	 *	and uses all function d, on entrances a_ij, and b_ij, and sets c_ij = d(a_ij, b_ij).
	 *	Thus the result will be 
	 *		
	 *							C = (c_ij)_ij = (d(a_ij, b_ij))_ij
	 *
	 *	Examples of use
	 *	
	 *	A = [[ 1  2  3 ]      	B = [[ 1  0  0 ]  
	 *		 [ 4  5  6 ]      		 [ 0  2  0 ] 
	 *		 [ 7  8  9 ]]     		 [ 0  0  3 ]]
	 *
	 * Then we will multiply these entrance wise, using the function (a,b) -> a*b
	 *
	 * Matrix C = A.entranceWise(B, (a,b) -> a*b);
	 *
	 * 	 C = [[ 1  0   0  ] 
	 *    	  [ 0  10  0  ] 
	 *    	  [ 0  0   27 ]]
	 *
	 * </p>
	 * 
	 * @param B 	Matrix
	 * @param d 	The function which will be used on A, B entrance wise.
	 * @return 		Matrix (d(a_ij, b_ij))_ij
	 */
	public Matrix entranceWise(Matrix B ,FunctionTwoParameters<Double,Double, Double> d){
		Matrix C = new Matrix(m,n);
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				Double d1 = new Double(get(i,j));
				Double d2 = new Double(B.get(i,j));
				System.out.println("a: " + get(i,j) + ", b: " + B.get(i,j) + ", d(a,b): " + d.apply(d1,d2));
				C.set(i,j, (double) d.apply(d1,d2));
			}
		}
		return C;
	}
	
	/**
	 * matrix (a/(a_ij)), element wise devision.
	 */
	public Matrix ewDivide(double a){
		return entranceWise(x -> a/x);
	}

	/**
	 * 'default' for ewDivide
	 *
	 * @return (1/a_ij)_ij
	 */
	public Matrix ewDivide(){
		return ewDivide(1.0);
	}

	/**
	 * taking the exponential function to every element in A
	 *
	 * @return (exp(a_ij))_ij
	 */
	public Matrix ewExp(){
		return entranceWise(x -> Math.exp(x));
	}

	/**
	 * taking logarithm to every element in A
	 *
	 * @return (log(a_ij))_ij
	 */
	public Matrix ewLog(){
		return entranceWise(x -> Math.log(x));
	}

	
	
	static public void randomRowPermutation(Matrix... args){
		int numOfRows = args[0].m;
		for(int i = 0; i < numOfRows; i++){
			int newpos = (int) (Math.random() * ((numOfRows-1) + 1)) + 0;
			for(Matrix mat: args){
				mat.swapRow(i,newpos);
			}
		}
	}
	


	/**
	 * Few examples of the use of matrix.
	 */
	public static void main (String[] args) {
		//Matrix M1 = new Matrix(2,4,1);
		//Matrix M2 = new Matrix(2,4,2);
		//M1.set(0,2,0);
		//M1.print();
		//System.out.println();

		//Matrix M3 = M1.transpose();

		//M3.print();
		//System.out.println();
		//System.out.println();
		//Matrix M4 = Matrix.identity(7,2);
		//M4.print();
		//System.out.println();
		//System.out.println();

		Matrix N1 = new Matrix(2,3,2);


		N1.set(0,2,3);
		N1.set(1,0,1);
		Matrix N2 = new Matrix(2,3,3);
		//Matrix N3 = N1.ewLog();
		
		N2.set(0,0,2);
		N2.set(0,1,1.5);
		N2.print();
		System.out.println("max: " + N2.max());
		System.out.println("min: " + N2.min());
		System.out.println("mean: " + N2.mean());
		System.out.println();
		N1.print();
		System.out.println();
		Matrix N3 = N1.entranceWise(x -> Math.exp(x));
		//Matrix N3 = N1.entranceWise(Math.exp);
		N3.print();
		//N3 = N1.entranceWise(x -> Math.exp(x));

		
		// Timing the entrancewise vs the other
		// int n = 20_000_000;
		// long startTime = System.nanoTime();
		// for(int i = 0; i < n; i++){
		// 	N3 = N1.entranceWise(x -> Math.exp(x));
		// }
		// long endTime = System.nanoTime();
		// long duration = (endTime - startTime)/1000000;
		// 
		// startTime = System.nanoTime();
		// for(int i = 0; i < n; i++){
		// 	N3 = N1.ewExp();
		// }
		// endTime = System.nanoTime();
		// long duration2 = (endTime - startTime)/1000000;

		// System.out.println("First (lambda): " + duration + " ms");
		// System.out.println("Second (eqexp): " + duration2 + " ms");

		//N3.print();

		
		Matrix I = Matrix.identity(2,3);
		I.print();

		Matrix N4 = N3.entranceWise(I, (a,b) -> a*b);
		N4.print();	

	}

	@FunctionalInterface
	public interface FunctionTwoParameters<One, Two, Three> {
	    public Three apply(One one, Two two);
	}
}
