package nz.ac.vuw.ecs.swen225.gp20.render;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
		maze.getBoard();
		view.refresh(maze);
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
	private int tileSize = 50;
	private final static int GAP = 2;
	private BoardPanel BoardPanel;

	/**
	 * Constructor for View
	 * 
	 * @param maze - the game state from the maze application
	 */
	View(Maze maze) {
		this.maze = maze;
		board = maze.getBoard();
		xSize = board.length;
		ySize = board[0].length;
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
		/*
		 * this.maze = maze; board = maze.getBoard(); Point p =
		 * maze.getPlayerPosition(); for (int i = p.x-4; i <= p.x+4; i++) {
		 * 
		 * for (int j = p.y-4; j <= p.y+4; j++) { if (dispList[i][j] != null) {
		 * dispList[i][j].setIcon(new ImageIcon(
		 * "src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/" + board[i][j].getName() +
		 * ".png")); } } } BoardPanel.repaint();
		 */
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
			// GridLayout layout = new GridLayout(xSize - 6, ySize - 6);
			GridLayout layout = new GridLayout(9, 9);
			setLayout(layout);
			Point p = maze.getPlayerPosition();
			/*
			 * TODO Add in if statement to get player position correct
			 *
			 *
			 */

			for (int i = p.x - 4; i <= p.x + 4; i++) {

				for (int j = p.y - 4; j <= p.y + 4; j++) {
					DispTile t = new DispTile();
					dispList[i][j] = t;

					ImageIcon ic = new ImageIcon(
							"src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/" + board[i][j].getName() + ".png");
					t.setIcon(ic);
					add(t);

				}
			}

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