/**
 * This class creates a server which has attributes
 * to check for availability and if it has waiting customer.
 * @author Weng Jia Jun A0199829B
 */

package cs2030.simulator;

import java.util.List;
import java.util.ArrayList;

public class Server {
    private final int identifier;
    private final boolean isAvailable;
    private final boolean hasWaitingCustomer;
    private final double nextAvailableTime;
    private final List<Customer> customerList;
    private final Simulator simulate;
    private final boolean inRest;

    /**
     * Constructor that creates a server.
     * 
     * @param identifier         - identity of the server
     * @param isAvailable        - if the server is available
     * @param hasWaitingCustomer - if the server has a waiting customer
     * @param nextAvailableTime  - the next time the server is available
     */
    public Server(int identifier, boolean isAvailable, boolean hasWaitingCustomer, 
        double nextAvailableTime) {
        this.identifier = identifier;
        this.isAvailable = isAvailable;
        this.hasWaitingCustomer = hasWaitingCustomer;
        this.nextAvailableTime = nextAvailableTime;
        this.customerList = new ArrayList<Customer>();
        this.simulate = null;
        this.inRest = false;
    }

    /**
     * Constructor that creates a server.
     * 
     * @param identifier         - identity of the server
     * @param isAvailable        - if the server is available
     * @param hasWaitingCustomer - if the server has a waiting customer
     * @param nextAvailableTime  - the next time the server is available
     * @param customerList       - the list containing the customers
     * @param simulate           - the simulator object
     * @param inRest             - whether the server is resting
     * 
     */
    public Server(int identifier, boolean isAvailable, boolean hasWaitingCustomer,
        double nextAvailableTime, List<Customer> customerList, Simulator simulate, boolean inRest) {
        this.identifier = identifier;
        this.isAvailable = isAvailable;
        this.hasWaitingCustomer = hasWaitingCustomer;
        this.nextAvailableTime = nextAvailableTime;
        this.customerList = customerList;
        this.simulate = simulate;
        this.inRest = inRest;
    }

    public int getIdentifier() {
        return identifier;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public boolean hasWaitingCustomer() {
        return hasWaitingCustomer;
    }

    public double getNextAvailableTime() {
        return nextAvailableTime;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public Simulator getSimulator() {
        return simulate;
    }

    public boolean isInRest() {
        return inRest;
    }

    @Override
    public String toString() {
        if (this.isAvailable()) {
            return this.getIdentifier() + " is available";
        } else {
            if (this.hasWaitingCustomer()) {
                return this.getIdentifier() + " is busy; waiting customer to be served at "
                        + String.format("%.3f", this.getNextAvailableTime());
            } else {
                return this.getIdentifier() + " is busy; available at "
                        + String.format("%.3f", this.getNextAvailableTime());
            }
        }
    }

}
