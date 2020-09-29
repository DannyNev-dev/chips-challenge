package nz.ac.vuw.ecs.swen225.gp20.maze.items;


import com.google.common.base.Preconditions;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import nz.ac.vuw.ecs.swen225.gp20.maze.Move;

/**
 * Represent the token of chip (the player).
 * @author Emanuel Evans (ID: 300472656)
 *
 */
public class Player implements Entity {
  
  //Ending of all the player names, stored to improve efficiency
  private static final String name = "chip";
  
  //Store where the player is facing
  private Move.Direction orientation;
  
  private List<Collectable> inventory;
  
  private Point position;
  
  private int chipsCollected;
  
  
  /**
   * Create a new player.
   * @param position where the player should initially be located
   */
  public Player(Point position) {
    Preconditions.checkArgument(position != null, "The player must have a well defeined position");
    this.inventory = new ArrayList<Collectable>();
    this.chipsCollected = 0;
    this.position = position;
  }
  
  
  /**
   * Create a player from a previous game.
   * @param inventory items which have already been collected
   * @param position where the player is currently located
   * @param chipsCollected number of chips which have been already collected
   */
  public Player(List<Collectable> inventory, Point position, int chipsCollected) {
    Preconditions.checkArgument(inventory != null, "Inventory can't be null when loading a player");
    Preconditions.checkArgument(position != null, "The player must have a well defeined position");
    Preconditions.checkArgument(chipsCollected >= 0, "Number of chips collected can't be negative");
   
    this.inventory = new ArrayList<Collectable>(inventory);
    this.position = position;
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
  public boolean isAccessible(Entity entity) {
    //TODO if player explode??
    return false;
  }

  @Override
  public boolean hasColleced(Collectable item) {
    return inventory.contains(item);
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

  
  
  //when move removes the item from the tile
}
