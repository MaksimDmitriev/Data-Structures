package api.test;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import api.DirectedGraph;

public class DirectedGraphTest {

	@Test
	public void inDegree() {
		DirectedGraph<Integer> directedGraph = new DirectedGraph<>();
		directedGraph.addEdge(1, 2);
		directedGraph.addEdge(2, 4);
		directedGraph.addEdge(3, 4);
		directedGraph.addEdge(5, 4);
		directedGraph.addEdge(6, 4);
		
		Set<Integer> expected = new HashSet<>();
		expected.add(2);
		expected.add(3);
		expected.add(5);
		expected.add(6);
		
		Assert.assertEquals(expected, directedGraph.inDegreeOf(4));
	}

	@Test
	public void outDegree() {
		DirectedGraph<Integer> directedGraph = new DirectedGraph<>();
		directedGraph.addEdge(1, 2);
		directedGraph.addEdge(2, 4);
		directedGraph.addEdge(3, 4);
		directedGraph.addEdge(5, 4);
		directedGraph.addEdge(6, 4);
		directedGraph.addEdge(2, 8);
		directedGraph.addEdge(2, 7);
		directedGraph.addEdge(2, 10);
		
		Set<Integer> expected = new HashSet<>();
		expected.add(4);
		expected.add(7);
		expected.add(8);
		expected.add(10);

		Assert.assertEquals(expected, directedGraph.outDegreeOf(2));
	}

}
