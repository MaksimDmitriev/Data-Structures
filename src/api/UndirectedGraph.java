package api;

public class UndirectedGraph<T> extends AbstractGraph<T> {
	
	public UndirectedGraph() {}
	
	public UndirectedGraph(UndirectedGraph<T> graph) {
		super(graph);
	}

	@Override
	void addOppositeEdge(T to, T from, double weight) {
		graphData.get(to).put(from, weight);
	}

	@Override
	void removeOppositeEdge(T to, T from) {
		graphData.get(to).remove(from);
	}
	
	@Override
	boolean isSource(T node, T source) {
		return node == source;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof UndirectedGraph)) {
			return false;
		}
		UndirectedGraph<?> other = (UndirectedGraph<?>) obj;
		return graphData.equals(other.graphData);
	}
	
	@Override
	public int hashCode() {
		return graphData.hashCode();
	}
	
}