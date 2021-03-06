// Class to run tests on new changes before implementing in actual app.
package com.example.theotherlegacy.smarttictactoe;

import java.util.ArrayList;
import java.util.Collections;

public class MiniMax {
	static int[] nextMove = { 0, 0 };
	static ArrayList<int[]> globmoves = new ArrayList<int[]>();

	public static void main(String[] args) {

	}

	public static void myfunc() {
		//starting state for running tests					
		int[][] testState = { { 0, 0, 0}, 
				      { 0, 0, 0}, 
			              { 0, 0, 0} };
		//Testing code commented out
		//
		//ArrayList<int [][]> posmoves = nextMoves (testState,2);
		/*
		 * for(int [][] move :posmoves){ sayBoard(move); System.out.println(); }
		 */
		//minimax(testState, 0, 2, 1);
		//System.out.println(nextMove[0]+" "+nextMove[1]);
		// System.out.println(minimax(testState, 0, 2));
		// System.out.println(minimax(testState, 0, 2));
		// System.out.println(nextMove[0] + "," + nextMove[1]);
	}
	
	
	//takes the current state as a 2d array and the current player and returns the move with the 
	//highest node value and the lowest index.
	public int getAIMove(int[][] state,int player){
		minimax(state,0,player,1);
		if (nextMove[0]==0){
			if(nextMove[1]==0)
				return 0;
			else if (nextMove[1]==1)
				return 1;
			else
				return 2;}
		else if (nextMove[0]==1){
			if(nextMove[1]==0)
				return 3;
			else if (nextMove[1]==1)
				return 4;
			else
				return 5;}
		else{
			if(nextMove[1]==0)
				return 6;
			else if (nextMove[1]==1)
				return 7;
			else
				return 8;
		}

		}


	//modified minimax algorithim that searches graph for the closest move (lowest depth) with 
	//the highest score
	public static int minimax(int[][] state, int depth, int player, int iter) {
		int gameStatus = isGameOver(state);
		if (gameStatus == 2) {
			return (10 - depth);
		} else if (gameStatus == 1) {
			return (depth - 10);
		} else if (gameStatus == 0) {
			return 0;
		} else if (player == 1) {
			ArrayList<Integer> scores = new ArrayList<Integer>();
			ArrayList<int[][]> posmoves = nextMoves(state, player,iter);
			for (int[][] move : posmoves) {
				scores.add(minimax(move, (depth + 1), 2, 0));
			}
			return Collections.min(scores);
			/*
			 * ArrayList<Integer> scores = new ArrayList<Integer>(); for (int i
			 * = 0; i < 3; i++) { for (int j = 0; j < 3; j++) { currentState =
			 * state.clone(); if (currentState[i][j] == 0) { currentState[i][j]
			 * = 1; scores.add(minimax(currentState, (depth + 1), 2)); } } }
			 * return (Collections.min(scores));
			 */
		} else {
			ArrayList<Integer> scores = new ArrayList<Integer>();
			ArrayList<int[][]> posmoves = nextMoves(state, player,iter);
			for (int[][] move : posmoves) {
				// sayBoard(move);
				scores.add(minimax(move, (depth + 1), 1, 0));

			}
			int max = Collections.max(scores);

			if(iter==1){
			int index = scores.indexOf(max);
			nextMove = globmoves.get(index).clone();
			return max;
			}
				
			else{
			
			return max;
			}
			
		}

	}

	//generates the next possible moves given a gamestate
	public static ArrayList<int[][]> nextMoves(int[][] state, int player, int iter) {
		ArrayList<int[]> posmoves = new ArrayList<int[]>();
		ArrayList<int[][]> nextStates = new ArrayList<int[][]>();
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				int[][] myclone = new int[3][3];
				for (int j = 0; j < 3; j++) {
					myclone[j] = state[j].clone();
				}
				if (myclone[x][y] != 0)
					continue;
				myclone[x][y] = player;
				nextStates.add(myclone);
				// System.out.println(x+" " +y);
				posmoves.add(new int[] { x, y });

			}
		}
		// for(int i=0; i< posmoves.size();i++){
		// //System.out.println(move[0]+" "+move[1]);
		// nextStates.add((moveToState(posmoves.get(i), state, player, i)));
		// }
		if (iter==1){
			globmoves = new ArrayList<int[]>();
			globmoves=posmoves;
			return nextStates;
			}
		
		return nextStates;
	}

	/*
	 * private static int[][] moveToState(int[] move, int[][] state, int player,
	 * int index) { int[][][] clonegrid = new int[9][3][3]; sayBoard(state);
	 * clonegrid[index] =state.clone();
	 * clonegrid[index][move[0]][move[1]]=player; return clonegrid[index]; }
	 */

	public static void sayBoard(int[][] board) {
		for (int i = 0; i < 3; i++) {
			System.out.println();
			for (int j = 0; j < 3; j++) {
				System.out.print(board[i][j] + " ");
			}
		}
	}
  
	
	//checks if the gamee is over, if it is returns either 1, 2 or 0 indicating that either
	//player x won, player o won or it is a tie game. In the case of the game not being over
	//it returns a -1
	
	public static int isGameOver(int[][] grid) {
		if ((grid[0][0] != 0) && (grid[0][0] == grid[0][1]) && (grid[0][1] == grid[0][2]))
			return grid[0][0];
		else if ((grid[1][0] != 0) && (grid[1][0] == grid[1][1]) && (grid[1][1] == grid[1][2]))
			return grid[1][0];
		else if ((grid[2][0] != 0) && (grid[2][0] == grid[2][1]) && (grid[2][1] == grid[2][2]))
			return grid[2][0];
		else if ((grid[0][0] != 0) && (grid[0][0] == grid[1][0]) && (grid[1][0] == grid[2][0]))
			return grid[0][0];
		else if ((grid[1][1] != 0) && (grid[0][1] == grid[1][1]) && (grid[1][1] == grid[2][1]))
			return grid[1][1];
		else if ((grid[0][2] != 0) && (grid[0][2] == grid[1][2]) && (grid[1][2] == grid[2][2]))
			return grid[0][2];
		else if ((grid[1][1] != 0) && (grid[0][0] == grid[1][1]) && (grid[1][1] == grid[2][2]))
			return grid[1][1];
		else if ((grid[0][2] != 0) && (grid[0][2] == grid[1][1]) && (grid[0][2] == grid[2][0])) {
			return grid[0][2];
		} else {
			int zeros = 0;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (grid[i][j] == 0)
						zeros++;
				}
			}
			if (zeros == 0)
				return 0;
			else
				return -1;
		}

	}
}
