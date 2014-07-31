//William Pearigen
//COP 3503
//Program 1 - Sudoku
//May 27, 2014


import java.io.IOException;
import java.util.*;

public class Sudoku {
	

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		int numPuzzles = scanner.nextInt();
		int[][] puzzle;
		int i;
		
		//one iteration for each puzzle
		for(i = 0; i < numPuzzles; i++)
		{
			puzzle = readPuzzle(scanner);
			System.out.println("Test case " + (i+1) + ":\n");
			if(solvePuzzle(puzzle))
				printPuzzle(puzzle);
			else if(!solvePuzzle(puzzle))
				System.out.println("No solution possible");
		}
		scanner.close();
		


	}
	
	//scans in the puzzles into a 9X9 array
	public static int[][] readPuzzle(Scanner scanner) throws IOException
	{
		int[][] puzzle = new int[9][9];
		int i, j;
		for(i = 0; i < 9; i++)
		{
			for(j = 0; j < 9; j++)
				puzzle[i][j] = scanner.nextInt();
			System.out.println();
		}
		return puzzle;
	}
	
	//basic print function that prints the 9X9 array
	public static void printPuzzle(int[][] puzzle)
	{
		int i, j;
		for(i = 0; i < 9; i++)
		{
			for(j = 0; j< 9; j++)
				System.out.print(puzzle[i][j] + " ");
			System.out.println();
		}
	}
	
	//solves the puzzle, if possible, through recursion and backtracking
	public static boolean solvePuzzle(int[][] puzzle) {
		
		//nested for loops go through the array across by columns, and then down by rows
	    for (int i = 0; i < 9; i++) {
	        for (int j = 0; j < 9; j++) {
	        	//if current cell us a fixed number then it will skip it and move on
	            if (isFilled(puzzle, i, j)) {
	                continue;
	            }
	            //checks values 1-9 on empty cells until it finds a valid number
	            for (int num = 1; num <= 9; num++) {
	                if (isValid(puzzle, i, j, num)) {
	                    puzzle[i][j] = num;
                    if (solvePuzzle(puzzle)) 
                        return true;
                    //resets the number to 0 and backtracks if a previous number needs to be adjusted
                     else 
                        puzzle[i][j] = 0;
	                }
	            }
	            //returns false if there is no solution to the puzzle
	            return false;
	        }
	    }
	    //returns true if the puzzle is solved
	    return true;
	}
	
	//checks to see if the value is a set fixed variable or not
	public static boolean isFilled(int[][] puzzle,int i,int j)
	{
		if(puzzle[i][j] != 0)
			return true;
		else
			return false;
	}
	
	//checks to see if the same number occurs in the same row, column, or 3X3 box
	public static boolean isValid(int[][] puzzle, int a, int b, int key)
	{
		int i = 0;
		if(key == 0)
			return false;
		//looks and each column and returns false if another instance is found
		while(i < 9){
			if(puzzle[a][i] == key && i != b)
				return false;
			i++;
		}
		i = 0;
		//looks through each row and returns false if another instance is found
		while(i < 9){
			if(puzzle[i][b] == key && i != a)
				return false;
			i++;
		}
		//returns false if in the same 3X3 box
		if(!isValidBox(puzzle, a, b, key))
			return false;
		//if none of the cases happen, return true for valid placement
		return true;
	}
	
	//secondary function to check if there's another instance in the 3X3 box
	public static boolean isValidBox(int[][] puzzle, int a, int b, int key){
		//sets the starting point at the top left of the related 3X3 box
		int startRow = a / 3 * 3;
		int startCol = b / 3 * 3;
		
		//checks by columns and then rows to see if another instance occurs,
		//and returns false if found
		for(int i = startRow; i < startRow + 3; i++)
			for(int j = startCol; j < startCol + 3; j++)
				if(!(i == a && j == b))
					if(puzzle[i][j] == key)
						return false;
		//returns true if no other instances occur in the related box
		return true;
	}
}
