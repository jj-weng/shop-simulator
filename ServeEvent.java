/**
* This class creates a serveEvent
* that returns new Pair containing a Done Event
* and also either a HumanServer or Selfserver
* @author Weng Jia Jun A0199829B
*/

package cs2030.simulator;

public class ServeEvent extends Event {

    /**
     * Constructor that creates a ServeEvent.
     * 
     * @param customer - a new customer
     * @param servers  - a list of servers
     * @param time     - the time of serving
     * @param server   - the server serving the customer
     */
    ServeEvent(Customer customer, Server server, double time) {
        super(customer, server, time, x -> {
            double serviceTime = customer.getServiceTime();
            Server temp = server;
            if (server instanceof HumanServer) {
                temp = new HumanServer(server.getIdentifier(), false, 
                    server.hasWaitingCustomer(), time + serviceTime,
                    server.getCustomerList(),server.getSimulator(), false);
            } else if (server instanceof SelfServer) {
                temp = new SelfServer(server.getIdentifier(), false, 
                    server.hasWaitingCustomer(), time + serviceTime,
                    server.getCustomerList(),server.getSimulator(), false);
            }

            if (temp.getCustomerList().size() > 0) {
                temp.getCustomerList().remove(0);
            }
            return new Pair<Shop, Event>(
                    x.replace(temp), new DoneEvent(customer, server, time + serviceTime));
        });
    }


    @Override
    public String toString() {
        String temp = "";
        if (this.getCustomer().isGreedy() == true) {
            temp = "(greedy)";
        }
        if (this.getServer().getIdentifier() <= Simulator.numOfServer) {
            return String.format("%.3f", this.getCurrentTime()) + " " +
                getCustomer().getId() + temp + " served by server "
                + getServer().getIdentifier();
        } else {
            return String.format("%.3f", this.getCurrentTime()) + " " + 
                getCustomer().getId() + temp + " served by self-check "
                + getServer().getIdentifier();
        }
        
    }

}
