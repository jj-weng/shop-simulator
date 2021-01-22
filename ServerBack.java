/**
 * This class creates a Server Back event which extends from
 * parent class Event
 * @author Weng Jia Jun A0199829B
 */

package cs2030.simulator;

public class ServerBack extends Event {

    /**
     * Constructor for ServerBack object.
     * 
     * @param customer - The current customer
     * @param time -  The current time
     * @param server - The current server
     */
    public ServerBack(Customer customer, double time, Server server) {
        super(customer, server, time, x -> {                      
            Server temp = new HumanServer(server.getIdentifier(), server.isAvailable(),
                server.hasWaitingCustomer(), time, server.getCustomerList(),
                server.getSimulator(), false);
            if (server.getCustomerList().size() > 0) { 
                return new Pair<Shop,Event>(x.replace(temp),
                    new ServeEvent(temp.getCustomerList().get(0), server, time));
            } else {
                return new Pair<Shop,Event>(x.replace(temp),null);
            }
        });    
    }
}
