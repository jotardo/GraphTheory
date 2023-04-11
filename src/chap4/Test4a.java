package chap4;

import graph.AGraph;
import graph.DirectedGraph;
import graph.RootedTree;
import graph.Tree;
import graph.UndirectedGraph;

public class Test4a {
	public static void main(String[] args) {
		Tree g = new Tree(8);
		g.addEdge(0, 5);
		g.addEdge(1, 5);
		g.addEdge(1, 6);
		g.addEdge(2, 5);
		g.addEdge(2, 3);
		g.addEdge(2, 7);
		g.addEdge(4, 5);
		System.out.println(g.isTree());
//		g.printAdjMatrix();
//		g.printEdgeList();
		System.out.println("------------------------------------------");
		RootedTree rt = g.toRootedTree(2);
//		rt.printEdgeList();
		System.out.println("------------------------------------------");
		RootedTree g2 = new RootedTree(8, 5);
		g2.addEdge(5, 0);
		g2.addEdge(5, 1);
		g2.addEdge(5, 2);
		g2.addEdge(5, 4);
		g2.addEdge(2, 3);
		g2.addEdge(2, 7);
		g2.addEdge(1, 6);
		System.out.println("------------------------------------------");
		g2.printEdgeList();
		g2.printAdjMatrix();
		System.out.println("Distance from v5 to v4 = " + g2.routeLength(5, 4));
		System.out.println("Distance from v0 to v2 = " + g2.routeLength(0, 2));
		System.out.println("Distance from v2 to v6 = " + g2.routeLength(2, 6));
		System.out.println("Eccentricity of v4 = " + g2.eccentricity(4));
		System.out.println("Eccentricity of v2 = " + g2.eccentricity(2));
		System.out.println("Center of g = " + g2.getTreeCenter()[0]  + " :: "+ g2.getTreeCenter()[1]);
		System.out.println("Radius of g = " + g2.getTreeRadius());
		Tree treeFromG2 = g2.removeRoot();
		treeFromG2.printEdgeList();
		System.out.println("------------------------------------------");
		UndirectedGraph test = new UndirectedGraph(8);
		test.addEdge(0, 1);
		test.addEdge(0, 3);
		test.addEdge(0, 4);
		test.addEdge(1, 2);
		test.addEdge(1, 3);
		test.addEdge(1, 4);
		test.addEdge(1, 5);
		test.addEdge(2, 5);
		test.addEdge(2, 7);
		test.addEdge(3, 4);
		test.addEdge(3, 4);
		test.addEdge(3, 4);
		test.addEdge(3, 5);
		test.addEdge(3, 7);
		test.addEdge(5, 7);
		test.addEdge(6, 7);
		test.printEdgeList();;
		test.getSpanningTree_BFS().printEdgeList();
		test.getSpanningTree_DFS().printEdgeList();
	}
}
