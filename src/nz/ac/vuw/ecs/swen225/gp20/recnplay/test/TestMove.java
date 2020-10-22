package nz.ac.vuw.ecs.swen225.gp20.recnplay.test;

import java.awt.Point;
import java.util.List;


/**
 * A mock class for test usage.
 * Main attributes are same as SingleMove class.
 * @author YanLu
 *
 */
public class TestMove {

	private TestDirection direction;
    List<Point> steps;
    
    /**
     * Four different directions.
     * @author YanLu
     *
     */
    public enum TestDirection {
        /**
         * Towards the North of the board.
         */
        UP,
        /**
         * Towards the East of the board.
         */
        RIGHT,
        /**
         * Towards the South of the board.
         */
        DOWN,
        /**
         * Towards the West of the board.
         */
        LEFT
      }
 
    private TestMove() {
		this.direction = null;
    }

    /**
     * Constructor of TestMove.
     * @param direction where this move is going to
     */
    public TestMove(TestDirection direction) {
    	this();	
        this.direction = direction;
    }

    /**
     * Getter.
     * @return direction where this move is going to
     */
    public TestDirection getDirection() {
        return direction;
    }
}