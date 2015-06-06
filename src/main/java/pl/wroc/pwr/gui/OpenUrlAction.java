package pl.wroc.pwr.gui;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class OpenUrlAction implements ActionListener  {
	
	private String url;
	
	public OpenUrlAction(String url){
		this.setUrl(url);
	}
	
      public void actionPerformed(ActionEvent e) {
    	  try{
    	URI uri = new URI(url);	
        open(uri);
    	  }catch(URISyntaxException ex){
    		  ex.printStackTrace();
    	  }

      }
      
	 private static void open(URI uri) {
		    if (Desktop.isDesktopSupported()) {
		      try {
		        Desktop.getDesktop().browse(uri);
		      } catch (IOException e) { /* TODO: error handling */ }
		    } else { /* TODO: error handling */ }
    }

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


    }
