package nz.ac.vuw.ecs.swen225.gp20.recnplay.test;

/**
 * A mock class for test usage.
 * @author YanLu
 *
 */
public class Action {

    private TestMove tMove;

    private Action() {
    }

    /**
     * Constructor of Action.
     * @param tMove
     */
    public Action(TestMove tMove) {
        this();
        this.tMove = tMove;
    }

    /**
     * Getter.
     * @return TestMove object.
     */
    public TestMove getMove() {
        return tMove;
    }
}