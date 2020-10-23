package nz.ac.vuw.ecs.swen225.gp20.maze;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.awt.Point;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Collectable;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Entity;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Player;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Treasure;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.ExitTile;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.InfoTile;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.Tile;
import nz.ac.vuw.ecs.swen225.gp20.persistence.LevelReader;

/**
 * This class manages the logic for the game.
 * It controls what actions are allowed to change the state of those objects
 * 
 * @author Emanuel Evans
 *
 */
public class Maze {
  
  //Reference to the main player controlled by the user
  private Player player;
  
  //Representation of the board state
  private Board board;
  
  /**
   * Total number of chips to be collect in the level of this maze.
   */
  public final int target;
  
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
  
  /**
   * Represent a special interaction of the player with the board.
   * @author Emanuel Evans (ID: 300472656)
   *
   */
  public static enum SpecialEvent {
    /**
     * When Chap picks up a key and puts it in his inventory.
     */
    KEY_PICKED_UP,
    /**
     * When Chap picks up a water bucket and puts it in his inventory.
     */
    BUCKET_PICKED_UP,
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
     * When another entity kills the player.
     */
    CHAP_DIED_MURDERED,
    /**
     * Chap dies when he visited a dangerous cell.
     * This value specified that he died because of poisoning
     */
    CHAP_DIED_POISONED,
    /**
     * The player cured themselves from the poison.
     */
    POISONED_CURED,
    /**
     * Specify that the player has died due to a fire.
     */
    CHAP_DIED_BURNT,
    /**
     * Chap extinguish a fire.
     */
    FIRE_EXTINGUISHED,
    /**
     * When the player is on the info tile.
     */
    INFO_POINT,
    /**
     * This events is used to indicate a move being rejected.
     * For instance when the player moves to an inaccessible cell like a wall.
     */
    MOVE_REJECTED
    
  }
  
  /**
   * Create a new maze given a level.
   * @param level of the maze this cannot be negative
   * @throws IOException if no object has that level
   */
  public Maze(int level) throws IOException {
    checkArgument(level > 0, "levels can't be negative, level 1 is the first level");
    LevelReader loader = new LevelReader(level);
    this.player = loader.loadPlayer();
    this.board = new Board(loader.loadBoard());
    this.target = loader.loadTarget();
    this.level = level;
    loader.setMaze(this);
    assert (isPlayerPosValid());
    assert (areChipCollectedMatching());
    
  }
  
  /**
   * Create a new maze given an already created loader.
   * @param loader object used to obtain the data. This cannot be null.
   */ 
  public Maze(LevelReader loader) {
    checkArgument(loader != null, "The loader must be well defined");
    this.player = loader.loadPlayer();
    this.board = new Board(loader.loadBoard());
    this.target = loader.loadTarget();
    this.level = loader.getLevelNum();
    loader.setMaze(this);
    assert (isPlayerPosValid());
    assert (areChipCollectedMatching());
    
  }

  /**
   * Move the player from it's current position to a different tile in the board.
   * This require the player to be in a valid position when the method is invoked.
   * Which includes that the position store in the player object match the tile
   * where the player is located in the board. And that the player is allowed to 
   * stand on that tile.
   * Moreover it requires the state invariants such that the number of treasures is 
   * constant to be true. 
   * 
   * @param move indicates where should the player move to. This cannot be null.
   * @return whether the move was successful
   * 
   */
  public boolean movePlayer(Move move) {
    checkArgument(isPlayerPosValid());
    boolean successful = false;
    
    if (moveEntity(move, player)) {
      
      player.setOrientation(move.getLastDirection());
      successful = true;
    } else {
      lastEvent = SpecialEvent.MOVE_REJECTED;
    }
    
    //Check that there the player position matches and that there is exactly one player on the board
    assert (isPlayerPosValid());
    return successful;
  }
  
  /**
   * Move an Entity of the board.
   * This require the given entity to be in a valid position when the method is invoked.
   * Which includes that the position store in the entity object match the tile
   * where the entity is located in the board. And that the entity is allowed to 
   * stand on that tile.
   * Moreover it requires the state invariants such that the number of treasures is 
   * constant to be true.
   * 
   * @param move indicates where the entity should move to. This cannot be null.
   * @param movingEntity who needs to be moved
   * @return whether the move was successful
   */
  public boolean moveEntity(Move move, Entity movingEntity) {
    checkArgument(move != null, "A well initialized move is required");
    checkArgument(isEntityPosValid(movingEntity));
    checkState(areChipCollectedMatching(), 
        "The game status is invalid, the number of treasures left in the board do not match");
    
    if (status != GameState.PLAYING) {
      //Moves can't be applied unless the game is active
      return false;
    }
    

    lastEvent = null; //Reset any special events from the last movement
    info = null;
    
    Point oldPos = movingEntity.getPosition();
    
    Point newPos = move.getDestination(oldPos);
    
    if (!board.isPointInsideBoard(newPos)) {
      //Trying to move outside the board;
      return false;
    }
    
    //Tile where the entity is trying to move into
    Tile newTile = board.getTile(newPos);
    
    //Check if entity can enter the new tile
    if (!newTile.isAccessible(movingEntity)) {
      return false;
    }
    
    //Remove entity from old tile
    board.getTile(oldPos).replaceItem(null);
    //Change entity position
    movingEntity.setPosition(newPos);
    
    if (newTile.hasAction()) {
      //An action is an interaction with an item which might trigger a special event to be recorded
      //For instance a key could be picked up or a door opened
      lastEvent = newTile.applyAction(movingEntity);
      
    }
    
    //Add entity to new tile
    board.getTile(newPos).replaceItem(movingEntity);
    
    //Note that if the entity will die in this move the movement will still be valid
    updateStatus();
    
    assert (isEntityPosValid(movingEntity));
    assert (areChipCollectedMatching());
    
    return true;
  }
  
  
  
  //---------------------------------------------------------------//
  //       START of methods to investigate the game status         //
  //---------------------------------------------------------------//
  
  /**
   * Get the number of chips which the player still have to collect to complete the level.
   * @return number of chips which are left in the board
   */
  public int getChipsLeft() {
    checkState(areChipCollectedMatching());
    
    int remainingChips = target - player.getChipsCollected();
    return remainingChips;
  }
  
  /**
   * Get the list of items collected by the player.
   * This list does not includes chips (treasures), use getChipsLeft() to obtain that information
   * The player must have initialised, this is done in the constructor and should always be true. 
   * @return unmodifiable list of collected items
   */
  public List<Collectable> getPlayerInventory() {
    checkNotNull(player, "There must be a player on the board to get its inventory");
    return Collections.unmodifiableList(player.getInventory());
  }
  
  /**
   * Retrieve the number indicating the level that this maze is representing.
   * Note that once the maze has been created the level will not change.
   * @return the level of this maze
   */
  public int getLevel() {
    return level;
  }

  /**
   * Check what the current state of the game is.
   * It differentiate from when the game has been won, lost or if it's still on.
   * @return the current state of the game
   */
  public GameState getStatus() {
    return status;
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
  
  /**
   * Check if the player has interacted with the board in a special way during the last move.
   * @return a special event if it occurred in the last player move otherwise null
   */
  public SpecialEvent getLastSpecialEvent() {
    return lastEvent;
  }
  
  //---------------------------------------------------------------//
  //       END of methods to investigate the game status           //
  //---------------------------------------------------------------//
  
  
  //---------------------------------------------------------------//
  //     START of methods to obtain data to render the maze        //
  //---------------------------------------------------------------//
  
  /**
   * Get the position of the player within the board.
   * This is mainly used to optimise the render process
   * Note that if the player has just been murdered the position where he died
   * will be retured even though the player is no longer on that tile.
   * @return a point holding a copy of the player's coordinates
   */
  public Point getPlayerPosition() {
    checkArgument(lastEvent == SpecialEvent.CHAP_DIED_MURDERED || isPlayerPosValid());
    return new Point(player.getPosition().x, player.getPosition().y);
  }
  
  /**
   * Get a copy of the player in this maze.
   * Note that if the player has just been murdered it will not be on the board anymore
   * but it will still be returned and its's position will hold the coordinates of its last tile.
   * @return a clone of the player in this maze
   */
  public Player getPlayer() {
    checkArgument(lastEvent == SpecialEvent.CHAP_DIED_MURDERED || isPlayerPosValid());
    return player.clone();
  }
  
  /**
   * Return a copy of the board data.
   *@return board grouping data about the maze's tiles
   */
  public Tile[][] getBoard() {
    return board.getBoard();
  }
  
  /**
   * Return a clone of the board.
   * Ideally this will be the only method to get the board. TODO .
   * @return board grouping data about the maze's tiles
   */
  public Board getBoardObject() {
    return board.clone();
  }
  
  //---------------------------------------------------------------//
  //       END of methods to obtain data to render the maze        //
  //---------------------------------------------------------------//
  
  
  /**
   * Update the status of the game based on the special events which occurred during the last move.
   */
  private void updateStatus() {
    if (lastEvent != null && lastEvent.name().contains("CHAP_DIED")) {
      //Some tile or item has kill the player
      status = GameState.GAME_LOST;
      
    } else if (lastEvent == SpecialEvent.INFO_POINT) {
      Tile playerTile = board.getTile(player.getPosition());
      
      assert (playerTile instanceof InfoTile) : 
        "The info point event should only occur when the player is on an info tile";
      
      info = ((InfoTile) playerTile).getInfo();
      
    } else if (isGameWon()) {
      //The player has reached the final tile
      status = GameState.GAME_WON;
      System.out.print("Well done you completed the level!!!");
    }
  }
  
  /**
   * Check whether the player has won the game.
   * @return whether the player has won the game.
   */
  private boolean isGameWon() {
    //Get the coordinates of the tile where the player should be located
    Point playerPos = player.getPosition();
    
    //Check if it is on the exit tile
    return board.getTile(playerPos) instanceof ExitTile;
  }
  
  /**
   * Check whether the position store in the player object match the board.
   * @return whether the tile at the player coordinate actually contains a player
   * @throws RuntimeException is the player is undefined or has an invalid position
   */
  private boolean isPlayerPosValid() {
    checkNotNull(player, "There must be a player on the board to validate its position");
    
    return isEntityPosValid(player) && isThereOnlyOnePlayer();    
  }
  
  /**
   * Check whether the given entity position is valid.
   * It looks if it is within the board and if the coordinates match the state of the board
   * @param entity to check its position. It cannot be null.
   * @return whether the given entity's position is valid
   */
  private boolean isEntityPosValid(Entity entity) {
    checkNotNull(entity, "The give entity must be well defined to validate its position");
    
    //Get the coordinates of the tile where the entity should be located
    Point entityPos = entity.getPosition();
    
    if (!board.isPointInsideBoard(entityPos)) {
      //Entity position is outside the board;
      throw new RuntimeException(entity.getName() + " must be within the board boundaries");
    }
    
    //Validate the entity position against the board hence check that tile contains the given entity
    if (!board.getTile(entityPos).contains(entity)) {
      throw new RuntimeException(
          "The position stated in the given entity (" + entity.getName() 
          + ") doesn't match its actual position in the board");
    }
    
    //This check will cover several situation including if the player will ever stand on a wall
    if (!board.getTile(entityPos).isAccessible(entity)) {
      throw new RuntimeException(entity.getName() 
          + " is not allowed to stand on this tile");
    }
    
    return true;
  }
  
  /**
   * Check that exactly one player is on the board.
   * @return True if there is exactly one player, false otherwise
   */
  private boolean isThereOnlyOnePlayer() {
    return board.countItems(Player.class) == 1;
  }
  
  /**
   * Check whether the chips which the player declares to have collected match the board status.
   * Hence, if they are the same the number of treasures removed from the board since the start 
   * of the game.
   */
  private boolean areChipCollectedMatching() {
    int chipsOnBoard = board.countItems(Treasure.class);
    if (chipsOnBoard < 0) {
      throw new RuntimeException("The chips that still have to be collected can't be negative");
      
    } else if (player.getChipsCollected() < 0) {
      throw new RuntimeException("The chips collected by the player can't be negative");
      
    } else if (chipsOnBoard != (target - player.getChipsCollected())) {
      throw new RuntimeException(
          "The number of treasures which have been collected from the board does not match"
          + "the number of treasures the player decleare to have collected");
    }
    return  true;
  }
}
