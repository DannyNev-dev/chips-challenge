package nz.ac.vuw.ecs.swen225.gp20.recnplay;

import java.util.Iterator;

public class EventIterator implements Iterator{
	
	private Record record;
	private long latency;

	@Override
	public boolean hasNext() {
		Iterator it = record.getIterator();
		while(it.hasNext()) {
			return true;
		}
		return false;
	}

	public Object next(long latency) {
		Iterator it = record.getIterator();
		while(it.hasNext()) {
			try {
				Thread.sleep(latency);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return it.next();
		}
		return null;
		
	}

	@Override
	public Object next() {
		return latency;
	}

}
