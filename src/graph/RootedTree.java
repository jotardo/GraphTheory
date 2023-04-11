package graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class RootedTree extends DirectedGraph{

	private int root;
	
	public RootedTree(int num, int root) {
		super(num);
		this.root = root;
	}
	
	public int getRoot() {
		return root;
	}
	
	//set a different root
	public void setRoot(int root) {
		this.root = root;
		this.adjMatrix = removeRoot().toRootedTree(root).adjMatrix;
	}

	public boolean isValidTree() {
		return maxVertex - 1 == numEdges();
	}
	
	@Override
	public void addEdge(int p1, int p2) {
		super.addEdge(p1, p2);
		if (isEulerGraph())
			removeEdge(p1, p2);
	}
	
	@Override
	public boolean isTree() {
		return true;
	}
	
	//no more root || toTree method
	public Tree removeRoot() {
		return new Tree(toUndirectedGraph());
	}

	public int eccentricity(int v) {
		int result = 0;
		for (int i = 0; i < maxVertex; i++)
			result = Math.max(result, routeLength(v, i));
		return result;
	}

	public int[] getTreeCenter() {
		int[] result = new int[2];
		result[0] = -1;
		result[1] = -1;
		int vertex = 0;
		for (int i = 0; i < maxVertex; i++)
			if (eccentricity(i) < eccentricity(vertex)) {
				vertex = i;
				result[0] = i;
			}
		for (int i = 0; i < maxVertex; i++)
			if (result[0] == eccentricity(vertex) && i != vertex) {
				result[1] = i;
				break;
			}
		return result;
	}

	public int getTreeRadius() {
		return eccentricity(getTreeCenter()[0]);
	}

	public int routeLength(int v1, int v2) {
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[] visited = new boolean[this.maxVertex];
		int[] distance = new int[this.maxVertex];
		int v;
		//diem dau
		visited[v1] = true;
		q.add(v1);
		//vong lap check dg di
		while (!q.isEmpty()) {
			v = q.poll();
			for (int i = 0; i < this.maxVertex; i++) {
				if ((this.adjMatrix[v][i] > 0 || this.adjMatrix[i][v] > 0) && !visited[i]) {
					distance[i] = distance[v] + 1;
					q.add(i);
					visited[i] = true;
				}
			}
		}
		return distance[v2];
	}

}
