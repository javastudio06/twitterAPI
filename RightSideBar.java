package devoriginal;

import twitter4j.*;
import javax.swing.*;
import java.awt.*;

public class RightSideBar extends JPanel{
    int componentsWidth = 180;
    int panelHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    public RightSideBar(TwitterAPI twitterAPI) throws TwitterException {
        super(new FlowLayout());
        this.setSize(componentsWidth, panelHeight);
        this.setPreferredSize(new Dimension(componentsWidth, panelHeight));
        this.setMaximumSize(new Dimension(componentsWidth, panelHeight));

        this.add(new SearchTweetsPanel(twitterAPI));
        this.add(new DirectMessagePanel(twitterAPI));
        this.add(new TrendsPanel(twitterAPI));
    }
}
