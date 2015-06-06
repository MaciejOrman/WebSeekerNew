package pl.wroc.pwr.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import pl.wroc.pwr.searchengine.FarooSearch;
import pl.wroc.pwr.searchengine.dto.FarooResults;
import pl.wroc.pwr.searchengine.dto.Link;

import com.mashape.unirest.http.exceptions.UnirestException;

public class SearchAction implements ActionListener{
	private Container pane;
	JPanel panel;
	JScrollPane scrollablePane;
	
	public SearchAction(Container pane, String query){
		this.pane =pane;
		panel = new JPanel(new GridLayout(10, 1));
		

	}

    public void actionPerformed(ActionEvent event) {
    	
    	panel.removeAll();
    	pane.remove(panel);
    	if(scrollablePane!=null){
    		pane.remove(scrollablePane);
    	}
        	panel.validate();
		panel.repaint();

		

        FarooSearch farooSearch = new FarooSearch();
        try {
		FarooResults results = farooSearch.searchInFaroo(((JTextField)pane.getComponent(0)).getText());
		List<Link> resultLinks = results.getResults();
		for(int i=0; i<resultLinks.size();i++){
			Link link = resultLinks.get(i);
			JButton button = new JButton();
			String result = "<HTML>";
			result+="<h2>"+link.getTitle()+"</h2>";
			result+="<h4>"+link.getUrl()+"</h4>";
			result+=link.getKwic();
			result+="</HTML>";
			
		    button.setText(result);
		    button.setHorizontalAlignment(SwingConstants.LEFT);
		   // button.setBorderPainted(false);
		   // button.setOpaque(false);
		    button.setBackground(Color.WHITE);
		    button.addActionListener(new OpenUrlAction(link.getUrl()));
		    panel.add(button);
		}
		 scrollablePane = new JScrollPane(panel);
		scrollablePane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollablePane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollablePane.setPreferredSize(new Dimension(1000, 800));
		pane.add(scrollablePane);
		pane.validate();
		pane.repaint();
		
		
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
