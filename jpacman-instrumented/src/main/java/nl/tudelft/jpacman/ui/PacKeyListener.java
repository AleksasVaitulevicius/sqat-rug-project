package nl.tudelft.jpacman.ui;import coverageApi.Collect;import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map; class PacKeyListener implements KeyListener {

	/**
	 * The mappings of keyCode to action.
	 */
	private final Map<Integer, Action> mappings;
	
	/**
	 * Create a new key listener based on a set of keyCode-action pairs.
	 * @param keyMappings The mappings of keyCode to action.
	 */
	PacKeyListener(Map<Integer, Action> keyMappings) {Collect.Hit("PacKeyListener.java","PacKeyListener(Map<Integer, Action> keyMappings)");assert keyMappings != null; Collect.Hit("PacKeyListener.java","PacKeyListener(Map<Integer, Action> keyMappings)", "577"); this.mappings = keyMappings; Collect.Hit("PacKeyListener.java","PacKeyListener(Map<Integer, Action> keyMappings)", "608");}
	
	@Override
	public void keyPressed(KeyEvent e) {Collect.Hit("PacKeyListener.java","keyPressed(KeyEvent e)");assert e != null; Collect.Hit("PacKeyListener.java","keyPressed(KeyEvent e)", "698"); Action action = mappings.get(e.getKeyCode()); Collect.Hit("PacKeyListener.java","keyPressed(KeyEvent e)", "719"); if (action != null) {
			action.doAction();
		} Collect.Hit("PacKeyListener.java","keyPressed(KeyEvent e)", "768");}

	@Override
	public void keyTyped(KeyEvent e) {Collect.Hit("PacKeyListener.java","keyTyped(KeyEvent e)");Collect.Hit("PacKeyListener.java","keyTyped(KeyEvent e)", "4828");}
	
	@Override
	public void keyReleased(KeyEvent e) {Collect.Hit("PacKeyListener.java","keyReleased(KeyEvent e)");Collect.Hit("PacKeyListener.java","keyReleased(KeyEvent e)", "4828");}
}