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
        setTitle("No pierdan la volunta wachos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800,500);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
