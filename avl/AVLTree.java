package avl;

import java.util.Arrays;

class AVLTree {
	Node root;

	int height(Node node) {
		if (node == null)
			return 0;
		return node.height;
	}

	// Get maximum of two integers
	int max(int a, int b) {
		return (a > b) ? a : b;
	}

	// Right rotate subtree rooted with y
	public Node rightRotate(Node y) {
		Node x = y.left;
		Node T2 = x.right;
		if (T2 == null) {
			x.right = new Node(y.key);
		} else {
			// Perform rotation
			y.left = T2;
			x.right = y;
			// Update heights
			y.height = max(height(y.left), height(y.right)) + 1;
			x.height = max(height(x.left), height(x.right)) + 1;
			// Return new root
		}
		return x;
	}// Left rotate subtree rooted with x

	public Node leftRotate(Node x) {
		Node y = x.right;
		Node T2 = y.left;
		if (T2 == null) {
			y.left = new Node(x.key);
		} else {
			// Perform rotation
			x.right = T2;
			y.left = x;
			preOrder(y);
			// Update heights
			x.height = max(height(x.left), height(x.right)) + 1;
			y.height = max(height(y.left), height(y.right)) + 1;
			// Return new root
		}
		return y;
	}

	// Get Balance factor of node
	public int getBalance(Node node) {
		if (node == null)
			return 0;
		return height(node.left) - height(node.right);
	}
	
	
	public Node insert(Node node, int key) {
		// Normal BST insertion
		if (node == null) {
			return (new Node(key));
		}
		if (key < node.key) {
			node.left = insert(node.left, key);
			node.height += 1;
		} else if (key > node.key) {
			node.right = insert(node.right, key);
			node.height += 1;
		} else {// Duplicate keys not allowed
			return node;
		}
		//若左边高度高于右边 1 以上，往右旋转
		if (getBalance(node) > 1) {
			node = rightRotate(node);
		} else if (getBalance(node) < -1) {
			node = leftRotate(node);
		} else {
		}
		return node;
	}

	// Preorder traversal of the tree.
	public void preOrder(Node node) {
		if (node != null) {
			System.out.print(node.key + " ");
			preOrder(node.left);
			preOrder(node.right);
		}
	}

	public static void main(String[] args) {
		AVLTree tree = new AVLTree();
		/* Constructing tree */tree.root = tree.insert(tree.root, 8);
		
		tree.root = tree.insert(tree.root, 14);
		tree.root = tree.insert(tree.root, 33);
		tree.root = tree.insert(tree.root, 21);
		tree.root = tree.insert(tree.root, 40);
		tree.root = tree.insert(tree.root, 55);
		tree.root = tree.insert(tree.root, 6);
		tree.root = tree.insert(tree.root, 5);
		System.out.println("Preorder traversal of constructed tree is : ");
		tree.preOrder(tree.root);
	}
}