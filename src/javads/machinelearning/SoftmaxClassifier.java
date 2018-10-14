package javads.machinelearning;

import javads.matrix.*;

public class SoftmaxClassifier{

	Matrix W;
	int k = 2;

	/**
	 * 
	 * @param X 	data points (n x k) matrix  (each row should correspond to a data point)
	 * @return 		
	 */
	public Matrix softmax(Matrix X){
		Matrix maxinrow = X.maxInRow();

		Matrix Xcop = new Matrix(X.getArray());
		for(int i = 0; i < maxinrow.m; i++){
			for(int j = 0; j < X.n; j++){
				Xcop.set(i,j,Xcop.get(i,j) - maxinrow.get(i,0));
			}
		}
		Matrix logExpSum = Xcop;
		logExpSum = logExpSum.ewExp().sum(1).ewLog();
		logExpSum = logExpSum.add(maxinrow);
		for(int i = 0; i < maxinrow.m; i++){
			for(int j = 0; j < X.n; j++){
				Xcop.set(i,j, X.get(i,j) - logExpSum.get(i,0));
			}
		}

		return Xcop.ewExp();	
	}

	/**
	 * 
	 *
	 * @param y	 column vector
	 * @param k	 number of classes
	 */
	public Matrix oneInKEncoding(Matrix y, int k){
		Matrix res = new Matrix(y.m,k,0);
		for (int i = 0; i < y.m; i++){
			res.set(i,(int) y.get(i,0), 1);
		}
		return res;
	}

	/**
	 * 
	 */
	public Matrix grad(Matrix X, Matrix y, Matrix W){
		Matrix Y = oneInKEncoding(y,k);
		Matrix sXW = softmax(X.times(W));
		Matrix grad = X.T().times(Y.add(sXW.times(-1))).times(-1);
		return grad;
	}

	public Matrix fit(Matrix X, Matrix Y, double lr, int batchSize, double epochs){
		this.W = new Matrix(X.n, k, 0);

		for(int e = 0; e < epochs; e++){
			for(int i = 0; i < Y.m; i += batchSize){
				shuffel(X,Y);

				// Splitting data up in batches.
				Matrix X_batch = X.rowRange(i, Math.min(i+batchSize, X.m));
				Matrix Y_batch = Y.rowRange(i, Math.min(i+batchSize, Y.m));

				// calculating gradient.
				Matrix grad	= grad(X_batch, Y_batch, this.W);
					
				this.W = grad.times(lr).times(-1).add(this.W);
			}		
		}

		return new Matrix(1,1);
	}

	public Matrix predict(Matrix X){
		Matrix sm = softmax(X.times(this.W));
		return sm.argmax(1);
	}

	public double score(Matrix X, Matrix y){
		Matrix pred = predict(X);
		double score = 0;
		for(int i = 0; i < pred.m; i++){
			score += ((pred.get(i,0) == y.get(i,0)) ? 1 : 0);
		}
		score /= ((pred.m != 0) ? pred.m: 1);
		return score;
	}


	public void setNumberOfClasses(int k){
		this.k = k;
	}

	/**
	 * Method used for shuffeling data used for stochastic gradient descent.
	 *
	 * @param X,y 	matricies to be shuffled
	 */

	private void shuffel(Matrix X, Matrix y){
		for(int i = 0; i < X.m; i++){
			int newpos = (int) (Math.random() * ((X.m-1) + 1)) + 0;
			X.swapRow(i,newpos);
			y.swapRow(i,newpos);
		}
	}
	

	public static void main (String[] args) {
		double[][] d = new double[][]{{1,5,4},{1,6,3},{1,2,5}};
		double[][] _y = new double[][]{{0},{0},{1}};
		Matrix m = new Matrix(d);
		Matrix y = new Matrix(_y);

		SoftmaxClassifier smc = new SoftmaxClassifier();
		smc.fit(m, y, 0.01, 9,100);
		
		Matrix smmat = smc.softmax(m);
		Matrix enc = smc.oneInKEncoding(y,2);
		//enc.print();	


	}

}


