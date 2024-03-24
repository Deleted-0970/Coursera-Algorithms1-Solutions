import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	private double[] results;

	public PercolationStats(int n, int trials) {
		results = new double[trials];
		for (int i = 0; i < trials; i++) {
			Percolation sim = new Percolation(n);
			while (!sim.percolates()) {
				sim.open(StdRandom.uniformInt(1, n + 1), StdRandom.uniformInt(1, n + 1)); 
				// last num non inclusive
			}
			results[i] = sim.numberOfOpenSites() * 1.0 / (n * n); 
		}
	}
	
	public double mean() {
		return StdStats.mean(results);
	}

	public double stddev() {
		return StdStats.stddev(results);
	}

	public double confidenceLo() {
		return this.mean() - (1.96 * this.stddev());
	}

	public double confidenceHi() {
		return this.mean() + (1.96 + this.stddev());
	}

	public static void main(String[] args) {
		PercolationStats test = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		System.out.println("mean                    = " + test.mean());
		System.out.println("stddev                  = " + test.stddev());
		System.out.println("95% confidence interval = [" + test.confidenceLo() + ", " + test.confidenceHi() + "]");
	}
}
