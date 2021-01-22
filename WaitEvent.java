
/**
 *This class creates a waiting event
 *where a customer is waiting and returns a new Pair with null event 
 *with either a Human Server or Self Server.
 * @author Weng Jia Jun A0199829B
 */

package cs2030.simulator;

public class WaitEvent extends Event {
    
    /**
     * Constructor that creates a waitEvent.
     * 
     * @param customer - the current customer
     * @param time    - the time of waiting
     * @param server  - the server that has a waiting customer
     */
    WaitEvent(Customer customer, Server server, double time) {
        super(customer, server, time, x -> {  
            Server temp = server;
            if (server instanceof HumanServer) {
                temp = new HumanServer(server.getIdentifier(), false, false,
                server.getNextAvailableTime(),server.getCustomerList(), 
                server.getSimulator(),false);
            } else {
                temp = new SelfServer(server.getIdentifier(), false, false,
                server.getNextAvailableTime(),server.getCustomerList(), 
                server.getSimulator(), false);
            }
            
            temp.getCustomerList().add(customer);
            return new Pair<Shop,Event>(x.replace(temp), null);
        });
    }
    
    /**
     * String method that returns if a customer is greedy.
     * 
     * @return A string depending on whether customer is greedy
     */

    @Override
    public String toString() {
        String temp = "";
        if (this.getCustomer().isGreedy() == true) {
            temp = "(greedy)";
        }
        if (this.getServer().getIdentifier() <= Simulator.numOfServer) {
            return String.format("%.3f", getCurrentTime()) + " " + getCustomer().getId() +
                temp + " waits to be served by server " + getServer().getIdentifier();
        } else {
            return String.format("%.3f",this.getCurrentTime()) + " " + 
                        this.getCustomer().getId()  + temp +
                        " waits to be served by self-check " +
                        this.getServer().getIdentifier();
        }   
    }
}
