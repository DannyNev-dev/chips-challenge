package nz.ac.vuw.ecs.swen225.gp20.recnplay.test;

import java.awt.Point;
import java.util.List;


public class TestMove {

	private TestDirection direction;
    List<Point> steps;
    
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

    public TestMove(TestDirection direction) {
    	this();	
        this.direction = direction;
    }

    public TestDirection getDirection() {
        return direction;
    }
    
    
}