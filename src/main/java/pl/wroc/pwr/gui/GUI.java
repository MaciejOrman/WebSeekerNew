package pl.wroc.pwr.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import com.mashape.unirest.http.exceptions.UnirestException;

import pl.wroc.pwr.searchengine.FarooSearch;
import pl.wroc.pwr.searchengine.dto.FarooResults;
import pl.wroc.pwr.searchengine.dto.Link;

public class GUI extends JFrame{
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

        //createLayout(queryTextField,  searchButton);

        setTitle("Web seeker");
        setSize(1100, 900);
        //setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void createLayout(JComponent... arg) {

        Container pane = getContentPane();
        //GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(new FlowLayout());
    	JScrollPane scrollablePane = new JScrollPane(pane);
		scrollablePane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);


        for(int i=0; i<arg.length;i++){
        	pane.add(arg[i]);
        }
    }

	
}
