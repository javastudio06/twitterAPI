package devoriginal;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.util.List;

public class SendDirectMessageDialog extends JFrame implements ActionListener {

    int componentsWidth = 180;
    TwitterAPI twitterAPI;
    JComboBox followersComboBox;
    JTextField messageTextField;
    JButton sendButton;

    public SendDirectMessageDialog(TwitterAPI twitterAPI) {
        super("Direct Message Dialog");

        this.twitterAPI = twitterAPI;

        setLayout(new FlowLayout());
        setSize(new Dimension(620, 100));
        setPreferredSize(new Dimension(620, 100));
        setLocationRelativeTo(null);

        messageTextField = new JTextField();
        messageTextField.setSize(new Dimension(600, 20));
        messageTextField.setPreferredSize(new Dimension(600, 20));
        sendButton = new JButton("Send");
        sendButton.addActionListener(this);

        followersComboBox = new JComboBox();
        try {
        	List<User> users = twitterAPI.getFollowersList();
            String[] followerArr = new String[users.size()];
            for (int i = 0; i < users.size(); i++) {
                followerArr[i] = users.get(i).getScreenName();
            }
            followersComboBox = new JComboBox(followerArr);
        } catch (Exception e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading followers list.");
        }

        add(messageTextField);
        add(followersComboBox);
        add(sendButton);
    }

    public void actionPerformed(ActionEvent e) {
        try {
        	twitterAPI.sendDirectMessage(followersComboBox.getSelectedItem().toString(), 
                 messageTextField.getText());
            JOptionPane.showMessageDialog(this, "Message Sent Successfully");
            messageTextField.setText("");
        } catch (Exception e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error sending direct messsage.");
        }
    }
}
