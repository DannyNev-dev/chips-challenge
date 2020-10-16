package nz.ac.vuw.ecs.swen225.gp20.maze;

import com.google.common.base.Preconditions;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.Tile;



/**
 * Indicates where should the player move to.
 * 
 * @author Emanuel Evans (ID: 300472656)
 *
 */
public class SingleMove implements Move {
  
  private Direction direction;
  List<Point> steps;

  /**
   * Construct a new move made of a single step.
   * @param direction where is this move going to
   */
  public SingleMove(Direction direction) {
    this();
    Preconditions.checkArgument(direction != null, "The direction of a move can't be undefiened");
    this.direction = direction;
    //steps = new ArrayList<Point>();
  }
  
  /**
   * This constructor should not be used. 
   * It was required by Jackson library for serialisation purposes
   */
  private SingleMove() {
    /*throw new UnsupportedOperationException(
       "The direction must be defined as an argument, this constructor can't be used");
       */
  }
  
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
  
  
 
  /**
   * Get the direction this move is pointing to.
   * @return were the move is going to
   */
  public Direction getDirection() {
    return direction;
  }



  @Override
  public List<Point> getSteps() {
    return steps;
  }
  
  private void setSteps(List<Point> steps) {
    this.steps = steps;
  }
  

  @Override
  public Tile[][] apply(Tile[][] board) {
    // TODO Auto-generated method stub
    //throw new  NotImplementedException();
    return null;
  }

  @Override
  public Point getDestination(Point old) {
    switch (direction) {
      case UP:
        return new Point(old.x - 1, old.y);
      
      case RIGHT:
        return new Point(old.x, old.y + 1);
        
      case DOWN:
        return new Point(old.x + 1, old.y);
      
      default:
        //assert(direction == Direction.LEFT);
        return new Point(old.x, old.y - 1);
      
   // default:
      //throw new RuntimeException("The direction of this move is invalid");
    }
  } 
  /*
   * with side effects
   * 
   *
  /*
   * @Override
  public Point getDestination(Point old) {
    //Store new position for the steps
    //X row Y col
    Point destination = null;
    switch(direction) {
      case UP:
        destination = new Point(old.x-1, old.y);
        break;
      case RIGHT:
        destination = new Point(old.x, old.y+1);
        break;
      case DOWN:
        destination = new Point(old.x+1, old.y);
        break;
      case LEFT:
        break;
        destination = new Point(old.x, old.y-1);
      
   // default:
      //throw new RuntimeException("The direction of this move is invalid");
    }
    assert(direction != null);
    
    List<Point> steps = new ArrayList<Point>();
    steps.add(old);
    steps.add(destination);
    setSteps(steps);
    
    return destination;
  }
   */
  
  

  
}
