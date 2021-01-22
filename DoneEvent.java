/**
 *This class creates a DoneEvent
* that returns a pair with null event and
* either a Human Server or Self Server
* @author Weng Jia Jun A0199829B
*/

package cs2030.simulator;

public class DoneEvent extends Event {

    /**
     * Constructor that creates a DoneEvent.
     * 
     * @param customer - a new customer
     * @param time     - the time when the customer is done
     * @param server   - the server who is done with serving
     */
    DoneEvent(Customer customer, Server server, double time) {
        super(customer, server, time, x -> {
            Server temp = server;
            if (server instanceof HumanServer) {
                temp = new HumanServer(server.getIdentifier(), true, server.hasWaitingCustomer(),
                        time, server.getCustomerList(), server.getSimulator(), false);
            } else if (server instanceof SelfServer) {
                temp = new SelfServer(server.getIdentifier(), true, server.hasWaitingCustomer(),
                        time, server.getCustomerList(), server.getSimulator(), false);
            }
            return new Pair<Shop,Event>(x.replace(temp),null);
        });
    }

    @Override
    public String toString() {
        String temp = "";
        if (this.getCustomer().isGreedy() == true) {
            temp = "(greedy)";
        }
        if (this.getServer().getIdentifier() <= Simulator.numOfServer) {
            return String.format("%.3f", getCurrentTime()) + " " + getCustomer().getId() + 
                    temp + " done serving by server "
                    + getServer().getIdentifier();
        } else {
            return String.format("%.3f", getCurrentTime()) + " " + getCustomer().getId() + 
                    temp + " done serving by self-check "
                    + getServer().getIdentifier();
        }
        
    }

}
