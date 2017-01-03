package api;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DirectedGraph<T> extends AbstractGraph<T> {

	@Override
	public boolean removeNode(T nodeId, boolean clear) {
		if (!hasNode(nodeId)) {
			return false;
		}
		boolean result = false;
		if (clear) {
			result = (graphData.put(nodeId, null) != null);
		} else {
			result = true;
			// No need to set result as remove(nodeId) != null. It's always true
			graphData.remove(nodeId);
		}
		Iterator<T> edgesIter = graphData.keySet().iterator();
		while (edgesIter.hasNext()) {
			result |= (graphData.get(edgesIter.next()).remove(nodeId) != null);
		}
		return result;
	}

	@Override
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
		return fromEdgeUpdated;
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
	
	public final Set<T> outDegreeOf(T nodeId) {
		if (graphData.containsKey(nodeId)) {
			return graphData.get(nodeId).keySet();
		} else {
			return null;
		}
	}

}