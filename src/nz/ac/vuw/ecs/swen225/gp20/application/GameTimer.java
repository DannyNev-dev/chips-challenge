package nz.ac.vuw.ecs.swen225.gp20.application;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author camilalis 300504575
 */



public class GameTimer {

  private int currentMin;
  private int currentSec;
  private boolean restarted = false;


  /**Timer constructor.
   * @param min from where it starts.
   * @param sec from where it starts.
   */
  public GameTimer(int min, int sec) {
    this.currentMin = min;
    this.currentSec = sec;
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
      if( currentSec == 0 && currentMin > 0){ //Decreases seconds only if minute is above 0.
        currentSec = 59;
        currentMin--;
      }else if( timeOut()){
        timer.cancel();
        GUIWindow.display("Game Over\nYou run out of time!");
        System.exit(0);
      }
      //Check is number only contains 1 digit to add 0 for displaying purposes.
      if(currentSec < 10){
        GUIWindow.getTimer().setText(currentMin + ":0" + currentSec);
      }else {
        GUIWindow.getTimer().setText(currentMin + ":" + currentSec);
      }
    }
  };

  /**
   * Runs a countdown starting at 2 minutes.
   */
  public void start(){
    timer.scheduleAtFixedRate(task, 1000, 1000);
  }

  /**
   * Stops countdown and stored the current timer components to be able to restart it later.
   */
  public void pause(){
    timer.cancel();
  }

  /**
   * Tells whether or not time is out.
   * @return time left
   */
  private boolean timeOut(){ return currentSec == 0 && currentMin ==0 ;}

  /**
   * Minute shown on timer.
   * @return integer of the current minute
   */
  public int getCurrentMin(){
    return currentMin;
  }

  /**
   * Seconds shown on the timer.
   * @return integer of the current minute
   */
  public int getCurrentSec(){
    return currentSec;
  }

  /**
   * Method used to trim seconds into 0-9 digits to display more
   * easily with images.
   * @return array of digits withing second;
   */
  public char[] getDigitsOfSecs(){
    return String.valueOf(currentSec).toCharArray();
  }

}
    
