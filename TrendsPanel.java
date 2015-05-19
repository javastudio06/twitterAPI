package devoriginal;

import twitter4j.Trends; 
import twitter4j.Twitter;
import twitter4j.TwitterException;
import javax.swing.*; 
import java.awt.*; 

public class TrendsPanel extends JPanel {
   Twitter twitterAPI;
   int componentsWidth = 180;
   public TrendsPanel(TwitterAPI twitterAPI) throws TwitterException 
   { 
      super(new FlowLayout());
      this.setSize(new Dimension(componentsWidth, 220));
      this.setPreferredSize(new Dimension(componentsWidth, 220));
      JLabel trendsLabel = new JLabel("Trends");
      trendsLabel.setSize(new Dimension(componentsWidth, 20));
      trendsLabel.setPreferredSize(new Dimension(componentsWidth, 20));
      Trends trends = twitterAPI.getPlaceTrends(44418);
      String trendsArr[] = new String[trends.getTrends().length];
      for (int i = 0; i < trends.getTrends().length; i++) {
          trendsArr[i] = trends.getTrends()[i].getName();
      } 
      JList trendsList = new JList(trendsArr);
      trendsList.setSize(new Dimension(componentsWidth, 200));
      trendsList.setPreferredSize(new Dimension(componentsWidth, 200));
      this.add(trendsLabel);
      this.add(trendsList); 
   } 
}