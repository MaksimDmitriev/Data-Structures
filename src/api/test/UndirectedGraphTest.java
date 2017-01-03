package api.test;

import org.junit.Assert;
import org.junit.Test;

import api.UndirectedGraph;

public class UndirectedGraphTest {

	@Test
	public void addEdgeNoEdges() {
		UndirectedGraph<Integer> graph = new UndirectedGraph<>();
		Assert.assertTrue(graph.addEdge(1, 2));
	}

	@Test
	public void addEdgeNotAdjacent() {
		UndirectedGraph<Integer> graph = new UndirectedGraph<>();
		graph.addEdge(1, 3);
		graph.addEdge(2, 4);
		Assert.assertTrue(graph.addEdge(1, 2));
	}

	@Test
	public void addEdgeWithNewWeight() {
		UndirectedGraph<Integer> graph = new UndirectedGraph<>();
		graph.addEdge(1, 3);
		Assert.assertTrue(graph.addEdge(1, 3, 2.0));
		Assert.assertEquals(0, Double.compare(2.0, graph.getEdgeWeight(1, 3)));
		Assert.assertEquals(0, Double.compare(2.0, graph.getEdgeWeight(3, 1)));
	}

	@Test
	public void addEdgeAlreadyConnected() {
		UndirectedGraph<Integer> graph = new UndirectedGraph<>();
		graph.addEdge(1, 3);
		Assert.assertFalse(graph.addEdge(1, 3));
	}

}
