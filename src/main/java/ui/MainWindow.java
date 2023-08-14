package ui;

import javax.swing.*;

public class MainWindow extends JFrame{
    public MainWindow(){
        // Here we create the main frame and set:
        // - a title for the title bar
        // - its behavior when the user clicks the red X
        // - its size
        // - the location where the window appears (center of the screen)
        // finally we display the window
        this.setTitle("No pierdan la volunta wachos");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(800,500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
