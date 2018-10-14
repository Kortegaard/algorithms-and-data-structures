package javads.machinelearning;
import java.util.*;
import javads.matrix.*;
import javads.machinelearning.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;

public class Tester {

	public static void wineSoftmax() throws FileNotFoundException{
		double lr = 0.1;
		int epochs = 200;
		int batchSize = 16;


		Dataset ds = Dataset.wine();
		SoftmaxClassifier smc = new SoftmaxClassifier();
		smc.setNumberOfClasses(3);
		ds.y = ds.y.add(-1);
		ds.X.addColumnLeft(new Matrix(ds.X.m,1,1));
		Matrix.randomRowPermutation(ds.y, ds.X);

		double train_perc = 0.80;
		int train_part = (int) Math.floor(train_perc*ds.X.m);

		Matrix X_train = ds.X.rowRange(0,train_part);
		Matrix y_train = ds.y.rowRange(0,train_part);

		Matrix X_test = ds.X.rowRange(train_part,ds.X.m);
		Matrix y_test = ds.y.rowRange(train_part,ds.y.m);

		smc.fit(X_train, y_train, lr, batchSize, epochs);
		
		double ein = smc.score(X_train,y_train);
		double eout = smc.score(X_test,y_test);
		System.out.println("In sample score: " + ein );
		System.out.println("out of sample score: " + eout );
	

	}
	
	//IN SAMPLE ERROR ON 98.876 %
    public static void main(String[] args) throws FileNotFoundException {
	
		wineSoftmax();
		

		//double ein = sm.score(X_train,y_train);
		//double eout = sm.score(X_test,y_test);
		//System.out.println("In sample score: " + ein );
		//System.out.println("out of sample score: " + eout );
	
    }

}

