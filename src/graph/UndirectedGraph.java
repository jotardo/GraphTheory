package graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class UndirectedGraph extends AGraph {

	/* Loop counts as 2 here */
	public UndirectedGraph(int num) {
		super(num);
	}

	@Override
	public void addEdge(int p1, int p2) {
		if (p1 < 0 || p1 >= this.maxVertex || p2 < 0 | p2 >= this.maxVertex)
			return;
		this.adjMatrix[p1][p2]++;
		this.adjMatrix[p2][p1]++;
	}

	@Override
	public void removeEdge(int p1, int p2) {
		if (p1 < 0 || p1 >= this.maxVertex || p2 < 0 | p2 >= this.maxVertex)
			return;
		if (this.adjMatrix[p1][p2] > 0 && this.adjMatrix[p2][p1] > 0) {
			this.adjMatrix[p1][p2]--;
			this.adjMatrix[p2][p1]--;
		}
	}

	@Override
	public int deg(int point) {
		if (point < 0 || point >= this.maxVertex)
			return -10;
		int result = 0;
		for (int i = 0; i < this.maxVertex; i++)
			result += this.adjMatrix[point][i];
		return result;
	}

	@Override
	public void printEdgeList() {
		System.out.print("Undirected: ");
		for (int row = 0; row < this.adjMatrix.length; row++)
			for (int column = row; column < this.adjMatrix[row].length; column++)
				if (this.adjMatrix[row][column] > 0)
					for (int k = 0; k < this.adjMatrix[row][column]; k++)
						System.out.printf("(%d, %d) ", row, column);
		System.out.println();
	}

	@Override
	public int numEdges() {
		int result = 0;
		for (int row = 0; row < this.adjMatrix.length; row++)
			for (int column = 0; column < this.adjMatrix[row].length; column++)
				result += this.adjMatrix[row][column];
		return result / 2;
	}

	public boolean isBipartiteGraph() {
		if (!isSimpleGraph())
			return false;
		throw new NoSuchMethodError("This method is NOT completed");
	}
	
	public boolean isConnectedGraph() {
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[] visited = new boolean[this.maxVertex];
		int v;
		int vertexConnect = 1;
		//diem dau
		visited[0] = true;
		q.add(0);
		//vong lap check dg di
		while (!q.isEmpty()) {
			v = q.poll();
			for (int i = 0; i < this.maxVertex; i++) {
				if (this.adjMatrix[v][i] > 0 && !visited[i]) {
					q.add(i);
					visited[i] = true;
					vertexConnect++;
				}
			}
		}
		return vertexConnect == this.maxVertex;
	}

	@Override
	public boolean isEulerGraph() {
		if (maxVertex < 2)
			return false;
		if (!isConnectedGraph())
			return false;
		for (int v = 0; v < this.maxVertex; v++) {
			if (deg(v) % 2 != 0)
				return false;
		}
		return true;
	}

	@Override
	public boolean isHalfEulerGraph() {
		if (maxVertex < 2)
			return false;
		if (!isConnectedGraph())
			return false;
		int degOdd = 0;
		for (int v = 0; v < this.maxVertex; v++) {
			if (deg(v) % 2 != 0) {
				degOdd++;
				if (degOdd > 2)
					return false;
			}
		}
		return degOdd == 2;
	}

	@Override
	public AGraph copy() {
		UndirectedGraph g = new UndirectedGraph(maxVertex);
		g.adjMatrix = new byte[maxVertex][maxVertex];
		for (int i = 0; i < maxVertex; i++)
			for (int j = 0; j < maxVertex; j++)
				g.adjMatrix[i][j] = this.adjMatrix[i][j];
		return g;
	}
	
	public LinkedList<Integer> getEulerCircuit() {
		if (!isEulerGraph())
			return null;
		
		LinkedList<Integer> circuit = new LinkedList<>();
		LinkedList<Integer> subCircuit = new LinkedList<>();
		UndirectedGraph graph2 = (UndirectedGraph) copy();
		int i = 0;
		circuit.add(0);
		while (graph2.numEdges() > 0) {
			for (int v = 0; v < graph2.maxVertex; v++) {
				if (graph2.deg(v) > 0 && circuit.indexOf(v) >= 0) {
					i = v;
					break;
				}
			}
			subCircuit = graph2.getSubCircuit(i);
			circuit = replaceCircuit(circuit, i, subCircuit);
		}
		return circuit;
	}

	public LinkedList<Integer> getEulerPath() {
		
		LinkedList<Integer> path = new LinkedList<>();
		AGraph a = copy();
		
		Stack<Integer> s = new Stack<Integer>();
		int vertex;
		boolean noEdge;
		s.push(0);
		path.add(0);
//		System.out.print("from " + 0 + " : ");
		while (a.numEdges() > 0) {
			noEdge = true;
			vertex = s.peek();
			for (int i = 0; i < a.maxVertex; i++) {
				if (a.adjMatrix[vertex][i] > 0) {
//					System.out.print(vertex + " -> " + i + " || ");
					s.push(i);
					a.removeEdge(vertex, i);
					path.add(i);
					noEdge = false;
					break;
				}
			}
			if (noEdge) {
				s.pop();
//				System.out.print(vertex + " -> " + s.peek() + " || ");
				path.add(s.peek());
			}
		}
		return path;
	}
	
	protected LinkedList<Integer> getSubCircuit(int vertexStart) {
		LinkedList<Integer> result = new LinkedList<>();
		int i = vertexStart;
		result.add(vertexStart);
		while (deg(i) > 0) {
			for (int j = 0; j < maxVertex; j++) {
				if (adjMatrix[i][j] > 0) {
					result.add(j);
					removeEdge(i, j);
					i = j;
					break;
				}
			}
		}
//		System.out.println(result);
		return result;
	}
	
	protected LinkedList<Integer> replaceCircuit(LinkedList<Integer> original, int point, LinkedList<Integer> circuit) {
		LinkedList<Integer> result = new LinkedList<>();
		for (int i = 0; i < original.size(); i++) {
			if (i == point) {
				result.addAll(circuit);
			}
			else
				result.add(original.get(i));
		}
		return result;
	}

}
