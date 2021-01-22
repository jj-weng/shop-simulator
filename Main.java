
/**
 * This is the main class
 * Reads the input args and subsequently 
 * forms a priority queue which is then passed into the Runner
 * @author Weng Jia Jun A0199829B
 */

import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Supplier;

import cs2030.simulator.Customer;
import cs2030.simulator.Shop;
import cs2030.simulator.Event;
import cs2030.simulator.Runner;
import cs2030.simulator.EventComparator;
import cs2030.simulator.ArriveEvent;
import cs2030.simulator.Simulator;
import cs2030.simulator.Server;
import cs2030.simulator.SelfServer;
import cs2030.simulator.HumanServer;

class Main {
    public static void main(String[] args) {

        int baseSeed = 0;
        int numOfCustomer = 0;
        int numOfServers = 1;
        int numOfSs = 0;
        int maxQueueLength = 1;
        double arrivalRate = 0;
        double serviceRate = 0;
        double restingRate = 0;
        double restingProbability = 0;
        double greedyProbability = 0;

        if (args.length == 5) {
            baseSeed = Integer.parseInt(args[0]);
            numOfServers = Integer.parseInt(args[1]);
            numOfCustomer = Integer.parseInt(args[2]);
            arrivalRate = Double.parseDouble(args[3]);
            serviceRate = Double.parseDouble(args[4]);
        }

        if (args.length == 6) {
            baseSeed = Integer.parseInt(args[0]);
            numOfServers = Integer.parseInt(args[1]);
            maxQueueLength = Integer.parseInt(args[2]);
            numOfCustomer = Integer.parseInt(args[3]);
            arrivalRate = Double.parseDouble(args[4]);
            serviceRate = Double.parseDouble(args[5]);
        }

        if (args.length == 8) {
            baseSeed = Integer.parseInt(args[0]);
            numOfServers = Integer.parseInt(args[1]);
            maxQueueLength = Integer.parseInt(args[2]);
            numOfCustomer = Integer.parseInt(args[3]);
            arrivalRate = Double.parseDouble(args[4]);
            serviceRate = Double.parseDouble(args[5]);
            restingRate = Double.parseDouble(args[6]);
            restingProbability = Double.parseDouble(args[7]);
        }

        if (args.length == 9) {
            baseSeed = Integer.parseInt(args[0]);
            numOfServers = Integer.parseInt(args[1]);
            numOfSs = Integer.parseInt(args[2]);
            maxQueueLength = Integer.parseInt(args[3]);
            numOfCustomer = Integer.parseInt(args[4]);
            arrivalRate = Double.parseDouble(args[5]);
            serviceRate = Double.parseDouble(args[6]);
            restingRate = Double.parseDouble(args[7]);
            restingProbability = Double.parseDouble(args[8]);
        }

        if (args.length == 10) {
            baseSeed = Integer.parseInt(args[0]);
            numOfServers = Integer.parseInt(args[1]);
            numOfSs = Integer.parseInt(args[2]);
            maxQueueLength = Integer.parseInt(args[3]);
            numOfCustomer = Integer.parseInt(args[4]);
            arrivalRate = Double.parseDouble(args[5]);
            serviceRate = Double.parseDouble(args[6]);
            restingRate = Double.parseDouble(args[7]);
            restingProbability = Double.parseDouble(args[8]);
            greedyProbability = Double.parseDouble(args[9]);
        }

        int countCustomer = 1;
        int countServer = 1;
        int left = 0;
        int served = 0;
        double waitTime = 0;


        Simulator simulate = new Simulator(numOfServers, baseSeed, 
                arrivalRate, serviceRate, maxQueueLength,
                restingRate, restingProbability, greedyProbability);

        List<Customer> customerList = new ArrayList<Customer>();
        List<Server> serverList = new ArrayList<Server>();

        double arriveRandom = 0;
        double start = 0;
        while (countCustomer <= numOfCustomer) {
            boolean isGreedy = false;

            if (simulate.genRandomGreedy() < simulate.getGreedyProbability()) {
                isGreedy = true;
            } 

            Supplier<Double> supplier = () -> simulate.getServiceTime();
            if (isGreedy == true) {
                if (countCustomer == 1) {
                    customerList.add(new Customer(countCustomer, 0, supplier, true));
                } else {
                    arriveRandom = simulate.getArrivalTime() + start;
                    start = arriveRandom;
                    customerList.add(new Customer(countCustomer, arriveRandom, supplier, true));
                }
                countCustomer++;
            } else {
                if (countCustomer == 1) {
                    customerList.add(new Customer(countCustomer, 0, supplier, false));
                } else {
                    arriveRandom = simulate.getArrivalTime() + start;
                    start = arriveRandom;
                    customerList.add(new Customer(countCustomer, arriveRandom, supplier, false));
                }
                countCustomer++;
            }
        }

        //System.out.println(customerList.toString());

        final ArrayList<Customer> selfList = new ArrayList<Customer>();

        while (countServer <= numOfServers + numOfSs) {
            if (countServer <= numOfServers) {
                serverList.add(new HumanServer(countServer, true, false, 0,
                            new ArrayList<Customer>(), simulate, false));
            } else {
                serverList.add(new SelfServer(countServer, true, false, 0, 
                            selfList, simulate, false));
            }
            countServer++;
        }

        Shop shop = new Shop(serverList);

        PriorityQueue<Event> pq = new PriorityQueue<Event>(new EventComparator());

        for (Customer customer : customerList) {
            pq.add(new ArriveEvent(customer));
        }

        Runner.run(pq, shop, left, served, waitTime, numOfServers);
    }
}
