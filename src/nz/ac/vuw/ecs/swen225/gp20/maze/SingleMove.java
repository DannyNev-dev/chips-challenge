package nz.ac.vuw.ecs.swen225.gp20.maze;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Preconditions;

import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.Tile;

/**
 * Indicates where should the player move to.
 * 
 * @author Emanuel Evans (ID: 300472656)
 *
 */
public class SingleMove implements Move {
  
  private final Direction direction;
  List<Point> steps;
  

  /**
   * Construct a new move made of a single step.
   * @param direction where is this move going to
   */
  public SingleMove(Direction direction) {
    Preconditions.checkArgument(direction != null, "The direction of a move can't be undefiened");
    this.direction = direction;
    //steps = new ArrayList<Point>();
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
    //Store new position for the steps
    Point destination = null;
    switch(direction) {
      case UP:
        destination = new Point(old.x, old.y-1);
      
      case RIGHT:
        destination = new Point(old.x+1, old.y);
        
      case DOWN:
        destination = new Point(old.x, old.y+1);
      
      case LEFT:
        
        destination = new Point(old.x-1, old.y);
      
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
  
  

  
}
