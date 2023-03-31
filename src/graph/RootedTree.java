package graph;

public class RootedTree extends DirectedGraph{

	private int root;
	
	public RootedTree(int num, int root) {
		super(num);
		this.root = root;
	}
	
	public int getRoot() {
		return root;
	}
	
	public void setRoot(int root) {
		this.root = root;
		this.adjMatrix = toTree().toRootedTree(root).adjMatrix;
	}

	public boolean isValidTree() {
		return maxVertex - 1 == numEdges();
	}
	
	@Override
	public void addEdge(int p1, int p2) {
		super.addEdge(p1, p2);
		if (isEulerGraph())
			super.removeEdge(p1, p2);
	}
	
	@Override
	public boolean isTree() {
		return true;
	}
	
	public Tree toTree() {
		return new Tree(toUndirectedGraph());
		
	}

}
