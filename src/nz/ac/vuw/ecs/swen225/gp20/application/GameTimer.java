package nz.ac.vuw.ecs.swen225.gp20.application;
import java.util.*;
import java.util.Timer;

/**
 *
 * @author camilalis
 */
    
    
    
public class GameTimer {

private int currentMin =1, currentSec =60, stoppedSec, stoppedMin;
private boolean restarted = false;

private Timer timer = new Timer();

    /**
     *  Timer used to do the countdown for the game on each level
     */
    private TimerTask task = new TimerTask() {
    @Override
        public void run() {
            currentSec--;
            if( currentSec == 0 && currentMin > 0){
                currentSec = 59;
                currentMin--;
            }else if( currentMin == 0 && currentSec == 0){
                timer.cancel();
                GUIWindow.display("Game Over\nYou run out of time!");
            }else if( restarted ){
                currentSec = stoppedSec;
                currentMin = stoppedMin;
                restarted = false;
            }
            if(currentSec < 10){
                GUIWindow.timer.setText(currentMin + " : 0" + currentSec);
            }else {
                GUIWindow.timer.setText(currentMin + " : " + currentSec);
            }
            //System.out.println( "Countdown " + currentMin + " : " + currentSec);
        }
    };

    /**
     * Runs a countdown starting at 2 minutes
     */
    public void start(){
       // timeOut = false;
        timer.scheduleAtFixedRate(task, 1000, 1000);
    }

    /**
     * Stops countdown and stored the current timer components
     * to be able to restart it later
     */
    public void pause(){
        stoppedSec = currentSec;
        stoppedMin = currentMin;
        timer.cancel();
    }

    /**
     * Set to tell when to restart the old countdown
     * @return always true
     */
    public boolean setRestarted(){
        return restarted = true;
    }

    /**
     * Tells whether or not time is out.
     */
    public boolean timeOut(){ return currentSec == 0 && currentMin ==0 ;}

    /**
     * Method will be used to display timer with images
     * @return integer of the current minute
     */
    public int getMinute(){
        return currentMin;
    }

    /**
     * Method used to trim seconds into 0-9 digits to display more
     * easily with images
     * @return array of digits withing second;
     */
    public char[] getDigitsOfSecs(){
        return String.valueOf(currentSec).toCharArray();
    }

}
    
