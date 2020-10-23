package nz.ac.vuw.ecs.swen225.gp20.maze;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.awt.Point;
import java.util.Random;

/**
 * Create move objects made of a single step.
 * It primarily indicates the direction in which the player would like to move.
 * Given a set of coordinates it can get the destination.
 * 
 * @author Emanuel Evans (ID: 300472656)
 *
 */
public class SingleMove implements Move {
  
  /**
   * The direction of the last step, it this case of the only step.
   * It couldn't simply be called direction because json required it
   * to be the same as the getter (defined in the Move interface) 
   */
  private Direction lastDirection;

  /**
   * Construct a new move made of a single step.
   * @param direction where is this move going to. It cannot be null.
   */
  public SingleMove(Direction direction) {
    this();
    checkArgument(direction != null, "The direction of a move can't be undefiened");
    this.lastDirection = direction;
  }
  
  /**
   * This constructor has only been included for compatibility purposes. 
   * It was required by Jackson library for serialisation purposes
   */
  private SingleMove() {}
  
  /**
   * Create a SingleMove with a random direction.
   * @return a new SingleMove with a random direction
   */
  public static SingleMove createRandomlyMove() {
    //Get a random number which will correspond to a direction
    int index = new Random().nextInt(Direction.values().length);
    
    //Get a random direction for the move
    Direction randomDirection = Direction.values()[index];
    
    //Construct the new move given the random direction
    return new SingleMove(randomDirection);
  }
  
  @Override
  public Direction getLastDirection() {
    checkNotNull(lastDirection, "invalid move, its direction must be defined");
    return lastDirection;
  }

  @Override
  public Point getDestination(Point old) {
    
    checkArgument(old != null, "The starting point given must be well defined");
    checkNotNull(lastDirection, "invalid move, its direction must be defined");
    
    switch (lastDirection) {
      case UP:
        return new Point(old.x - 1, old.y);
      
      case RIGHT:
        return new Point(old.x, old.y + 1);
        
      case DOWN:
        return new Point(old.x + 1, old.y);
      
      default:
        assert (lastDirection == Direction.LEFT) : 
          "Only four direction were allowed for a move when this method was implemented";
        return new Point(old.x, old.y - 1);
    }
  } 
}
