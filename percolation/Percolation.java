import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private int[][] board; // representation of board. 0 is closed, 1 is filled.
	private WeightedQuickUnionUF connect; // algorithm to create unions
	private int n; // side of square board

	public Percolation(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException("board too small");
		}
		this.n = n;
		this.board = new int[n][n];
		this.connect = new WeightedQuickUnionUF(n * n + 2);
		// +2 to represent a top and bottom
	}

	public void open(int row, int col) {
		exceptionCheck(row, col);
		if (!this.isOpen(row, col)) {
			this.board[row - 1][col - 1]++; // open spot
			if (row == 1) {
				this.connect.union(0, col); // connect to top if top row
			} else if (row == this.n) { 
				this.connect.union(this.n * this.n + 1, this.n * (this.n - 1) + col); // connect to bottom if bottom row
			}
			if (row - 1 > 0 && this.isOpen(row - 1, col)) {
				this.connect.union(index(row, col), index(row - 1, col));
			}
			if (row + 1 < this.n + 1 && this.isOpen(row + 1, col)) {
				this.connect.union(index(row, col), index(row + 1, col));
			}
			if (col - 1 > 0 && this.isOpen(row, col - 1)) {
				this.connect.union(index(row, col), index(row, col - 1));
			}
			if (col + 1 < this.n + 1 && this.isOpen(row, col + 1)) {
				this.connect.union(index(row, col), index(row, col + 1));
			}
		}
	}

	public boolean isOpen(int row, int col) {
		exceptionCheck(row, col);
		return board[row - 1][col - 1] == 1;
	}

	public boolean isFull(int row, int col) {
		exceptionCheck(row, col);
		return this.connect.find(index(row, col)) == this.connect.find(0);
	}

	public int numberOfOpenSites() {
		int open = 0;
		for (int i = 0; i < this.n; i++) {
			for (int j = 0; j < this.n; j++) {
				open += this.board[i][j];
			}
		}
		return open;
	}

	public boolean percolates() {
		return this.connect.find(0) == this.connect.find(this.n * this.n + 1);
	}

	public static void main(String[] args) {
		Percolation p = new Percolation(3);
		p.open(1, 1);
		System.out.println(p.isOpen(1, 1));
		System.out.println(p.isFull(1, 1));
		p.open(2, 1); 
		System.out.println(p.isOpen(2, 1));
		System.out.println(p.isFull(2, 1));
		p.open(2, 2);
		p.open(3, 2);
		System.out.println("result: " + p.percolates());
	}

	private void exceptionCheck(int i, int j) {
		if (i > n || i <= 0 || j > n || j <= 0) {
			throw new IllegalArgumentException("out of bounds");
		}
	}
	
	private int index(int row, int col) {
		return (row - 1) * n + col;  
	}
}
