package nz.ac.vuw.ecs.swen225.gp20.render;

import nz.ac.vuw.ecs.swen225.gp20.maze.Maze;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Player;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.Tile;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Render {

	private View view;
	private Maze maze;

	public Render(Maze maze) {
		this.maze = maze;
		view = new View(maze);
	}

	public void updateRender() {
		maze.getBoard();
		view.refresh(maze);
	}

	public JPanel getView() {
		return view.getMainPanel();
	}

	/*
	 * testing only public static void main(String[] args) { Maze m = new Maze(null,
	 * null, 1, 1); new Render(m); }
	 */
}

//game controller is fine

class View {

	private DispTile[][] dispList;
	private Maze maze;
	private int xSize;
	private int ySize;
	Tile[][] board;
	private int tileSize = 41;

	private final static int GAP = 2;
	private MainPanel mainPanel;

	View(Maze maze) {
		this.maze = maze;
		board = maze.getBoard();
		xSize = board.length;
		ySize = board[0].length;
		dispList = new DispTile[xSize][ySize];
		createAndShowGUI();
	}

	void refresh(Maze maze) {
		this.maze = maze;
		board = maze.getBoard();
		for (int i = 0; i < dispList.length; i++) {
			for (int j = 0; j < dispList[0].length; j++) {
				if (dispList[i][j] != null) {
					dispList[i][j].setIcon(new ImageIcon(
							"src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/" + board[i][j].getName() + ".png"));
				}
			}
		}
		mainPanel.repaint();
	}

	private void createAndShowGUI() {
		JFrame f = new JFrame("TestFrame");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel = new MainPanel();
		f.add(mainPanel);
		f.pack();
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

	JButton getButton() {
		return mainPanel.getButton();
	}

	class MainPanel extends JPanel {

		private BottomPanel bPanel;

		MainPanel() {

			setLayout(new BorderLayout(GAP, GAP));
			add(new BoardPanel(), BorderLayout.CENTER);
		}

		JButton getButton() {
			return bPanel.getButton();
		}
	}

	class BoardPanel extends JPanel {

		BoardPanel() {
			setBorder(BorderFactory.createLineBorder(Color.BLACK, GAP));
			GridLayout layout = new GridLayout(xSize - 6, ySize - 6);
			setLayout(layout);

			for (int i = 3; i < xSize - 3; i++) {

				for (int j = 3; j < ySize - 3; j++) {
					DispTile t = new DispTile();
					dispList[i][j] = t;
					ImageIcon ic = new ImageIcon(
							"src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/" + board[i][j].getName() + ".png");
					t.setIcon(ic);
					add(t);
				}
			}

		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
		}
	}

	class DispTile extends JLabel {

		DispTile() {
			setPreferredSize(new Dimension(tileSize, tileSize));
			setBorder(BorderFactory.createLineBorder(Color.BLACK, GAP));
		}
	}

	class Player extends JLabel {

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.BLUE);
		}
	}

	class BottomPanel extends JPanel {

		JButton button = new JButton("Move Player");

		BottomPanel() {
			add(button);
		}

		JButton getButton() {
			return button;
		}
	}
}