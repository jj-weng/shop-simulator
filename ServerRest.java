/**
 * This class creates a Server Rest event which extends from
 * parent class Event
 * @author Weng Jia Jun A0199829B
 */


package cs2030.simulator;

public class ServerRest extends Event {

    /**
     * Constructor for ServerRestobject.
     * 
     * @param customer - The current customer
     * @param time -  The current time
     * @param server - The current server
     */
    public ServerRest(Customer customer, double time, Server server) {
        super(customer, server, time,  x -> {
            double restTime = server.getSimulator().getRestPeriod();
            
            Server temp = new HumanServer(server.getIdentifier(), server.isAvailable(),
                server.hasWaitingCustomer(), time + restTime,
                server.getCustomerList(), server.getSimulator(), true);        
            return new Pair<Shop,Event>(x.replace(temp),
                new ServerBack(customer, time + restTime, temp));
        });  
    }
}