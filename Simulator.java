
/**
 * This is the Simulator class
 * @author Weng Jia Jun A0199829B
 */

package cs2030.simulator;

public class Simulator {
    int seedRandom;
    static int numOfServer = 1;
    int numOfSelfCheckout;
    double restingRate;
    double serviceRate;
    static int maxQueueLength = 1;
    static RandomGenerator r;
    double restingProbablity;
    double greedyProbability;

    /**
     * Constructor for simulator.
     * 
     * @param numOfServerA       The number of servers
     * @param seedRandom        The random seed
     * @param arrivalRate       The arrival rate 
     * @param serviceRate       The service rate
     * @param maxQueueLengthA   The maximum queue length
     * @param restingRate       The resting rate of Human Server
     * @param restingProbablity The resting probablity of Human Server
     * @param greedyProbability The greedy probability of customer
     */
    public Simulator(int numOfServerA, final int seedRandom,
            final double arrivalRate, final double serviceRate, 
                int maxQueueLengthA, double restingRate,
                double restingProbablity, double greedyProbability) {
        numOfServer = numOfServerA;
        maxQueueLength = maxQueueLengthA;
        this.restingProbablity = restingProbablity;
        this.greedyProbability = greedyProbability;
        r = new RandomGenerator(seedRandom, arrivalRate, serviceRate, restingRate);
    }
    
    public double getServiceTime() {
        return r.genServiceTime();
    }

    public double getArrivalTime() {
        return r.genInterArrivalTime();
    }

    public double getRestingProbablity() {
        return restingProbablity;
    }

    public double getRandomRest() {
        return r.genRandomRest();
    }

    public double getRestPeriod() {
        return r.genRestPeriod();
    } 
    
    public double getGreedyProbability() {
        return greedyProbability;
    }

    public double genRandomGreedy() {
        return r.genCustomerType();
    }
}
