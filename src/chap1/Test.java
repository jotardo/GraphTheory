package chap1;

import graph.AGraph;
import graph.UndirectedGraph;

public class Test {
	public static void main(String[] args) {
		AGraph a = new UndirectedGraph(5);
		System.out.println("(a=0,b=1,c=2,d=3,e=4)");
		a.addEdge(0, 1);
		a.addEdge(0, 4);
		a.addEdge(1, 2);
		a.addEdge(1, 3);
		a.addEdge(1, 4);
		a.addEdge(2, 3);
		a.addEdge(2, 4);
		a.addEdge(3, 4);
		/*duplicated*/
		a.addEdge(0, 0);
		a.addEdge(0, 1);
		a.addEdge(0, 1);
		a.addEdge(2, 2);
		a.addEdge(2, 3);
		a.addEdge(2, 3);
		a.printAdjMatrix();
		a.printEdgeList();
		System.out.printf("Bac cua tung dinh : deg(A)=%d\ndeg(B)=%d\ndeg(C)=%d\ndeg(D)=%d\ndeg(E)=%d\n", a.deg(0), a.deg(1), a.deg(2), a.deg(3), a.deg(4));
		System.out.println("So canh : " + a.numEdges());
		System.out.println(a.isSimpleGraph());
		System.out.println(a.numOfConnectedGraph());
	}
}
