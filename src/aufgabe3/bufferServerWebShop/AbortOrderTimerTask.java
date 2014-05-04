package aufgabe3.bufferServerWebShop;

import java.util.TimerTask;

import aufgabe3.WebShop;

public class AbortOrderTimerTask extends TimerTask {

	@Override
	public void run() {
		WebShop.item = WebShop.currentBuffer.remove();
		System.err.println("Bestellung abgebrochen! \n");
	}
}
