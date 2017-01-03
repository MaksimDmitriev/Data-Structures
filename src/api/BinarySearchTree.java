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
		if (root == null) {
			return false;
		}
		Queue<Node> nodes = new ArrayDeque<>();
		nodes.add(root);
		boolean encounteredNull = false;
		while (!nodes.isEmpty()) {
			Node node = nodes.remove();
			if (node.left != null) {
				nodes.add(node.left);
			} else {
				encounteredNull = true;
			}
			if (node.right != null) {
				if (encounteredNull) {
					return false;
				}
				nodes.add(node.right);
			}
		}
		return true;
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
	}
}
