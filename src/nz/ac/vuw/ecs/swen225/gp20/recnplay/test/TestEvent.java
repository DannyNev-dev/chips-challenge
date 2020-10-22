package nz.ac.vuw.ecs.swen225.gp20.recnplay.test;

/**
 * A mock class for test usage.
 * Similar to Event class.
 * @author YanLu
 *
 */
public class TestEvent {

    private TestMove tMove;

    private TestEvent() {
    }

    /**
     * Constructor of Action.
     * @param a created mock move object
     */
    public TestEvent(TestMove tMove) {
        this();
        this.tMove = tMove;
    }

    /**
     * Getter.
     * @return current TestMove object
     */
    public TestMove getMove() {
        return tMove;
    }
}