package pa_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * @author Caining Wang
 * 
 *         A wrapper class for RBTree
 */

public class Intervals {

	// counter used to set ID for each interval
	private static final AtomicInteger count = new AtomicInteger(0);

	// consecutive ID for each interval
	private int ID = 0; // If deletion is done, this could be used to keep track of edpoints for the
						// same interval.

	// left endpoint
	private Node left;

	// right endpoint
	private Node right;

	// the red-black tree used
	private RBTree tree;

	// collection of intervals
	private HashMap<Integer, Intervals> intvs = new HashMap<Integer, Intervals>();

	/**
	 * Constructor with no parameters.
	 */
	public Intervals() {
		tree = new RBTree();
	}

	/**
	 * 
	 * Adds the interval with left endpoint a and right endpoint b to the collection
	 * of intervals. Each newly inserted interval must be assigned an ID. The IDs
	 * should be consecutive; that is, the ID of the interval inserted on the ith
	 * call of this method should be i. For example if intervalInsert is called
	 * successively to insert intervals [5,7],[4,9],[1,8], then the IDs of these
	 * intervals should be 1,2,3, respectively.These IDs are permanent for the
	 * respective intervals. Keep track of the IDs, as multiple intervals that have
	 * the same endpoints on both sides can be added. intervalInsertshould run in
	 * O(logn)time
	 * 
	 * @param a
	 * @param b
	 */
	public void intervalInsert(int a, int b) {
		Intervals temp = new Intervals();
		temp.setLeft(a);
		temp.setRight(b);
		temp.setID(count.incrementAndGet());
		intvs.put(temp.getID(), temp);

		tree.insertNode(temp.getLeft());
		tree.insertNode(temp.getRight());

	}

	/**
	 * To delete an interval from delete.
	 * 
	 * 
	 * Deletes the interval whose ID (gener-ated byintervalInsert) isintervalID.
	 * Returnstrueif deletion was successful. This method should run in
	 * O(logn)time.Note.TheintervalDeletemethod isoptional; that is, you are not
	 * requiredto implement it. However, your codemustprovide
	 * anintervalDeletemethodeven if you choose not to implement interval deletion.
	 * If you do not implementdeletion, theintervalDeletemethod should consist of
	 * just one line that returnsfalse.
	 * 
	 * @param intervalID
	 * @return
	 */
	public boolean intervalDelete(int intervalID) {
		Intervals temp = intvs.get(intervalID);

		if(intvs.remove(intervalID) != null) {
			tree.RBDelete(temp.getLeft());
			tree.RBDelete(temp.getRight());
			return true;
		}
		return false;
	}

	/**
	 * Finds the endpoint that has maximum overlap and returns its value. This
	 * method should run in constant time.
	 * 
	 * @return
	 */
	public int findPOM() {

		return tree.getRoot().getEmax().getValue();

	}

	/**
	 * Returns the red-black tree used, which is an object of typeRBTree.
	 * 
	 * @return
	 */
	public RBTree getRBTree() {
		return tree;
	}

	// Add more functions as you see fit.
	/**
	 * Set the left endpoint for a interval
	 * 
	 * @param endpoint
	 */
	public void setLeft(int endpoint) {
		left = new Node(new Endpoint(endpoint), true);
	}

	/**
	 * Get the the interval's left endpoint
	 * 
	 * @return
	 */
	public Node getLeft() {
		return left;
	}

	/**
	 * Set the right endpoint for a interval
	 * 
	 * @param endpoint
	 */
	public void setRight(int endpoint) {
		right = new Node(new Endpoint(endpoint), false);
	}

	/**
	 * Get the the interval's right endpoint
	 * 
	 * @return
	 */
	public Node getRight() {
		return right;
	}

	/**
	 * Set an ID for a interval
	 * 
	 * @param id
	 */
	public void setID(int id) {
		ID = id;
	}

	/**
	 * Get the the interval's ID
	 * 
	 * @return
	 */
	public int getID() {
		return ID;
	}


}
