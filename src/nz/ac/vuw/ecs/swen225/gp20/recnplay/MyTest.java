package nz.ac.vuw.ecs.swen225.gp20.recnplay;

public class MyTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// How to use your save()
		// new ActionSave.run(m)

		// Movement m = new Movement(abc)
		// save(m)
		// Which Type? x = load()
		// verify m.equals(x)
	}

}

class Movement extends Event {
	String direction;

	Movement(String d) {
		this.direction = d;
	}
}