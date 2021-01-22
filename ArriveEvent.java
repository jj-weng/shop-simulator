package cs2030.simulator;

/**
 * This class creates a new ArriveEvents which extends from abstract class
 * Event.
 * 
 * @author Weng Jia Jun A0199829B
 */

public class ArriveEvent extends Event {

    private static Pair<Shop, Event> p;

    /**
     * Constructor that creates a ArriveEvent.
     * 
     * @param cus - a new customer
     */

    public ArriveEvent(Customer cus) {
        super(cus, null, cus.getArrivalTime(), x -> {
            p = new Pair<Shop, Event>(x, new LeaveEvent(cus, null, cus.getArrivalTime()));
            x.find(j -> j.isAvailable() && j.isInRest() == false).ifPresentOrElse(i -> {
                p = new Pair<Shop, Event>(x, new ServeEvent(cus, i, cus.getArrivalTime()));
            }, () -> {
                    if (!cus.isGreedy()) {
                        x.find(z -> z.getCustomerList().size() < Simulator.maxQueueLength
                            && z.hasWaitingCustomer() == false).ifPresent(i -> {
                                p = new Pair<Shop, Event>(x, 
                                    new WaitEvent(cus, i, cus.getArrivalTime()));
                            });
                    } else {
                        x.greedyCustomer().ifPresent(i -> {
                            p = new Pair<Shop, Event>(x,
                                new WaitEvent(cus, i, cus.getArrivalTime()));
                        });
                    }
                });
            return p;
        });
    }

    @Override
    public String toString() {
        String temp = "";
        if (this.getCustomer().isGreedy() == true) {
            temp = "(greedy)";
        }
        return String.format("%.3f", getCustomer().getArrivalTime()) + " " + 
                getCustomer().getId() + temp + " arrives";
    }
}
