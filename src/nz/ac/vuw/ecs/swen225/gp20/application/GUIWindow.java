/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.vuw.ecs.swen225.gp20.application;

import nz.ac.vuw.ecs.swen225.gp20.maze.Maze;
import nz.ac.vuw.ecs.swen225.gp20.maze.Move;
import nz.ac.vuw.ecs.swen225.gp20.maze.SingleMove;
import nz.ac.vuw.ecs.swen225.gp20.persistence.LevelReader;
import nz.ac.vuw.ecs.swen225.gp20.recnplay.Event;
import nz.ac.vuw.ecs.swen225.gp20.recnplay.EventListener;
import nz.ac.vuw.ecs.swen225.gp20.render.Render;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

/**
 *
 * @author camilalis
 * From Master
 */
public class GUIWindow extends javax.swing.JFrame {

    /**
     * Creates new form Window
     */
    public GUIWindow() {
        numberOnPanel();
        initComponents();
        this.eventListener = EventListener.eventListenerFactory();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gameCanvas = new javax.swing.JPanel();
        levelAndTimer = new javax.swing.JPanel();
        levelText = new javax.swing.JLabel();
        levelNumber = new javax.swing.JLabel();
        timerText = new javax.swing.JLabel();
        timer = new javax.swing.JLabel();
        boardCanvas = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        gameButton = new javax.swing.JMenu();
        newGame = new javax.swing.JMenuItem();
        replay = new javax.swing.JMenuItem();
        fileButton = new javax.swing.JMenu();
        saveButton = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 207, 18));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        gameCanvas.setBackground(new java.awt.Color(0, 208, 18));

        levelAndTimer.setBackground(new java.awt.Color(204, 204, 204));
        levelAndTimer.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, null, new java.awt.Color(102, 102, 102)));

        levelText.setBackground(new java.awt.Color(0, 0, 0));
        levelText.setFont(new java.awt.Font("Arial Narrow", 1, 48)); // NOI18N
        levelText.setForeground(new java.awt.Color(0, 153, 153));
        levelText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        levelText.setText("L E V E L");

        levelNumber.setBackground(new java.awt.Color(0, 0, 0));
        levelNumber.setFont(new java.awt.Font("Arial Narrow", 1, 80)); // NOI18N
        levelNumber.setForeground(new java.awt.Color(0, 0, 204));
        levelNumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        levelNumber.setToolTipText("");
        levelNumber.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(0, 0, 0), null, null));
        levelNumber.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        timerText.setFont(new java.awt.Font("Arial Narrow", 1, 48)); // NOI18N
        timerText.setForeground(new java.awt.Color(0, 153, 153));
        timerText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timerText.setText("T I M E");
        timerText.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        timer.setBackground(new java.awt.Color(0, 0, 0));
        timer.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 55)); // NOI18N
        timer.setForeground(new java.awt.Color(0, 0, 204));
        timer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timer.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(0, 0, 0), null, null));
        timer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout levelAndTimerLayout = new javax.swing.GroupLayout(levelAndTimer);
        levelAndTimer.setLayout(levelAndTimerLayout);
        levelAndTimerLayout.setHorizontalGroup(
            levelAndTimerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(levelAndTimerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(levelAndTimerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(levelText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(levelAndTimerLayout.createSequentialGroup()
                        .addGroup(levelAndTimerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(timerText, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(levelNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(timer, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 17, Short.MAX_VALUE))))
        );
        levelAndTimerLayout.setVerticalGroup(
            levelAndTimerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(levelAndTimerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(levelText, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(levelNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(timerText, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(timer, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        timerText.getAccessibleContext().setAccessibleName("time");

        boardCanvas.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        boardCanvas.setPreferredSize(new java.awt.Dimension(562, 469));
        boardCanvas.setRequestFocusEnabled(false);
        boardCanvas.setSize(new java.awt.Dimension(562, 469));

        javax.swing.GroupLayout boardCanvasLayout = new javax.swing.GroupLayout(boardCanvas);
        boardCanvas.setLayout(boardCanvasLayout);
        boardCanvasLayout.setHorizontalGroup(
            boardCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 558, Short.MAX_VALUE)
        );
        boardCanvasLayout.setVerticalGroup(
            boardCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 465, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout gameCanvasLayout = new javax.swing.GroupLayout(gameCanvas);
        gameCanvas.setLayout(gameCanvasLayout);
        gameCanvasLayout.setHorizontalGroup(
            gameCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gameCanvasLayout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(boardCanvas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
                .addComponent(levelAndTimer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93))
        );
        gameCanvasLayout.setVerticalGroup(
            gameCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gameCanvasLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(gameCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(gameCanvasLayout.createSequentialGroup()
                        .addComponent(boardCanvas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(67, Short.MAX_VALUE))
                    .addGroup(gameCanvasLayout.createSequentialGroup()
                        .addComponent(levelAndTimer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(67, 67, 67))))
        );

        gameButton.setText("Game");

        newGame.setText("New Game");
        gameButton.add(newGame);

        replay.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        replay.setText("Replay");
        replay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                replayActionPerformed(evt);
            }
        });
        gameButton.add(replay);

        jMenuBar1.add(gameButton);

        fileButton.setText("File");

        saveButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        saveButton.setText("Save Game");
        fileButton.add(saveButton);

        jMenuBar1.add(fileButton);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gameCanvas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gameCanvas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loadBottonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadBottonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_loadBottonActionPerformed

    private void saveBottonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBottonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saveBottonActionPerformed

    private void replayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_replayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_replayActionPerformed

    private void loadBottonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loadBottonMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_loadBottonMouseClicked

    private void jFileChooser2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooser2ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jFileChooser2ActionPerformed

    /**
     * Forms a warning message if user clicks exit bottom.
     * @param evt default event.
     */
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        c.pause();
        int confirm = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to leave this match?\n You will lose all your progress if\n you leave without saving",
                "Leave Game?", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
        if (confirm == JOptionPane.OK_OPTION) {
            //close current game, it will not affect other game in different windows
            evt.getWindow().dispose();
            //System.exit(0); //close all windows
        } else if (confirm == JOptionPane.OK_CANCEL_OPTION) {
            //close current game, it will not affect other game in different windows
            c.setRestarted();
            //System.exit(0); //close all windows
        }
    }//GEN-LAST:event_formWindowClosing

    /**
     * Forms the window when running the game and allows user to select a level.
     * @param evt default event
     */
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        int numSelected;
        JRadioButton one = new JRadioButton("1");
        JRadioButton two = new JRadioButton("2");
        
        //Group the radio buttons.
        ButtonGroup levelSelected = new ButtonGroup();
        levelSelected.add(one);
        levelSelected.add(two);
        
        final JComponent[] inputs = new JComponent[]{
            new JLabel("Choose a level to play"),
            one,
            two,
        };
        
        int result = JOptionPane.showConfirmDialog(null, inputs, "Welcome", JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            if (one.isSelected()) {
                numSelected = 1;
            } else{
                numSelected = 2;
            }
                c = new GameTimer();
                setLevelNumber(numSelected);
                render = new Render(m);
                //boardCanvas = render.getView();
                c.start(); // starts time out

        }
  
    }//GEN-LAST:event_formWindowOpened

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased

          if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            new SingleMove(Move.Direction.LEFT);
            System.out.println("Left");
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
           new SingleMove(Move.Direction.DOWN);
            System.out.println("Down");
        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            new SingleMove(Move.Direction.RIGHT);
            System.out.println("Right");
        }
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            new SingleMove(Move.Direction.UP);
            System.out.println("UP");
        }
    }//GEN-LAST:event_formKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUIWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIWindow().setVisible(true);
            }
        });

       //if(mode.equals("Running")){
//        c = new GameTimer();
//        c.start();
       //}
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel boardCanvas;
    private javax.swing.JMenu fileButton;
    private javax.swing.JMenu gameButton;
    private javax.swing.JPanel gameCanvas;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel levelAndTimer;
    private javax.swing.JLabel levelNumber;
    private javax.swing.JLabel levelText;
    private javax.swing.JMenuItem newGame;
    private javax.swing.JMenuItem replay;
    private javax.swing.JMenuItem saveButton;
    private static javax.swing.JLabel timer;
    private javax.swing.JLabel timerText;
    // End of variables declaration//GEN-END:variables
   // Game variables
    private static ImageIcon[] numberImg = new ImageIcon[10];
    private String mode;
    private int level;
    private enum modes { Run, Load, Save, Replay}
    private static GameTimer c;
    private Render render;
    private static Maze m;
    private EventListener eventListener;
    private Move move;
    
   /**
     * initialize the number images  by linking each face to its image and storing them.
     */
    public void numberOnPanel() {
        for (int i = 0; i < numberImg.length; i++) {
            numberImg[i] = new ImageIcon("/nz/ac/vuw/ecs/swen225/gp20/application/data/numbers/" + i + ".png");
            //set the size
            numberImg[i].setImage(numberImg[i].getImage().getScaledInstance(90, 100, Image.SCALE_DEFAULT));
            
        }
    }
                            
    /**
     * Display board grid on the GUI by receiving information from the Render module.
     * @return return a canvas for the Render module to display it in the game's
     * GUI.
     */
    public JPanel getBoardCanvas() {
        return boardCanvas;
    }

    /**
     * Displays the level on the GUI by receiving the information from the maze
     * module.
     * @param level level chosen by user.
     */
    public void setLevelNumber(int level) {
       this.level = level;
        levelNumber.setText("0" + level);
        this.eventListener.onEvent(Event.eventOfLevelSetting(level));
        try {
          m =  LevelReader.deserializeLevel(level);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Countdown for game when players initializes the game.
     * @return countdown for game
     */
    public static JLabel getTimer(){ return timer;}


    /**
     *Mode or status to tell the gui what actions should be performed.
     * @return game mode ( loading, saving, running, replaying).
     */
    public String getMode() {
        return mode;
    }

    /**
     * Set current game mode.
     * @param mode index of the mode within the modes enum
     */
    public void setMode( int mode){
        if( mode > modes.values().length || mode < 0){
            throw new IndexOutOfBoundsException(" Index must be within 0-3");
        }
       this.mode = modes.values()[mode].name();
    }

    /**
     * Set current game mode.
     * @param mode Name of the mode within the modes enum.
     */
    public void setMode( String mode){
        for( int i = 0; i < modes.values().length; i++) {
            if(!mode.equals(modes.values()[i])){
                throw new NullPointerException("Mode not found. Check for spelling errors");
            }
            this.mode = modes.valueOf(mode).name();
        }
    }

    /**
     *
     * @param data
     */
    public static void display(String data) {
        int result = JOptionPane.showConfirmDialog(null, data, "Alert", JOptionPane.PLAIN_MESSAGE);
    }
    
//     /**
//     * create a pop up window
//     * @param data text shown inside the window
//     * @param title of the window
//     */
//    public void display(String data, String title) {
//        int result = JOptionPane.showConfirmDialog(null, data, title, JOptionPane.PLAIN_MESSAGE);
//    }
}
