package devoriginal;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

import javax.swing.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.awt.*;

public class FollowerPanel extends JPanel {
    URL imageUrl;
    int panelWidth = 250;
    int panelHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    java.util.List<User> followers;
    JLabel followerName;
    JLabel followerImg;
    JPanel followerPanel;
    JLabel followerTitleLabel;
    JScrollPane followerJScrollPane;

    public FollowerPanel(TwitterAPI twitterAPI) throws TwitterException {
        followers = twitterAPI.getFollowersList();
        this.setLayout(new GridLayout(1, followers.size()));
        this.setSize(panelWidth, panelHeight);
        this.setPreferredSize(new Dimension(panelWidth, panelHeight));
        this.setMinimumSize(new Dimension(panelWidth, panelHeight));

        followerPanel = new JPanel();
        followerPanel.setLayout(new GridLayout(followers.size(), 1, 0, 0));
        followerPanel.setSize(panelWidth, panelHeight);
        followerPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));
        followerPanel.setMinimumSize(new Dimension(panelWidth, panelHeight));


        int subPanelHeight = 50;
        for (User u : followers) {
            try {
				imageUrl = new URL(u.getProfileImageURL());
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            followerName = new JLabel(u.getName());

            followerImg = new JLabel(new ImageIcon(imageUrl));
            followerImg.setSize(50, 50);
            followerImg.setPreferredSize(new Dimension(50, subPanelHeight));
            followerImg.setMaximumSize(new Dimension(50, subPanelHeight));
            JPanel thisFollowerPanel = new JPanel(new GridLayout(1, 2));
            thisFollowerPanel.add(followerName);
            thisFollowerPanel.add(followerImg);

            followerPanel.add(thisFollowerPanel);
        }
        followerJScrollPane = new JScrollPane(followerPanel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, // vertical bar
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(followerJScrollPane);
    }
}
