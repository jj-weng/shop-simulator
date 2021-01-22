/**
 * This class creates HumanServers
 * that extends from parent class of Servers
 * @author Weng Jia Jun A0199829B
 */

package cs2030.simulator;

import java.util.List;

public class HumanServer extends Server {
	/**
	 * Constructor for Human Server.
	 * 
	 * @param identifier         The identifier of the server
	 * @param isAvailable        Whether the server is available
	 * @param hasWaitingCustomer Whether the server has a waiting customer
	 * @param nextAvailableTime  The next available time of the server
	 */
	public HumanServer(int identifier, boolean isAvailable, boolean hasWaitingCustomer, double nextAvailableTime) {
		super(identifier, isAvailable, hasWaitingCustomer, nextAvailableTime);
	}

	/**
	 * Constructor for Human Server.
	 * 
	 * @param identifier         The identifier of the server
	 * @param isAvailable        Whether the server is available
	 * @param hasWaitingCustomer Whether the server has a waiting customer
	 * @param nextAvailableTime  The next available time of the server
	 * @param customerList       The customer list of the server
	 * @param simulate           The simulator object
	 * @param isInRest           Whether the serve is currently resting
	 */
	public HumanServer(int identifier, boolean isAvailable, boolean hasWaitingCustomer, double nextAvailableTime,
			List<Customer> customerList, Simulator simulate, boolean isInRest) {
		super(identifier, isAvailable, hasWaitingCustomer, nextAvailableTime, customerList, simulate, isInRest);

	}
}