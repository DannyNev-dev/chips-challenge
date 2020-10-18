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
  
  private Direction direction;

  /**
   * Construct a new move made of a single step.
   * @param direction where is this move going to
   */
  public SingleMove(Direction direction) {
    this();
    checkArgument(direction != null, "The direction of a move can't be undefiened");
    this.direction = direction;
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
  public Direction getFinalDirection() {
    checkNotNull(direction, "This move has not been created properly, direction must be defined");
    return direction;
  }

  @Override
  public Point getDestination(Point old) {
    
    checkArgument(old != null, "The starting point given must be well defined");
    checkNotNull(direction, "This move has not been created properly, direction must be defined");
    
    switch (direction) {
      case UP:
        return new Point(old.x - 1, old.y);
      
      case RIGHT:
        return new Point(old.x, old.y + 1);
        
      case DOWN:
        return new Point(old.x + 1, old.y);
      
      default:
        assert (direction == Direction.LEFT) : 
          "Only four direction were allowed for a move when this method was implemented";
        return new Point(old.x, old.y - 1);
    }
  } 
}
