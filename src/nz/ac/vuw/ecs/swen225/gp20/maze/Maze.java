package nz.ac.vuw.ecs.swen225.gp20.maze;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.common.base.Preconditions;
import java.awt.Point;
import java.util.Collections;
import java.util.List;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Collectable;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Key;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Player;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.ExitTile;
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
  
  private final int level;
  
  private GameState status = GameState.PLAYING;
  
  /**
   * Define the what are the different states a game can be in.
   * @author Emanuel Evans (ID: 300472656)
   *
   */
  public enum GameState{
    /**
     * When the player is still able to move.
     */
    PLAYING,
    /**
     * When the player lost the game.
     */
    GAME_LOST,
    /**
     * When the player won the game.
     */
    GAME_WON
  }

  /**
   * Load a maze.
   * 
   * @param player coordinates of the Tile containing the player
   * @param board 2-D array of tiles grouping each location on the maze
   * @param target Chips left to collect
   * @param level the level of the current board
   */
  public Maze(Player player, Tile[][] board, int target, int level) {
    Preconditions.checkArgument(player != null);
    Preconditions.checkArgument(board != null);
    Preconditions.checkArgument(target >= 0, "there can't be a negative target");
    Preconditions.checkArgument(level >= 0, "levels can't be negative");
    this.player = player;
    this.board = board;
    //TODO clone board
    this.target = target;
    this.level = level;
    assert(isPlayerPosValid());
  }

  /**
   * Move the player from it's current position to a different tile in the board.
   * 
   * @param move indicates where should the player move to
   * @return whether the move was successful
   */
  public boolean movePlayer(SingleMove move) {
    Preconditions.checkArgument(move != null, "A well initialized move is required");
    Preconditions.checkArgument(isPlayerPosValid());
    Preconditions.checkState(status == GameState.PLAYING, "Moves can't be applied unless the game is still active");
    
    //TODO improve move functionality this is just an initial approach
    
    Point oldPos = player.getPosition();
    
    Point newPos = move.getDestination(oldPos);
    if(!isPointInsideBoard(newPos)) {
      //Trying to move outside the board;
      return false;
    }
    
    //Check if player can enter the new tile
    if(!board[newPos.x][newPos.y].isAccessible(player)) {
      return false;
    }
    
    //Remove player from old tile
    board[oldPos.x][oldPos.y].replaceItem(null);
    //Change player position
    player.setPosition(newPos);
    
    //Add player to new tile
    board[newPos.x][newPos.y].replaceItem(player);
    
    if(isGameWon()) {
      status = GameState.GAME_WON;
    }
    
    assert(isPlayerPosValid());
    
    return true;
  }
  
  private boolean isPointInsideBoard(Point pos) {
    return pos.x >=0 && pos.x < board.length 
        && pos.y >=0 && pos.y < board[0].length;
  }
  
  private boolean isGameWon() {
    //Get the coordinates of the tile where the player should be located
    Point playerPos = player.getPosition();
    
    //Check if it is on the exit tile
    return board[playerPos.x][playerPos.y] instanceof ExitTile;
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
  
  /**
   * Check whether the position store in the player object match the board.
   * @return whether the tile at the player coordinate actually contains a player
   * @throws RuntimeException is the player is undefined or has an invalid position
   */
  private boolean isPlayerPosValid() {
    if (player == null) {
      throw new RuntimeException("There must be a player on the board");
    }
    //Get the coordinates of the tile where the player should be located
    Point playerPos = player.getPosition();
    
    if(!isPointInsideBoard(playerPos)) {
      //Player position is outside the board;
      throw new RuntimeException("The player most be within the board boundaries");
    }
    
    //Validate the player position against the board, hence check that tile contains a player
    if (!board[playerPos.x][playerPos.y].containsItemType(Player.class)) {
      throw new RuntimeException(
          "The position of the given player doesn't match the player tile in the board");
    }
    
    return true;    
  }
  
  /**
   * Retrieve the number indicating the level that this maze is representing
   * @return the level of this maze
   */
  public int getLevel() {
    return level;
  }

  /**
   * Check what the current state of the game is
   * @return the current state of the game
   */
  public GameState getStatus() {
    return status;
  }
  
  /* 
   * for when the timer is over?
  public void setStatus(GameState status) {
    this.status = status;
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

}
