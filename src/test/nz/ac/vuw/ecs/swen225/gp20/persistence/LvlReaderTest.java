package test.nz.ac.vuw.ecs.swen225.gp20.persistence;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.junit.jupiter.api.Test;

import nz.ac.vuw.ecs.swen225.gp20.application.GuiWindow;
import nz.ac.vuw.ecs.swen225.gp20.maze.Maze;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Player;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.Tile;
import nz.ac.vuw.ecs.swen225.gp20.persistence.LevelReader;

// TODO: Auto-generated Javadoc
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
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
		@Test
		void test1() throws IOException {
			LevelReader lR = new LevelReader(1);
			assertEquals(lR.loadBoard().getClass(),Tile[][].class);	
		}
		
		/**
		 * Test 1.5.
		 * Test that we are able to create a maze from the level 2
		 *
		 * @throws IOException Signals that an I/O exception has occurred.
		 */
		@Test
		void test1point5() throws IOException {
			LevelReader lR = new LevelReader(2);
			assertEquals(lR.loadBoard().getClass(),Tile[][].class);	
		}
		
		/**
		 * Test 2.
		 * Test that we are able to create player
		 *
		 * @throws IOException Signals that an I/O exception has occurred.
		 */
		@Test
		void test2() throws IOException {
			LevelReader lR = new LevelReader(1);
			assertEquals(lR.loadPlayer().getClass(),Player.class);	
		}
		
		/**
		 * Test 3.
		 * Test that we are able to get target
		 *
		 * @throws IOException Signals that an I/O exception has occurred.
		 */
		@Test
		void test3() throws IOException {
			LevelReader lR = new LevelReader(1);
			assertEquals(lR.loadTarget(),7);	
		}
		
		/**
		 * Test 4.
		 * checks that we can create level 2 and set maze
		 *
		 * @throws IOException Signals that an I/O exception has occurred.
		 */
		@Test
		void test4() throws IOException {
			LevelReader lR = new LevelReader(2);
			Maze m = new Maze(lR);
			assertTrue(lR.setMaze(m));
		}
		
		/**
		 * Test 5.
		 * checks that we can create level 2 and set application
		 *
		 * @throws IOException Signals that an I/O exception has occurred.
		 */
		@Test
		void test5() throws IOException {
			LevelReader lR = new LevelReader(2);
			Maze m = new Maze(lR);
			lR.setMaze(m);
			GuiWindow gw = new GuiWindow();
			assertTrue(lR.setApplication(gw));
		}
		/**
		 * Exception test level positively greater test 1.
		 */
		@Test
		void eTest1() {
			assertThrows(IOException.class, () -> 
			new LevelReader(4));
		}
		
		/**
		 * Exception test 2 level negative.
		 */
		@Test
		void eTest2() {
			assertThrows(IOException.class, () -> 
			new LevelReader(-1));
		}
}
