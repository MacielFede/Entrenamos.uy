package ui;

import javax.swing.*;
import java.awt.*;

public class HorizontalMenu extends JMenu {
        public HorizontalMenu(String label) {
            super(label);
            JPopupMenu pm = getPopupMenu();
            pm.setLayout(new BoxLayout(pm, BoxLayout.LINE_AXIS));
        }

        public Dimension getMinimumSize() {
            return getPreferredSize();
        }

        public Dimension getMaximumSize() {
            return getPreferredSize();
        }

        public void setPopupMenuVisible(boolean b) {
            boolean isVisible = isPopupMenuVisible();
            if (b != isVisible) {
                if ((b) && isShowing()) {
                    //Set location of popupMenu (pulldown or pullright).
                    //Perhaps this should be dictated by L&F.
                    int x;
                    int y;
                    Container parent = getParent();
                    if (parent instanceof JPopupMenu) {
                        x = 0;
                        y = getHeight();
                    } else {
                        x = getWidth();
                        y = 0;
                    }
                    getPopupMenu().show(this, x, y);
                } else {
                    getPopupMenu().setVisible(false);
                }
            }
        }
    }

