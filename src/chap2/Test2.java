package chap2;

import graph.AGraph;
import graph.DirectedGraph;
import graph.UndirectedGraph;

public class Test2 {
	public static void main(String[] args) {
		undirected1();
		undirected2();
		directed();
		undirectedEuler();
		directedEuler();
	}

	private static void directedEuler() {
		AGraph g = new DirectedGraph(5);
		g.addEdge(0, 2);
		g.addEdge(1, 0);
		g.addEdge(2, 1);
		g.addEdge(2, 4);
		g.addEdge(3, 2);
		g.printAdjMatrix();
		System.out.println("Euler circuit :: " + g.getEulerCircuit());
		System.out.println("Euler path :: " + g.getEulerPath());
	}

	private static void undirectedEuler() {
		AGraph g = new UndirectedGraph(6);
		g.addEdge(0, 1);
		g.addEdge(0, 3);
		g.addEdge(1, 2);
		g.addEdge(1, 3);
		g.addEdge(1, 4);
		g.addEdge(2, 4);
		g.addEdge(3, 4);	//them canh nay thi do thi la do thi euler, bo di thi nua euler
		g.addEdge(3, 5);
		g.addEdge(4, 5);
		System.out.println("Euler circuit = " + g.getEulerCircuit());
		System.out.println("Euler path = " + g.getEulerPath());
		System.out.println("----------------------------------------------");
	}

	private static void directed() {
		AGraph a3 = new DirectedGraph(5);
		a3.addEdge(0, 1);
		a3.addEdge(0, 2);
		a3.addEdge(0, 3);
		a3.addEdge(1, 2);
		a3.addEdge(3, 4);
		a3.printEdgeList();;
		System.out.println(a3.isConnectedGraph());
		System.out.println();
		((DirectedGraph) a3).toUndirectedGraph().printEdgeList();;
		System.out.println("----------------------------------------------");
	}

	private static void undirected1() {
		AGraph a1 = new UndirectedGraph(8);
		a1.addEdge(0, 1);
		a1.addEdge(0, 3);
		a1.addEdge(0, 4);
		a1.addEdge(1, 2);
		a1.addEdge(1, 3);
		a1.addEdge(1, 4);
		a1.addEdge(1, 5);
		a1.addEdge(2, 5);
		a1.addEdge(2, 7);
		a1.addEdge(3, 4);
		a1.addEdge(3, 4);
		a1.addEdge(3, 4);
		a1.addEdge(3, 5);
		a1.addEdge(3, 7);
		a1.addEdge(5, 7);
		a1.addEdge(6, 7);
		a1.printAdjMatrix();
		a1.BFS(5);
		a1.DFS(2);
		System.out.println("is Simple graph : " + a1.isSimpleGraph());
		System.out.println("Is Connected Graph : " + a1.isConnectedGraph());
		System.out.println("Path from 5 - 2 exists : " + a1.hasPath(5, 2));
		System.out.println("----------------------------------------------");
	}

	private static void undirected2() {
		AGraph a2 = new UndirectedGraph(11);
		a2.addEdge(0, 1);
		a2.addEdge(0, 3);
		a2.addEdge(0, 4);
		a2.addEdge(1, 2);
		a2.addEdge(1, 3);
		a2.addEdge(1, 4);
		a2.addEdge(1, 5);
		a2.addEdge(2, 5);
		a2.addEdge(2, 7);
		a2.addEdge(3, 4);
		a2.addEdge(3, 4);
		a2.addEdge(3, 4);
		a2.addEdge(3, 5);
		a2.addEdge(3, 7);
		a2.addEdge(5, 7);
		a2.addEdge(6, 7);
		a2.addEdge(8, 9);
		a2.addEdge(8, 10);
		a2.addEdge(9, 10);
		a2.printAdjMatrix();
		a2.BFS(5);
		a2.DFS(8);
		System.out.println("is Simple graph : " + a2.isSimpleGraph());
		System.out.println("Is Connected Graph : " + a2.isConnectedGraph());
		System.out.println("Path from 2 - 4 exists : " + a2.hasPath(2, 4));
		System.out.println("Path from 5 - 8 exists : " + a2.hasPath(5, 8));
		System.out.println(a2.numOfConnectedGraph());
		System.out.println("----------------------------------------------");
		
	}
}
