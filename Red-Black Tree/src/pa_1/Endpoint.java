package pa_1;

/**
 * @author Caining Wang
 * 
 *         Endpoint class for Node.
 */
public class Endpoint {
	int endpoint;

	public Endpoint(int endpoint) {
		this.endpoint = endpoint;
	}

	/**
	 * returns the endpoint value. For example if the End point object represents
	 * the left end point of the interval [1,3], this would return 1.
	 * 
	 * @return
	 */
	public int getValue() {
		// TODO: Modify it accordingly.
		return endpoint;
	}
}
