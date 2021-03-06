package api.test;

import org.junit.Assert;
import org.junit.Test;

import api.UndirectedGraph;

public class UndirectedGraphTest {
	
	private static final double EPSILON = 0.000001;

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
		graph.addEdge(1, 3, 2.0);
		Assert.assertTrue(Double.compare(2.0, graph.getEdgeWeight(1, 3)) == 0);
	}

	@Test
	public void checkWeightUpdated() {
		UndirectedGraph<Integer> graph = new UndirectedGraph<>();
		graph.addEdge(1, 3);
		graph.addEdge(1, 3, 2.0);
		final double expected = 2.0;
		Assert.assertEquals(expected, graph.getEdgeWeight(1, 3), EPSILON);
	}

	@Test
	public void checkOppositeWeightUpdated() {
		UndirectedGraph<Integer> graph = new UndirectedGraph<>();
		graph.addEdge(1, 3);
		graph.addEdge(1, 3, 3.6);
		final double expected = 3.6;
		Assert.assertEquals(expected, graph.getEdgeWeight(1, 3), EPSILON);
	}

	@Test
	public void connectAlreadyConnectedNodes() {
		UndirectedGraph<Integer> graph = new UndirectedGraph<>();
		graph.addEdge(1, 3);
		Assert.assertFalse(graph.addEdge(1, 3));
	}

	@Test
	public void connectOppositeOfAlreadyConnectedNodes() {
		UndirectedGraph<Integer> graph = new UndirectedGraph<>();
		graph.addEdge(1, 3);
		Assert.assertFalse(graph.addEdge(3, 1));
	}

	@Test
	public void hasNoCyclesTwoNodes() {
		UndirectedGraph<Integer> graph = new UndirectedGraph<>();
		graph.addEdge(1, 3);
		Assert.assertFalse(graph.hasCycles());
	}

	@Test
	public void hasSelfLoop() {
		UndirectedGraph<Integer> graph = new UndirectedGraph<>();
		graph.addEdge(1, 1);
		Assert.assertTrue(graph.hasCycles());
	}

	@Test
	public void hasNoLoopsSingleNode() {
		UndirectedGraph<Integer> graph = new UndirectedGraph<>();
		graph.addNode(12);
		Assert.assertFalse(graph.hasCycles());
	}

	@Test
	public void hasSelfLoop2() {
		UndirectedGraph<Integer> graph = new UndirectedGraph<>();
		graph.addEdge(1, 2);
		graph.addEdge(2, 3);
		graph.addEdge(3, 3);
		Assert.assertTrue(graph.hasCycles());
	}

	@Test
	public void hasCycles() {
		UndirectedGraph<Integer> graph = new UndirectedGraph<>();
		graph.addEdge(1, 3);
		graph.addEdge(1, 2);
		graph.addEdge(2, 3);
		Assert.assertTrue(graph.hasCycles());
	}

	@Test(expected = IllegalArgumentException.class)
	public void addNonEdge() {
		UndirectedGraph<Integer> graph = new UndirectedGraph<>();
		graph.addEdge(1, 2, Double.NaN);
	}

	@Test(expected = IllegalArgumentException.class)
	public void addNegativeInfinityEdge() {
		UndirectedGraph<Integer> graph = new UndirectedGraph<>();
		graph.addEdge(1, 2, Double.NEGATIVE_INFINITY);
	}

	@Test
	public void addPositiveInfinityEdge() {
		UndirectedGraph<Integer> graph = new UndirectedGraph<>();
		graph.addEdge(1, 2, Double.POSITIVE_INFINITY);
		Assert.assertTrue(graph.getEdgeWeight(1, 2) == Double.POSITIVE_INFINITY);
	}

	@Test
	public void nanWeightWhenNoEdge() {
		UndirectedGraph<Integer> graph = new UndirectedGraph<>();
		graph.addNode(1);
		graph.addNode(34);
		Assert.assertTrue(Double.isNaN(graph.getEdgeWeight(1, 34)));
	}

	@Test
	public void nanWeightWhenNoNodes() {
		UndirectedGraph<Integer> graph = new UndirectedGraph<>();
		graph.addNode(1);
		graph.addNode(34);
		Assert.assertTrue(Double.isNaN(graph.getEdgeWeight(2, 67)));
	}

	@Test
	public void hasCyclesEmpty() {
		UndirectedGraph<Integer> graph = new UndirectedGraph<>();
		Assert.assertFalse(graph.hasCycles());
	}
	
	@Test
	public void removeEdge() {
		UndirectedGraph<Integer> graph = new UndirectedGraph<>();
		graph.addEdge(1, 2);
		graph.addEdge(1, 3);
		graph.addEdge(2, 5);
		Assert.assertTrue(graph.removeEdge(1, 2));
		Assert.assertFalse(graph.hasEdge(2, 2));
		Assert.assertTrue(graph.hasEdge(1, 3));
		Assert.assertTrue(graph.hasEdge(2, 5));
	}

	@Test
	public void removeNode() {
		UndirectedGraph<Integer> graph = new UndirectedGraph<>();
		graph.addEdge(1, 2);
		graph.addEdge(1, 3);
		graph.addEdge(1, 4);
		graph.addEdge(1, 5);
		graph.addEdge(3, 4);
		graph.addEdge(3, 5);
		graph.addEdge(4, 5);
		graph.addEdge(4, 6);
		graph.addEdge(2, 4);
		graph.addEdge(5, 6);
		Assert.assertTrue(graph.removeNode(3));
		Assert.assertFalse(graph.hasEdge(1, 3));
		Assert.assertFalse(graph.hasEdge(5, 3));
		Assert.assertFalse(graph.hasEdge(4, 3));
	}
}
