package api.test;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import api.DirectedGraph;
import api.UndirectedGraph;

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
	
	@Test
	public void hasCycles() {
		DirectedGraph<Integer> directedGraph = new DirectedGraph<>();
		directedGraph.addEdge(1, 2);
		directedGraph.addEdge(2, 3);
		directedGraph.addEdge(3, 1);
		Assert.assertTrue(directedGraph.hasCycles());
	}
	
	@Test
	public void hasNoCycles() {
		DirectedGraph<Integer> directedGraph = new DirectedGraph<>();
		directedGraph.addEdge(1, 2);
		directedGraph.addEdge(2, 3);
		directedGraph.addEdge(1, 3);
		Assert.assertFalse(directedGraph.hasCycles());
	}
	
	@Test
	public void equalsSymmetric() {
		DirectedGraph<Integer> directedGraph = new DirectedGraph<>();
		directedGraph.addEdge(12, 56);
		directedGraph.addEdge(56, 100);
		DirectedGraph<Integer> directedGraph2 = directedGraph;
		Assert.assertTrue(directedGraph.equals(directedGraph2));
	}
	
	@Test
	public void equalsReflecsive() {
		DirectedGraph<Integer> directedGraph = new DirectedGraph<>();
		directedGraph.addEdge(12, 56);
		directedGraph.addEdge(56, 100);
		Assert.assertTrue(directedGraph.equals(directedGraph));
	}
	
	@Test
	public void equalsTransitive() {
		DirectedGraph<Integer> directedGraph = new DirectedGraph<>();
		directedGraph.addEdge(12, 56);
		directedGraph.addEdge(56, 100);
		
		DirectedGraph<Integer> directedGraph2 = new DirectedGraph<>();
		directedGraph2.addEdge(12, 56);
		directedGraph2.addEdge(56, 100);
		
		DirectedGraph<Integer> directedGraph3 = new DirectedGraph<>();
		directedGraph3.addEdge(12, 56);
		directedGraph3.addEdge(56, 100);
		
		Assert.assertTrue(directedGraph.equals(directedGraph2));
		Assert.assertTrue(directedGraph2.equals(directedGraph3));
		Assert.assertTrue(directedGraph.equals(directedGraph3));
	}
	
	@Test
	public void equalsOtherWrongType() {
		DirectedGraph<Integer> directedGraph = new DirectedGraph<>();
		directedGraph.addEdge(12, 56);
		directedGraph.addEdge(56, 100);
		
		UndirectedGraph<Integer> undirectedGraph = new UndirectedGraph<>();
		undirectedGraph.addEdge(12, 56);
		undirectedGraph.addEdge(56, 100);
		Assert.assertFalse(directedGraph.equals(undirectedGraph));
	}
	
	@Test
	public void equalsNotEqualGraphs() {
		DirectedGraph<Integer> directedGraph = new DirectedGraph<>();
		directedGraph.addEdge(12, 56);
		directedGraph.addEdge(56, 100);
		
		DirectedGraph<Integer> directedGraph2 = new DirectedGraph<>();
		directedGraph2.addEdge(12, 57);
		directedGraph2.addEdge(56, 100);
		
		Assert.assertFalse(directedGraph.equals(directedGraph2));
	}

}
