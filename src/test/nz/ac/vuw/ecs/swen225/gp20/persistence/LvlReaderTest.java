package test.nz.ac.vuw.ecs.swen225.gp20.persistence;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import nz.ac.vuw.ecs.swen225.gp20.maze.Maze;
import nz.ac.vuw.ecs.swen225.gp20.persistence.LevelReader;

// TODO: Auto-generated Javadoc
/**
 * The Class LvlReaderTest.
 *
 * @author OEM
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
		assertEquals(LevelReader.deserializeLevel(1).getClass(),Maze.class);	
	}
	
	/**
	 * Test 2.
	 * Test that we are able to 
	 * @throws IOException 
	 * 
	 */
	@Test
	void test2() throws IOException {
		//assertTrue(LevelReader.deserializeLevel(1).board[0][0] instanceof Tile);
	}
//exception testing
	@Test
	void eTest1() {
		assertThrows(IOException.class, () -> LevelReader.deserializeLevel(8));
	}
	@Test
	void eTest2() {
		assertThrows(IOException.class, () -> LevelReader.deserializeLevel(-4));
	}
	
	
}
