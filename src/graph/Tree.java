package graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Tree extends UndirectedGraph {

	public Tree(int num) {
		super(num);
	}
	
	public Tree(UndirectedGraph graph) {
		super(graph.maxVertex);
		super.adjMatrix = graph.adjMatrix;
	}

	@Override
	public void addEdge(int p1, int p2) {
		super.addEdge(p1, p2);
		if (isEulerGraph())
			super.removeEdge(p1, p2);
	}

	public boolean isValidTree() {
		return maxVertex - 1 == numEdges();
	}
	
	public RootedTree toRootedTree(int rootVertex) {
		RootedTree r = new RootedTree(maxVertex, rootVertex);
		
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[] visited = new boolean[this.maxVertex];
		int v;
		//BFS
		visited[rootVertex] = true;
		q.add(rootVertex);
		while (!q.isEmpty()) {
			v = q.poll();
			for (int i = 0; i < maxVertex; i++) {
				if (adjMatrix[v][i] > 0 && !visited[i]) {
					q.add(i);
					r.addEdge(v, i);
					visited[i] = true;
				}
			}
		}
		return r;
	}

}
