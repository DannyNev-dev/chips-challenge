package nz.ac.vuw.ecs.swen225.gp20.maze.items;


import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.Tile;

/**
 * Represent the token of chip (the player).
 * @author Emanuel Evans (ID: 300472656)
 *
 */
public class Player implements Entity {
  
  //Ending of all the player names, stored to improve efficiency
  private static final String suffix = "chipTile";
  
  //Store orientation? Change chip to upper case ^
  
  private List<Collectable> inventory;
  
  private Tile position;
  
  private int chipsCollected;
  
  
  /**
   * Create a new player.
   * @param position where the player should initially be located
   */
  public Player(Tile position) {
    Preconditions.checkNotNull(position, "The player must have a well defeined position");
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
  public Player(List<Collectable> inventory, Tile position, int chipsCollected) {
    Preconditions.checkArgument(chipsCollected >= 0, "Number of chips collected can't be negative");
    Preconditions.checkNotNull(position, "The player must have a well defeined position");
    
    this.inventory = new ArrayList<Collectable>(inventory);
    this.position = position;
    this.chipsCollected = chipsCollected;
  }

  /**
   * Get the tile object where the player is located.
   * @return the player position
   */
  public Tile getPosition() {
    return position;
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
    return suffix;
  }

  
  
  //when move removes the item from the tile
}
