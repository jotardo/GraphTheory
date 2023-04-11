package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public abstract class AGraph {

	protected int maxVertex;
	protected byte[][] adjMatrix;

	public AGraph(int num) {
		this.adjMatrix = new byte[num][num];
		this.maxVertex = num;
	}
	
	public abstract AGraph copy();

	public abstract void addEdge(int point1, int point2);

	public abstract void removeEdge(int point1, int point2);

	public abstract int deg(int point);

	public abstract void printEdgeList();

	public abstract int numEdges();

	public void importAdjMatrix(byte[][] newAdjMatrix) {
		for (int i = 0; i < maxVertex; i++)
			for (int j = 0; j < maxVertex; j++)
				this.adjMatrix[i][j] = newAdjMatrix[i][j];
	}

	public void printAdjMatrix() {
		for (int i = 0; i < adjMatrix.length; i++)
			System.out.println(Arrays.toString(adjMatrix[i]));
	}

	public boolean isSimpleGraph() {
		for (int r = 0; r < this.maxVertex; r++)
			for (int c = 0; c < this.maxVertex; c++)
				if (this.adjMatrix[r][c] > 1)
					return false;
		return true;
	}

	public void BFS(int startingPoint) {
		if (startingPoint < 0 || startingPoint >= this.maxVertex)
			return;
		
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[] visited = new boolean[this.maxVertex];
		int v;
		//diem dau
		System.out.print("BFS :: " + startingPoint + " ");
		visited[startingPoint] = true;
		q.add(startingPoint);
		//vong lap check dg di
		while (!q.isEmpty()) {
			v = q.poll();
			for (int i = 0; i < this.maxVertex; i++) {
				if (this.adjMatrix[v][i] > 0 && !visited[i]) {
					System.out.print(i + " ");
					q.add(i);
					visited[i] = true;
				}
			}
		}
		System.out.println();
	}
	
	public void DFS(int startingPoint) {
		if (startingPoint < 0 || startingPoint >= this.maxVertex)
			return;
		
		Stack<Integer> s = new Stack<Integer>();
		boolean[] visited = new boolean[this.maxVertex];
		int vertex;
		System.out.print("DFS :: " + startingPoint + " ");
		visited[startingPoint] = true;
		s.push(startingPoint);
		boolean noEdge;
		while (!s.isEmpty()) {
			noEdge = true;
			vertex = s.peek();
			for (int i = 0; i < this.maxVertex; i++) {
				if (this.adjMatrix[vertex][i] > 0 && !visited[i]) {
					System.out.print(i + " ");
					visited[i] = true;
					s.push(i);
					noEdge = false;
				}
			}
			if (noEdge) {
				s.pop();
			}
		}
		System.out.println();
	}

	public abstract boolean isConnectedGraph();
	
	public boolean hasPath(int point1, int point2) {
		if (point1 < 0 || point1 >= this.maxVertex || point2 < 0 | point2 >= this.maxVertex)
			return false;
		
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[] visited = new boolean[this.maxVertex];
		int v;
		//diem dau
		visited[point1] = true;
		q.add(point1);
		//vong lap check dg di
		while (!q.isEmpty()) {
			v = q.poll();
			for (int i = 0; i < this.maxVertex; i++) {
				if ((this.adjMatrix[v][i] > 0 || this.adjMatrix[i][v] > 0)  && !visited[i]) {
					if (i == point2)
						return true;
					else {
						q.add(i);
						visited[i] = true;
					}
				}
			}
		}
		return false;
	}
	
	public int numOfConnectedGraph() {
		ArrayList<Integer> visitedPoints = new ArrayList<>();
		int result = 0;
		Queue<Integer> queue;
		boolean[] visited;
		int vertex;
		for (int k = 0; k < this.maxVertex; k++) {
			if (visitedPoints.indexOf(k) < 0) {
				queue = new LinkedList<Integer>();
				visited = new boolean[this.maxVertex];
				vertex = 0;
				visited[k] = true;
				queue.add(k);
				while (!queue.isEmpty()) {
					vertex = queue.poll();
					for (int i = 0; i < this.maxVertex; i++) {
						if (this.adjMatrix[vertex][i] > 0 && !visited[i]) {
							queue.add(i);
							visited[i] = true;
							visitedPoints.add(i);
						}
					}
				}
				result++;
			}
		}
		return result;
	}
	
	public abstract boolean isEulerGraph();
	
	public abstract boolean isHalfEulerGraph();
	
	public abstract LinkedList<Integer> getEulerCircuit();

	public abstract LinkedList<Integer> getEulerPath();
	
	public boolean isTree() {
		return isConnectedGraph() && maxVertex - 1 == numEdges();
	}
	
	public Tree getSpanningTree_BFS() {
		Tree t = new Tree(maxVertex);
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[] visited = new boolean[this.maxVertex];
		int v;
		//diem dau
		System.out.print("BFS :: " + 0 + " ");
		visited[0] = true;
		q.add(0);
		//vong lap check dg di
		while (!q.isEmpty()) {
			v = q.poll();
			for (int i = 0; i < this.maxVertex; i++) {
				if (this.adjMatrix[v][i] > 0 && !visited[i]) {
					System.out.print(i + " ");
					q.add(i);
					visited[i] = true;
					t.addEdge(v, i);
				}
			}
		}
		System.out.println();
		return t;
	}
	
	public Tree getSpanningTree_DFS() {
		Tree t = new Tree(maxVertex);
		
		Stack<Integer> s = new Stack<Integer>();
		boolean[] visited = new boolean[this.maxVertex];
		int vertex;
		System.out.print("DFS :: " + 0 + " ");
		visited[0] = true;
		s.push(0);
		boolean noEdge;
		while (!s.isEmpty()) {
			noEdge = true;
			vertex = s.peek();
			for (int i = 0; i < this.maxVertex; i++) {
				if (this.adjMatrix[vertex][i] > 0 && !visited[i]) {
					System.out.print(i + " ");
					visited[i] = true;
					s.push(i);
					t.addEdge(vertex, i);
					noEdge = false;
					break;
				}
			}
			if (noEdge) {
				s.pop();
			}
		}
		System.out.println();
		return t;
	}
}
