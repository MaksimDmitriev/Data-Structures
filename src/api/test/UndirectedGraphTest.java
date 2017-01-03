package api.test;

import org.junit.Assert;
import org.junit.Test;

import api.UndirectedGraph;

public class UndirectedGraphTest {

	@Test
	public void addFirstNode() {
		UndirectedGraph<Integer> graph = new UndirectedGraph<>();
		Assert.assertTrue(graph.addNode(1));
	}

	@Test
	public void checkFirstNodeAdded() {
		UndirectedGraph<Integer> graph = new UndirectedGraph<>();
		graph.addNode(1000);
		Assert.assertTrue(graph.hasNode(1000));
	}

	@Test
	public void checkNotExistingNodeAbsent() {
		UndirectedGraph<Integer> graph = new UndirectedGraph<>();
		graph.addNode(1000);
		Assert.assertFalse(graph.hasNode(2000));
	}

	@Test
	public void addNode() {
		UndirectedGraph<Integer> graph = new UndirectedGraph<>();
		graph.addNode(1);
		Assert.assertTrue(graph.addNode(2));
	}

	@Test
	public void addAlreadyAddedNode() {
		UndirectedGraph<Integer> graph = new UndirectedGraph<>();
		graph.addNode(1);
		Assert.assertFalse(graph.addNode(1));
	}

	@Test
	public void addEdgeToEmptyGraph() {
		UndirectedGraph<Integer> graph = new UndirectedGraph<>();
		Assert.assertTrue(graph.addEdge(1, 2));
	}

	@Test
	public void checkEdgeAddedToEmptyGraph() {
		UndirectedGraph<Integer> graph = new UndirectedGraph<>();
		graph.addEdge(1, 2);
		Assert.assertTrue(graph.hasEdge(1, 2));
	}

	@Test
	public void checkOppositeEdgeAddedToEmptyGraph() {
		UndirectedGraph<Integer> graph = new UndirectedGraph<>();
		graph.addEdge(1, 2);
		Assert.assertTrue(graph.hasEdge(2, 1));
	}

	@Test
	public void connectExistingNodes() {
		UndirectedGraph<Integer> graph = new UndirectedGraph<>();
		graph.addNode(1);
		graph.addNode(2);
		Assert.assertTrue(graph.addEdge(1, 2));
	}
	
	@Test
	public void checkExistingNodesConnected() {
		UndirectedGraph<Integer> graph = new UndirectedGraph<>();
		graph.addNode(1);
		graph.addNode(2);
		graph.addEdge(1, 2);
		Assert.assertTrue(graph.hasEdge(1, 2));
	}
	
	@Test
	public void checkExistingOppositeNodesConnected() {
		UndirectedGraph<Integer> graph = new UndirectedGraph<>();
		graph.addNode(1);
		graph.addNode(2);
		graph.addEdge(1, 2);
		Assert.assertTrue(graph.hasEdge(2, 1));
	}

	@Test
	public void updateEdgeWeight() {
		UndirectedGraph<Integer> graph = new UndirectedGraph<>();
		graph.addEdge(1, 3);
		Assert.assertTrue(graph.addEdge(1, 3, 2.0));
	}
	
	@Test
	public void checkWeightUpdated() {
		UndirectedGraph<Integer> graph = new UndirectedGraph<>();
		graph.addEdge(1, 3);
		graph.addEdge(1, 3, 2.0);
		Assert.assertTrue(Double.compare(graph.getEdgeWeight(1, 3), 2.0) == 0);
	}
	
	@Test
	public void checkOppositeWeightUpdated() {
		UndirectedGraph<Integer> graph = new UndirectedGraph<>();
		graph.addEdge(1, 3);
		graph.addEdge(1, 3, 2.0);
		Assert.assertTrue(Double.compare(graph.getEdgeWeight(3, 1), 2.0) == 0);
	}

	@Test
	public void connectAlreadyConnectedNodes() {
		UndirectedGraph<Integer> graph = new UndirectedGraph<>();
		graph.addEdge(1, 3);
		Assert.assertFalse(graph.addEdge(1, 3));
	}

}
