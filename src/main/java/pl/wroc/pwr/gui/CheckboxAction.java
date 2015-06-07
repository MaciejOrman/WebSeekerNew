package pl.wroc.pwr.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JCheckBox;

public class CheckboxAction implements ActionListener{
	static Map<Integer,Boolean> isLinkUsefulMap = new HashMap<Integer,Boolean>();

	public void actionPerformed(ActionEvent e) {
		Integer buttonID = Integer.parseInt(e.getActionCommand());
		
		if(isLinkUsefulMap.containsKey(buttonID)){
			if(isLinkUsefulMap.get(buttonID)==true){
				isLinkUsefulMap.put(buttonID, false);
			}else{
				isLinkUsefulMap.put(buttonID, true);
			}		
		}
		else{
			isLinkUsefulMap.put(buttonID, true);
		}
		System.out.println(isLinkUsefulMap);
	}

}
