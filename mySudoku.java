import java.util.*;
public class mySudoku{
	public  boolean isRowClean(int [][]board, int row, int num){
		for(int i=0; i<9; i++){
			if(board[row][i]==num){
				return false;
			}
		}
		return true;
	}
	public  boolean isColClean(int [][]board, int col, int num){
		for(int i=0; i<9; i++){
			if(board[i][col]==num){
				return false;
			}
		}
		return true;
	}
	//
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
	public boolean canPlace(int [][] board, int row, int col, int num){
		if(isRowClean(board, row, num)==true&&isColClean(board, col, num)==true&&is3x3Clean(board, row-(row%3), col-(col%3), num)==true){
			return true;
		}
		return false;
	}
	// the Sudoku function absically runs sudoku where the method is a boolean return type such that if the puzzle is solvable, it will return a true if not it will return false
	//Back tracking in this part is where the code when a number cant be filled, it reverts the number back to a zero and goes back to the for loop to loop through the other numbers tha could be used
	public boolean sudoku(int [][] board){
		//int row;
		//int col;
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
				board[row][col]=0;
			}
		}
		//it restarts nubmer elemination process for filling in the slot 
		return false;
	}

	public static void main(String [] args){
		int [][] board= new int[][] {{5,3,0,0,7,0,0,0,0},
							{6,0,0,1,9,5,0,0,0}, {0,9,8,0,0,0,0,6,0},{8,0,0,0,6,0,0,0,3},{4,0,0,8,0,3,0,0,1},{7,0,0,0,2,0,0,0,6},{0,6,0,0,0,0,2,8,0},{0,0,0,4,1,9,0,0,5},{0,0,0,0,8,0,0,7,9}};
		Scanner input= new Scanner(System.in);

		//This prints out the board
		for(int i=0; i<9; i++){
			for(int j=0; j<9; j++){
				if(j==8){
					System.out.print(board[i][j]);
					System.out.println();
				}else{
					System.out.print(board[i][j]);
				}
			}
		}
		mySudoku puzzle= new mySudoku();
		if(puzzle.sudoku(board)==true){
			System.out.println("puzzle is solved");
			// correct visual of printing board
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
					//System.out.println();
					System.out.println("-----------");
					//System.out.println();
				}
			}
		}else{
			System.out.println("No answer");
		}
	}
}