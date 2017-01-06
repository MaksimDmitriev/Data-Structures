package api;

import java.util.ArrayDeque;
import java.util.Queue;

public class BinarySearchTree {

	private Node root;

	public void insert(int key) {
		if (root == null) {
			root = new Node(key);
		} else {
			Node parent = null;
			Node current = root;
			while (current != null) {
				parent = current;
				current = (key < current.key) ? current.left : current.right;
			}
			if (key < parent.key) {
				parent.left = new Node(key);
			} else {
				parent.right = new Node(key);
			}
		}
	}

	public void insertAll(int... keys) {
		for (int key : keys) {
			insert(key);
		}
	}

	private boolean isFull(Node node) {
		if (node == null) {
			return true;
		}
		return !(node.left == null ^ node.right == null) && isFull(node.left) && isFull(node.right);
	}

	public boolean isComplete() {
		boolean result = true;
		if (root == null) {
			result = false;
		} else {
			Queue<Node> nodes = new ArrayDeque<>();
			nodes.add(root);
			boolean hitLeaf = false;
			while (!nodes.isEmpty()) {
				Node node = nodes.remove();
				if (node.left == null) {
					if (node.right == null) {
						hitLeaf = true;
					} else {
						result = false;
						break;
					}
				} else {
					if (hitLeaf) {
						result = false;
						break;
					}
					nodes.add(node.left);
					if (node.right != null) {
						nodes.add(node.right);
					}
				}
			}
		}

		return result;
	}

	public boolean isFull() {
		return isFull(root);
	}

	private static class Node {

		int key;
		Node left;
		Node right;

		Node(int key) {
			this.key = key;
		}

		@Override
		public String toString() {
			return Integer.toString(key);
		}
	}
}
