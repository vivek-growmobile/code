import java.util.*;


public class Minesweeper {

	private int _height;
	private int _width;
	private int _bombs;
	private int[][] board;


	public Minesweeper (int height, int width, int bombs){
		_height = height;
		_width = width;
		_bombs = bombs;
		board = new int[width][height];

	}

	/*
	private class Coordinate {
		private int _x;
		private int _y;

		private Coordinate(int x, int y){
			_x = x;
			_y = y;
		}

		@Override
		public boolean equals(Object other){
			Map

		}


	}
	 */

	public int[] getNewCoords(){
		int[] coords = new int[2];
		Random rand = new Random();
		coords[0] = rand.nextInt(_width);
		coords[1] = rand.nextInt(_height);

		return coords;
	}

	public void setUpBombs(){
		//HashSet<int[]> filled = new HashSet<int[]>();
		for (int i = 0; i < _bombs; i++){
			int[] coords = getNewCoords();
			while (board[coords[0]][coords[1]] == -1){
				coords = getNewCoords();
			}
			board[coords[0]][coords[1]] = -1;
		}
	}

	public int neighborBombs(int x, int y){
		int numBombs = 0;

		if (x > 0 && y > 0 && board[x-1][y-1] == -1) numBombs++;

		if (x > 0 && board[x-1][y] == -1) numBombs++;

		if (x > 0 && y < _height - 1 && board[x-1][y+1] == -1) numBombs++;

		if (y > 0 && board[x][y-1] == -1) numBombs++;

		if (y < _height - 1 && board[x][y+1] == -1) numBombs++;

		if (x < _width - 1 && y > 0 && board[x+1][y-1] == -1) numBombs++;

		if (x < _width - 1 && board[x+1][y] == -1) numBombs++;

		if (x < _width - 1 && y < _height - 1 && board[x+1][y+1] == -1) numBombs++;

		return numBombs;
	}		

	public void printBoard(){
		for (int j = 0; j < _height; j++){
			for (int i = 0; i < _width; i++){
				if (board[i][j] == -1) System.out.print("* ");
				else {
					int numBombs = neighborBombs(i, j);
					System.out.print(numBombs + " ");
				}
				if (i == _width - 1) System.out.print("\n");
			}
		}
	}
	
	public static void main(String[] args){
		if (args.length != 3){
			System.out.println("Usage: Improper Number of Arguments");
			return;
		}
		Minesweeper ms = new Minesweeper(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
		ms.setUpBombs();
		ms.printBoard();
	}




}
