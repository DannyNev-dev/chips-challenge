package nz.ac.vuw.ecs.swen225.gp20.application;

import java.util.Timer;
import java.util.TimerTask;

/**
 *Class that manages the game count down.
 * @author Camila Lis 300504575
 */



public class GameTimer {

  private int currentMin;
  private int currentSec;
  private GuiWindow guiWindow;

  /**Timer constructor.
   * @param min from where it starts.
   * @param sec from where it starts.
   * @param guiWindow window where timer is displayed.
   */
  public GameTimer(int min, int sec, GuiWindow guiWindow) {
    this.currentMin = min;
    this.currentSec = sec;
    this.guiWindow = guiWindow;
    this.start();
  }

  private Timer timer = new Timer();

  /**.
   *  Timer used to do the countdown for the game on each level
   */
  private TimerTask task = new TimerTask() {
    @Override
    public void run() {
      currentSec--;
      if (currentSec == 0 && currentMin > 0) { //Decreases seconds only if minute is above 0.
        currentSec = 59;
        currentMin--;
      } else if (timeOut()) {
        timer.cancel();
        guiWindow.formWindowLost(guiWindow.getOpenEvt());
      }
      //Check is number only contains 1 digit to add 0 for displaying purposes.
      if (currentSec < 10) {
        guiWindow.getTimer().setText(currentMin + ":0" + currentSec);
      } else {
        guiWindow.getTimer().setText(currentMin + ":" + currentSec);
      }
    }
  };

  /**
   * Runs a countdown starting at 2 minutes.
   */
  public void start() {
    timer.scheduleAtFixedRate(task, 1000, 1000);
  }

  /**
   * Stops countdown and stored the current timer components to be able to restart it later.
   */
  public void pause() {
    timer.cancel();
  }

  /**
   * Tells whether or not time is out.
   * @return time left
   */
  public boolean timeOut() { 
    return currentSec == 0 && currentMin == 0;
  }

  /**
   * Minute shown on timer.
   * @return integer of the current minute
   */
  public int getCurrentMin() { 
    return currentMin;
  }
  
  /**
   * Seconds shown on the timer.
   * @return integer of the current minute
   */
  public int getCurrentSec() { 
    return currentSec; 
  }

}
