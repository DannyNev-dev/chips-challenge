package test.nz.ac.vuw.ecs.swen225.gp20.persistence;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import nz.ac.vuw.ecs.swen225.gp20.maze.Maze;
import nz.ac.vuw.ecs.swen225.gp20.persistence.LevelReader;

public class LvlReaderTest {
	
	@Test
	void test1() throws IOException {
		assertEquals(LevelReader.deserializeLevel(1).getClass(),Maze.class);
	}
}
