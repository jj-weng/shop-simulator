/**
 *This class creates a event and is the
 *abstract parent class of the 5 types of events.
 * @author Weng Jia Jun A0199829B
 */

package cs2030.simulator;

import java.util.function.Function;

public abstract class Event {
    private final Customer customer;
    private final Server server;
    private final Function<Shop, Pair<Shop,Event>> func;
    private final double time;


    /**
     * Constructor that creates a event.
     * 
     * @param customer - a new customer
     * @param server  - a server
     * @param time  - the current time
     * @param func  - a function that takes in a shop and return a new Pair
     */
    Event(Customer customer, Server server, double time, Function<Shop, Pair<Shop,Event>> func) {
        this.customer = customer;
        this.server = server;
        this.func = func;
        this.time = time;
    }

    final Pair<Shop, Event> execute(Shop shop) { // declared final to avoid overriding
        return this.func.apply(shop); // func is the Function property
    }

    public double getCurrentTime() {
        return time;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Server getServer() {
        return server;
    }

}
