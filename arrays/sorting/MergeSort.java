import java.util.*;

public class MergeSort {

	public void merge(ArrayList<Integer> A, int p, int q, int r){
		int n1 = q-p;
		int n2 = r-q+1;
		int[] L = new int[n1];
		int[] R = new int[n2];
		for(int i = 0; i < n1 ; i++)
			L[i] = A.get(p+i);
		for(int j = 0; j < n2 ; j++)
			R[j] = A.get(q+j);
		int i = 0;
		int j = 0;

		for(int k = p; k <= r; k++){
			if(j >= n2 || (i<n1 && L[i] <= R[j])){
				A.set(k,L[i]);
				i++;
			}else{
				A.set(k, R[j]);
				j++;
			}
		}
	}	

	public void mergeSort(ArrayList<Integer> A, int p, int r){
		int q;
		if(p<r){
			q = (int) Math.floor((p+r)/2);
		    mergeSort(A,p,q);
			mergeSort(A,q+1,r);
			merge(A,p,q+1,r);
		}
	}
	public void merge(int[] A, int p, int q, int r){
		int n1 = q-p;
		int n2 = r-q+1;
		int[] L = new int[n1];
		int[] R = new int[n2];
		for(int i = 0; i < n1 ; i++)
			L[i] = A[p+i];
		for(int j = 0; j < n2 ; j++)
			R[j] = A[q+j];
		int i = 0;
		int j = 0;

		for(int k = p; k <= r; k++){
			if(j >= n2 || (i<n1 && L[i] <= R[j])){
				A[k] = L[i];
				i++;
			}else{
				A[k] = R[j];
				j++;
			}
		}
	}	

	public void mergeSort(int[] A, int p, int r){
		int q;
		if(p<r){
			q = (int) Math.floor((p+r)/2);
		    mergeSort(A,p,q);
			mergeSort(A,q+1,r);
			merge(A,p,q+1,r);
		}
	}

	public void mergeSort(int[] A){
		mergeSort(A,0,A.length-1);
	}



}
