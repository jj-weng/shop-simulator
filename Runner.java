/**
* This class create a runner to run the whole priority queue.
 * @author Weng Jia Jun A0199829B
 */

package cs2030.simulator;

import java.util.PriorityQueue;

public class Runner {

    /**
     * Runs the priority queue and prints out all the respective events.
     * 
     * @param pq         Priority Queue taken in
     * @param shop       The current shop object
     * @param served     Number of customers served
     * @param left       Number of customers left
     * @param waitTime   Total wait time of the customers
     * @param numOfServer Number of human servers
     */
    public static void run(PriorityQueue<Event> pq, Shop shop, int served,
            int left, double waitTime, int numOfServer) {

        while (!pq.isEmpty()) {
            Event event = pq.poll();
            if (!(event instanceof ServerBack) && !(event instanceof ServerRest)) {
                System.out.println(event);
            }
            Pair<Shop, Event> pair = event.execute(shop);
            shop = pair.first();
            if (pair.second() != null) {
                pq.add(pair.second());
            }

            if (event instanceof LeaveEvent) {
                left++;
            }

            if (event instanceof ServeEvent) {
                served++;
                waitTime += event.getCurrentTime() - event.getCustomer().getArrivalTime();
            }

            if (event instanceof DoneEvent) {
                Server s = event.getServer();

                if (s.getIdentifier() <= Simulator.numOfServer) {
                    s = new HumanServer(s.getIdentifier(), true, s.hasWaitingCustomer(),
                    event.getCurrentTime(), s.getCustomerList(), s.getSimulator(), false);
                } else {
                    s = new SelfServer(s.getIdentifier(), true, s.hasWaitingCustomer(),
                    event.getCurrentTime(), s.getCustomerList(), s.getSimulator(), false);
                }
 
                if (s.getIdentifier() <= Simulator.numOfServer && 
                    s.getSimulator().getRandomRest() < s.getSimulator().getRestingProbablity()) {
                    pair = new Pair<Shop, Event>(shop.replace(s),
                        new ServerRest(event.getCustomer(), event.getCurrentTime(), s));
                    shop = pair.first();
                    pq.add(pair.second());

                } else if (s.getCustomerList().size() > 0) {
                    if (s.getIdentifier() <= numOfServer) {
                        s = new HumanServer(s.getIdentifier(), false, s.hasWaitingCustomer(),
                        event.getCurrentTime(), s.getCustomerList(), s.getSimulator(), false);
                    } else {
                        s = new SelfServer(s.getIdentifier(), false, s.hasWaitingCustomer(),
                        event.getCurrentTime(), s.getCustomerList(), s.getSimulator(), false);
                    }
                    
                    pair = new Pair<Shop, Event>(shop.replace(s),
                        new ServeEvent(s.getCustomerList().get(0), s, s.getNextAvailableTime()));
                    shop = pair.first();
                    pq.add(pair.second());  
                }
            }
        }

        double avgTime = waitTime / served;
        if (Double.isNaN(avgTime) == true) {
            avgTime = 0;
        }

        System.out.println("[" + String.format("%.3f", avgTime) + " " + served + " " + left + "]");
    }
}
