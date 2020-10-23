package nz.ac.vuw.ecs.swen225.gp20.maze.items;

import static com.google.common.base.Preconditions.checkArgument;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import nz.ac.vuw.ecs.swen225.gp20.maze.Maze.SpecialEvent;
import nz.ac.vuw.ecs.swen225.gp20.maze.Move;


/**
 * Represent the token of chip (the player).
 * The hero of the game. His movement is restricted by the nature of the tiles 
 * (for instance, he cannot move into walls). 
 * Note that the icon may depend on the current direction of movement.
 * @author Emanuel Evans (ID: 300472656)
 *
 */
public class Player implements Entity {
  
  //Ending of all the player names, stored to improve efficiency
  private static final String name = "chip";

  private List<Collectable> inventory;
  
  private Point position;
  
  //Store where the player is facing
  private Move.Direction orientation;
  
  private int chipsCollected;
  
  /**
   * Create a new player.
   * @param position where the player should initially be located
   */
  public Player(Point position) {
    checkArgument(position != null, "The player must have a well defeined position");
    this.inventory = new ArrayList<Collectable>();
    this.chipsCollected = 0;
    this.position = (Point) position.clone();
  }
  
  
  /**
   * Create a player from a previous game.
   * @param inventory items which have already been collected. It cannot be null
   * @param position where the player is currently located. It cannot be null
   * @param orientation where the player is facing.
   * @param chipsCollected number of chips which have been already collected. 
   *                       The number of chipsCollected cannot be negative
   */
  private Player(List<Collectable> inventory, Point position, 
      Move.Direction orientation,  int chipsCollected) {
    checkArgument(inventory != null, "Inventory can't be null when loading a player");
    checkArgument(position != null, "The player must have a well defeined position");
    checkArgument(chipsCollected >= 0, "Number of chips collected can't be negative");
   
    this.inventory = new ArrayList<Collectable>(inventory);
    this.position = (Point) position.clone();
    this.orientation = orientation;
    this.chipsCollected = chipsCollected;
  }

  /**
   * Increase the number of chips which have been collected by one.
   */
  public void collectChip() {
    this.chipsCollected++;
  }
  
  /**
   * Add an item to the inventory.
   * @param newItem which has been collected
   * @return whether the item have been added successfully
   */
  protected boolean collectItem(Collectable newItem) {
    return inventory.add(newItem);
  }

  /**
   * Get the direction the player is facing.
   * @return where the player is facing
   */
  public Move.Direction getOrientation() {
    return orientation;
  }

  /**
   * Set the direction the player is facing.
   * @param orientation where the player is facing
   */
  public void setOrientation(Move.Direction orientation) {
    this.orientation = orientation;
  }

  @Override
  public Point getPosition() {
    return position;
  }
  
  @Override
  public void setPosition(Point newCoordinates) {
    position = new Point(newCoordinates.x, newCoordinates.y);
  }

  @Override
  public boolean isAccessible(Entity entity) {
    //Other entity can move into the player's tile to kill them
    return true;
  }
  
  @Override
  public List<Collectable> getInventory() {
    return Collections.unmodifiableList(inventory);
  }

  @Override
  public int getChipsCollected() {
    return chipsCollected;
  }


  @Override
  public String getName() {
    return name;
  }
  
  @Override
  public void dropCollectable(Collectable toDrop) throws IllegalArgumentException {
    if (!inventory.contains(toDrop)) {
      throw new IllegalArgumentException("The given collectable is not in the player inventory");
    }
    
    inventory.remove(toDrop);
  }
  
  @Override
  public SpecialEvent applyAction(Entity entity) {
    //If any entity will try to move into the player tiles, the player will die
    return SpecialEvent.CHAP_DIED_MURDERED;
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + chipsCollected;
    result = prime * result + ((inventory == null) ? 0 : inventory.hashCode());
    result = prime * result + ((orientation == null) ? 0 : orientation.hashCode());
    result = prime * result + ((position == null) ? 0 : position.hashCode());
    return result;
  }


  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Player other = (Player) obj;
    if (chipsCollected != other.chipsCollected) {
      return false;
    }
    if (inventory == null) {
      if (other.inventory != null) {
        return false;
      }
    } else if (!inventory.equals(other.inventory)) {
      return false;
    }
    if (orientation != other.orientation) {
      return false;
    }
    if (position == null) {
      if (other.position != null) {
        return false;
      }
    } else if (!position.equals(other.position)) {
      return false;
    }
    return true;
  }


  /**
   * Override clone.
   * Create a new player with the same characteristics.
   */
  public Player clone() {
    List<Collectable> newInventory = new ArrayList<>();
    for (Collectable item : inventory) {
      newInventory.add(item);
    }
   
    return new Player(newInventory, position, orientation, chipsCollected);
  }
}
