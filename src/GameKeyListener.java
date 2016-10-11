import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameKeyListener implements KeyListener {
	public void keyTyped(KeyEvent e) {           
	}

	public void keyPressed(KeyEvent e) {
		System.out.println(e + " key was pressed");
	}

	public void keyReleased(KeyEvent e) {            
	}
}
