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
	ButtonPanel(final Map<String, Action> buttons, final JFrame parent) {super();Collect.Hit("ButtonPanel.java","ButtonPanel(final Map<String, Action> buttons, final JFrame parent)");assert buttons != null; Collect.Hit("ButtonPanel.java","ButtonPanel(final Map<String, Action> buttons, final JFrame parent)", "754"); assert parent != null; Collect.Hit("ButtonPanel.java","ButtonPanel(final Map<String, Action> buttons, final JFrame parent)", "781"); for (final String caption : buttons.keySet()) {
			JButton button = new JButton(caption);
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {Collect.Hit("ButtonPanel.java","actionPerformed(ActionEvent e)");buttons.get(caption).doAction(); Collect.Hit("ButtonPanel.java","actionPerformed(ActionEvent e)", "1027"); parent.requestFocusInWindow(); Collect.Hit("ButtonPanel.java","actionPerformed(ActionEvent e)", "1066");}
			});
			add(button);
		} Collect.Hit("ButtonPanel.java","ButtonPanel(final Map<String, Action> buttons, final JFrame parent)", "811");}
}
