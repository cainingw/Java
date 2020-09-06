package pa_1;

import java.util.ArrayList;
import java.util.List;
import pa_1.Node;

/**
 * @author Caining Wang
 * 
 *         maintains operations on RBTree.
 */
public class RBTree {

	private Node root;
	private Node nil;
	private int size;
	private int height;
	private final static int RED = 0;
	private final static int BLACK = 1;

	/**
	 * RB Tree constructor. It initializes nil node as well.
	 */
	public RBTree() {
		nil = new Node(new Endpoint(-1), true);
		nil.setisNil(true);
		nil.color = BLACK;
		root = nil;
		size = 0;
		height = 0;
	}

	/**
	 * Returns the root of the tree.
	 * 
	 * @return
	 */
	public Node getRoot() {
		return root;
	}

	/**
	 * Returns reference for the nil node, for the rbTree.
	 * 
	 * @return
	 */
	public Node getNILNode() {
		return nil;
	}

	/**
	 * Returns the number of internal nodes in the tree.
	 * 
	 * @return
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Returns the height of the tree.
	 * 
	 * @return
	 */
	public int getHeight() {
		return (int) (Math.log(size + 1) / Math.log(2.0)) * 2;
	}

	public void insertNode(Node z) {
		size++;
		if (root == nil) {
			z.left = nil;
			z.right = nil;
			z.color = BLACK;
			root = z;

		} else {
			Node y = nil;
			Node x = root;
			while (x != nil) {
				y = x;
				if (z.key < x.key) {
					x = x.left;
				} else {
					x = x.right;
				}
			}
			z.parent = y;
			if (y == nil) {
				root = z;
			} else if (z.key < y.key) {
				y.left = z;
			} else {
				y.right = z;
			}
			z.left = nil;
			z.right = nil;
			z.color = RED;
			RBInsertFixup(z);
		}

	}

	public void RBInsertFixup(Node z) {
		Node y;

		while (z.parent.color == RED) {
			if (z.parent == z.parent.parent.left) {
				y = z.parent.parent.right;
				if (y.color == RED) {
					z.parent.color = BLACK;
					y.color = BLACK;
					z.parent.parent.color = RED;
					z = z.parent.parent;
				} else {
					if (z == z.parent.right) {
						z = z.parent;
						LeftRotate(z);
					}
					z.parent.color = BLACK;
					z.parent.parent.color = RED;
					RightRotate(z.parent.parent);
				}
			} else {
				if (z.parent == z.parent.parent.right) {
					y = z.parent.parent.left;
					if (y.color == RED) {
						z.parent.color = BLACK;
						y.color = BLACK;
						z.parent.parent.color = RED;
						z = z.parent.parent;
					} else {
						if (z == z.parent.left) {
							z = z.parent;
							RightRotate(z);
						}
						z.parent.color = BLACK;
						z.parent.parent.color = RED;
						LeftRotate(z.parent.parent);
					}
				}
			}
			if (z == root) {
				break;
			}
		}
		root.color = BLACK;

	}

	public void LeftRotate(Node x) {
		Node y;
		y = x.right;
		x.right = y.left;
		if (y.left != null) {
			y.left.parent = x;
		}
		y.parent = x.parent;
		if (x.parent == null) {
			root = y;
		}

		else if (x == x.parent.left) {
			x.parent.left = y;
		} else {
			x.parent.right = y;
		}
		y.left = x;
		x.parent = y;
	}

	public void RightRotate(Node x) {
		Node y;
		y = x.left;
		x.left = y.right;
		if (y.right != null) {
			y.right.parent = x;
		}
		y.parent = x.parent;
		if (x.parent == null) {
			root = y;
		}

		else if (x == x.parent.right) {
			x.parent.right = y;
		}

		else {
			x.parent.left = y;
		}

		y.right = x;
		x.parent = y;
	}

	public void RBTransplant(Node u, Node v) {
		if (u.parent == nil || u.parent == null) {
			root = v;
		}else if (u == u.parent.left) {
			u.parent.left = v;
		} else {
			u.parent.right = v;
		}

		v.parent = u.parent;
	}

	void RBDelete(Node z) {
		
		if(size == 1) {
			root = nil;
			size--;
		}else {
			
			Node x;
			Node y = z;
			int yOrigColor = y.color;
			if (z.left == nil) {
				x = z.right;
				RBTransplant(z, z.right);
			}

			else if (z.right == nil) {
				x = z.left;
				RBTransplant(z, z.left);
			} else {
				y = Minimum(z.right);
				yOrigColor = y.color;
				x = y.right;
				if (y.parent == z) {
					x.parent = y;
				} else {
					RBTransplant(y, y.right);
					y.right = z.right;
					y.right.parent = y;
				}
				RBTransplant(z, y);
				y.left = z.left;
				y.left.parent = y;
				y.color = z.color;
			}
			if (yOrigColor == BLACK) {
				RBDeleteFixup(x);
			}
			size--;
		}
		
	}

	void RBDeleteFixup(Node x) {
		Node w;
		while (x != root && x.color == BLACK) {
			if (x == x.parent.left) {
				w = x.parent.right;
				if (w.color == RED) {
					w.color = BLACK;
					x.parent.color = RED;
					LeftRotate(x.parent);
					w = x.parent.right;
				}
				if (w.left.color == BLACK && w.right.color == BLACK) {
					w.color = RED;
					x = x.parent;
				} else {
					if (w.right.color == BLACK) {
						w.left.color = BLACK;
						w.color = RED;
						RightRotate(w);
						w = x.parent.right;
					}
					w.color = x.parent.color;
					x.parent.color = BLACK;
					w.right.color = BLACK;
					LeftRotate(x.parent);
					x = root;
				}
			} else {
				w = x.parent.left;
				if (w.color == RED) {
					w.color = BLACK;
					x.parent.color = RED;
					LeftRotate(x.parent);
					w = x.parent.left;
				}
				if (w.right.color == BLACK && w.left.color == BLACK) {
					w.color = RED;
					x = x.parent;
				} else {
					if (w.left.color == BLACK) {
						w.right.color = BLACK;
						w.color = RED;
						RightRotate(w);
						w = x.parent.left;
					}
					w.color = x.parent.color;
					x.parent.color = BLACK;
					w.left.color = BLACK;
					LeftRotate(x.parent);
					x = root;
				}
			}

		}
	}

	Node Minimum(Node n) {
		return n;
	}

}
