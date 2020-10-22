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
import nz.ac.vuw.ecs.swen225.gp20.persistence.level2.BugEntity;
import nz.ac.vuw.ecs.swen225.gp20.recnplay.Event;
import nz.ac.vuw.ecs.swen225.gp20.recnplay.EventIterator;
import nz.ac.vuw.ecs.swen225.gp20.recnplay.EventListener;
import nz.ac.vuw.ecs.swen225.gp20.render.Render;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyEditorSupport;
import java.io.*;
import java.io.IOException;
import java.util.HashMap;


/**
 *Main class of the application module. Creates the 
 *graphical user interface for the game Chip vs Chap
 *
 *@author camilalis 300504575. from Master
 */
public class GuiWindow extends JFrame {

  // GUI variables
  private javax.swing.JMenu Pause;
  private javax.swing.JButton autoReplay;
  private javax.swing.JPanel boardCanvas;
  private javax.swing.JLabel changeSpeedText;
  private javax.swing.JLabel chipsLeft;
  private javax.swing.JLabel chipsleftText2;
  private javax.swing.JMenuItem exitWithX;
  private javax.swing.JMenu fileMenu;
  private javax.swing.JPanel gameCanvas;
  private javax.swing.JMenu help;
  private javax.swing.JPanel inventoryPanel;
  private javax.swing.JLabel inventoryText;
  private javax.swing.JLabel inventoryText1;
  private javax.swing.JLabel item0;
  private javax.swing.JLabel item1;
  private javax.swing.JLabel item2;
  private javax.swing.JLabel item3;
  private javax.swing.JLabel item4;
  private javax.swing.JLabel item5;
  private javax.swing.JLabel item6;
  private javax.swing.JLabel item7;
  private javax.swing.JMenuBar jMenuBar1;
  private javax.swing.JMenuItem jMenuItem3;
  private javax.swing.JPanel levelAndTimer;
  private javax.swing.JLabel levelNumber;
  private javax.swing.JMenuItem levelOne;
  private javax.swing.JLabel levelText;
  private javax.swing.JMenuItem levelTwo;
  private javax.swing.JMenu levelsMenu;
  private javax.swing.JMenuItem newGameSameLevel;
  private javax.swing.JMenuItem pause;
  private javax.swing.JMenuItem replayButton;
  private javax.swing.JButton replayForwards;
  private javax.swing.JMenuItem rulesLegend;
  private javax.swing.JMenuItem saveButton;
  private javax.swing.JSlider speedChooser;
  private javax.swing.JLabel timer;
  private javax.swing.JLabel timerText;
  // Game variables
  private final JFileChooser fileChooser = new JFileChooser();
  private ImageIcon[] numberImg = new ImageIcon[10];
  private String mode;
  private static int level = -1;

  private enum modes {
    Run, Load, Save, Replay
  }

  private GameTimer gameCountdown;
  private Render render;
  private Maze m;
  private EventListener eventListener;
  private int pausedAtMin;
  private int pausedAtSec;
  private EventIterator eventIterator;
  private ImageIcon[] inventoryItems = new ImageIcon[8];
  private JLabel[] inventoryLabels = new JLabel[8];
  private int replaySpeed;
  private java.awt.event.WindowEvent evtOpen;
  private PropertyEditorSupport propertyEditorSupport = new PropertyEditorSupport();
  private int canMove = 1;

  /**
   * . Creates new Window
   */
  public GuiWindow() {
    initComponents();
    this.eventListener = EventListener.eventListenerFactory();
    this.setFocusable(true);
    // Replay buttons are desactivated until user activates Replay mode
    replayForwards.setEnabled(false);
    autoReplay.setEnabled(false);
    speedChooser.setEnabled(false);
    inventoryLabels[0] = item0;
    inventoryLabels[1] = item1;
    inventoryLabels[2] = item2;
    inventoryLabels[3] = item3;
    inventoryLabels[4] = item4;
    inventoryLabels[5] = item4;
    inventoryLabels[6] = item6;
    inventoryLabels[7] = item7;
  }

  /**
   * This method is called from within the constructor to initialize the form.
   */
  @SuppressWarnings("unchecked")
  private void initComponents() {

    gameCanvas = new javax.swing.JPanel();
    boardCanvas = new javax.swing.JPanel();
    levelAndTimer = new javax.swing.JPanel();
    levelText = new javax.swing.JLabel();
    levelNumber = new javax.swing.JLabel();
    timerText = new javax.swing.JLabel();
    timer = new javax.swing.JLabel();
    chipsLeft = new javax.swing.JLabel();
    chipsleftText2 = new javax.swing.JLabel();
    autoReplay = new javax.swing.JButton();
    replayForwards = new javax.swing.JButton();
    speedChooser = new javax.swing.JSlider();
    changeSpeedText = new javax.swing.JLabel();
    inventoryPanel = new javax.swing.JPanel();
    inventoryText = new javax.swing.JLabel();
    item0 = new javax.swing.JLabel();
    item1 = new javax.swing.JLabel();
    item2 = new javax.swing.JLabel();
    item3 = new javax.swing.JLabel();
    item4 = new javax.swing.JLabel();
    item5 = new javax.swing.JLabel();
    item6 = new javax.swing.JLabel();
    item7 = new javax.swing.JLabel();
    inventoryText1 = new javax.swing.JLabel();
    jMenuBar1 = new javax.swing.JMenuBar();
    Pause = new javax.swing.JMenu();
    pause = new javax.swing.JMenuItem();
    exitWithX = new javax.swing.JMenuItem();
    jMenuItem3 = new javax.swing.JMenuItem();
    newGameSameLevel = new javax.swing.JMenuItem();
    fileMenu = new javax.swing.JMenu();
    saveButton = new javax.swing.JMenuItem();
    replayButton = new javax.swing.JMenuItem();
    help = new javax.swing.JMenu();
    rulesLegend = new javax.swing.JMenuItem();
    levelsMenu = new javax.swing.JMenu();
    levelOne = new javax.swing.JMenuItem();
    levelTwo = new javax.swing.JMenuItem();

    setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
    setBackground(new java.awt.Color(102, 0, 0));
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(java.awt.event.WindowEvent evt) {
        formWindowClosing(evt);
      }

      public void windowOpened(java.awt.event.WindowEvent evt) {
        evtOpen = evt;
        mode = modes.Run.name();
        if (level != -1) {
          newGame(level);
        } else {
          formWindowOpened(evt);
        }
      }
    });
    addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyReleased(java.awt.event.KeyEvent evt) {
        keyReleasedSetMove(evt);
      }
    });

    gameCanvas.setBackground(new java.awt.Color(0, 0, 0));

    boardCanvas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    boardCanvas.setMaximumSize(new java.awt.Dimension(45000, 45000));
    boardCanvas.setPreferredSize(new java.awt.Dimension(450, 450));

    javax.swing.GroupLayout boardCanvasLayout = new javax.swing.GroupLayout(boardCanvas);
    boardCanvas.setLayout(boardCanvasLayout);
    boardCanvasLayout.setHorizontalGroup(boardCanvasLayout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 450, 
            Short.MAX_VALUE));
    boardCanvasLayout.setVerticalGroup(boardCanvasLayout.createParallelGroup(
        javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 450, Short.MAX_VALUE));

    levelAndTimer.setBackground(new java.awt.Color(102, 102, 102));
    levelAndTimer.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border
        .BevelBorder.RAISED, null, null,
        null, new java.awt.Color(102, 102, 102)));

    levelText.setBackground(new java.awt.Color(0, 0, 0));
    levelText.setFont(new java.awt.Font("Arial Narrow", 1, 48)); // NOI18N
    levelText.setForeground(new java.awt.Color(153, 0, 0));
    levelText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    levelText.setText("L E V E L");

    levelNumber.setBackground(new java.awt.Color(204, 204, 204));
    levelNumber.setFont(new java.awt.Font("Arial Narrow", 1, 80)); // NOI18N
    levelNumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    levelNumber.setToolTipText("");
    levelNumber.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border
        .BevelBorder.RAISED));
    levelNumber.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

    timerText.setFont(new java.awt.Font("Arial Narrow", 1, 48)); // NOI18N
    timerText.setForeground(new java.awt.Color(153, 0, 0));
    timerText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    timerText.setText("T I M E");
    timerText.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

    timer.setBackground(new java.awt.Color(204, 204, 204));
    timer.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 48)); // NOI18N
    timer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    timer.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border
        .BevelBorder.RAISED));
    timer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

    chipsLeft.setBackground(new java.awt.Color(204, 204, 204));
    chipsLeft.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 48)); // NOI18N
    chipsLeft.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    chipsLeft.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border
        .BevelBorder.RAISED));
    chipsLeft.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

    chipsleftText2.setFont(new java.awt.Font("Arial Narrow", 1, 48)); // NOI18N
    chipsleftText2.setForeground(new java.awt.Color(153, 0, 0));
    chipsleftText2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    chipsleftText2.setText("C H I P S");
    chipsleftText2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

    javax.swing.GroupLayout levelAndTimerLayout = new javax.swing.GroupLayout(levelAndTimer);
    levelAndTimer.setLayout(levelAndTimerLayout);
    levelAndTimerLayout.setHorizontalGroup(levelAndTimerLayout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(levelAndTimerLayout.createSequentialGroup().addGap(49, 49, 49)
            .addGroup(levelAndTimerLayout
            .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(levelNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 170,
                javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(timerText, javax.swing.GroupLayout.PREFERRED_SIZE, 175,
                javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(timer, javax.swing.GroupLayout.PREFERRED_SIZE, 169, 
                javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(chipsleftText2, javax.swing.GroupLayout.PREFERRED_SIZE, 175,
                javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(chipsLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 169,
                javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(68, Short.MAX_VALUE))
        .addComponent(levelText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing
            .GroupLayout.DEFAULT_SIZE,
            Short.MAX_VALUE));
    levelAndTimerLayout.setVerticalGroup(levelAndTimerLayout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(levelAndTimerLayout.createSequentialGroup()
            .addComponent(levelText, javax.swing.GroupLayout.PREFERRED_SIZE, 60, 
                javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(levelNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 66,
                javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(timerText, javax.swing.GroupLayout.PREFERRED_SIZE, 53, 
                javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 
                javax.swing.GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE)
            .addComponent(timer, javax.swing.GroupLayout.PREFERRED_SIZE, 72, 
                javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(chipsleftText2, javax.swing.GroupLayout.PREFERRED_SIZE, 53,
                javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(chipsLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 72, 
                javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(32, 32, 32)));

    timerText.getAccessibleContext().setAccessibleName("time");

    autoReplay.setBackground(new java.awt.Color(51, 51, 255));
    autoReplay.setForeground(new java.awt.Color(255, 255, 255));
    autoReplay.setText("A U T O R E P L A Y");
    autoReplay.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        autoReplayActionPerformed(evt);
      }
    });

    replayForwards.setBackground(new java.awt.Color(51, 51, 255));
    replayForwards.setForeground(new java.awt.Color(255, 255, 255));
    replayForwards.setText(">");
    replayForwards.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        replayForwardsActionPerformed(evt);
      }
    });

    speedChooser.setMaximum(10);
    speedChooser.setPaintLabels(true);
    speedChooser.setPaintTicks(true);
    speedChooser.setValue(5);
    speedChooser.addAncestorListener(new javax.swing.event.AncestorListener() {
      public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
        speedChooserAncestorAdded(evt);
      }

      public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
      }

      public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
      }
    });
    speedChooser.addChangeListener(new javax.swing.event.ChangeListener() {
      public void stateChanged(javax.swing.event.ChangeEvent evt) {
        speedChooserStateChanged(evt);
      }
    });

    changeSpeedText.setBackground(new java.awt.Color(51, 51, 255));
    changeSpeedText.setForeground(new java.awt.Color(153, 0, 0));
    changeSpeedText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    changeSpeedText.setText("C H A N G E   S P E E D");
    changeSpeedText.setAutoscrolls(true);

    inventoryPanel.setBackground(new java.awt.Color(102, 102, 102));
    inventoryPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax
        .swing.border.BevelBorder.RAISED));
    inventoryPanel.setForeground(new java.awt.Color(102, 102, 102));

    inventoryText.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
    inventoryText.setForeground(new java.awt.Color(153, 0, 0));
    inventoryText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    inventoryText.setText("I N V E N T O R Y");
    inventoryText.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

    item0.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border
        .BevelBorder.RAISED));

    item1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border
        .BevelBorder.RAISED));

    item2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border
        .BevelBorder.RAISED));

    item3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border
        .BevelBorder.RAISED));

    item4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border
        .BevelBorder.RAISED));

    item5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border
        .BevelBorder.RAISED));

    item6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border
        .BevelBorder.RAISED));

    item7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border
        .BevelBorder.RAISED));

    javax.swing.GroupLayout inventoryPanelLayout = new javax.swing.GroupLayout(inventoryPanel);
    inventoryPanel.setLayout(inventoryPanelLayout);
    inventoryPanelLayout.setHorizontalGroup(inventoryPanelLayout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(inventoryPanelLayout.createSequentialGroup()
            .addGroup(inventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout
                .Alignment.LEADING)
                .addGroup(inventoryPanelLayout.createSequentialGroup().addContainerGap()
                    .addGroup(inventoryPanelLayout.createParallelGroup(javax.swing
                        .GroupLayout.Alignment.TRAILING)
                        .addComponent(item0, javax.swing.GroupLayout.PREFERRED_SIZE, 66,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(item4, javax.swing.GroupLayout.PREFERRED_SIZE, 66,
                            javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(inventoryPanelLayout.createParallelGroup(javax.swing
                        .GroupLayout.Alignment.LEADING)
                        .addGroup(inventoryPanelLayout.createSequentialGroup()
                            .addComponent(item5, javax.swing.GroupLayout.PREFERRED_SIZE, 66,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(item6, javax.swing.GroupLayout.PREFERRED_SIZE, 66,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement
                                .RELATED).addComponent(item7,
                                javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing
                                .GroupLayout.PREFERRED_SIZE))
                        .addGroup(inventoryPanelLayout.createSequentialGroup()
                            .addComponent(item1, javax.swing.GroupLayout.PREFERRED_SIZE, 66,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(item2, javax.swing.GroupLayout.PREFERRED_SIZE, 66,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(item3,
                                javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout
                                .PREFERRED_SIZE))))
                .addGroup(inventoryPanelLayout.createSequentialGroup().addGap(14, 14, 14)
                    .addComponent(inventoryText,
                    javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout
                    .PREFERRED_SIZE)))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
    inventoryPanelLayout.setVerticalGroup(inventoryPanelLayout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(inventoryPanelLayout.createSequentialGroup().addContainerGap()
            .addComponent(inventoryText, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(inventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment
                .LEADING, false)
                .addComponent(item1, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                .addComponent(item2, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                .addComponent(item3, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                .addComponent(item0,
                    javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout
                    .DEFAULT_SIZE, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(inventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout
                .Alignment.LEADING)
                .addComponent(item4, javax.swing.GroupLayout.PREFERRED_SIZE, 62, 
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(item5, javax.swing.GroupLayout.PREFERRED_SIZE, 62, 
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(item6, javax.swing.GroupLayout.PREFERRED_SIZE, 62, 
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(item7, javax.swing.GroupLayout.PREFERRED_SIZE, 62,
                    javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(37, Short.MAX_VALUE)));

    inventoryText1.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
    inventoryText1.setForeground(new java.awt.Color(153, 0, 0));
    inventoryText1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    inventoryText1.setText("Replay Mode Controls");
    inventoryText1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

    javax.swing.GroupLayout gameCanvasLayout = new javax.swing.GroupLayout(gameCanvas);
    gameCanvas.setLayout(gameCanvasLayout);
    gameCanvasLayout.setHorizontalGroup(gameCanvasLayout.createParallelGroup(javax.swing
        .GroupLayout.Alignment.LEADING)
        .addGroup(gameCanvasLayout.createSequentialGroup().addGroup(gameCanvasLayout
            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gameCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment
                .LEADING)
                .addGroup(gameCanvasLayout.createSequentialGroup().addGap(57, 57, 57)
                    .addComponent(boardCanvas,
                    javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout
                    .DEFAULT_SIZE,
                    javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(gameCanvasLayout.createSequentialGroup().addGap(141, 141, 141)
                    .addComponent(inventoryText1,
                    javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout
                    .PREFERRED_SIZE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                    gameCanvasLayout.createSequentialGroup().addGap(70, 70, 70)
                        .addGroup(gameCanvasLayout.createParallelGroup(javax.swing.GroupLayout
                            .Alignment.LEADING, false)
                            .addComponent(replayForwards, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(autoReplay, javax.swing.GroupLayout.DEFAULT_SIZE,
                                437, Short.MAX_VALUE))))
            .addGroup(gameCanvasLayout.createSequentialGroup().addGap(206, 206, 206)
                .addComponent(changeSpeedText,
                javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout
                .PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                gameCanvasLayout.createSequentialGroup().addContainerGap().addComponent(
                    speedChooser,
                    javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout
                    .PREFERRED_SIZE)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 160, 
                Short.MAX_VALUE)
            .addGroup(gameCanvasLayout.createParallelGroup(javax.swing.GroupLayout
                .Alignment.LEADING)
                .addComponent(levelAndTimer, javax.swing.GroupLayout.PREFERRED_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout
                    .PREFERRED_SIZE).addComponent(inventoryPanel, javax.swing.GroupLayout
                        .PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, 
                        javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(107, 107, 107)));
    gameCanvasLayout.setVerticalGroup(gameCanvasLayout.createParallelGroup(
        javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(gameCanvasLayout.createSequentialGroup().addGap(34, 34, 34)
            .addGroup(gameCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment
                .LEADING).addComponent(levelAndTimer, javax.swing.GroupLayout.PREFERRED_SIZE,
                    456, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                    gameCanvasLayout.createSequentialGroup().addGap(6, 6, 6).addComponent(
                        boardCanvas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing
                        .GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(18, 18, 18)
            .addGroup(gameCanvasLayout.createParallelGroup(javax.swing.GroupLayout
                .Alignment.LEADING)
                .addGroup(gameCanvasLayout.createSequentialGroup()
                    .addComponent(inventoryText1, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(replayForwards, javax.swing.GroupLayout.PREFERRED_SIZE, 56,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(7, 7, 7)
                    .addComponent(autoReplay, javax.swing.GroupLayout.PREFERRED_SIZE, 53,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(changeSpeedText, javax.swing.GroupLayout.PREFERRED_SIZE, 44,
                        javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing
                            .LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(speedChooser,
                        javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing
                        .GroupLayout.PREFERRED_SIZE))
                .addComponent(inventoryPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing
                    .GroupLayout.PREFERRED_SIZE))
            .addContainerGap(12, Short.MAX_VALUE)));

    Pause.setText("Game");

    pause.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_SPACE, 0));
    pause.setText("Pause Game");
    pause.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        pauseActionPerformed(evt);
      }
    });
    Pause.add(pause);

    exitWithX.setAccelerator(
        javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, 
            java.awt.event.InputEvent.CTRL_DOWN_MASK));
    exitWithX.setText("Exit Game");
    exitWithX.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        exitWithXActionPerformed(evt);
      }
    });
    Pause.add(exitWithX);

    jMenuItem3.setAccelerator(
        javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, 
            java.awt.event.InputEvent.CTRL_DOWN_MASK));
    jMenuItem3.setText("Resume Game");
    jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        resumeGameActionPerformed(evt);
      }
    });
    Pause.add(jMenuItem3);

    newGameSameLevel.setAccelerator(
        javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, 
            java.awt.event.InputEvent.CTRL_DOWN_MASK));
    newGameSameLevel.setText("New Game");
    newGameSameLevel.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        newGameSameLevelActionPerformed(evt);
      }
    });
    Pause.add(newGameSameLevel);
    jMenuBar1.add(Pause);

    fileMenu.setText("File");

    saveButton.setAccelerator(
        javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, 
            java.awt.event.InputEvent.CTRL_DOWN_MASK));
    saveButton.setText("Save Game");
    saveButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        saveButtonActionPerformed(evt);
      }
    });
    fileMenu.add(saveButton);

    replayButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R,
        java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
    replayButton.setText("Replay Game");
    replayButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        replayButtonActionPerformed(evt);
      }
    });
    fileMenu.add(replayButton);

    jMenuBar1.add(fileMenu);

    help.setText("Help");

    rulesLegend.setText("Instructions - Rules");
    rulesLegend.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        rulesLegendActionPerformed(evt);
      }
    });
    help.add(rulesLegend);

    jMenuBar1.add(help);

    levelsMenu.setText("Levels");

    levelOne.setAccelerator(
        javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, 
            java.awt.event.InputEvent.CTRL_DOWN_MASK));
    levelOne.setText("Leve 1");
    levelOne.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        levelOneActionPerformed(evt);
      }
    });
    levelsMenu.add(levelOne);

    levelTwo.setAccelerator(
        javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, 
            java.awt.event.InputEvent.CTRL_DOWN_MASK));
    levelTwo.setText("Level 2");
    levelTwo.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        levelTwoActionPerformed(evt);
      }
    });
    levelsMenu.add(levelTwo);

    jMenuBar1.add(levelsMenu);

    setJMenuBar(jMenuBar1);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup().addComponent(gameCanvas, 
            javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
            Short.MAX_VALUE).addGap(6, 6, 6)));
    layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout
        .Alignment.LEADING).addComponent(
        gameCanvas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
        Short.MAX_VALUE));

    pack();
  }
      /**
       * Processes users input to move Chap around the board.
       * @param evt arrow key released.
       */
      private void keyReleasedSetMove(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_keyReleasedSetMove
          SingleMove singleMove = null;
          canMove = canMove+1;
          if( mode != null && !mode.equals("Replay") && canMove %2 == 0) {
              requestFocus();
              if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
                  singleMove = new SingleMove(Move.Direction.LEFT);
              } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                  singleMove = new SingleMove(Move.Direction.DOWN);
              } else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
                  singleMove = new SingleMove(Move.Direction.RIGHT);
              } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
                  singleMove = new SingleMove(Move.Direction.UP);
              } else {
                  return;
              }
              //Only updates event listener if move is valid.
              if(handleMovement(singleMove)) {
                  eventListener.onEvent(Event.eventOfChapMove(singleMove));
              }
          }
          //Bug moves regardless Chaps move is valid or not.
          propertyEditorSupport.firePropertyChange();
          render.updateRender();
          if(m.getStatus() == Maze.GameState.GAME_LOST){
              formWindowLost(evtOpen);
          }
      }//GEN-LAST:event_keyReleasedSetMove

      /**
       * Processes users input when the button for autoReplay is pressed.
       * @param evt autoReplay button clicked.
       */
      private void autoReplayActionPerformed(java.awt.event.ActionEvent evt) {

              replayForwards.setEnabled(false);
              EventIterator it = this.eventIterator;
              GuiWindow forwordable = this;
              Maze mz = this.m;

              ActionListener taskPerformer = new ActionListener() {
                  public void actionPerformed(ActionEvent evt) {
                      if (!it.hasNext()) {
                          ((Timer) evt.getSource()).stop();
                          System.out.println("Auto-Replay stopped iteration");
                          return;
                      }
                      Event ev = it.next();
                      SingleMove mv = null;
                      System.out.println("Auto-Replay event: " + ev.getType());
                      switch(ev.getType()){
                      case ChapMove:
                    	  mv = ev.getMove();
                          if (mv != null) {
                              System.out.println("Auto-Replay ChapMove: " + mv.getLastDirection());
                              forwordable.handleMovement(mv);
                              mv = null;
                          } else {
                              System.err.println("Auto-Replay ChapMove has no movement to replay ");
                          }
                          break;
                      case BugMove:
                    	  mv = ev.getMove();
                          if (mv != null) {
                              System.out.println("Auto-Replay BugMove: " + mv.getLastDirection() + "BugId: " + ev.getBugId());
	                    	  int bugId = ev.getBugId();
	                    	  BugEntity bug = forwordable.getBug(bugId);
	                    	  mz.moveEntity(mv, bug);
                          } else {
                              System.err.println("Auto-Replay BugMove has no movement to replay ");
                          }
                          break;
                      default:
                    	  System.err.println("Auto-Replay reads unexpected event: " + ev.getType());
                    	  break;  
                      }
                      int latency = (int) it.getLatency();
                      System.out.println("Auto-Replay latency updated to: " + latency);
                      //FayLu: Theoretically the speed might be adjusted during the auto-replay
                      ((Timer) evt.getSource()).setDelay(latency);
                  }
              };
              // When auto-replay is triggered the real-time speed value is used
              it.setSpeed(this.replaySpeed);
              int latency = (int) it.getLatency();
              System.out.println("Auto-Replay latency initialized to: " + latency);
              new Timer(latency, taskPerformer).start();

      }//GEN-LAST:event_autoReplayActionPerformed

      /**
       * Processes users input when the button for moving forwards is pressed.
       * @param evt '>' button clicked.
       */
      private void replayForwardsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_forwardsActionPerformed
    	  
    	  EventIterator it = this.eventIterator;
          GuiWindow forwordable = this;
          Maze mz = this.m;
          
          if (!it.hasNext()) {
              ((Timer) evt.getSource()).stop();
              System.out.println("Auto-Replay stopped iteration");
              return;
          }
          Event ev = it.next();
          SingleMove mv = null;
          System.out.println("Auto-Replay event: " + ev.getType());
          switch(ev.getType()){
          case ChapMove:
        	  mv = ev.getMove();
              if (mv != null) {
                  System.out.println("Auto-Replay ChapMove: " + mv.getLastDirection());
                  forwordable.handleMovement(mv);
                  mv = null;
              } else {
                  System.err.println("Auto-Replay ChapMove has no movement to replay ");
              }
              break;
          case BugMove:
        	  mv = ev.getMove();
              if (mv != null) {
                  System.out.println("Auto-Replay BugMove: " + mv.getLastDirection() + "BugId: " + ev.getBugId());
            	  int bugId = ev.getBugId();
            	  BugEntity bug = forwordable.getBug(bugId);
            	  mz.moveEntity(mv, bug);
              } else {
                  System.err.println("Auto-Replay BugMove has no movement to replay ");
              }
              break;
          default:
        	  System.err.println("Auto-Replay reads unexpected event: " + ev.getType());
        	  break;  
          }
//          if (this.eventIterator.hasNext() && mv == null) {
//              mv = this.eventIterator.next().getMove();
//          }
//          if (mv != null) {
//              System.out.println("Replay movement: " + mv.getLastDirection());
//              this.handleMovement(mv);
//          }else {
//              System.out.println("Replay finished the event iteration");
//          }

      }//GEN-LAST:event_forwardsActionPerformed

      /**
       * Processes users input when the button for saving is pressed.
       * @param evt save button on game menu is clicked or CTRL+S.
       */
      private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {
//GEN-FIRST:event_saveButtonActionPerformed
          if (!mode.equals(modes.Replay.name())) {
              EventListener.getRecord().saveToJson();
              System.exit(0);
          }else{
              display("You are not allowed to save while Replay mode is on.");
          }
      }//GEN-LAST:event_saveButtonActionPerformed

      /**
       * Processes users input when the button for displaying rules is pressed.
       * @param evt rules and instructions button clicked on the game menu.
       */
      private void rulesLegendActionPerformed(java.awt.event.ActionEvent evt) {
//GEN-FIRST:event_rulesLegendActionPerformed
        pausedAtMin = gameCountdown.getCurrentMin();
        pausedAtSec = gameCountdown.getCurrentSec();
        gameCountdown.pause();
        display(" ~ Use the arrows on your key board to move Chap around the board.\n"
            +" ~ To win the game make sure you collect all the chips on \n"
            +"  the board within 2 minutes and go to the blue tile.\n"
            +" ~ Open doors by collecting keys of the same color.\n"
            +" ~ On level 2 do not let the bug reach Chap and be careful\n"
                + "with the special tiles! Some items withing the board\n"
            + "will help you with those ;)"
            +" ~ If you want to see all your moves play Replay mode.\n"
            + "Use the \">\" button to replay forwards step by step (Default).\n"
                + "or set AutoReplay and adjust to your desired speed.\n"
            +"You can also save the game and resume later by going to \"File\" and\n"
            +"click on \"Save\"");
        if( mode.equals(modes.Run.name())) {
            gameCountdown = new GameTimer(pausedAtMin, pausedAtSec,this);
        }
      }//GEN-LAST:event_rulesLegendActionPerformed

    /**
     * Controls what happens when the speed chooser from Replay mode is changed.
     * @param evt speed changed from bar.
     */
    private void speedChooserStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_speedChooserStateChanged
        // TODO add your handling code here:
        	this.replaySpeed = speedChooser.getValue();
        if (this.eventIterator != null) {
        	// FayLu: User might adjust speed during auto-replay.
        	// If the slider changes, update new speed seed value to iterator and it will be used in auto-replay.
        	this.eventIterator.setSpeed(this.replaySpeed);
        }
    }


    

  /**
   * Forms a warning message if user wins game bottom.
   */
  private void formWindowWon() {
    gameCountdown.pause();
    if (level == 1) {
      int confirm = JOptionPane.showConfirmDialog(null, "Congratulations! You have won\n"
          + "Do you want to play level 2?",
          "Game Won", JOptionPane.WARNING_MESSAGE);
      if (confirm == JOptionPane.OK_OPTION) {
        level = 2;
        newGame(level);
      } else if (confirm == JOptionPane.CANCEL_OPTION) {
        System.exit(0); // close all windows
      }
    } else {
      display(" Congratulations!\nYou completed all levels");
      System.exit(0);
    }
  }

  /**
   * Forms a warning message if user wins game bottom.
   * 
   * @param evt event of opening the window.
   */
  public void formWindowLost(java.awt.event.WindowEvent evt) {
    String message = "Game Over\n";
    if (gameCountdown.timeOut()) {
      message = message + "You ran out of time\nDo you want to play this level again?";

    } else if (m.getStatus() == Maze.GameState.GAME_LOST) {
      gameCountdown.pause();
      message = message + m.getLastSpecialEvent().name().toLowerCase().replace("_", " ")
          + ".\nDo you want to play this level again?";
    }
    int confirm = JOptionPane.showConfirmDialog(null, message, "Game Over", 
        JOptionPane.YES_NO_OPTION);
    if (confirm == JOptionPane.YES_OPTION) {
      newGame(level);
    } else if (confirm == JOptionPane.NO_OPTION) {
      int confirm2 = JOptionPane.showConfirmDialog(null, "Do you want to play another level?",
          "Game Over",
          JOptionPane.YES_NO_OPTION);
      if (confirm2 == JOptionPane.YES_OPTION) {
        formWindowOpened(evtOpen);
      } else {
        System.exit(0); // close this window only
      }
    }
  }

  /**
   * Forms a warning message if user clicks exit bottom.
   * 
   * @param evt window is closing.
   */
  private void formWindowClosing(java.awt.event.WindowEvent evt) {
    // stores data locally before this timer gets destroyed on c.paused()
    pausedAtMin = gameCountdown.getCurrentMin();
    pausedAtSec = gameCountdown.getCurrentSec();
    gameCountdown.pause();
    int confirm = JOptionPane.showConfirmDialog(null,
        "Are you sure you want to leave the game?\n You will lose all your progress if\n"
        + "you leave without saving",
        "Leave Game?", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
    if (confirm == JOptionPane.OK_OPTION) {
      // closes all windows
      System.exit(0);
    } else if (confirm == JOptionPane.OK_CANCEL_OPTION && mode.equals(modes.Run.name())) {
      // new timer continues from where it was left of.
      gameCountdown = new GameTimer(pausedAtMin, pausedAtSec, this);
    }
  }

  /**
   * Forms the window when running the game and allows user to select a level.
   * 
   * @param evt window open.
   */
  private void formWindowOpened(java.awt.event.WindowEvent evt) {
    int numSelected;
    JRadioButton one = new JRadioButton("1");
    JRadioButton two = new JRadioButton("2");
    JRadioButton three = new JRadioButton("Resume last unfinished level ");
    mode = modes.Run.name();
    // Group the radio buttons.
    ButtonGroup levelSelected = new ButtonGroup();
    levelSelected.add(one);
    levelSelected.add(two);
    levelSelected.add(three);
    // Default option, level one
    one.setSelected(true);
    final JComponent[] inputs = new JComponent[] { 
        new JLabel("Choose a level to play"), one, two, three, };
    int result = JOptionPane.showConfirmDialog(null, inputs, "Welcome", JOptionPane.PLAIN_MESSAGE);
    if (result == JOptionPane.OK_OPTION) {
      if (one.isSelected()) {
        numSelected = 1;
      } else if (two.isSelected()) {
        numSelected = 2;
      } else if (three.isSelected()) {
        numSelected = readLevelFile();
      } else {
        // Here user has clicked OK without choosing option
        evt.getWindow().dispose();
        return;
      }
      newGame(numSelected);
    } else {
      evt.getWindow().dispose(); // changed to prevent bug when closing
    }
  }

  /**
   * Creates a game by creating a new timer and passes the information to the
   * other modules too.
   * 
   * @param level to be played.
   */
  private void newGame(int level) {
    // New timer for every game (2 minutes long)
    if (gameCountdown != null) {
      gameCountdown.pause();
    }
    gameCountdown = new GameTimer(1, 59, this);
    mode = modes.Run.name();
    // Updates level number with the input received by player and
    // informs other modules.
    setLevelNumber(level);
    boardCanvas.setVisible(false);
    render = new Render(m);
    boardCanvas = render.getView();
    render.updateRender();
    setVisible(false);
    gameCanvas.add(boardCanvas);
    boardCanvas.setLocation(70, 35);
    validate();
    repaint();
    setVisible(true);
    setChipsLeft();
  }

  /**
   * Updates other modules with the new move input by player.
   * 
   * @param mv move indicating direction chap moves on the board.
   * @return true if move is valid (Chap does not try to go on the wall).
   */
  private boolean handleMovement(SingleMove mv) {
    if (m.getStatus() != Maze.GameState.PLAYING) {
      return false;
    }
    
    boolean isValidMove = m.movePlayer(mv);

    render.updateRender();
    setChipsLeft();
    boardCanvas.setVisible(true);
    gameCanvas.add(boardCanvas);
    boardCanvas.setLocation(70, 35);
    validate();
    repaint();
    setVisible(true);
    popUpInfo(m.getInfo());
    displayInventory();
    propertyEditorSupport.firePropertyChange();
    if (m.getStatus() == Maze.GameState.GAME_WON) {
      formWindowWon();
    } else if (m.getStatus() == Maze.GameState.GAME_LOST) {
      formWindowLost(evtOpen);
    }
    transferFocus();
    
    return isValidMove;
  }

  /**
   * Processes users input to move Chap around the board.
   * 
   * @param evt arrow key released.
   */
  private void keyReleasedSetMove(java.awt.event.KeyEvent evt) {
    SingleMove singleMove = null;
    canMove = canMove + 1;
    if (mode != null && !mode.equals("Replay") && canMove % 2 == 0) {
      requestFocus();
      if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
        singleMove = new SingleMove(Move.Direction.LEFT);
      } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
        singleMove = new SingleMove(Move.Direction.DOWN);
      } else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
        singleMove = new SingleMove(Move.Direction.RIGHT);
      } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
        singleMove = new SingleMove(Move.Direction.UP);
      } else {
        return;
      }
      // Only updates event listener if move is valid.
      if (handleMovement(singleMove)) {
        eventListener.onEvent(Event.eventOfChapMove(singleMove));
      }
    }
  }

  /**
   * Processes users input when the button for saving is pressed.
   * 
   * @param evt save button on game menu is clicked or CTRL+S.
   */
  private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {
    if (!mode.equals(modes.Replay.name())) {
      EventListener.getRecord().saveToJson();
      System.exit(0);
    } else {
      display("You are not allowed to save while Replay mode is on.");
    }
  }

  /**
   * Activated when user clicks on Replay. Uploads JsonFile and parsers level.
   *
   * @param evt rReplay is clicked.
   */
  private void replayButtonActionPerformed(java.awt.event.ActionEvent evt) {
    pausedAtMin = gameCountdown.getCurrentMin();
    pausedAtSec = gameCountdown.getCurrentSec();
    gameCountdown.pause();
    JsonFileFilter fileFilter = new JsonFileFilter();

    fileChooser.setDialogTitle("Open Json File to Replay a match");
    fileChooser.setFileFilter(fileFilter);

    int returnVal = fileChooser.showOpenDialog(this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      timer.setText("-:- -"); // Timer is not applicable during replay mode
      // Allow user to use buttons for Replay mode
      replayForwards.setEnabled(true);
      autoReplay.setEnabled(true);
      speedChooser.setEnabled(true);
      saveButton.setEnabled(false);

      File file = fileChooser.getSelectedFile();
      mode = modes.Replay.name();
      System.out.println("Selected game record: " + file.getAbsolutePath());
      this.eventIterator = EventListener.getRecord()
          .getIteratorByFile(file.getAbsolutePath(), this.replaySpeed);
      replaySetLevel();
    } else {
      System.out.println("File access cancelled by user.");
      // Resume the app timer, return to current game
      mode = modes.Run.name(); // Game is back to running mode
      // resume timer
      gameCountdown = new GameTimer(pausedAtMin, pausedAtSec, this);
    }
  }

  /**
   * Processes users input when the button for autoReplay is pressed.
   *
   * @param evt autoReplay button clicked.
   */
  private void autoReplayActionPerformed(java.awt.event.ActionEvent evt) {
    replayForwards.setEnabled(false);
    EventIterator it = this.eventIterator;
    GuiWindow forwordable = this;

    ActionListener taskPerformer = new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        if (!it.hasNext()) {
          ((Timer) evt.getSource()).stop();
          System.out.println("Auto-Replay stopped iteration");
          return;
        }
        Event ev = it.next();
        SingleMove mv = null;
        System.out.println("Auto-Replay event: " + ev.getType());
        mv = ev.getMove();
        if (mv != null) {
          System.out.println("Auto-Replay movement: " + mv.getLastDirection());
          forwordable.handleMovement(mv);
          mv = null;
        } else {
          System.err.println("Auto-Replay expects a movement but event emitted is: " 
              + ev.getType());
        }
        int latency = (int) it.getLatency();
        System.out.println("Auto-Replay latency updated to: " + latency);
        // FayLu: Theoretically the speed might be adjusted during the auto-replay
        ((Timer) evt.getSource()).setDelay(latency);
      }
    };
    // When auto-replay is triggered the real-time speed value is used
    it.setSpeed(this.replaySpeed);
    int latency = (int) it.getLatency();
    System.out.println("Auto-Replay latency initialized to: " + latency);
    new Timer(latency, taskPerformer).start();

  }

  /**
   * Processes users input when the button for moving forwards is pressed.
   *
   * @param evt '>' button clicked.
   */
  private void replayForwardsActionPerformed(java.awt.event.ActionEvent evt) {
    SingleMove mv = null;
    if (this.eventIterator.hasNext() && mv == null) {
      mv = this.eventIterator.next().getMove();
    }
    if (mv != null) {
      System.out.println("Replay movement: " + mv.getLastDirection());
      this.handleMovement(mv);
    } else {
      System.out.println("Replay finished the event iteration");
    }

  }

  /**
   * Pauses the game and shows a message.
   * 
   * @param evt Click on pause game on the game menu or space key.
   */
  private void pauseActionPerformed(java.awt.event.ActionEvent evt) {
    pausedAtSec = gameCountdown.getCurrentSec();
    pausedAtMin = gameCountdown.getCurrentMin();
    gameCountdown.pause();
    display("GAME PAUSED");
    gameCountdown = new GameTimer(pausedAtMin, pausedAtSec, this);
  }

  /**
   * Starts a new game from level 1.
   * 
   * @param evt level is selected form the menu bar or CTRL+1
   */
  private void levelOneActionPerformed(java.awt.event.ActionEvent evt) {
    if (mode != null && !mode.equals(modes.Replay.name())) {
      level = 1;
      gameCountdown.pause();
      new GuiWindow().setVisible(true);
      if (GuiWindow.getWindows().length > 1) {
        for (int i = 0; i < GuiWindow.getWindows().length - 1; i++) {
          GuiWindow.getWindows()[i].dispose();
        }
      }
    } else {
      display("Replay mode ON. \nCannot set a new level.");
    }
  }

  /**
   * Starts a new game from level 2.
   * 
   * @param evt level is selected form the menu bar or CTRL+2
   */
  private void levelTwoActionPerformed(java.awt.event.ActionEvent evt) {
    if (mode != null && !mode.equals(modes.Replay.name())) {
      level = 2;
      gameCountdown.pause();
      new GuiWindow().setVisible(true);
      if (GuiWindow.getWindows().length > 1) {
        for (int i = 0; i < GuiWindow.getWindows().length - 1; i++) {
          GuiWindow.getWindows()[i].dispose();
        }
      } else {
        display("Replay mode on. \nCannot set a new level.");
      }
    }
  }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Pause;
    private javax.swing.JButton autoReplay;
    private javax.swing.JPanel boardCanvas;
    private javax.swing.JLabel changeSpeedText;
    private javax.swing.JLabel chipsLeft;
    private javax.swing.JLabel chipsleftText2;
    private javax.swing.JMenuItem exitWithX;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JPanel gameCanvas;
    private javax.swing.JMenu help;
    private javax.swing.JPanel inventoryPanel;
    private javax.swing.JLabel inventoryText;
    private javax.swing.JLabel inventoryText1;
    private javax.swing.JLabel item0;
    private javax.swing.JLabel item1;
    private javax.swing.JLabel item2;
    private javax.swing.JLabel item3;
    private javax.swing.JLabel item4;
    private javax.swing.JLabel item5;
    private javax.swing.JLabel item6;
    private javax.swing.JLabel item7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel levelAndTimer;
    private javax.swing.JLabel levelNumber;
    private javax.swing.JMenuItem levelOne;
    private javax.swing.JLabel levelText;
    private javax.swing.JMenuItem levelTwo;
    private javax.swing.JMenu levelsMenu;
    private javax.swing.JMenuItem newGameSameLevel;
    private javax.swing.JMenuItem pause;
    private javax.swing.JMenuItem replayButton;
    private javax.swing.JButton replayForwards;
    private javax.swing.JMenuItem rulesLegend;
    private javax.swing.JMenuItem saveButton;
    private javax.swing.JSlider speedChooser;
    private javax.swing.JLabel timer;
    private javax.swing.JLabel timerText;
    // End of variables declaration//GEN-END:variables
      // Game variables
    private final JFileChooser fileChooser = new JFileChooser();
    private ImageIcon[] numberImg = new ImageIcon[10];
    private String mode;
    private static int level = -1;
    private enum modes { Run, Load, Save, Replay}
    private GameTimer gameCountdown;
    private Render render;
    private Maze m;
    private EventListener eventListener;
    private int pausedAtMin;
    private int pausedAtSec;
    private EventIterator eventIterator;
    private ImageIcon[] inventoryItems = new ImageIcon[8];
    private JLabel[] inventoryLabels = new JLabel[8];
    private int replaySpeed;
    private java.awt.event.WindowEvent evtOpen;
    private PropertyEditorSupport propertyEditorSupport= new PropertyEditorSupport();
    private int canMove = 1;
    
    private HashMap<Integer, BugEntity> bugMap = new HashMap<Integer, BugEntity>();
    
    public void addBug(int bugId, BugEntity bug) {
    	this.bugMap.put(bugId, bug);
    }
    
    public BugEntity getBug(int bugId) {
    	return this.bugMap.get(bugId);
    }

    /**
     * Initialices the images and JPanels honding them to display Chap's inventory.
     */
    private void displayInventory(){
        for (int i = 0; i < inventoryItems.length; i++) {
            if(i<m.getPlayerInventory().size()) {
                inventoryItems[i] = new ImageIcon("src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/" + m.getPlayerInventory().get(i).getName() + "Tile.png");
                //set the size
                inventoryItems[i].setImage(inventoryItems[i].getImage().getScaledInstance(66, 62, Image.SCALE_DEFAULT));
                inventoryLabels[i].setIcon(inventoryItems[i]);
                // inventoryLabels[i].setToolTipText(m.getPlayerInventory().get(i).getName());
            } else {
                inventoryLabels[i].setIcon(null);
            }


  /**
   * Resumes unfinished games.
   *
   * @param evt Click or CTRL+R.
   */
  private void resumeGameActionPerformed(java.awt.event.ActionEvent evt) {
    display("This feature was not implemented");
  }

  /**
   * Creates a new game from last unfinished level.
   *
   * @param evt Click or CTRL+P
   */
  private void newGameSameLevelActionPerformed(java.awt.event.ActionEvent evt) {
    gameCountdown.pause(); // old timer is stopped
    new GuiWindow().setVisible(true);
    // Game always starts from Run mode
    mode = modes.Run.name();
    // Makes sure there is nly one window open at the time
    // since game has a countdown I found it pointless to have
    // mode than one window open.
    if (GuiWindow.getWindows().length > 1) {
      for (int i = 0; i < GuiWindow.getWindows().length - 1; i++) {
        GuiWindow.getWindows()[i].dispose();
      }
    }
  }

  /**
   * Exits game without saving nor warnings.
   *
   * @param evt Click on exit game in the game menu or CTRL+X
   */
  private void exitWithXActionPerformed(java.awt.event.ActionEvent evt) {
    System.exit(0);
  }

  private void speedChooserAncestorAdded(javax.swing.event.AncestorEvent evt) {

  }

  /**
   * Processes users input when the button for displaying rules is pressed.
   *
   * @param evt rules and instructions button clicked on the game menu.
   */
  private void rulesLegendActionPerformed(java.awt.event.ActionEvent evt) {
    pausedAtMin = gameCountdown.getCurrentMin();
    pausedAtSec = gameCountdown.getCurrentSec();
    gameCountdown.pause();
    display(" ~ Use the arrows on your key board to move Chap around the board.\n"
        + " ~ To win the game make sure you collect all the chips on \n"
        + "  the board within 2 minutes and go to the blue tile.\n"
        + " ~ Open doors by collecting keys of the same color.\n"
        + " ~ On level 2 do not let the bug reach Chap and be careful\n"
        + "with the special tiles! Some items withing the board\n" + "will help you with those ;)\n"
        + " ~ If you want to see all your moves play Replay mode.\n"
        + "Use the \">\" button to replay forwards step by step (Default).\n"
        + "or set AutoReplay and adjust to your desired speed.\n"
        + "You can also save the game and resume later by going to \"File\" and\n" 
        + "click on \"Save\"");
    if (mode.equals(modes.Run.name())) {
      gameCountdown = new GameTimer(pausedAtMin, pausedAtSec, this);
    }
  }

  /**
   * Initialices the images and JPanels honding them to display Chap's inventory.
   */
  private void displayInventory() {
    for (int i = 0; i < inventoryItems.length; i++) {
      if (i < m.getPlayerInventory().size()) {
        inventoryItems[i] = new ImageIcon(
            "src/nz/ac/vuw/ecs/swen225/gp20/render/TileFile/" + m.getPlayerInventory()
            .get(i).getName() + "Tile.png");
        // set the size
        inventoryItems[i].setImage(inventoryItems[i].getImage()
            .getScaledInstance(66, 62, Image.SCALE_DEFAULT));
        inventoryLabels[i].setIcon(inventoryItems[i]);
        // inventoryLabels[i].setToolTipText(m.getPlayerInventory().get(i).getName());
      } else {
        inventoryLabels[i].setIcon(null);
      }
    }

  }

  /**
   * If the player is on top of the info tile application shows a pop up message.
   *
   * @param information hint to help player.
   */
  private void popUpInfo(String information) {
    if (information != null) {
      pausedAtMin = gameCountdown.getCurrentMin();
      pausedAtSec = gameCountdown.getCurrentSec();
      gameCountdown.pause();
      display(information);
      gameCountdown = new GameTimer(pausedAtMin, pausedAtSec, this);
    }
  }

  /**
   * Displays the number of treausures left on the board.
   */
  private void setChipsLeft() {
    int chips = m.target - m.getChipsLeft();
    chipsLeft.setText(chips + " / " + m.target);
  }

  /**
   * Allocates the level for the replay mode.
   */
  private void replaySetLevel() {
    if (mode != null && mode.equals(modes.Replay.name())) {
      if (this.eventIterator == null) {
        System.err.println("Please select the saved game to replay from File menu");
        return;
      }
      Event evt = this.eventIterator.next();
      System.out.println("Replay saved game in level: " + evt.getLevel());
      this.setLevelNumber(evt.getLevel());
      boardCanvas.setVisible(false);
      render = new Render(m);
      boardCanvas = render.getView();
      gameCanvas.add(boardCanvas);
      boardCanvas.setLocation(70, 35);
      validate();
      repaint();
      setVisible(true);
    }
  }

    /**
     * Countdown for game when players initializes the game.
     * @return countdown for game
     */
    public JLabel getTimer() { return timer;}

    /**
     * Current render within this game.
     * @return board of current game.
     */
    public Render getRender() { return render;}

    /**
     * Event of window opening.
     * @return event when GUI Window opens.
     */
    public WindowEvent getOpenEvt(){ return evtOpen;}

    /**
     * Notifies record and replay module where the bugs move.
     * @param move direction of bug.
     * @param entityID bug reference.
     */
    public void notifyRecord(SingleMove move, int entityId){
        //TODO
        // Update Single move to move and implement a new parameter to
        // add the bug's ID (as there are 2)
    	//@TODO: Need to check if game is not continuing (maze.mode?), don't emit event.
    	if(mode != null && !mode.equals(modes.Replay.name())) {
    		eventListener.onEvent(Event.eventOfBugMove(move, entityId));
    	}
    }

      /**
   * Displays the level on the GUI by receiving the information from the maze
   * module.
   *
   * @param level level chosen by user.
   */
  private void setLevelNumber(int level) {
    GuiWindow.level = level;
    levelNumber.setText("0" + level);
    writeLevelFile(level + "");
    if (mode != null && !mode.equals(modes.Replay.name())) {
      this.eventListener.onEvent(Event.eventOfLevelSetting(level));

    try {
      LevelReader lr = new LevelReader(level);
      m = new Maze(lr);
      lr.setApplication(this);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Writes text file where the number of the last level played is stored.
   *
   * @param level last unfinished level.
   */
  private void writeLevelFile(String level) {
    File old = new File("src/nz/ac/vuw/ecs/swen225/gp20/application/data/level.txt");
    old.delete();
    try {
      FileWriter writer = new FileWriter("src/nz/ac/vuw/ecs/swen225/" 
          + "gp20/application/data/level.txt", true);
      writer.write(level);
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Reads text file where the number of the last level played was stored.
   */
  private int readLevelFile() {
    int lastLevel = -1;
    try {
      FileReader reader = new FileReader("src/nz/ac/vuw/ecs/swen225/" 
          + "gp20/application/data/level.txt");
      BufferedReader bufferedReader = new BufferedReader(reader);

      int line;

      line = Integer.parseInt(bufferedReader.readLine());
      System.out.println(line);
      lastLevel = line;
      reader.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
    if (lastLevel == 1 || lastLevel == 2) {
      return lastLevel;
    } else {
      display("Opps! You have not played this game\n before. \nLet's start from level one");
      return 1;
    }
  }

  /**
   * Countdown for game when players initializes the game.
   *
   * @return countdown for game
   */
  public JLabel getTimer() {
    return timer;
  }

  /**
   * Current render within this game.
   *
   * @return board of current game.
   */
  public Render getRender() {
    return render;
  }

  /**
   * Event of window opening.
   *
   * @return event when GUI Window opens.
   */
  public WindowEvent getOpenEvt() {
    return evtOpen;
  }

  /**
   * Notifies record and replay module where the bugs move.
   *
   * @param move     direction of bug.
   * @param entityID bug reference.
   */
  public void notifyRecord(SingleMove move, int entityID) {
    eventListener.onEvent(Event.eventOfBugMove(move));
  }

  /**
   * .
   *
   * @param data message to be displayed.
   */
  public void display(String data) {
    int result = JOptionPane.showConfirmDialog(null, data, "Alert", JOptionPane.PLAIN_MESSAGE);
  }

  /**
   * Adds listeners/observers to notify them when users presses a key.
   *
   * @param propertyChangeListener observer.
   */
  public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener) {
    propertyEditorSupport.addPropertyChangeListener(propertyChangeListener);
  }

  /**
   * .
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
          .getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException | InstantiationException | UnsupportedLookAndFeelException
        | IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(GuiWindow.class.getName())
           .log(java.util.logging.Level.SEVERE, null, ex);
    }

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new GuiWindow().setVisible(true);
      }
    });
  }
}
