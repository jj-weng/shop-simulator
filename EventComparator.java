/**
 * This class is a comparator that
 * overrides the Comparator method to return events in priority of their time
 * followed by their ID.
 * @author Weng Jia Jun A0199829B
 */

package cs2030.simulator;

import java.util.Comparator;

public class EventComparator implements Comparator<Event> {

    /**
     * Compare the two events.
     */
    public int compare(Event one, Event two) {
        if (one.getCurrentTime() < two.getCurrentTime()) {
            return -1;
        } else if (one.getCurrentTime() > two.getCurrentTime()) {
            return 1;
        } else if (one.getCustomer().getId() < two.getCustomer().getId()) {
            return -1;
        } else if (one.getCustomer().getId() > two.getCustomer().getId()) {
            return 1;
        } else {
            return 0;
        }
    }
}
