package nz.ac.vuw.ecs.swen225.gp20.recnplay.test;

public class Action {

    private TestMove tMove;

    private Action() {
    }

    public Action(TestMove tMove) {
        this();
        this.tMove = tMove;
    }

    public TestMove getMove() {
        return tMove;
    }
}