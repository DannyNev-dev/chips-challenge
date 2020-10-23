package nz.ac.vuw.ecs.swen225.gp20.render;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import nz.ac.vuw.ecs.swen225.gp20.maze.Board;
import nz.ac.vuw.ecs.swen225.gp20.maze.Maze;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Entity;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.Tile;

/**
 * Render class This class renders the maze and provides a return method for the
 * GUI.
 * 
 * @author Jordan Simmons
 **/
public class Render {

	private View view;
	private Maze maze;

	/**
	 * Constructor for the the Render class
	 * 
	 * @param maze - this is the game state pulled from maze package
	 */
	public Render(Maze maze) {
		this.maze = maze;
		view = new View(maze);
	}

	/**
	 * This updates the JPanel and changes the current view of the game
	 */
	public void updateRender() {
		// maze.getBoard();
		view.refresh(maze);
		// view.updateLastPosition();
	}

	/**
	 * Returns the current JPanel to Application package
	 * 
	 * @return JPanel of the updated gameboard
	 */
	public JPanel getView() {
		return view.getBoardPanel();
	}
}

/**
 * View Class creates a JPanel to return to Application Package
 * 
 * @author Jordan Simmons
 *
 */

class View {

	private DispTile[][] dispList;
	private Maze maze;
	private int xSize;
	private int ySize;
	Tile[][] board;
	Board oldBoard;
	private int tileSize = 50;
	private BoardPanel BoardPanel;
	private Point lastPosition = null;
	private ArrayList<String> oldToken = new ArrayList<String>();
	private Point lastPoint = new Point(-100, -100);
	private boolean updateChip = false;

	/**
	 * Constructor for View
	 * 
	 * @param maze - the game state from the maze application
	 */
	View(Maze maze) {
		this.maze = maze;
		board = maze.getBoard();
		oldBoard = new Board(maze.getBoard());
		xSize = 9; // board.length;
		ySize = 9; // board[0].length;
		dispList = new DispTile[xSize][ySize];
		createAndShowGUI();
	}

	/**
	 * Refreshes the view of the maze. This is called when a change in the maze
	 * occurs
	 * 
	 * @param maze - the game state from the maze application
	 */
	void refresh(Maze maze) {

		Point p = maze.getPlayerPosition();
		//Checks to see if chip has moved since last update to avoid unneeded rendering of certain tiles.
		if (!lastPoint.equals(p)) {
			updateChip = true;
			lastPoint = new Point(p.x, p.y);
		}
		String moveDirection = findDirection(p);
		board = maze.getBoard();
		Point lastP = new Point(lastPosition);
		//Checks to see if there was a special event on the last move so we can handle it accordingly
		String specEvent;
		if (maze.getLastSpecialEvent() == null) {
			specEvent = "null";
		} else {
			specEvent = maze.getLastSpecialEvent().toString();
		}

		//Beginning of the renderer update
		//Establish the variable for timer
		int delay = 75; // milliseconds
		//ActionLister used by timer
		ActionListener taskPerformer = new ActionListener() {
			int count = 1;

			public void actionPerformed(ActionEvent evt) {
				//Only allows 4 animations
				if (count == 5) {
					return;
				}
				//If player moved in a way they are not allowed too display confused animatin and confused sound.
				if (specEvent.equals("MOVE_REJECTED")) {
					int x = p.x - 4;
					for (int i = 0; i < xSize; i++) {
						int y = p.y - 4;
						for (int j = 0; j < ySize; j++) {
							if (p.x == x && p.y == y && count == 1) {
								playSound("obstruction");
							}
							if (p.x == x && p.y == y && count < 4) {
								dispList[i][j].setIcon(
										new ImageIcon("src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/chipConfused"
												+ count + ".png"));
							}
							if (p.x == x && p.y == y && count == 4) {
								dispList[i][j].setIcon(new ImageIcon("src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/"
										+ board[x][y].getName() + ".png"));
							}

							y++;
						}
						x++;
					}
					count++;
				} // Called when chip dies from fire
				else if (specEvent.equals("CHAP_DIED_BURNT")) {
					if (count == 1) {
						playSound("gameOver");
					}
					int x = p.x - 4;
					for (int i = 0; i < xSize; i++) {
						int y = p.y - 4;
						for (int j = 0; j < ySize; j++) {
							if (p.x == x && p.y == y) {
								dispList[i][j].setIcon(new ImageIcon(
										"src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/death" + count + ".png"));
							}
							y++;
						}
						x++;
					}
					count++;
				} //Called when chip gets killed generically 
				else if (specEvent.equals("CHAP_DIED_MURDERED")) {
					playSound("gameOver");
					int x = p.x - 4;
					for (int i = 0; i < xSize; i++) {
						int y = p.y - 4;
						for (int j = 0; j < ySize; j++) {
							if (p.x == x && p.y == y) {
								dispList[i][j].setIcon(new ImageIcon(
										"src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/death" + count + ".png"));
							}
							y++;
						}
						x++;
					}
					count++;
				} //Renders the board under the impression no special events occurred
				else {
					/*
					 * constructs a nested for loop to loop through display list
					 * Initiate x and y variables to pull information from the board (maze class)
					 * X and Y variables are constructed in such a way they link up to i and j. This
					 * allows us to get corresponding items from board.
					  */
					int x = p.x - 4;
					for (int i = 0; i < xSize; i++) {
						int y = p.y - 4;
						for (int j = 0; j < ySize; j++) {
							int newPosOldToken = x;
							//updates chips last position. Used for animation purposes
							if (lastP.x == x && lastP.y == y && updateChip) {
								playMoveAnimation(count, i, j, x, y, p, moveDirection, true, newPosOldToken);
							} //updates chips current position used for animation purposes
							else if (p.x == x && p.y == y && updateChip) {
								//used to find chips move direction along. Returns an int to be used to reference
								// oldToken which stores all the previous tiles around chip.
								switch (moveDirection) {
								case "Left":
									newPosOldToken = 0;
									break;
								case "Right":
									newPosOldToken = 1;
									break;
								case "Up":
									newPosOldToken = 2;
									break;
								case "Down":
									newPosOldToken = 3;
									break;
								}
								//Calls sound affects for all items when they are interacted with
								if ((count == 1 && oldToken.get(newPosOldToken).equals("treasureTile"))
										|| (count == 1 && oldToken.get(newPosOldToken).equals("redLockedTile"))
										|| (count == 1 && oldToken.get(newPosOldToken).equals("greenLockedTile"))
										|| (count == 1 && oldToken.get(newPosOldToken).equals("blueLockedTile"))
										|| (count == 1 && oldToken.get(newPosOldToken).equals("blueKeyTile"))
										|| (count == 1 && oldToken.get(newPosOldToken).equals("redKeyTile"))
										|| (count == 1 && oldToken.get(newPosOldToken).equals("greenKeyTile"))
										|| (count == 1 && oldToken.get(newPosOldToken).equals("exitLockTile"))
										|| (count == 1 && oldToken.get(newPosOldToken).equals("exitTile"))
										|| (count == 1 && oldToken.get(newPosOldToken).equals("fireTile"))
										|| (count == 1 && oldToken.get(newPosOldToken).equals("waterBucketTile"))
										|| (count == 1 && oldToken.get(newPosOldToken).equals("medicineTile"))) {
									playSound(oldToken.get(newPosOldToken));
								}
								//upon ending of animation this sets the tile that chip was on previously to
								//the tile it should be in the current state
								if (count < 3) {
									dispList[i][j]
											.setIcon(new ImageIcon("src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/"
													+ oldToken.get(newPosOldToken) + ".png"));
								}
								//calls chip animation
								playMoveAnimation(count, i, j, x, y, p, moveDirection, false, newPosOldToken);
								//updates the tiles around chip to be used in the next render update
								if (count == 4) {
									oldToken.clear();
									oldToken.add(0, board[x][y - 1].getName());
									oldToken.add(1, board[x][y + 1].getName());
									oldToken.add(2, board[x - 1][y].getName());
									oldToken.add(3, board[x + 1][y].getName());
								}
								//renders all tiles on the board with the exception of chip and his animations
							} else {
								if (dispList[i][j] != null) {
									if (board[x][y].getName().equals("bugTile")) {
										Entity bug = (Entity) board[x][y].getItem();
										dispList[i][j].setIcon(new ImageIcon(bug.getFile(board[x][y].getName())));
									} else {
										dispList[i][j]
												.setIcon(new ImageIcon("src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/"
														+ board[x][y].getName() + ".png"));
									}
								}
							}
							y++;
						}
						x++;
					}

					count++;
					BoardPanel.repaint();
				}
			}

		};
		new Timer(delay, taskPerformer).start();

		oldBoard = new Board(maze.getBoard());
		lastPosition = maze.getPlayerPosition();
	}

	/**
	 * Facilitates the animation for player movements
	 * 
	 * @param count          - Passes the count from the timer so specific
	 *                       animations can be carried out
	 * @param i              - Location (i) in the for loop to reference dispList
	 *                       which is the view
	 * @param j              - Location (j) in the for loop to reference dispList
	 *                       which is the view
	 * @param x              - Location (x) in the for loop to reference the board
	 *                       (maze class)
	 * @param y              - Location (y) in the for loop to reference the board
	 *                       (maze class)
	 * @param p              - Houses the point of the player
	 * @param moveDirection  - Provides the movement direction of the player
	 * @param last           - Advises whether animation needs to occur on the cell
	 *                       the player currently is in or the last cell the player
	 *                       was in.
	 * @param newPosOldToken - Passes the location in oldToken that houses the
	 *                       previous tile that the player was on used for animation
	 * 
	 */
	public void playMoveAnimation(int count, int i, int j, int x, int y, Point p, String moveDirection, boolean last,
			int newPosOldToken) {
		//This is called when the renderer tries to update the previous tile chip was on. 
		//This is used to animate chip "out" of the cell in a given direction
		if (last) {
			if (count < 3) {
				if (board[x][y].getName().equals("infoTile")) {
					dispList[i][j].setIcon(new ImageIcon("src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/chipInfoTile"
							+ moveDirection + count + ".png"));
				} else {
					if (moveDirection == null) {
						dispList[i][j]
								.setIcon(new ImageIcon("src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/chipTile.png"));
					} else {
						dispList[i][j].setIcon(new ImageIcon("src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/chipTile"
								+ moveDirection + count + ".png"));
					}
				}
			} else if (count >= 3) {
				dispList[i][j].setIcon(new ImageIcon(
						"src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/" + board[x][y].getName() + ".png"));
			}
		}
		//This is used to animate chips entry into the cell.
		if (!last) {
			if ((oldToken.get(newPosOldToken).equals("fireTile") && (count > 1 && count < 4))
					|| (oldToken.get(newPosOldToken).equals("treasureTile") && (count > 1 && count < 3))) {
				dispList[i][j].setIcon(new ImageIcon("src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/"
						+ oldToken.get(newPosOldToken) + "Open" + moveDirection + (count + 1) + ".png"));
				return;
			}
			if (count < 4 && count > 1) {
				dispList[i][j].setIcon(new ImageIcon("src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/"
						+ board[x][y].getName() + moveDirection + (count + 1) + ".png"));
			} else if (count == 4) {
				dispList[i][j].setIcon(new ImageIcon(
						"src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/" + board[x][y].getName() + ".png"));
			}

		}
	}

	/**
	 * Plays the sound effect corresponding to the tile
	 * 
	 * @param name - Name of tile so the correct sound can be played
	 */

	public void playSound(String name) {
		new Thread(new Runnable() {
			public void run() {
				try {
					Clip clip = AudioSystem.getClip();
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(

							Render.class.getResourceAsStream("SoundFile/" + name + ".wav"));
					clip.open(inputStream);
					clip.start();
				} catch (Exception e) {
					System.err.println("Error in play sound: " + e.getMessage());
				}
			}
		}).start();
	}

	/**
	 * Finds the direction of the player movement
	 * 
	 * @param p - takes the new point of player to be used in calculation
	 * @return - the direction in string form
	 */
	public String findDirection(Point p) {
		if (lastPosition.x > p.x) {
			return "Up";
		} else if (lastPosition.x < p.x) {
			return "Down";
		} else if (lastPosition.y > p.y) {
			return "Left";
		} else if (lastPosition.y < p.y) {
			return "Right";
		}

		return null;
	}

	public void updateLastPosition() {
		lastPosition = maze.getPlayerPosition();
	}

	/**
	 * Creates the JPanel to be returned
	 */
	private void createAndShowGUI() {
		JFrame f = new JFrame("TestFrame");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BoardPanel = new BoardPanel();
		f.add(BoardPanel);
		f.pack();
	}

	/**
	 * Gets the main panel so it can be returned to the Application Package
	 * 
	 * @return - returns a JPanel with the current game state displayed.
	 */
	public JPanel getBoardPanel() {
		return BoardPanel;
	}

	/**
	 * BoardPanel class creates the actual JPanel which is returned to Application
	 * package
	 * 
	 * @author Jordan Simmons
	 *
	 */
	@SuppressWarnings("serial")
	class BoardPanel extends JPanel {
		BoardPanel() {
			//Sets panel to a 9x9 grid (what is displayed. Board can be larger than this).
			GridLayout layout = new GridLayout(9, 9);
			setLayout(layout);
			Point p = maze.getPlayerPosition();
			//creates the board
			int x = p.x - 4;
			for (int i = 0; i < xSize; i++) {
				int y = p.y - 4;
				for (int j = 0; j < ySize; j++) {
					if (x == p.x && y == p.y) {
						oldToken.add(0, board[x][y - 1].getName());
						oldToken.add(1, board[x][y + 1].getName());
						oldToken.add(2, board[x - 1][y].getName());
						oldToken.add(3, board[x + 1][y].getName());
					}

					DispTile t = new DispTile();
					dispList[i][j] = t;
					t.setIcon(new ImageIcon(
							"src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/" + board[x][y].getName() + ".png"));

					add(dispList[i][j]);

					y++;
				}
				x++;
			}

			lastPosition = maze.getPlayerPosition();
		}
	}

	/**
	 * This creates the tiles to be displayed using the tiles passed from the maze
	 * application.
	 * 
	 * @author Jordan Simmons
	 *
	 */
	@SuppressWarnings("serial")
	class DispTile extends JLabel {

		DispTile() {
			setPreferredSize(new Dimension(tileSize, tileSize));
		}
	}

}