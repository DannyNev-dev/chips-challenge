package test.nz.ac.vuw.ecs.swen225.gp20.persistence;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import nz.ac.vuw.ecs.swen225.gp20.maze.Maze;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Player;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.Tile;
import nz.ac.vuw.ecs.swen225.gp20.persistence.LevelReader;

/**
 * The Class LvlReaderTest.
 *
 * @author Daniel
 */
public class LvlReaderTest {
	//Functionality Tests	
		/**
		 * Test 1.
		 * Test that we are able to create a maze from the level
		 * @throws Exception 
		 */
		@Test
		void test1() throws Exception {
			LevelReader lR = new LevelReader(1);
			assertEquals(lR.loadBoard().getClass(),Tile[][].class);	
		}
		/**
		 * Test 2.
		 * Test that we are able to create player
		 * @throws Exception 
		 */
		@Test
		void test2() throws Exception {
			LevelReader lR = new LevelReader(1);
			assertEquals(lR.loadPlayer().getClass(),Player.class);	
		}
		/**
		 * Test 3.
		 * Test that we are able to get target
		 * @throws Exception 
		 */
		@Test
		void test3() throws Exception {
			LevelReader lR = new LevelReader(1);
			assertEquals(lR.loadTarget(),7);	
		}
		@Test
		void eTest1() {
			assertThrows(IOException.class, () -> 
			new LevelReader(4));
		}
		@Test
		void eTest2() {
			assertThrows(IOException.class, () -> 
			new LevelReader(-1));
		}
}
