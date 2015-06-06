package pl.wroc.pwr;

import java.awt.EventQueue;

import pl.wroc.pwr.gui.GUI;

public class Main {

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
        
            public void run() {
                GUI ex = new GUI();
                ex.setVisible(true);
            }
        });
    }
}
