package nz.ac.vuw.ecs.swen225.gp20.render;


import nz.ac.vuw.ecs.swen225.gp20.maze.Maze;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Player;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.Tile;

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

    private Model model;
    private View view;
    private Maze maze;

    public Render(Maze maze) {
    	this.maze = maze;
        model = new Model();
        view = new View(model);
    }

    private void movePlayer() {
    	maze.getBoard();
        view.refresh();
    }

    /*testing only */
    public static void main(String[] args) {
    	Maze m = new Maze(null, null, 1, 1);
        new Render(m);
    }
}

//game controller is fine

class View {

    private final static int GAP = 2;
    Model model;
    private MainPanel mainPanel;

    View(Model model){
        this.model = model;
        createAndShowGUI();
    }

    void refresh() {
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
            GridLayout layout = new GridLayout(model.getBoardRows(), 
            model.getBoardCols());
            setLayout(layout);

            for (int i = 0; i <model.getBoardRows(); i++)   {

                for (int j = 0; j < model.getBoardCols(); j++)  {
                	Tile t = new Tile();
                	String[][] Board = model.getBoard();
                	ImageIcon ic = new ImageIcon(Board[i][j] + ".png");
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

    class Tile extends JLabel {

        Tile() {
            setPreferredSize(new Dimension(model.getSquareSize(), model.getSquareSize()));
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

class Model{

	String[][] board = {
			{"wallTile", "wallTile","wallTile","wallTile"},
			{"wallTile", "freeTile","freeTile","wallTile"},
			{"wallTile", "chapTile","freeTile","wallTile"},
			{"wallTile", "wallTile","wallTile","wallTile"}		
	};
	
    private int boardRows = 4, boardCols = 4, squareSize = 41;

    String[][] getBoard() {return board;}
    
    int getBoardRows() {return boardRows; }

    int getBoardCols() {return boardCols; }

    int getSquareSize() {return squareSize; }
}
