package api;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Iterator;
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

	// O(V+E)
	protected final Map<T, Map<T, Double>> graphData;
	private final Map<T, Color> colorMap = new HashMap<>();
	private int edgeCount;
	
	public AbstractGraph() {
		graphData = new HashMap<>();
	}
	
	public AbstractGraph(AbstractGraph<T> graph) {
		graphData = new HashMap<>(graph.graphData);
	}

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
	 * Adds the node with ID {@code nodeId} to this graph. O(1)
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
	public final boolean removeNode(T nodeId) {
		final boolean hasNode = hasNode(nodeId);
		if (hasNode) {
			graphData.remove(nodeId);
			Iterator<T> nodeIterator = graphData.keySet().iterator();
			while (nodeIterator.hasNext()) {
				graphData.get(nodeIterator.next()).remove(nodeId);
			}
		}
		return hasNode;
	}

	/**
	 * @param from
	 * @param to
	 * @param weight
	 *            the weight of the edge.
	 * @return {@code true} only if the edge was not present in the graph, or
	 *         the weight of the edge has changed.
	 */
	public final boolean addEdge(T from, T to, double weight) {
		if (weight == Double.NEGATIVE_INFINITY || Double.isNaN(weight)) {
			throw new IllegalArgumentException("weight must be a number or the positive inifinity");
		}
		ensureCanAddEdge(from);
		ensureCanAddEdge(to);
		Double oldWeight = graphData.get(from).put(to, weight);
		if (oldWeight == null) {
			edgeCount++;
		}
		boolean fromEdgeUpdated = (oldWeight == null || Double.compare(oldWeight, weight) != 0);
		if (fromEdgeUpdated) {
			addOppositeEdge(to, from, weight);
		}
		return fromEdgeUpdated;
	}
	
	final void ensureCanAddEdge(T node) {
		Map<T, Double> fromEdges = graphData.get(node);
		if (fromEdges == null) {
			graphData.put(node, new HashMap<>());
		}
	}
	
	abstract void addOppositeEdge(T to, T from, double weight);

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
	public final boolean addEdge(T from, T to) {
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
	 * Removes the edge from {@code from} and {@code to}.
	 * 
	 * @param from
	 *            the tail node of the edge to remove.
	 * @param to
	 *            the head node of the edge to remove.
	 * @return {@code true} if the target edge was in this graph, and thus is
	 *         removed.
	 */
	public final boolean removeEdge(T from, T to) {
		if (hasEdge(from, to)) {
			graphData.get(from).remove(to);
			removeOppositeEdge(to, from);
			return true;
		} else {
			return false;
		}
	}
	
	abstract void removeOppositeEdge(T to, T from);

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
				// Not visited yet
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

	private boolean hasCyclesInternal(T current, T source) {
		colorMap.put(current, Color.GREY);
		Map<T, Double> adjMap = graphData.get(current);
		if (adjMap == null) {
			colorMap.put(current, Color.BLACK);
			return false;
		} else {
			Set<T> adjNodes = adjMap.keySet();
			for (T adj : adjNodes) {
				if (!colorMap.containsKey(adj)) {
					if (hasCyclesInternal(adj, current)) {
						return true;
					}
				} else if (!isSource(adj, source) && colorMap.get(adj) == Color.GREY) {
					return true;
				}
			}
			colorMap.put(current, Color.BLACK);
		}
		return false;
	}
	
	abstract boolean isSource(T adj, T source);

	public final boolean hasCycles() {
		if (graphData.isEmpty()) {
			return false;
		} else {
			try {
				return hasCyclesInternal(graphData.keySet().iterator().next(), null);
			} finally {
				colorMap.clear();
			}
		}
	}
	
	@Override
	public String toString() {
		return graphData.isEmpty() ? "[]" : graphData.toString();
	}

	private enum Color {
		GREY, BLACK
	}
}