package nz.ac.vuw.ecs.swen225.gp20.maze.items;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * The block that prevents the exit to be reached unless all chips have been collected.
 * A level can contain multiple exit block with different targets.
 * This can allow more complex level where the player need to collect at least a certain
 * Amount of keys before moving to the next part of the board.
 * @author Emanuel Evans (ID: 300472656)
 *
 */
public class ExitLock implements Item {
  
  //Store name shared by all exit blocks
  private static final String name = "exitLock";
  
  //Number of chips required to open this lock
  private final int target;
  
  
  /**
   * Create a new exit lock.
   * @param target the number of chips required to open this lock
   */
  public ExitLock(int target) {
    checkArgument(target >= 0, "there can't be a negative target");
    this.target = target;
  }

  @Override
  public boolean isAccessible(Entity entity) {
    //Check if the player has collected enough chips
    return entity.getChipsCollected() >= target;
  }

  @Override
  public String getName() {
    //Not static because it needs to override Tile and not all tiles have static names
    return name;
  }

}
