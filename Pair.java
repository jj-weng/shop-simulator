/**
 * This class creates a new Pair object
 * @author Weng Jia Jun A0199829B
 */

package cs2030.simulator;

public class Pair<T,U> {
    T t;
    U u;

    Pair(T t, U u) {
        this.t = t;
        this.u = u;
    }

    static <T,U> Pair<T,U> of(T t, U u) {
        return new Pair<T,U>(t,u);
    }

    T first() {
        return this.t;
    }

    U second() {
        return this.u;
    }
}
