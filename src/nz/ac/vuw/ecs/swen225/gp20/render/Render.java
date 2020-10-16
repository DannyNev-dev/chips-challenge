package nz.ac.vuw.ecs.swen225.gp20.render;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import javafx.animation.Animation;
import nz.ac.vuw.ecs.swen225.gp20.maze.Board;
import nz.ac.vuw.ecs.swen225.gp20.maze.Maze;
import nz.ac.vuw.ecs.swen225.gp20.maze.Maze.SpecialEvent;
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
		int delay = 100; // milliseconds
		ActionListener taskPerformer = new ActionListener() {
			int count = 0;
			int dispPlayerX = -1;
			int dispPlayerY = -1;

			int dispLastPlayerX = -1;
			int dispLastPlayerY = -1;

			public void actionPerformed(ActionEvent evt) {
				if (count == 4) {
					return;
				}

				// dispList = new DispTile[xSize][ySize];
				if (count == 0) {
					System.out.println(count);
					int x = p.x - 4;
					for (int i = 0; i < xSize; i++) {
						int y = p.y - 4;
						for (int j = 0; j < ySize; j++) {
							if (lastP.x == x && lastP.y == y) {

								dispLastPlayerX = i;
								dispLastPlayerY = j;
								System.out.println("LAST: " + dispLastPlayerX + " " + dispLastPlayerY);
								dispList[i][j].setIcon(
										new ImageIcon("src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/chipRight1.png"));
							} else if (p.x == x && p.y == y) {
								int newPosOldToken = x;
								switch (moveDirection) {
								case "Left":
									newPosOldToken = 0;
								case "Right":
									newPosOldToken = 1;
								case "Up":
									newPosOldToken = 2;
								case "Down":
									newPosOldToken = 3;
								}
								dispList[i][j].setIcon(
										new ImageIcon("src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/" +oldToken.get(newPosOldToken) + ".png"));
								dispPlayerX = i;
								dispPlayerY = j;
								System.out.println("Current ");
								System.out.println("oldPosTile ");
								oldToken.clear();
								oldToken.add(0, board[x][y-1].getName());
								oldToken.add(1, board[x][y+1].getName());
								oldToken.add(2, board[x-1][y].getName());
								oldToken.add(3, board[x+1][y].getName());
								System.out.println(oldToken);
								System.out.println(board[x][y].getName());

							}else {
								if (dispList[i][j] != null) {

									dispList[i][j]
											.setIcon(new ImageIcon("src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/"
													+ board[x][y].getName() + ".png"));

								}
							}
							y++;
						}
						x++;
					}
				}
				if (count == 1) {
					System.out.println(count);
					// updates current sport
					dispList[dispPlayerX][dispPlayerY]
							.setIcon(new ImageIcon("src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/chipRight3.png"));
					// updates previous spot
					dispList[dispLastPlayerX][dispLastPlayerY]
							.setIcon(new ImageIcon("src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/chipRight2.png"));
				} else if (count == 2) {
					System.out.println(count);
					// updates current sport
					dispList[dispPlayerX][dispPlayerY]
							.setIcon(new ImageIcon("src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/chipRight4.png"));
					// updates previous spot
					dispList[dispLastPlayerX][dispLastPlayerY]
							.setIcon(new ImageIcon("src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/freeTile.png"));

				} else if (count == 3) {
					System.out.println(count);
					// updates current sport
					dispList[dispPlayerX][dispPlayerY]
							.setIcon(new ImageIcon("src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/chipTile.png"));

				}

				count++;

			}
		};
		new Timer(delay, taskPerformer).start();

//LETS FIX THE ANIMATION
//		// ANIMATION
//		int delay = 1; // milliseconds
//		ActionListener taskPerformer = new ActionListener() {
//			int count = 0;
//
//			public void actionPerformed(ActionEvent evt) {
//				if (count == 4) {
//					return;
//				}
//				System.out.println(count);
//				// dispList = new DispTile[xSize][ySize];
//				int x = p.x-4;
//				for (int i = 0; i < xSize; i++) {
//					int y = p.y-4;
//					for (int j = 0; j < ySize; j++) {
//						if ((p.x == x && p.y == y) && lastPosition != p) {
//							System.out.println("test");
//							try {
//								final Image[] frames = {
//										ImageIO.read(new File("src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/chipRight"
//												+ 1 + ".png")),
//										ImageIO.read(new File("src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/chipRight"
//												+ 2 + ".png")),
//										ImageIO.read(new File("src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/chipRight"
//												+ 3 + ".png")),
//										ImageIO.read(new File("src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/chipRight"
//												+ 4 + ".png")) };
//
//								dispList[i][j].setIcon(new ImageIcon(frames[count]));
//								//BoardPanel.add(dispList[i][j]);
//								
//							} catch (IOException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//
//						} else {
//							try {
//								Image[] frames = {
//										ImageIO.read(new File("src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/"
//												+ board[x][y].getName() + ".png")),
//										ImageIO.read(new File("src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/"
//												+ board[x][y].getName() + ".png")),
//										ImageIO.read(new File("src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/"
//												+ board[x][y].getName() + ".png")),
//										ImageIO.read(new File("src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/"
//												+ board[x][y].getName() + ".png")) };
//								if (dispList[i][j] != null) {
//									System.out.println("spam");
//
//									
//									dispList[i][j].setIcon(new ImageIcon(frames[count]));
//									//BoardPanel.add(dispList[i][j]);
//									
//								}
//							} catch (IOException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//						}
//						/*
//						 * 
//						 * if(p.x == i && p.y == j) {
//						 * System.out.println("src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/chipRight"
//						 * + count + ".png"); DispTile t = new DispTile(); dispList[i][j] = t; ImageIcon
//						 * ic = new ImageIcon(
//						 * "src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/chipRight" + count + ".png");
//						 * t.setIcon(ic); } else { DispTile t = new DispTile(); dispList[i][j] = t;
//						 * 
//						 * ImageIcon ic = new ImageIcon(
//						 * "src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/" + board[i][j].getName() +
//						 * ".png"); t.setIcon(ic); //add(t); }
//						 */
//						y++;
//					}
//					x++;
//				}
//				count++;
//
//			}
//		};
//		new Timer(delay, taskPerformer).start();

		/*
		 * Old stuff this.maze = maze; board = maze.getBoard(); Point p =
		 * maze.getPlayerPosition(); for (int i = p.x - 4; i <= p.x + 4; i++) {
		 * 
		 * for (int j = p.y - 4; j <= p.y + 4; j++) { if (dispList[i][j] != null) {
		 * dispList[i][j].setIcon(new ImageIcon(
		 * "src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/" + board[i][j].getName() +
		 * ".png")); } } }
		 */
		// add your elements
		// BoardPanel.revalidate();
		oldBoard = new Board(maze.getBoard());
		lastPosition = maze.getPlayerPosition();
		BoardPanel.repaint();

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
					if(x == p.x && y == p.y) {
						oldToken.add(0, board[x][y-1].getName());
						oldToken.add(1, board[x][y+1].getName());
						oldToken.add(2, board[x-1][y].getName());
						oldToken.add(3, board[x+1][y].getName());
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
			
			/*
			 * for (int i = p.x - 4; i <= p.x + 4; i++) { for (int j = p.y - 4; j <= p.y +
			 * 4; j++) { add(dispList[i][j]);
			 * 
			 * 
			 * 
			 * } }
			 */

//			// GridLayout layout = new GridLayout(xSize - 6, ySize - 6);
//			GridLayout layout = new GridLayout(9, 9);
//			setLayout(layout);
//			Point p = maze.getPlayerPosition();
//			/*
//			 * TODO Add in if statement to get player position correct
//			 *
//			 *
//			 */
//			System.out.println(lastPosition);
//			if (lastPosition == null) {
//				for (int i = p.x - 4; i <= p.x + 4; i++) {
//
//					for (int j = p.y - 4; j <= p.y + 4; j++) {
//						if (p.x == i && p.y == j) {
//							try {
//								final Image[] frames = {
//										ImageIO.read(new File("src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/chipRight"
//												+ 1 + ".png")),
//										ImageIO.read(new File("src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/chipRight"
//												+ 2 + ".png")),
//										ImageIO.read(new File("src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/chipRight"
//												+ 3 + ".png")),
//										ImageIO.read(new File("src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/chipRight"
//												+ 4 + ".png")) };
//
//								DispTile t = new DispTile();
//								dispList[i][j] = t;
//
//								ImageIcon ic = new ImageIcon(frames[0]);
//								t.setIcon(ic);
//								add(t);
//							} catch (IOException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//
//						} else {
//							try {
//								final Image[] frames = {
//										ImageIO.read(new File("src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/"
//												+ board[i][j].getName() + ".png")),
//										ImageIO.read(new File("src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/"
//												+ board[i][j].getName() + ".png")),
//										ImageIO.read(new File("src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/"
//												+ board[i][j].getName() + ".png")),
//										ImageIO.read(new File("src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/"
//												+ board[i][j].getName() + ".png")) };
//
//								DispTile t = new DispTile();
//								dispList[i][j] = t;
//
//								ImageIcon ic = new ImageIcon(frames[0]);
//								t.setIcon(ic);
//								add(t);
//							} catch (IOException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//						}
//					}
//				}
//			} else {
//
//				// ANIMATION
//				int delay = 50; // milliseconds
//				ActionListener taskPerformer = new ActionListener() {
//					int count = 0;
//
//					public void actionPerformed(ActionEvent evt) {
//						if (count == 4) {
//							return;
//						}
//						System.out.println(count);
//						// dispList = new DispTile[xSize][ySize];
//
//						for (int i = p.x - 4; i <= p.x + 4; i++) {
//
//							for (int j = p.y - 4; j <= p.y + 4; j++) {
//								if (p.x == i && p.y == j) {
//									try {
//										final Image[] frames = {
//												ImageIO.read(new File(
//														"src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/chipRight" + 1
//																+ ".png")),
//												ImageIO.read(new File(
//														"src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/chipRight" + 2
//																+ ".png")),
//												ImageIO.read(new File(
//														"src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/chipRight" + 3
//																+ ".png")),
//												ImageIO.read(new File(
//														"src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/chipRight" + 4
//																+ ".png")) };
//
//										dispList[i][j].setIcon(new ImageIcon(frames[count]));
//									} catch (IOException e) {
//										// TODO Auto-generated catch block
//										e.printStackTrace();
//									}
//
//								} else {
//									try {
//										Image[] frames = {
//												ImageIO.read(new File("src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/"
//														+ board[i][j].getName() + ".png")),
//												ImageIO.read(new File("src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/"
//														+ board[i][j].getName() + ".png")),
//												ImageIO.read(new File("src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/"
//														+ board[i][j].getName() + ".png")),
//												ImageIO.read(new File("src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/"
//														+ board[i][j].getName() + ".png")) };
//
//										dispList[i][j].setIcon(new ImageIcon(frames[count]));
//									} catch (IOException e) {
//										// TODO Auto-generated catch block
//										e.printStackTrace();
//									}
//								}
//								/*
//								 * 
//								 * if(p.x == i && p.y == j) {
//								 * System.out.println("src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/chipRight"
//								 * + count + ".png"); DispTile t = new DispTile(); dispList[i][j] = t; ImageIcon
//								 * ic = new ImageIcon(
//								 * "src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/chipRight" + count + ".png");
//								 * t.setIcon(ic); } else { DispTile t = new DispTile(); dispList[i][j] = t;
//								 * 
//								 * ImageIcon ic = new ImageIcon(
//								 * "src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/" + board[i][j].getName() +
//								 * ".png"); t.setIcon(ic); //add(t); }
//								 */
//							}
//						}
//						count++;
//
//					}
//				};
//				new Timer(delay, taskPerformer).start();
//			}
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