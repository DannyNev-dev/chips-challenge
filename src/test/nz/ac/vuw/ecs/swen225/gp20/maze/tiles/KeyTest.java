package test.nz.ac.vuw.ecs.swen225.gp20.maze.tiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

//import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.Key;

class KeyTest {

  @Test
  void keyNameTests() {
    for(Key.Colour type : Key.Colour.values()) {
      Key tmpKey = new Key(type);
      
      assertEquals(type.name().toLowerCase()+"KeyTile", tmpKey.getName());
    }
  }

}
