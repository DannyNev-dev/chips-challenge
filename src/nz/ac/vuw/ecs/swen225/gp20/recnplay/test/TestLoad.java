package nz.ac.vuw.ecs.swen225.gp20.recnplay.test;

import nz.ac.vuw.ecs.swen225.gp20.recnplay.EventListener;
import nz.ac.vuw.ecs.swen225.gp20.recnplay.RecordedGame;

/**
 * test load file function.
 * @author YanLu
 *
 */
public class TestLoad {
	/**
	 * test file load function.
	 * @param args
	 */
	public static void main(String[] args) {
		EventListener listener = EventListener.eventListenerFactory();
		RecordedGame rg = EventListener.getRecord().loadGame("Level2_13_10_2020_15-39.json");
		System.out.print(rg.toString());		
	}
}