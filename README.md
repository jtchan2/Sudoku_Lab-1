# Sudoku_Lab-1
CS245 - Lab Assignment 01 - Sudoku Solver

Team members: 
	Justin Chan and Suchitoto Rose Tabares-Tarquinio

Goal:
	Intake semi completed sudoku board and find solution for remaing empty cells and output completed sudoku board using Backtracking Algorithm

Comments:
	Outline of functions implementation:
	-main: 
		-create initial board: rowsXcolumns=9x9=n^2
		-printScreen: n^2
		-sudoku: 
			-isSpaceEmpty: n^2
			n -canPlace: 
				-isRowClean: n
				-isColClean: n
				-is3x3Clean: n
			  -sudoku: n
		-printScreen: n^2	

	Runtime: O(n^m), n is number of possible numbers for each cell digits 1 through 9, m is number of empty cells that the algorithm must find a number to place in to complete board. 

	Space Complexity: O(n^2), board 2 dimensional array of 9x9=81 cells
	
Resources:
	Sudoku Solver Backtracking Algorithm Explained: https://www.youtube.com/watch?v=nCAL4QbUdxM
	Backtracking Algorithm:
	-Find empty boxes
	-Try all numbers
	-Validate (-row -column -3x3 box)
	-Repeat
	-Backtracking
		If the value left does not work on that cell, go to previous cell where you inputed value and change to another possible value
