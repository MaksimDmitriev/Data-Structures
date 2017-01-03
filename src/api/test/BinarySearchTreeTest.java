package api.test;
import org.junit.Assert;
import org.junit.Test;

import api.BinarySearchTree;

public class BinarySearchTreeTest {

	@Test
	public void isCompleteEmpty() {
		BinarySearchTree binarySearchTree = new BinarySearchTree();
		binarySearchTree.insert(12);
		Assert.assertTrue(binarySearchTree.isComplete());
	}

	@Test
	public void isRootAndLeftEmpty() {
		BinarySearchTree binarySearchTree = new BinarySearchTree();
		binarySearchTree.insertAll(12, 4);
		Assert.assertTrue(binarySearchTree.isComplete());
	}

	@Test
	public void isRootLeftAndRightEmpty() {
		BinarySearchTree binarySearchTree = new BinarySearchTree();
		binarySearchTree.insertAll(12, 7, 78);
		Assert.assertTrue(binarySearchTree.isComplete());
	}

	@Test
	public void isTreeLevelsComplete() {
		BinarySearchTree binarySearchTree = new BinarySearchTree();
		binarySearchTree.insertAll(12, 7, 78, 1, 2, 45, 89);
		Assert.assertTrue(binarySearchTree.isComplete());
	}

	@Test
	public void isTreeLevelsNoLastRightComplete() {
		BinarySearchTree binarySearchTree = new BinarySearchTree();
		binarySearchTree.insertAll(12, 7, 78, 1, 2, 45);
		Assert.assertTrue(binarySearchTree.isComplete());
	}

}
