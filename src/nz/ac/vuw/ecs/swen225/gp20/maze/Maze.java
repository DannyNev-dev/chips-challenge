package nz.ac.vuw.ecs.swen225.gp20.maze;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.common.base.Preconditions;
import java.awt.Point;
import java.util.Collections;
import java.util.List;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Collectable;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Key;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Player;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.Tile;

/**
 * This class manages the logic for the maze.
 * 
 * @author Emanuel Evans
 *
 */
public class Maze {
  
  //Reference to the main player controlled by the user
  private Player player;
  
  //2-D array of tiles grouping each location on the maze
  private Tile[][] board;
  
  //Total number of chips to collect
  private final int target;
  
  
  

  /**
   * Load a maze.
   * 
   * @param player coordinates of the Tile containing the player
   * @param board 2-D array of tiles grouping each location on the maze
   * @param target Chips left to collect
   */
  public Maze(Player player, Tile[][] board, int target) {
    Preconditions.checkArgument(board[playerPos.x][playerPos.y].containsItemType(Player.class));
    Preconditions.checkArgument(target >= 0);
    this.player = player;
    this.board = board;
    //TODO clone board
    this.target = target;
  }


  /**
   * Move the player from it's current position to a different tile in the board.
   * 
   * @param move indicates where should the player move to
   * @return whether the move was successful
   */
  public boolean movePlayer(SingleMove move) {
    return false;
  }
  
  /**
   * Get the number of chips which the player still have to collect to complete the level.
   * @return number of chips which are left in the board
   */
  public int getChipsLeft() {
    int remainingChips = target - player.getChipsCollected();
    assertTrue(remainingChips >= 0);
    return remainingChips;
  }
  
  /**
   * Get the list of items collected by the player.
   * This list does not includes chips (treasures), use getChipsLeft() to obtain that information
   * @return unmodifiable list of collected items
   */
  public List<Collectable> getPlayerInventory() {
    Preconditions.checkNotNull(player, "There must be a player on the board to get its inventory");
    return Collections.unmodifiableList(player.getInventory());
  }
  
  /*
   * Get a copy of the tile where the player is located.
   * @return the tile where the player is standing
   */
  /*
  public Tile getPlayerTile() {
    Preconditions.checkNotNull(player, "There must be a player on the board to get its position");
    //Get the coordinates of the tile where the player should be located
    Point p = player.getPosition();
    //Validate the player position against the board, hence check that tile contains a player
    Preconditions.checkArgument(board[p.x][p.y].containsItemType(Player.class));
    
    return board[p.x][p.y];
    
    
  }
  */
  


  /**
   * Manual Test.
   * 
   * @param args initial arguments
   */
  public static void main(String... args) {
    //Preconditions.checkState(test, "test");
    System.out.println("The sky is " + Key.Colour.BLUE);
    
    //assert(false);
  }

}
