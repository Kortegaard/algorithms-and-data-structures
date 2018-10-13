package javads.machinelearning;
import java.util.*;

import javads.matrix.*;


public class LogisticRegressionClassifier{
	
	/**
	 * Weight matrix ( shape [d x 1] which means column vector of size d ) 
	 */
	Matrix w;

	/**
	 * Calculates sigmain on every element in the list, and returns that list.
	 * 
	 * <p>
	 * We use the sigmoid function as the logistic function s(x) = 1/(1+e^{-x})
	 * </p>
	 *
	 * @param list	list to be calculated sigmoid of
	 * @return 		array of the same size and imput with sigmoid calculated on each imput
	 */
	public Matrix sigmoid(Matrix X){
		return X.times(-1).ewExp().add(1).ewDivide();
	}


	/**
	 * Calculating the gradient of Ein = (1/n)*NNL(D|w), where D is the test points.
	 *
	 * @param X		X.shape = (n x d)
	 * @param y		y.shape = (n x 1)
	 * @param w		w.shape = (d x 1)
	 */
	public Matrix grad(Matrix X, Matrix y, Matrix w){
		Matrix grad = X.T().times(y.add(sigmoid(X.times(w)).times(-1))).times(-1);
		return grad;
	}

	/**
	 * Fitting the weight, using the data X, with result y.
	 *
	 * @param X  			X.shape = n x d
	 * @param y  			y.shape = n x 1
	 * @param w 			w.shape = d x 1 ( if null, w = (0,0,..,0)^T is used
	 * @param lr 			step size during the gradient descent
	 * @param batach_isze	size of batches, used in the mini batch SGD.
	 * @param epochs 		number of epoachs to runs the SGD for.
	 */
	public void fit(Matrix X, Matrix y, Matrix w, double lr, int batch_size, int epochs){
		// Setting weights based on if they are given in the parameters
		// or setting w = (0,0,..,0)^t, if w is null
		if (w == null) {
			this.w = new Matrix(X.n,1,0);
		}else{
			this.w = w;
		}
		
		// running all the epochs
		for(int e = 0; e < epochs; e++){
			for(int i = 0; i < y.m; i += batch_size){
				shuffel(X,y);

				// Splitting data up in batches.
				Matrix X_batch = X.rowRange(i, Math.min(i+batch_size, y.m));
				Matrix y_batch = y.rowRange(i, Math.min(i+batch_size, y.m));

				// calculating gradient.
				Matrix grad	= grad(X_batch, y_batch, this.w);
				
				for(int j = 0; j < this.w.m; j++){
					this.w = grad.times(lr/batch_size).times(-1).add(this.w);
				}
			}
		}
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
	
	/**
	 * TODO
	 */
	public double cost(){
		return 0;
	}

	/**
	 * Predicting outcome on data point, basicly using the fitted function on the data.
	 * 
	 * @param X	 data to be predicted
	 * @return 	 Matrix (d x 1), containing the predictions.
	 */
	public Matrix predict(Matrix X){
		Matrix sigm = sigmoid(X.times(this.w));
		for(int i = 0; i < sigm.m; i++){
			sigm.set(i,0,((sigm.get(i,0) <= 0.5 ) ? 0 : 1));
		}
		return sigm;
	}
	
	/**
	 * Scoring the fitted, on data (X) where the outcome (y) is already know
	 *
	 * @param X		Data Matrix (n x d)
	 * @param y		outcome matrix/vector (d x 1)
	 * @return 		The mean of how correct the fitted function was on the data
	 */
	public double score(Matrix X, Matrix y){
		Matrix pred = predict(X);
		double score = 0;
		for(int i = 0; i < pred.m; i++){
			score += ((pred.get(i,0) == y.get(i,0)) ? 1 : 0);
		}
		score /= ((pred.m != 0) ? pred.m: 1);
		return score;
	}


	public static void main (String[] args) {
		//double[] test = new double[]{0,1,2};
		double[][] _X = new double[][]{{1,-2,3}, {1,-1,3}, {1,-2,2}, {1,-0,3}, {1,0,4}, {1,-1,4}, {1,1,5}, {1,2,4}, {1,1,3}, {1,0,2}, {1,-1,1}, {1,-2,1}, {1,-3,2}, {1,2,5}, {1,0,5}, {1,-1,5}, {1,-2,4}, {1,-3,3}, {1,-3,4}, {1,-3,0}, {1,-3,1}, {1,-4,1}, {1,-4,2}, {1,-4,0}, {1,-2,0}, {1,3,5}, {1,3,6}, {1,2,6}, {1,1,6}, {1,4,4}, {1,5,4}, {1,6,4}, {1,3,3}, {1,3,4}, {1,3,5}, {1,3,6}, {1,3,7}, {1,2,2}, {1,2,3}, {1,2,4}, {1,2,5}, {1,2,6}, {1,1,1}, {1,1,2}, {1,1,3}, {1,1,4}, {1,1,5}, {1,1,6}, {1,0,1}, {1,0,2}, {1,0,3}, {1,0,4}, {1,0,5}, };
		
		int[] _y = new int[]{
			1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		};

		double[] _w = new double[]{0,0,0};

		//double[][] _X = new double[][]{{1,0},{1,1},{2,3}};
		//int[] _y = new int[]{0,0,1};
		//double[] _w = new double[]{0,0};

		Matrix X = new Matrix(_X);
		Matrix y = new Matrix(_y);
		Matrix w = new Matrix(_w);

		LogisticRegressionClassifier logreg = new LogisticRegressionClassifier();
		

		logreg.fit(X,y,w,.1,8,100);
		logreg.w.print();

		double ein = logreg.score(X,y);
		System.out.println("In sample score: " + ein );
		
	}
}


