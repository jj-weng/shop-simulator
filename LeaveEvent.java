/**
 * This class creates a LeaveEvent
 * that reurns a pair with null event
 * @author Weng Jia Jun A0199829B
 */

package cs2030.simulator;


public class LeaveEvent extends Event {

    /**
     * Constructor that creates a LeaveEvent.
     * 
     * @param customer - a new customer
     * @param server  - a server
     * @param time     - the time when the customer leaves
     */
    LeaveEvent(Customer customer, Server server, double time) {
        super(customer, server, time, x -> new Pair<Shop, Event>(x, null));
    }

    @Override
    public String toString() {
        String temp = "";
        if (this.getCustomer().isGreedy() == true) {
            temp = "(greedy)";
        }
        return String.format("%.3f", getCurrentTime()) + " " +
            getCustomer().getId() + temp + " leaves";
    }

}
