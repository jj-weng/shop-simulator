/**
 * This class creates a Shop that contains
 * a list of servers
 * @author Weng Jia Jun A0199829B
 */

package cs2030.simulator;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.Optional;
import java.util.function.Predicate;



public class Shop {
    int n;
    List<Server> list;

    /**
     * Constructor for shop by taking in an integer n.
     * @param n - The number of servers
     */
    public Shop(int n) {
        this.n = n;
        List<Server> temp = new ArrayList<Server>();
        IntStream.range(0,n).forEach(x -> { 
            temp.add(new Server(x + 1,true,false,0));
        });
        list = temp;      
    }

    /**
     * Constructor for shop by taking in a List of servers.
     * @param listIn - The List containing servers
     */
    public Shop(List<Server> listIn) {
        List<Server> listTemp = new ArrayList<Server>();
        listTemp.addAll(listIn);
        list = listTemp;
        n = listIn.size();
    }

    Optional<Server> find(Predicate<Server> pred) {
        return list.stream().filter(pred).findAny();
    }

    Shop replace(Server s) {
        List<Server> listTemp = new ArrayList<>();
        listTemp.addAll(list);
        int index = s.getIdentifier();
        listTemp.set(index - 1, s);
        return new Shop(listTemp);
    }

    
    /**
     * Returns the server that a greedy customer will go to.
     */
    public Optional<Server> greedyCustomer() {
        int min = list.stream().map(x -> x.getCustomerList().size())
                                .min(Integer::compare).orElse(-1);
        return list.stream().filter(i -> !i.hasWaitingCustomer())
                            .filter(i -> i.getCustomerList().size() < Simulator.maxQueueLength) 
                            .filter((x -> x.getCustomerList().size() == min))
                            .findFirst();
    }

    @Override 
    public String toString() {
        return list.toString();
    }
}
