/**
* This class creates a new customer
* with an ID and an arrival time.
* @author Weng Jia Jun A0199829B
*/

package cs2030.simulator;

import java.util.function.Supplier;

public class Customer {
    private final int id;
    private final double arrivalTime;
    private final Supplier<Double> supplier;
    private final boolean isGreedy;

    /**
     * Constructor that creates a customer.
     * 
     * @param id          - the id of the customer
     * @param arrivalTime - the time which the customer arrives
     */

    public Customer(int id, double arrivalTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.supplier = null;
        this.isGreedy = false;
    }

    
    /**
     * Constructor that creates a customer.
     * 
     * @param id          - the id of the customer
     * @param arrivalTime - the time which the customer arrives
     * @param supplier - supplier that supplies the service time of a customer
     * @param isGreedy - determines if the customer is greedy
     */
    public Customer(int id, double arrivalTime, Supplier<Double> supplier, boolean isGreedy) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.supplier = supplier;
        this.isGreedy = isGreedy;
    }

    public int getId() {
        return id;
    }

    public double getArrivalTime() {
        return arrivalTime;
    }

    public Supplier<Double> getSupplier() {
        return supplier;
    }

    public double getServiceTime() {
        return supplier.get();
    }

    public boolean isGreedy() {
        return isGreedy;
    }

    @Override
    public String toString() {
        return getId()  + " arrives at " + getArrivalTime();
    }
}