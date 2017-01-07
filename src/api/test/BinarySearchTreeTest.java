package api.test;

import org.junit.Assert;
import org.junit.Test;

import api.BinarySearchTree;

public class BinarySearchTreeTest {

	@Test
	public void isCompleteRootOnly() {
		BinarySearchTree binarySearchTree = new BinarySearchTree();
		binarySearchTree.insert(12);
		Assert.assertTrue(binarySearchTree.isComplete());
	}

	@Test
	public void isCompleteRootAndLeft() {
		BinarySearchTree binarySearchTree = new BinarySearchTree();
		binarySearchTree.insertAll(12, 4);
		Assert.assertTrue(binarySearchTree.isComplete());
	}

	@Test
	public void isCompleteRootLeftAndRight() {
		BinarySearchTree binarySearchTree = new BinarySearchTree();
		binarySearchTree.insertAll(12, 7, 78);
		Assert.assertTrue(binarySearchTree.isComplete());
	}

	@Test
	public void isCompleteLastLevelFilled() {
		BinarySearchTree binarySearchTree = new BinarySearchTree();
		binarySearchTree.insertAll(12, 7, 78, 1, 11, 45, 89);
		Assert.assertTrue(binarySearchTree.isComplete());
	}

	@Test
	public void isCompleteLastLevelNotFilledCompletely() {
		BinarySearchTree binarySearchTree = new BinarySearchTree();
		binarySearchTree.insertAll(12, 7, 78, 1, 11, 45);
		Assert.assertTrue(binarySearchTree.isComplete());
	}

	@Test
	public void isNotCompleteLeftAbsent() {
		BinarySearchTree binarySearchTree = new BinarySearchTree();
		binarySearchTree.insertAll(12, 7, 78, 1, 11, 89);
		Assert.assertFalse(binarySearchTree.isComplete());
	}

	@Test
	public void isNotCompleteRightAbsent() {
		BinarySearchTree binarySearchTree = new BinarySearchTree();
		binarySearchTree.insertAll(12, 7, 78, 1, 45, 89);
		Assert.assertFalse(binarySearchTree.isComplete());
	}

	@Test
	public void isCompleteEmpty() {
		BinarySearchTree binarySearchTree = new BinarySearchTree();
		Assert.assertFalse(binarySearchTree.isComplete());
	}

	@Test
	public void isCompleteLinkedListLeft() {
		BinarySearchTree binarySearchTree = new BinarySearchTree();
		binarySearchTree.insertAll(new int[] { 10, 9, 8 });
		Assert.assertFalse(binarySearchTree.isComplete());
	}

	@Test
	public void isCompleteLinkedListRight() {
		BinarySearchTree binarySearchTree = new BinarySearchTree();
		binarySearchTree.insertAll(new int[] { 10, 11 });
		Assert.assertFalse(binarySearchTree.isComplete());
	}

	@Test
	public void isCompleteLastButOneLevelNotFilled() {
		BinarySearchTree binarySearchTree = new BinarySearchTree();
		binarySearchTree.insertAll(new int[] { 10, 6, 15, 5, 8, 13, 4 });
		Assert.assertFalse(binarySearchTree.isComplete());
	}

	@Test
	public void isFullRoot() {
		BinarySearchTree binarySearchTree = new BinarySearchTree();
		binarySearchTree.insert(12);
		Assert.assertTrue(binarySearchTree.isFull());
	}

	@Test
	public void isFullRootAndTwoChildren() {
		BinarySearchTree binarySearchTree = new BinarySearchTree();
		binarySearchTree.insertAll(12, 11, 13);
		Assert.assertTrue(binarySearchTree.isFull());
	}

	@Test
	public void isFull() {
		BinarySearchTree binarySearchTree = new BinarySearchTree();
		binarySearchTree.insertAll(12, 10, 15, 1, 11, 13, 78);
		Assert.assertTrue(binarySearchTree.isFull());
	}

	@Test
	public void isFullRootAndLeft() {
		BinarySearchTree binarySearchTree = new BinarySearchTree();
		binarySearchTree.insertAll(12, 10);
		Assert.assertFalse(binarySearchTree.isFull());
	}

	@Test
	public void isFullRootAndRight() {
		BinarySearchTree binarySearchTree = new BinarySearchTree();
		binarySearchTree.insertAll(12, 13);
		Assert.assertFalse(binarySearchTree.isFull());
	}

	@Test
	public void isFullGapAtLastLevel() {
		BinarySearchTree binarySearchTree = new BinarySearchTree();
		binarySearchTree.insertAll(12, 10, 15, 1, 13, 78);
		Assert.assertFalse(binarySearchTree.isFull());
	}

	@Test
	public void isFullGapAtLastLevel2() {
		BinarySearchTree binarySearchTree = new BinarySearchTree();
		binarySearchTree.insertAll(12, 10, 15, 1, 11, 78);
		Assert.assertFalse(binarySearchTree.isFull());
	}

}
