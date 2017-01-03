package api;

public class UndirectedGraph<T> extends AbstractGraph<T> {

	@Override
	void addOppositeEdge(T to, T from, double weight) {
		ensureEdge(to, from);
		graphData.get(to).put(from, weight);
	}

	@Override
	void removeOppositeEdge(T to, T from) {
		graphData.get(to).remove(from);
	}
}