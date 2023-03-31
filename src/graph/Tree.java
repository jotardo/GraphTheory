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

	public int getEccentricity(int v) {
		int result = 0;
		for (int i = 0; i < maxVertex; i++)
			result = Math.max(result, routeLength(v, i));
		return result;
	}

	public int[] getTreeCenter() {
		int[] result = new int[2];
		int vertex = 0;
		for (int i = 0; i < maxVertex; i++)
			if (getEccentricity(vertex) < getEccentricity(i)) {
				vertex = i;
				result[0] = i;
			}
		for (int i = 0; i < maxVertex; i++)
			if (result[0] == getEccentricity(i) && i != 0) {
				result[1] = i;
				break;
			}
		return result;
	}

	public int getTreeRadius() {
		return getEccentricity(getTreeCenter()[0]);
	}

	public int routeLength(int v1, int v2) {
		int result = 0;

		Stack<Integer> stack = new Stack<>();
		boolean noEdgeToContinue;
		int point = 0;
		boolean[] visited = new boolean[maxVertex];
		stack.push(v1);
		visited[v1] = true;
//		System.out.print("start at ::" + v1);
		while (point != v2) {
			noEdgeToContinue = true;
			point = stack.peek();
			for (int i = 0; i < maxVertex; i++) {
				if (adjMatrix[point][i] > 0 && !visited[i]) {
//					System.out.print(" -> " + i);
					stack.push(i);
					noEdgeToContinue = false;
					result++;
					point = i;
					visited[i] = true;
					break;
				}
			}
			if (noEdgeToContinue) {
				stack.pop();
//				System.out.print(" -> " + stack.peek());
				result--;
			}
		}
//		System.out.println();
		return result;
	}
	
	public RootedTree toRootedTree(int rootVertex) {
		RootedTree r = new RootedTree(maxVertex, rootVertex);
		
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[] visited = new boolean[this.maxVertex];
		int v;
		//diem dau
		visited[rootVertex] = true;
		q.add(rootVertex);
		//vong lap check dg di
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
