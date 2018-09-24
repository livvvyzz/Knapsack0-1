package GraphSearch;

public class Node {

	private int value;
	private Node parent;
	private int index;
	private int weight;
	
	
	public Node(int i, int w, int val, Node par) {
		this.value = val;
		this.parent = par;
		this.index = i; 
		this.weight = w;
	}
	
	public Node() {
		this(0,0,0, null);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + index;
		result = prime * result + weight;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null) {
			return false;
		}
		if(getClass() != obj.getClass()) {
			return false;
		}
		
		Node other = (Node) obj;
		
		if(index != other.getIndex() || weight != other.getWeight()) {
			return false;
		}
		
		return true;
	}
	
	public int getVal() {
		return this.value;
	}
	
	public int getWeight() {
		return this.weight;
	}
	
	public Node getParent() {
		return this.parent;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	public void setVal(int val) {
		this.value = val;
	}
	
	public void setWt(int wt) {
		this.weight = wt;
	}
	
	public void setParent(Node par) {
		this.parent = par;
	}
	
	public void setIndex(int i ) {
		this.index = i;
	}

}
