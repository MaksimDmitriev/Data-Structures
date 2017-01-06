package api;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class DirectedGraph<T> extends AbstractGraph<T> {
	
	public DirectedGraph() {}
	
	public DirectedGraph(DirectedGraph<T> graph) {
		super(graph);
	}

	public final Set<T> inDegreeOf(T nodeId) {
		if (hasNode(nodeId)) {
			Set<T> inDegree = new HashSet<>();
			Iterator<T> fromIter = graphData.keySet().iterator();
			while (fromIter.hasNext()) {
				T from = fromIter.next();
				if (graphData.get(from).containsKey(nodeId)) {
					inDegree.add(from);
				}
			}
			return inDegree;
		} else {
			return null;
		}
	}

	@Override
	void addOppositeEdge(T to, T from, double weight) {
	}

	@Override
	void removeOppositeEdge(T to, T from) {
	}

	public final Set<T> outDegreeOf(T nodeId) {
		if (graphData.containsKey(nodeId)) {
			return graphData.get(nodeId).keySet();
		} else {
			return null;
		}
	}
	
	@Override
	boolean isSource(T node, T source) {
		return false;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof DirectedGraph)) {
			return false;
		}
		DirectedGraph<?> other = (DirectedGraph<?>) obj;
		return graphData.equals(other.graphData);
	}
	
	@Override
	public int hashCode() {
		return graphData.hashCode();
	}

}