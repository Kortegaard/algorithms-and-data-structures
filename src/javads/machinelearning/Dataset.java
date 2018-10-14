package javads.machinelearning;

import java.util.*;
import javads.matrix.*;
import javads.machinelearning.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;



public class Dataset{

	static String baseDir = "./src/javads/machinelearning/datasets/";

	public Matrix X;
	public Matrix y;

	public Dataset(Matrix X, Matrix y){
		this.X = X;
		this.y = y;
	}

	public static Dataset wine() throws FileNotFoundException {


		Scanner scanner = new Scanner(new File(Dataset.baseDir + "wine.csv"));
		//scanner.useDelimiter(",");
	
		int i = 0;

		// Matrix to contain wine data
		Matrix data = new Matrix(0,14);
		
		//Extracting data form csv file
		while (scanner.hasNextLine())
		{
			
			if( i == 0 ){ 
				i++; 
				scanner.nextLine();
				continue; 
			}
			
			String line = scanner.nextLine();
			String[] fields = line.split(",");

			double[] arrDouble = new double[fields.length];
			for(int j = 0; j < fields.length; j++){
			   arrDouble[j] = Double.parseDouble(fields[j]);
			}
			
			data.addRow(arrDouble);
			i++;
		}
		scanner.close();

		Matrix X = data.colRange(1,14);
		Matrix y = data.colRange(0,1);

 		return new Dataset(X,y);
	}

	public static void main (String[] args) throws FileNotFoundException {
		Dataset.wine();		
	}

}
