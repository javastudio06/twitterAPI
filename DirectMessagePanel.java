package devoriginal;

import twitter4j.Twitter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class DirectMessagePanel extends JPanel {

    int componentsWidth = 180;

    public DirectMessagePanel(final TwitterAPI twitterAPI) {
        JButton sendDirectMessageButton = new JButton("Send Direct Message");
        sendDirectMessageButton.setSize(new Dimension(componentsWidth, 20));
        sendDirectMessageButton.setPreferredSize(new Dimension(componentsWidth, 20));
        sendDirectMessageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SendDirectMessageDialog dialog = new SendDirectMessageDialog(twitterAPI);
                dialog.setVisible(true);
            }
        });

        this.add(sendDirectMessageButton);
    }
}
