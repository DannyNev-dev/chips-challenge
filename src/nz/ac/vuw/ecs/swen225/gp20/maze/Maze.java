package nz.ac.vuw.ecs.swen225.gp20.maze;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Collectable;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Item;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Player;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.ExitTile;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.InfoTile;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.Tile;
import nz.ac.vuw.ecs.swen225.gp20.persistence.LevelReader;

/**
 * This class manages the logic for the maze.
 * 
 * @author Emanuel Evans
 *
 */
public class Maze {
  
  //Reference to the main player controlled by the user
  private Player player;
  
  //private List<Entity> bugs = new ArrayList<>();
  
  //Representation of the board state
  private Board board;
  
  //Total number of chips to collect
  private final int target;
  
  private final int level;
  
  private GameState status = GameState.PLAYING;
  
  //The last crucial event which happened in the game
  private SpecialEvent lastEvent;
  
  //Message to display when the player gets on the info tile
  private String info;
  
  /**
   * Define the what are the different states a game can be in.
   * @author Emanuel Evans (ID: 300472656)
   *
   */
  public static enum GameState {
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
  
  public static enum SpecialEvent {
    /**
     * When Chap picks up a key and puts it in his inventory.
     */
    KEY_PICKED_UP,
    /**
     * When Chap collects a treasure (chip).
     */
    TREASURE_PICKED_UP,
    /**
     * When Chap uses a key to remove a block form the make. 
     * Removing a block is usually referred as opening a door,
     * As it gives a better idea of what is happening. However,
     * In the game logic the term block is used because once it is removed it can't go back. 
     * Therefore it isn't exactly a door as it can't be closed after it was unlocked with a key.  
     */
    DOOR_OPENED,
    /**
     * Chap dies when he visited a dangerous cell.
     * This value specified that he died because of poisoning
     */
    CHAP_DIED_POISONED,
    /**
     * Specify that the player has died due to a fire.
     */
    CHAP_DIED_BURNT,
    /**
     * When the player is on the info tile
     */
    INFO_POINT
    
  }
  

  /**
   * Load a maze.
   * 
   * @param player coordinates of the Tile containing the player
   * @param boardData 2-D array of tiles grouping each location on the maze
   * @param target Chips left to collect
   * @param level the level of the current board
   */
  public Maze(Player player, Tile[][] boardData, int target, int level) {
    
    //TODO remove this constructor!!
    
    checkArgument(player != null, "There must be a player on the board");
    checkArgument(boardData != null);
    checkArgument(target >= 0, "there can't be a negative target");
    checkArgument(level >= 0, "levels can't be negative");
    this.player = player;
    this.board = new Board(boardData);
    //TODO clone board
    this.target = target;
    this.level = level;
    assert (isPlayerPosValid());
  }
  
  /**
   * Create a new maze given a level.
   * @param level of the maze
   * @throws IOException if no object has that level
   */
  public Maze(int level) throws IOException {
    checkArgument(level >= 0, "levels can't be negative");
    LevelReader loader = new LevelReader(level);
    this.player = loader.loadPlayer();
    this.board = new Board(loader.loadBoard());
    //TODO clone board
    this.target = loader.loadTarget();
    this.level = level;
    assert (isPlayerPosValid());
    
  }

  /**
   *Merge board.
   *@return board grouping data about the maze's tiles
   */
  public Tile[][] getBoard() {
    return board.getBoard();
  }
  
  /**
   * Ideally this will be the only method to get the board. TODO .
   * @return board grouping data about the maze's tiles
   */
  public Board getBoardObject() {
    return board;
  }
  
  
  

  /**
   * Move the player from it's current position to a different tile in the board.
   * 
   * @param move indicates where should the player move to
   * @return whether the move was successful
   */
  public boolean movePlayer(SingleMove move) {
    if (status != GameState.PLAYING) {
      //TODO remove when application checks the game status
      return false;
    }
    
    checkArgument(move != null, "A well initialized move is required");
    checkArgument(isPlayerPosValid());
    checkState(status == GameState.PLAYING, "Moves can't be applied unless the game is active");
    
    //TODO improve move functionality this is just an initial approach
    
    lastEvent = null; //Reset any special events from the last movement
    info = null;
    
    Point oldPos = player.getPosition();
    
    Point newPos = move.getDestination(oldPos);
    
    if (!board.isPointInsideBoard(newPos)) {
      //Trying to move outside the board;
      return false;
    }
    
    //Tile where the player is trying to move into
    Tile newTile = board.getTile(newPos);
    
    //Check if player can enter the new tile
    if (!newTile.isAccessible(player)) {
      return false;
    }
    
    //Remove player from old tile
    board.getTile(oldPos).replaceItem(null);
    //Change player position
    player.setPosition(newPos);
    
    if (newTile.hasAction()) {
      //An action is an interaction with an item which might trigger a special event to be recorded
      //For instance a key could be picked up or a door opened
      lastEvent = newTile.applyAction(player);
      
    }
    
    if (lastEvent != null && lastEvent.name().contains("CHAP_DIED")) {
      //Some tile or item has kill the player
      status = GameState.GAME_LOST;
      
    } else if (lastEvent == SpecialEvent.INFO_POINT) {
      assert (newTile instanceof InfoTile) : 
        "The info point event should only occur when the player is on an info tile";
      
      info = ((InfoTile) newTile).getInfo();
      
    } else if (isGameWon()) {
      //The player has reached the final tile
      status = GameState.GAME_WON;
      System.out.print("Well done you completed the level!!!");
      return false;
    }
    
    //Add player to new tile
    board.getTile(newPos).replaceItem(player);
    
    
    
    assert (isPlayerPosValid());
    
    return true;
  }
  
  
  
  //---------------------------------------------------------------//
  //       START of methods to investigate the game status         //
  //---------------------------------------------------------------//
  
  
  private boolean isGameWon() {
    //Get the coordinates of the tile where the player should be located
    Point playerPos = player.getPosition();
    
    //Check if it is on the exit tile
    return board.getTile(playerPos) instanceof ExitTile;
  }
  
  /**
   * Get the number of chips which the player still have to collect to complete the level.
   * @return number of chips which are left in the board
   */
  public int getChipsLeft() {
    int remainingChips = target - player.getChipsCollected();
    assert remainingChips >= 0 : "The chips that still have to be collected can't be negative";
    assert remainingChips <= target : 
      "The chips to be collected can't more than the initial number of chips";
    return remainingChips;
  }
  
  /**
   * Get the list of items collected by the player.
   * This list does not includes chips (treasures), use getChipsLeft() to obtain that information
   * @return unmodifiable list of collected items
   */
  public List<Collectable> getPlayerInventory() {
    checkNotNull(player, "There must be a player on the board to get its inventory");
    return Collections.unmodifiableList(player.getInventory());
  }
  
  /**
   * Retrieve the number indicating the level that this maze is representing.
   * @return the level of this maze
   */
  public int getLevel() {
    return level;
  }

  /**
   * Check what the current state of the game is.
   * @return the current state of the game
   */
  public GameState getStatus() {
    return status;
  }
  
  /**
   * Check what the current state of the game is.
   * @return a special even if it occurred in the last player move otherwise null
   */
  public SpecialEvent getLastSpecialEvent() {
    return lastEvent;
  }
  
  /**
   * Return an info message cased by the current move.
   * Currently only InfoTile has the ability to generate a message after a move
   * 
   * @return an info message or null if the current tile has no info
   */
  public String getInfo() {
    checkState(info == null ^ lastEvent == SpecialEvent.INFO_POINT,
        "The player should have just moved to an info tile if an info message is avaliable");
    return info;
  }
  
  //---------------------------------------------------------------//
  //       END of methods to investigate the game status           //
  //---------------------------------------------------------------//
  
  /**
   * Get the position of the player within the board.
   * This is mainly used to optimise the render process
   * @return a point holding a copy of the player's coordinates
   */
  public Point getPlayerPosition() {
    checkArgument(isPlayerPosValid());
    return new Point(player.getPosition().x, player.getPosition().y);
  }
  
  /**
   * Check whether the position store in the player object match the board.
   * @return whether the tile at the player coordinate actually contains a player
   * @throws RuntimeException is the player is undefined or has an invalid position
   */
  private boolean isPlayerPosValid() {
    /*
    if (player == null) {
      throw new RuntimeException("There must be a player on the board");
    }
    */
    //Get the coordinates of the tile where the player should be located
    Point playerPos = player.getPosition();
    
    if (!board.isPointInsideBoard(playerPos)) {
      //Player position is outside the board;
      throw new RuntimeException("The player must be within the board boundaries");
    }
    
    //Validate the player position against the board, hence check that tile contains a player
    if (!board.getTile(playerPos).containsItemType(Player.class)) {
      throw new RuntimeException(
          "The position of the given player doesn't match the player tile in the board");
    }
    
    return true;    
  }
  
  
  
  
  
  /* 
   * for when the timer is over?
  public void setStatus(GameState status) {
    this.status = status;
  }
  */

  /*
   * Manual Test.
   * 
   * @param args initial arguments
   */
  /*
  public static void main(String... args) {
    //Preconditions.checkState(test, "test");
    System.out.println("The sky is " + Key.Colour.BLUE);
    
    //assert(false);
  }
  
  */
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
