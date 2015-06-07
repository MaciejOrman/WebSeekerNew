package pl.wroc.pwr.gui;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class GUI extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8273397752974601758L;

	public GUI(){
		initUI();
	}

    private void initUI() {
    	setLayout(new FlowLayout());
    	final JTextField queryTextField = new JTextField(25);
    	add(queryTextField);
        JButton searchButton = new JButton("Search in Faroo");
        add(searchButton);
        Container pane = getContentPane();
        searchButton.addActionListener(new SearchAction(pane, queryTextField.getText()));


        setTitle("Web seeker");
        setSize(1300, 900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
