import java.util.*;
public class mySudoku{
	//checks if row doesnt have the number 
	public  boolean isRowClean(int [][]board, int row, int num){
		for(int i=0; i<9; i++){
			if(board[row][i]==num){
				return false;
			}
		}
		return true;
	}
	//Checks if the column contains the number that will be inputed to sudoku grid, where if it doesnt reutrns true
	public  boolean isColClean(int [][]board, int col, int num){
		for(int i=0; i<9; i++){
			if(board[i][col]==num){
				return false;
			}
		}
		return true;
	}
	//Checks if in a 3 by 3 square if the number is contained in it.
	public boolean is3x3Clean(int [][]board, int row, int col, int num){
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				if(board[row+i][col+j]==num){
					return false;
				}
			}
		}
		return true;
	}
	//function isSpaceEmpty checks if the grid location has no numerical value
	//So that it can place a number in that slot
	//O(n^2)
	public int [] isSpaceEmpty(int [][]board){
		int []emptySpace= new int[2];
		emptySpace[0]=10;
		emptySpace[1]=10;
		for(int row=0; row<9; row++){
			for(int col=0; col<9; col++){
				if(board[row][col]==0){
					emptySpace[0]=row;
					emptySpace[1]=col;
					return emptySpace;
				}
			}
		}
		emptySpace[0]=10;
		emptySpace[1]=10;
		return emptySpace;
	}
	//This checks if the coordinate on the board cna place the specific number down
	public boolean canPlace(int [][] board, int row, int col, int num){
		if(isRowClean(board, row, num)==true&&isColClean(board, col, num)==true &&is3x3Clean(board, row-(row%3), col-(col%3), num)==true){
			return true;
		}
		return false;
	}
	// the Sudoku function basically runs sudoku where the method is a boolean return type such that if the puzzle is solvable, it will return a true if not it will return false
	//Back tracking in this part is where the code when a number cant be filled, it reverts the number back to a zero and goes back to the for loop to loop through the other numbers tha could be used
	//
	public boolean sudoku(int [][] board){
		int[] coordinate= isSpaceEmpty(board);
		if(coordinate[0]==10){
			return true;
		}
		int row=coordinate[0];
		int col=coordinate[1];
		for(int i=1; i<10; i++){
			//checks if nubmer for grid slot can be used
			if(canPlace(board,row,col,i)==true){
				board[row][col]=i;
				//if true place number and continue on to next grid coordinate
				if(sudoku(board)==true){
					return true;
				}
				//if number can not be placed in position, the previous slots nubmer is reverted back to zero
				//aka backtracking
				board[row][col]=0;
			}
		}
		//it restarts nubmer elemination process for filling in the slot 
		return false;
	}
	// this fucntion basiclly prints out the screen/board of sudoku
	// where columns are made when j=2 or 5
	//It also creates a line divission after every 3 rows
	public void printScreen(int [][] board){
		for(int i=0; i<9; i++){
				for(int j=0; j<9; j++){
					if(j==2||j==5){
						System.out.print(board[i][j]);
						System.out.print("|");
					}else if(j==8){
						System.out.print(board[i][j]);
						System.out.println();
					}else{
						System.out.print(board[i][j]);
					}
					
				}
				if(i==2|| i==5){
					
					System.out.println("-----------");
					
				}
			}
	}

	public static void main(String [] args){
		int [][] board= new int[9][9];
		Scanner input= new Scanner(System.in);
		System.out.println("Sudoku: The CODE");
		System.out.println("Enter your own Sudoku puzzle!");
		for(int row=0; row<board.length; row++){
			for(int col=0; col<board.length; col++){
				System.out.println("Enter a digits 1-9 and a 0 if you want the an empty space.");
				System.out.println("What number for Row: "+(row+1)+" Column: "+(col+1));
				int number= input.nextInt();
				//While loop just checks if number inputed was a acceptable answer if not re runs until number is accepted 
				while(number>9||number<0){
					System.out.println("Sorry only digits 1 to 9 and 0 for indicating blank spaces!");
					number=input.nextInt();
				}
				board[row][col]=number;
			}
		}
		
		
		mySudoku puzzle= new mySudoku();
		puzzle.printScreen(board);
		System.out.println();
		//Checks if sudoku methods runs the whole way through aka puzzle solved, if not returns a false meaning not solvable 
		if(puzzle.sudoku(board)==true){
			System.out.println("puzzle is solved:");
			// correct visual of printing board
			System.out.println();
			puzzle.printScreen(board);
		}else{
			System.out.println("No Solution to the puzzle");
		}
	}
}