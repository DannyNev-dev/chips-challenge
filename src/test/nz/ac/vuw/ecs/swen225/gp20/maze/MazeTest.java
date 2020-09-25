package test.nz.ac.vuw.ecs.swen225.gp20.maze;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;

import org.junit.jupiter.api.Test;

import nz.ac.vuw.ecs.swen225.gp20.maze.Maze;
import nz.ac.vuw.ecs.swen225.gp20.maze.Move;
import nz.ac.vuw.ecs.swen225.gp20.maze.SingleMove;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Player;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.ItemTile;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.Tile;

class MazeTest {

  @Test
  void movePlayerTest() {
    MazeWrapper mazeWrapper = new MazeWrapper(5, new Point(1,1));
    
    mazeWrapper.getMaze().movePlayer(new SingleMove(Move.Direction.UP));
    
    assert(mazeWrapper.getBoard()[0][1].containsItemType(Player.class));
    
    
    assert(true);
  }
  
  /**
   * Allows wider access to the board for testing purpose
   * @author Emanuel Evans (ID: 300472656)
   *
   */
  private class MazeWrapper{
    private Maze maze;
    private Tile[][] board;
    
    private MazeWrapper(int size, Point playerPos){
      board = new Tile[size][size];
      for(int row = 0; row < size; row++) {
        for(int col = 0; col < size; col++) {
          board[row][col] = new ItemTile(null);
        }
      }
      Player p = new Player(playerPos);
      
      board[playerPos.x][playerPos.y].replaceItem(p);
      
      maze = new Maze(p, board, 0, 1);
    }
    
    private Maze getMaze(){ return maze; }
    
    private Tile[][] getBoard(){ return board; }
    
  }

  
  //Check there is exactly one playerTile in the board

}
