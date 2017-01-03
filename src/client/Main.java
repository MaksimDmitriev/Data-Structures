package client;

import api.DirectedGraph;

public class Main {

	public static void main(String[] args) {
		DirectedGraph<Integer> directedGraph = new DirectedGraph<>();
		directedGraph.addEdge(1, 2);
		directedGraph.addEdge(2, 3);
		directedGraph.addEdge(2, 4);
		directedGraph.addEdge(4, 1);
		
		
		System.out.println(directedGraph.dfs());
	}

}