package pl.wroc.pwr.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import pl.wroc.pwr.searchengine.FarooSearch;
import pl.wroc.pwr.searchengine.dto.FarooResults;
import pl.wroc.pwr.searchengine.dto.Link;

import com.mashape.unirest.http.exceptions.UnirestException;

public class SearchAction implements ActionListener {
	private Container pane;
	JPanel panel;
	JScrollPane scrollablePane;

	public SearchAction(Container pane, String query) {
		this.pane = pane;
		panel = new JPanel(new GridLayout(10, 1));
	}

	public void actionPerformed(ActionEvent event) {

		panel.removeAll();
		pane.remove(panel);

		if (scrollablePane != null) {
			pane.remove(scrollablePane);
		}
		refreshPanel();

		FarooSearch farooSearch = new FarooSearch();
		try {
			String query = ((JTextField) pane
					.getComponent(0)).getText();
			FarooResults results = farooSearch.searchInFaroo(query);
			List<Link> resultLinks = results.getResults();
			JButton linkButton;
			JCheckBox isLinkUsefulCheckbox;
			CheckboxAction checkboxAction = new CheckboxAction();
			
			for (int i = 0; i < resultLinks.size(); i++) {
				Link link = resultLinks.get(i);
				linkButton = createLinkButton(link);
				panel.add(linkButton);
				
				isLinkUsefulCheckbox = new JCheckBox("Is link useful for your query?");
				isLinkUsefulCheckbox.setActionCommand(""+i);
				isLinkUsefulCheckbox.addActionListener(checkboxAction);
				
				panel.add(isLinkUsefulCheckbox);
			}
			scrollablePane = new JScrollPane(panel);
			scrollablePane
					.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
			scrollablePane
					.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scrollablePane.setPreferredSize(new Dimension(1200, 800));
			pane.add(scrollablePane);
			refreshPanel();

		} catch (UnirestException e) {
			e.printStackTrace();
		}
	}

	private JButton createLinkButton(Link link) {
		JButton linkButton= new JButton();;
		String linkLabel = getLinkLabel(link);
		linkButton.setText(linkLabel);
		linkButton.setHorizontalAlignment(SwingConstants.LEFT);
		linkButton.setBackground(Color.WHITE);
		linkButton.addActionListener(new OpenUrlAction(link.getUrl()));
		return linkButton;
	}

	private String getLinkLabel(Link link) {
		String result = "<HTML>";
		result += "<h2>" + link.getTitle() + "</h2>";
		result += "<h4>" + link.getUrl() + "</h4>";
		result += link.getKwic();
		result += "</HTML>";
		return result;
	}
	
	private void refreshPanel() {
		pane.validate();
		pane.repaint();
	}

}
