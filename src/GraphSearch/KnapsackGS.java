package GraphSearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KnapsackGS {

	private int[] count;
	private int[] values;
	private int[] weights;
	private int n;
	private int W;
	private Node root;
	private int soln;
	private int bm = 0;

	public KnapsackGS(int[] value, int[] weights, int wt, int n, int[] names) {
		System.out.println("--------------------------------");
		System.out.println("Graph Search O-N");
		this.values = value;
		this.weights = weights;
		this.W = wt;
		this.n = n;
		int[] s = GraphSearch();
		for (int i = 0; i < s.length; i++) {
			if (s[i] == 1)
				System.out.println("Item " + (names[i] + 1) + " with weight " + weights[i] + " and value " + value[i]);
		}
	}

	public int[] GraphSearch() {

		Node root = new Node();

		Node best = root;

		Map<String, Node> nodes = new HashMap<>();
		List<Node> fringe = new ArrayList<Node>();
		fringe.add(root);
		nodes.put("0,0", root);


		while (fringe.size() != 0) {
			bm++;

			Node curr = fringe.remove(fringe.size() - 1);

			if (curr.getIndex() + 1 > n) {
				continue;
			}

			boolean notTakeNew = getNode(nodes, curr.getIndex() + 1, curr.getWeight(), curr.getVal(), curr);

			Node notTake = nodes.get((curr.getIndex() + 1) + "," + curr.getWeight());

			if (notTakeNew) {
				fringe.add(notTake);
			}

			if (curr.getWeight() + weights[curr.getIndex()] <= W) {

				boolean takeNew = getNode(nodes, curr.getIndex() + 1, curr.getWeight() + weights[curr.getIndex()],
						curr.getVal() + values[curr.getIndex()], curr);

				Node take = nodes.get((curr.getIndex() + 1) + "," + (curr.getWeight() + weights[curr.getIndex()]));
				if (take.getVal() > best.getVal()) {
					best = take;
				}

				if (takeNew) {
					fringe.add(take);
				}
			}
		}
		this.soln = best.getVal();
		System.out.println("Solution: " + best.getVal());
		System.out.println("Items included are...");
		int[] sol = new int[n];

		Node curr = best;

		while (curr.getParent() != null) {
			bm++;
			Node p = curr.getParent();

			if (curr.getWeight() != p.getWeight()) {
				sol[p.getIndex()]++;
			}

			curr = p;
		}

		
		return sol;

	}

	
	public static boolean getNode(Map<String, Node> nodes, int i, int j, int value, Node current) {
		if (nodes.containsKey(i + "," + j)) {
			Node n = nodes.get(i + "," + j);
			if (n.getVal() < value) {
				n.setVal(value);
				n.setParent(current);
			}

			return false;
		}

		Node n = new Node();
		n.setIndex(i);
		n.setWt(j);
		n.setVal(value);
		n.setParent(current);
		nodes.put(i + "," + j, n);

		return true;
	}
	
	public int getSol() {
		return this.soln;
	}
	
	public int getBM() {
		return bm;
	}

}
