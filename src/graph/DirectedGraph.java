package graph;

import java.util.LinkedList;

public class DirectedGraph extends AGraph {

	/* Loop counts as 2 here */
	public DirectedGraph(int num) {
		super(num);
	}

	@Override
	public void addEdge(int p1, int p2) {
		if (p1 < 0 || p1 >= this.maxVertex || p2 < 0 | p2 >= this.maxVertex)
			return;
		this.adjMatrix[p1][p2]++;
	}

	@Override
	public void removeEdge(int p1, int p2) {
		if (p1 < 0 || p1 >= this.maxVertex || p2 < 0 | p2 >= this.maxVertex)
			return;
		if (this.adjMatrix[p1][p2] > 0) {
			this.adjMatrix[p1][p2]--;
		}
	}

	@Override
	public int deg(int point) {
		if (point < 0 || point >= this.maxVertex)
			return Integer.MIN_VALUE;
		return degIn(point) + degOut(point);
	}

	private int degOut(int point) {
		int result = 0;
		for (int i = 0; i < this.maxVertex; i++)
			result += this.adjMatrix[point][i];
		return result;
	}

	private int degIn(int point) {
		int result = 0;
		for (int i = 0; i < this.maxVertex; i++)
			result += this.adjMatrix[i][point];
		return result;
	}

	@Override
	public void printEdgeList() {
		System.out.print("Directed: ");
		for (int row = 0; row < this.adjMatrix.length; row++)
			for (int column = 0; column < this.adjMatrix[row].length; column++)
				if (this.adjMatrix[row][column] > 0)
					for (int k = 0; k < this.adjMatrix[row][column]; k++) {
						System.out.printf("(%d, %d) ", row, column);
					}
		System.out.println();
	}

	@Override
	public int numEdges() {
		int result = 0;
		for (int i = 0; i < this.maxVertex; i++)
			result += degIn(i);
		return result;
	}

	public boolean isConnectedGraph() {
		UndirectedGraph result = toUndirectedGraph();
		return result.isConnectedGraph();
	}

	public UndirectedGraph toUndirectedGraph() {
		UndirectedGraph result = new UndirectedGraph(maxVertex);
		result.importAdjMatrix(adjMatrix);
		for (int row = 0; row < result.maxVertex; row++)
			for (int column = row + 1; column < result.maxVertex; column++) {
				if (result.adjMatrix[row][column] > 0) {
					result.adjMatrix[column][row] = 1;
//					System.out.println(column + "::" + row + "added!");
					continue;
				}
				if (result.adjMatrix[column][row] > 0) {
					result.adjMatrix[row][column] = 1;
//					System.out.println(row + "::" + column + "added!");
					continue;
				}
			}
		return result;
	}

	@Override
	public boolean isEulerGraph() {
		if (maxVertex < 2)
			return false;
		if (!isConnectedGraph())
			return false;
		for (int v = 0; v < this.maxVertex; v++) {
			if (degIn(v) != degOut(v))
				return false;
		}
		return true;
	}

	@Override
	public boolean isHalfEulerGraph() {
		boolean hasSpecialDegIn = false;
		boolean hasSpecialDegOut = false;
		if (maxVertex < 2)
			return false;
		if (!isConnectedGraph())
			return false;
		for (int v = 0; v < this.maxVertex; v++) {
			if (Math.abs(degIn(v) - degOut(v)) >= 2)
				return false;
			if (degIn(v) - degOut(v) == 1)
				if (hasSpecialDegIn)
					return false;
				else
					hasSpecialDegIn = true;
			if (degOut(v) - degIn(v) == 1)
				if (hasSpecialDegOut)
					return false;
				else
					hasSpecialDegOut = true;
		}
		return true;
	}

	@Override
	public AGraph copy() {
		DirectedGraph g = new DirectedGraph(maxVertex);
//		g.adjMatrix = new byte[maxVertex][maxVertex];
//		for (int i = 0; i < maxVertex; i++)
//			for (int j = 0; j < maxVertex; j++)
//				g.adjMatrix[i][j] = this.adjMatrix[i][j];
		g.importAdjMatrix(this.adjMatrix);
		return g;
	}

	public LinkedList<Integer> getEulerCircuit() {
		if (!isEulerGraph())
			return null;

		LinkedList<Integer> circuit = new LinkedList<>();
		LinkedList<Integer> subCircuit = new LinkedList<>();
		DirectedGraph graph2 = (DirectedGraph) copy();
		int i = 0;
		circuit.add(0);
		while (graph2.numEdges() > 0) {
			for (int v = 0; v < graph2.maxVertex; v++) {
				if (graph2.degOut(v) > 0 && circuit.indexOf(v) >= 0) {
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
		DirectedGraph a = (DirectedGraph) copy();

		int vertex = getVertexOutMoreThanIn();
		if (vertex == -1)
			return path;
		path.add(vertex);
//		System.out.print("from " + vertex + " : ");
		while (a.numEdges() > 0) {
			for (int i = 0; i < a.maxVertex; i++) {
				if (a.adjMatrix[vertex][i] > 0) {
					System.out.print(vertex + " -> " + i + " || ");
					path.add(i);
					a.removeEdge(vertex, i);
					vertex = i;
					break;
				}
			}
		}
		return path;
	}

	private int getVertexOutMoreThanIn() {
		for (int v = 0; v < this.maxVertex; v++) {
			if (degOut(v) - degIn(v) == 1)
				return v;
		}
		return -1;
	}

	protected LinkedList<Integer> getSubCircuit(int vertexStart) {
		LinkedList<Integer> result = new LinkedList<>();
		int i = vertexStart;
		result.add(vertexStart);
		while (degOut(i) > 0) {
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
			} else
				result.add(original.get(i));
		}
		return result;
	}

}
