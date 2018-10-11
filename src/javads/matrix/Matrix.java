
package javads.matrix;

public class Matrix{

	int m,n;
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
					temp += get(j,k) * B.get(j,i);
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
	 * element-wise multiplication
	 *
	 * @param B 	Matrix to multiply to A (entrance wise)
	 * @return 		matrix (a_{ij} * b_{ij})_ij
	 */
	public Matrix ewTimes(Matrix B){
		Matrix C = new Matrix(m,n);
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				C.set(i,j,get(i,j)*B.get(i,j));
			}
		}
		return C;
	}

	/**
	 * element-wise mutliplication
	 *
	 * @param B 	Matrix to divide with A (element wise)
	 * @return 		(a_ij/b_ij)_ij
	 */
	public Matrix ewDivide(Matrix B){
		Matrix C = new Matrix(m,n);
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				if(B.get(i,j) == 0){
					throw new IllegalArgumentException("Zero division error");
				}
				C.set(i,j,get(i,j)/B.get(i,j));
			}
		}
		return C;
	}

	/**
	 * matrix (a/(a_ij)), element wise devision.
	 */
	public Matrix ewDivide(double a){
		Matrix C = new Matrix(m,n);
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				if(get(i,j) == 0){
					throw new IllegalArgumentException("Zero division error");
				}
				C.set(i,j,a/get(i,j));
			}
		}
		return C;
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
		Matrix C = new Matrix(m,n);
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				C.set(i,j,Math.exp(get(i,j)));
			}
		}
		return C;
	}

	/**
	 * taking logarithm to every element in A
	 *
	 * @return (log(a_ij))_ij
	 */
	public Matrix ewLog(){
		Matrix C = new Matrix(m,n);
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				if(get(i,j) <= 0){
					throw new IllegalArgumentException("log error - Log(x) only defined for x>0");
				}
				C.set(i,j,Math.log(get(i,j)));
			}
		}
		return C;
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
	public static Matrix identity(int n, int m){
		Matrix C = new Matrix(m,n);
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				C.set(i,j, (( i == j ) ? 1 : 0));
			}
		}

		return C;
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
			
	}
}
