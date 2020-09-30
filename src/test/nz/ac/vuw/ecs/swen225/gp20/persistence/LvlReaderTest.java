package test.nz.ac.vuw.ecs.swen225.gp20.persistence;

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
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	void test1() throws IOException {
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
	}

	@Test
	void eTest1() {
		assertThrows(IOException.class, () -> LevelReader.deserializeLevel(8));
	}
	@Test
	void eTest2() {
		assertThrows(IOException.class, () -> LevelReader.deserializeLevel(-4));
	}
	
	
}
