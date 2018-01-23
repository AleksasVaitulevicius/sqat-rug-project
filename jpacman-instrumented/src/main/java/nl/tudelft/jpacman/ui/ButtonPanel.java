package nl.tudelft.jpacman.ui;import coverageApi.Collect;import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel; class ButtonPanel extends JPanel {
	//
	/**
	 * Default serialisation ID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Create a new button panel with a button for every action.
	 * @param buttons The map of caption - action for each button.
	 * @param parent The parent frame, used to return window focus.
	 */
	ButtonPanel(final Map<String, Action> buttons, final JFrame parent) {super();Collect.Hit("ButtonPanel.java","ButtonPanel(final Map<String, Action> buttons, final JFrame parent)");assert buttons != null; Collect.Hit("ButtonPanel.java","ButtonPanel(final Map<String, Action> buttons, final JFrame parent)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/ButtonPanel.java|(754,23,<30,2>,<30,25>)"); assert parent != null; Collect.Hit("ButtonPanel.java","ButtonPanel(final Map<String, Action> buttons, final JFrame parent)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/ButtonPanel.java|(781,22,<31,2>,<31,24>)"); for (final String caption : buttons.keySet()) {
			JButton button = new JButton(caption);
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {Collect.Hit("ButtonPanel.java","actionPerformed(ActionEvent e)");buttons.get(caption).doAction(); Collect.Hit("ButtonPanel.java","actionPerformed(ActionEvent e)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/ButtonPanel.java|(1027,32,<39,5>,<39,37>)"); parent.requestFocusInWindow(); Collect.Hit("ButtonPanel.java","actionPerformed(ActionEvent e)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/ButtonPanel.java|(1066,30,<40,5>,<40,35>)");}
			});
			add(button);
		} Collect.Hit("ButtonPanel.java","ButtonPanel(final Map<String, Action> buttons, final JFrame parent)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/ButtonPanel.java|(811,322,<33,2>,<44,3>)");}
}
