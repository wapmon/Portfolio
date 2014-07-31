//William Pearigen
//Program 4 - Home
//Arup Guha - COP 3503
//July 1, 2014
import java.util.*;

public class Home {

	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);

		//set variables for a start and end intersection, and total number of intersections
		int numMaps = scanner.nextInt();
		int numInter, interA, interB;
		
		
		for(int h = 0; h< numMaps; h++)
		{
			numInter = scanner.nextInt();
			
			//distance matrix is made of floats to have more precise distances
			float[][] matrix = new float[numInter][numInter];
			int[][] path = new int[numInter][numInter];
			
			//scans in the matrix
			for(int i = 0; i < numInter; i++)
			{
				for(int j = 0; j < numInter; j++)
					matrix[i][j] = scanner.nextFloat();
			}
			
			//sets up the path matrix to find shorts path intersection to intersection
			for(int i = 0; i < numInter; i++)
			{
				for(int j = 0; j < numInter; j++)
					path[i][j] = j;
			}
			interA = scanner.nextInt();
			interB = scanner.nextInt();
			int end = interB;
			
			//rearranges the matrices to have most optimized distances and paths
			findShortestPath(matrix, path);
			
			System.out.printf("Map #%d\nThe shortest distance between %d and %d is %.2f\n",h + 1,interA, interB, matrix[interA][interB]);

	    	String myPath = interB + "";
	    	
	    	//loops backwards until it finds the begininning of the path
	    	while (path[interA][interB] != interB) {
	    		myPath = path[interA][interB] + "->" + myPath;
	    		interB = path[interA][interB];
	    	}
	    	

	    	myPath = interA + "->" + myPath;
	    	System.out.printf("The shortest path from %d to %d is %s\n\n", interA, end, myPath);
	    	
	  	}
			scanner.close();
		}
	
	//rearranges the matrices to have most optimized distances and paths
	public static void findShortestPath(float[][] matrix, int[][] path)
	{
		int n = matrix.length;

		
    	for (int k=0; k<n;k++) {


      		for (int i=0; i<n; i++) {
        		for (int j=0; j<n;j++) {
          			if (matrix[i][k]+matrix[k][j] < matrix[i][j]) {
          				matrix[i][j] = (matrix[i][k]+matrix[k][j]);
          				path[i][j] = k;
          			}
          		}
      		}
    	}
	}

}
