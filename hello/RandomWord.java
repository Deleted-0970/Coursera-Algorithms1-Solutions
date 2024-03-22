import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
	public static void main(String[] args) {
		String curr = "";
		int count = 1;
		while (!StdIn.isEmpty()) {
			String store = StdIn.readString();
			curr = StdRandom.bernoulli(1.0 / count) ? store : curr;
			count++;
		}
		System.out.println(curr);
	}
}
