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

    /*testing only */
    public static void main(String[] args) {
    	Maze m = new Maze(null, null, 1, 1);
        new Render(m);
    }
}

//game controller is fine

class View {
	
	private Maze maze;
	private int xSize;
	private int ySize;
	Tile[][] board;
	private int tileSize = 41;

    private final static int GAP = 2;
    private MainPanel mainPanel;

    View(Maze maze){
    	this.maze = maze;
    	board = maze.getBoard();
    	xSize = board.length;
    	ySize = board[0].length;
        createAndShowGUI();
    }

    void refresh(Maze maze) {
    	this.maze = maze;
        mainPanel.repaint();
    }

    private void createAndShowGUI() {
        JFrame f = new JFrame("TestFrame");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel = new MainPanel();
        f.add(mainPanel);
        f.pack();
        f.setVisible(true);
    }
    
    public JPanel getMainPanel() {
    	return mainPanel;
    }

    JButton getButton() {   return mainPanel.getButton();   }

    class MainPanel extends JPanel {

        private BottomPanel bPanel;

        MainPanel() {
        	
            setLayout(new BorderLayout(GAP,GAP));
            add(new BoardPanel(), BorderLayout.CENTER);
        }

        JButton getButton() {   return bPanel.getButton();  }
    }

    class BoardPanel extends JPanel {
    	
        BoardPanel()   {
            setBorder(BorderFactory.createLineBorder(Color.BLACK, GAP));
            GridLayout layout = new GridLayout(xSize, 
            ySize);
            setLayout(layout);

            for (int i = 0; i < xSize; i++)   {

                for (int j = 0; j < ySize; j++)  {
                	DispTile t = new DispTile();
                	ImageIcon ic = new ImageIcon(board[i][j].getName() + ".png");
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

    class Player extends JLabel{

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLUE);
        }
    }

    class BottomPanel extends JPanel {

        JButton button = new JButton("Move Player");

        BottomPanel(){
            add(button);
        }

        JButton getButton() {   return button;  }
    }
}