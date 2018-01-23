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
	PacKeyListener(Map<Integer, Action> keyMappings) {Collect.Hit("PacKeyListener.java","PacKeyListener(Map<Integer, Action> keyMappings)");assert keyMappings != null; Collect.Hit("PacKeyListener.java","PacKeyListener(Map<Integer, Action> keyMappings)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacKeyListener.java|(577,27,<24,2>,<24,29>)"); this.mappings = keyMappings; Collect.Hit("PacKeyListener.java","PacKeyListener(Map<Integer, Action> keyMappings)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacKeyListener.java|(608,28,<25,2>,<25,30>)");}
	
	@Override
	public void keyPressed(KeyEvent e) {Collect.Hit("PacKeyListener.java","keyPressed(KeyEvent e)");assert e != null; Collect.Hit("PacKeyListener.java","keyPressed(KeyEvent e)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacKeyListener.java|(698,17,<30,2>,<30,19>)"); Action action = mappings.get(e.getKeyCode()); Collect.Hit("PacKeyListener.java","keyPressed(KeyEvent e)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacKeyListener.java|(719,45,<31,2>,<31,47>)"); if (action != null) {
			action.doAction();
		} Collect.Hit("PacKeyListener.java","keyPressed(KeyEvent e)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacKeyListener.java|(768,49,<32,2>,<34,3>)");}

	@Override
	public void keyTyped(KeyEvent e) {Collect.Hit("PacKeyListener.java","keyTyped(KeyEvent e)");Collect.Hit("PacKeyListener.java","keyTyped(KeyEvent e)", "|project://sqat-analysis/src/sqat/series2/A1b_DynCov.rsc|(4826,34)");}
	
	@Override
	public void keyReleased(KeyEvent e) {Collect.Hit("PacKeyListener.java","keyReleased(KeyEvent e)");Collect.Hit("PacKeyListener.java","keyReleased(KeyEvent e)", "|project://sqat-analysis/src/sqat/series2/A1b_DynCov.rsc|(4826,34)");}
}