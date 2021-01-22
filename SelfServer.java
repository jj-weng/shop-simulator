/**
 * This class creates SelfServer
 * that extends from parent class of Servers
 * @author Weng Jia Jun A0199829B
 */

package cs2030.simulator;

import java.util.List;

public class SelfServer extends Server {

    public SelfServer(int identifier, boolean isAvailable, boolean hasWaitingCustomer,
        double nextAvailableTime, List<Customer> customerList, Simulator simulate, boolean inRest) {
        super(identifier, isAvailable, hasWaitingCustomer,
            nextAvailableTime, customerList, simulate, inRest);
    } 
}
