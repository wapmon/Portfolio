//William Pearigen
//COP 3503 - Arup Guha
//Program 2 - Underground Cables
//June 6, 2014

import java.util.*;
import java.io.*;



public class cables {
	
	static class DisjointSets
	{
		
		//Parent array set up to hold each vertex
		private int[] parent;
		public DisjointSets(int size)
		{
			//Every vertex is in a set with itself initially
			//Set each to -1 to represent at the root
			parent = new int[size];
			for(int i=0;i<size;i++)
				parent[i] = -1;
		}
		
		//recursively go through the tree until a -1 is found, representing the root
		public int find(int x)
		{
			if(parent[x] == -1)
				return x;
			//setting parent[x] and returning it takes care of path compression
			return parent[x] = find(parent[x]);
		}
		
		//joins together 2 sets making the second's root the same as the first's
		public void union(int source, int dest)
		{
			parent[find(source)] = find(dest);
		}
	}
	
	//Class for a point coordinate, contains an x and y value
	static class Point
	{
		public Point(int a, int b) {
			this.x = a;
			this.y = b;
		}
		public int x;
		public int y;
		
		//finds the distance of current point and another passed into method
		//using the distance formula - sqrt((x1 - x0)^2 + (y1 - y0)^2)
		public double distance(int a, int b)
		{
			return Math.sqrt((x - a) * (x - a) + (y - b) * (y - b));
		}
		
	}
	
	//an edge contains a weight (distance between 2 points) and 2 ints for the source
	//and destination nodes (ints represent position in array of points)
	static class Edge
	{
		double weight;
		int source, dest;
		public Edge(int a, int b, double weight){
			this.weight = weight;
			this.source = a;
			this.dest = b;
		}
		
	}
	

	public static void main(String[] args) throws FileNotFoundException {
		FileReader reader = new FileReader("C:/Users/wapmo_000/workspace/UndergroundCables/src/sampleCables");
		Scanner scanner = new Scanner(reader);
		int numPoints = scanner.nextInt(), index;
		while(numPoints != 0)
		{
			//set up arrays for all the vertices and edges and a new set
			Point points[] = new Point[numPoints];
			
			//maximum number of edges = n(n-1)/2
			Edge edges[] = new Edge[(numPoints * (numPoints - 1)) / 2];
			DisjointSets set = new DisjointSets(numPoints);
			
			//populate the points
			for(int j = 0; j < numPoints; j++){
				points[j] = new Point(scanner.nextInt(), scanner.nextInt());
			}
			
			//index used to make sure there is no overlapping when filling the edge array
			index = 0;
			
			//populate all possible edges
			for(int i = 0; i < numPoints; i++)
			{
				for(int k = i + 1; k < numPoints; k++)
				{
					edges[index++] = new Edge(i, k, points[i].distance(points[k].x, points[k].y));
				}
			}
			
			//sort edges by weight using bubble sort
			sortEdges(edges);
			
			int numEdges = 0;
			double totalDistance = 0;
			
			//MST is found when number of Edges = number of vertices - 1
			for(int i = 0; i < edges.length; i++)
			{
				if(numEdges == numPoints - 1)
					break;
				
				//entered if the root of the first set is not the same as the root of the second set
				//(if they are the same root this detects a cycle)
				if(set.find(edges[i].source) != set.find(edges[i].dest))
				{
					numEdges++;
					totalDistance += edges[i].weight;
					set.union(edges[i].source, edges[i].dest);
				}
			}
			System.out.format("Shortest possible distance = %.2f\n", totalDistance);
			
			numPoints = scanner.nextInt();
		}
		scanner.close();

	}
	
	//simple bubble sort used to sort all the edges in the array by their weight in ascending order
	public static void sortEdges(Edge edges[])
	{
		int j;
		boolean flag = true;
		Edge temp;
		while (flag)
		{
			flag = false;
			for(j = 0; j < edges.length -1; j++)
			{
				if(edges[j].weight > edges[j+1].weight)
					{
						temp = edges[j];
						edges[j] = edges[j+1];
						edges[j+1] = temp;
						flag = true;
					} 
			} 
		}
	}
}
