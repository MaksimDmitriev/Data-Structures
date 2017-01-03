package api;

public class AVLTree {

	private Node root;

	public AVLTree(int... keys) {
		addAll(keys);
	}

	public void addAll(int... keys) {
		for (int key : keys) {
			add(key);
		}
	}

	public void add(int key) {
		if (root == null) {
			root = new Node(key);
		} else {
			insert(root, key);
		}
	}

	private int bfactor(Node p) {
		return height(p.right) - height(p.left);
	}

	private Node balance(Node p) {
		fixHeight(p);
		int bfactor = bfactor(p);
		if (bfactor == 2) {
			if (bfactor(p.right) < 0) {
				p.right = rotateright(p.right);
			}
			return rotateleft(p);
		} else if (bfactor == -2) {
			if (bfactor(p.left) > 0)
				p.left = rotateleft(p.left);
			return rotateright(p);
		}
		return p;
	}

	private Node rotateright(Node node) {
		Node left = node.left;
		Node leftRight = left.right;

		left.right = node;
		node.left = leftRight;
		fixHeight(node);
		fixHeight(left);
		return left;
	}

	private Node rotateleft(Node node) {
		Node right = node.right;
		Node rightLeft = right.left;

		right.left = node;
		node.right = rightLeft;
		fixHeight(node);
		fixHeight(right);
		return right;
	}

	private void fixHeight(Node node) {
		int hLeft = height(node.left);
		int hRight = height(node.right);
		node.height = Math.max(hLeft, hRight) + 1;
	}

	private int height(Node node) {
		return node == null ? -1 : node.height;
	}

	private Node insert(Node current, int key) {
		if (current != null) {
			if (key < current.key) {
				current.left = insert(current.left, key);
			} else {
				current.right = insert(current.right, key);
			}
			return balance(current);
		}
		return new Node(key);
	}

	private static class Node {

		int key;
		Node left;
		Node right;
		int height;

		public Node(int key) {
			this.key = key;
		}

		@Override
		public String toString() {
			return Integer.toString(key);
		}
	}

}
