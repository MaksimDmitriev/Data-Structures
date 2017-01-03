package api;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * The implementation is partially based on
 * http://codereview.stackexchange.com/q/116686/23821
 * 
 * @author Maksim
 *
 * @param <T>
 */
public abstract class AbstractGraph<T> {
	
	protected Map<T, Map<T, Double>> graphData = new HashMap<>();
	private Map<T, Color> colorMap = new HashMap<>();
	private int edgeCount;

	/**
	 * Returns the number of nodes in this graph.
	 * 
	 * @return the size of this graph.
	 */
	public final int getNodeCount() {
		return graphData.size();
	}

	/**
	 * Returns the number of edges in this graph.
	 * 
	 * @return the number of edges.
	 */
	public final int getEdgeCount() {
		return edgeCount;
	}

	/**
	 * Adds the node with ID {@code nodeId} to this graph.
	 * 
	 * @param nodeId
	 *            the ID of the node to add.
	 * @return {@code true} if the structure of this graph has changed, which is
	 *         the same as that the added node was not present in the graph.
	 */
	public final boolean addNode(T nodeId) {
		if (hasNode(nodeId)) {
			return false;
		} else {
			graphData.put(nodeId, null);
			return true;
		}
	}

	/**
	 * Checks whether the given node is present in this graph.
	 * 
	 * @param nodeId
	 *            the query node.
	 * @return {@code true} if the query node is in this graph. {@code false}
	 *         otherwise.
	 */
	public final boolean hasNode(T nodeId) {
		return graphData.containsKey(nodeId);
	}

	/**
	 * Removes the argument node from this graph.
	 * 
	 * @param nodeId
	 *            the node to remove.
	 * @return {@code true} only if the node was present in the graph which
	 *         means that the structure of the graph has changed.
	 */
	public abstract boolean removeNode(T nodeId, boolean clear);

	/**
	 * Creates an edge between {@code tailNodeId} and {@code headNodeId} with
	 * weight {@code weight}. It depends on the concrete implementation of this
	 * abstract class what an edge {@code (tailNodeId, headNodeId)}. Two
	 * possible cases are an undirected edge and a directed edge. Refer to the
	 * documentation of the implementing graph type.
	 * <p>
	 * If some of the input nodes are not present in this graph, it will be
	 * created silently.
	 * 
	 * @param from
	 * @param to
	 * @param weight
	 *            the weight of the edge.
	 * @return {@code true} only if the edge was not present in the graph, or
	 *         the weight of the edge has changed.
	 */
	// TODO: must be abstract
	public boolean addEdge(T from, T to, double weight) {
		addNode(from);
		addNode(to);
		
		Map<T, Double> fromEdges = graphData.get(from);
		final boolean fromHadNoEdges = (fromEdges == null);
		if (fromHadNoEdges) {
			fromEdges = new HashMap<>();
		}

		Double oldWeight = fromEdges.put(to, weight);
		boolean fromEdgeUpdated = (oldWeight == null || Double.compare(oldWeight, weight) != 0);
		if (fromHadNoEdges) {
			graphData.put(from, fromEdges);
		}
		if (fromEdgeUpdated) {
			edgeCount++;
		}
		return fromEdgeUpdated;
	}

	/**
	 * Creates an edge between {@code tailNodeId} and {@code headNodeId} with
	 * the default weight of 1.0. This method is a shortcut for constructing
	 * (virtually) unweighted graphs.
	 * 
	 * @param tailNodeId
	 *            the tail node of the edge.
	 * @param headNodeId
	 *            the head node of the edge.
	 * @return {@code true} only if the edge was not present in the graph, or
	 *         the weight of the edge has changed.
	 */
	public boolean addEdge(T from, T to) {
		return addEdge(from, to, 1.0);
	}

	/**
	 * Returns the weight of the edge {@code (tailNodeId, headNodeId)}. If the
	 * query edge does not exist, returns {@link java.lang.Double#NaN}.
	 * 
	 * @param from
	 * @param to
	 * @return the weight of the edge.
	 */
	public final double getEdgeWeight(T from, T to) {
		if (hasEdge(from, to)) {
			return graphData.get(from).get(to);
		} else {
			return Double.NaN;
		}
	}

	/**
	 * Removes the edge from {@code tailNodeId} and {@code headNodeId}.
	 * 
	 * @param from
	 *            the tail node of the edge to remove.
	 * @param to
	 *            the head node of the edge to remove.
	 * @return {@code true} if the target edge was in this graph, and thus is
	 *         removed.
	 */
	// TODO: must be abstract
	public boolean removeEdge(T from, T to) {
		if (hasEdge(from, to)) {
			graphData.get(from).remove(to);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns a boolean value indicating whether this graph contains an edge
	 * from {@code tailNodeId} to {@code headNodeId}.
	 * 
	 * @param tailNodeId
	 *            the tail node of the query edge.
	 * @param headNodeId
	 *            the head node of the query edge.
	 * @return {@code true} only if the query edge is in this graph.
	 */
	public final boolean hasEdge(T from, T to) {
		Map<T, Double> fromAdj = graphData.get(from);
		return fromAdj != null && fromAdj.get(to) != null;
	}

	/**
	 * Removes all nodes and edges from this graph.
	 */
	public final void clear() {
		graphData.clear();
	}

	public final String dfs() {
		if (graphData.isEmpty()) {
			return "[]";
		} else {
			StringBuilder builder = new StringBuilder();
			dfsInternal(graphData.keySet().iterator().next(), builder);
			colorMap.clear();
			return builder.toString();
		}
	}

	private void dfsInternal(T node, StringBuilder builder) {
		colorMap.put(node, Color.GREY);
		builder.append(node + " ");
		Map<T, Double> adj = graphData.get(node);
		if (adj != null) {
			for (T adjNode : adj.keySet()) {
				if (colorMap.get(adjNode) == null) {
					dfsInternal(adjNode, builder);
				}
			}
		}
		colorMap.put(node, Color.BLACK);
	}

	public final String bfs() {
		if (graphData.isEmpty()) {
			return "[]";
		}
		StringBuilder builder = new StringBuilder();
		Queue<T> queue = new ArrayDeque<>();
		queue.add(graphData.keySet().iterator().next());
		while (!queue.isEmpty()) {
			T node = queue.remove();
			builder.append(node + " ");
			colorMap.put(node, Color.GREY);
			Map<T, Double> adjNodes = graphData.get(node);
			if (adjNodes != null) {
				for (T adjNode : adjNodes.keySet()) {
					if (colorMap.get(adjNode) == null) {
						queue.add(adjNode);
					}
				}
			}
			colorMap.put(node, Color.BLACK);
		}
		colorMap.clear();
		return builder.toString();
	}

	private boolean hasCyclesInternal(T node) {
		colorMap.put(node, Color.GREY);
		Map<T, Double> adjMap = graphData.get(node);
		if (adjMap == null) {
			return false;
		} else {
			Set<T> adjNodes = adjMap.keySet();
			for (T adj : adjNodes) {
				if (colorMap.get(adj) == Color.GREY) {
					return true;
				} else {
					return hasCyclesInternal(adj);
				}
			}
		}
		return false;
	}

	public final boolean hasCycles() {
		if (graphData.isEmpty()) {
			return false;
		} else {
			try {
				return hasCyclesInternal(graphData.keySet().iterator().next());
			} finally {
				colorMap.clear();
			}
		}
	}

	private enum Color {
		GREY, BLACK
	}
}