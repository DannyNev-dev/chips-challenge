package nz.ac.vuw.ecs.swen225.gp20.render;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import nz.ac.vuw.ecs.swen225.gp20.maze.Board;
import nz.ac.vuw.ecs.swen225.gp20.maze.Maze;
import nz.ac.vuw.ecs.swen225.gp20.maze.Maze.SpecialEvent;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.Tile;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

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
	 * Constructor for the the Render function
	 * 
	 * @param maze - this is the game state pulled from maze package
	 */
	public Render(Maze maze) {
		this.maze = maze;
		view = new View(maze);
	}

	/**
	 * This updates the JPanel so it can be returned to Application package
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
	private final static int GAP = 2;
	private BoardPanel BoardPanel;
	private Point lastPosition = null;
	private ArrayList<String> oldToken = new ArrayList<String>();

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
		String moveDirection = findDirection(p);
		board = maze.getBoard();
		// BoardPanel.removeAll();

		Point lastP = new Point(lastPosition);

		// ANIMATION
		int delay = 75; // milliseconds
		ActionListener taskPerformer = new ActionListener() {
			int count = 1;

			public void actionPerformed(ActionEvent evt) {
				if (count == 5) {
					return;
				}
				int x = p.x - 4;
				for (int i = 0; i < xSize; i++) {
					int y = p.y - 4;
					for (int j = 0; j < ySize; j++) {
						if (lastP.x == x && lastP.y == y) {
							playMoveAnimation(count, i, j, x, y, p, moveDirection, true);
						} else if (p.x == x && p.y == y) {
							int newPosOldToken = x;
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
							if((count == 1 && oldToken.get(newPosOldToken).equals("treasureTile")) 
									|| (count == 1 && oldToken.get(newPosOldToken).equals("redLockedTile"))
									|| (count == 1 && oldToken.get(newPosOldToken).equals("greenLockedTile"))
									|| (count == 1 && oldToken.get(newPosOldToken).equals("blueLockedTile"))
									|| (count == 1 && oldToken.get(newPosOldToken).equals("blueKeyTile"))
									|| (count == 1 && oldToken.get(newPosOldToken).equals("redKeyTile"))
									|| (count == 1 && oldToken.get(newPosOldToken).equals("greenKeyTile"))
									|| (count == 1 && oldToken.get(newPosOldToken).equals("exitLockTile"))
									|| (count == 1 && oldToken.get(newPosOldToken).equals("exitTile"))
									) {
								playSound(oldToken.get(newPosOldToken));
							}
							if (count < 3) {
								dispList[i][j].setIcon(new ImageIcon("src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/"
										+ oldToken.get(newPosOldToken) + ".png"));
							}
							if (count == 4) {
								oldToken.clear();
								oldToken.add(0, board[x][y - 1].getName());
								oldToken.add(1, board[x][y + 1].getName());
								oldToken.add(2, board[x - 1][y].getName());
								oldToken.add(3, board[x + 1][y].getName());
							}
							playMoveAnimation(count, i, j, x, y, p, moveDirection, false);

						} else {
							if (dispList[i][j] != null) {

								dispList[i][j].setIcon(new ImageIcon("src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/"
										+ board[x][y].getName() + ".png"));

							}
						}
						y++;
					}
					x++;
				}

				count++;
				BoardPanel.repaint();

			}
		};
		new Timer(delay, taskPerformer).start();

		oldBoard = new Board(maze.getBoard());
		lastPosition = maze.getPlayerPosition();
		// BoardPanel.repaint();

	}

	public void playMoveAnimation(int count, int i, int j, int x, int y, Point p, String moveDirection, boolean last) {
		if (last) {
			if (count < 3) {
				if (board[x][y].getName().equals("infoTile")) {
					System.out.println("LAST INFO: " + "chipInfoTile" + moveDirection + count + ".png");
					dispList[i][j].setIcon(new ImageIcon("src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/chipInfoTile"
							+ moveDirection + count + ".png"));
				} else {
					System.out.println("LAST: " + board[x][y].getName());
					dispList[i][j].setIcon(new ImageIcon("src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/chipTile"
							+ moveDirection + count + ".png"));
				}
			} else if (count >= 3) {
				dispList[i][j].setIcon(new ImageIcon(
						"src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/" + board[x][y].getName() + ".png"));
			}
		}
		if (!last) {
			if (count < 4 && count > 1) {
				System.out.println("CURRENT : " + board[x][y].getName() + moveDirection + (count + 1) + ".png");
				dispList[i][j].setIcon(new ImageIcon("src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/"
						+ board[x][y].getName() + moveDirection + (count + 1) + ".png"));
			} else if (count == 4) {
				dispList[i][j].setIcon(new ImageIcon(
						"src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/" + board[x][y].getName() + ".png"));
			}

		}
	}
	
	public void playSound(String name) {
		String soundFile = "src/nz/ac/vuw/ecs/swen225/gp20/render/SoundFile/" + name + ".wav";
	    InputStream in;
		try {
			in = new FileInputStream(soundFile);
		    AudioStream audioStream = new AudioStream(in);
		    AudioPlayer.player.start(audioStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

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
	class BoardPanel extends JPanel {
		BoardPanel() {
			GridLayout layout = new GridLayout(9, 9);
			setLayout(layout);
			Point p = maze.getPlayerPosition();

			int x = p.x - 4;
			for (int i = 0; i < xSize; i++) {
				int y = p.y - 4;
				for (int j = 0; j < ySize; j++) {
					if (x == p.x && y == p.y) {
						oldToken.add(0, board[x][y - 1].getName());
						oldToken.add(1, board[x][y + 1].getName());
						oldToken.add(2, board[x - 1][y].getName());
						oldToken.add(3, board[x + 1][y].getName());
						System.out.println(oldToken);
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
	class DispTile extends JLabel {

		DispTile() {
			setPreferredSize(new Dimension(tileSize, tileSize));
		}
	}

}