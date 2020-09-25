package nz.ac.vuw.ecs.swen225.gp20.maze;

import java.awt.Point;
import java.util.List;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.Tile;

/**
 * Indicates where should the player move to.
 * 
 * @author Emanuel Evans (ID: 300472656)
 *
 */
public class SingleMove implements Move {
  
  private final Direction direction;
  
  

  /**
   * Construct a new move made of a single step.
   * @param direction where is this move going to
   */
  public SingleMove(Direction direction) {
    super();
    //check direction?
    this.direction = direction;
  }

  @Override
  public List<Point> getSteps() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Tile[][] apply(Tile[][] board) {
    // TODO Auto-generated method stub
    //throw new  NotImplementedException();
    return null;
  }

  @Override
  public Point getDestination(Point old) {
    switch(direction) {
    case UP:
      return new Point(old.x+1, old.y);
    
    case RIGHT:
      return new Point(old.x, old.y+1);
      
    case DOWN:
      return new Point(old.x-1, old.y);
    
    case lEFT:
      return new Point(old.x, old.y-1);
      
    default:
      throw new RuntimeException("The direction of this move is invalid");
    }
  }
  
  

  
}
