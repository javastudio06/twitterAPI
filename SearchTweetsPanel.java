package devoriginal;

import twitter4j.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchTweetsPanel extends JPanel {
    TwitterAPI twitterAPI;
    JButton searchTweetsButton;
    JTextField searchTweetsTextField;
    int componentsWidth = 180;
    int panelHeight = 50;

    public SearchTweetsPanel(final TwitterAPI twitterAPI) {
        super(new FlowLayout());
        this.twitterAPI = twitterAPI;

        this.setSize(componentsWidth, panelHeight);
        this.setPreferredSize(new Dimension(componentsWidth, panelHeight));
        this.setMaximumSize(new Dimension(componentsWidth, panelHeight));

        searchTweetsButton = new JButton("Search Tweets");
        searchTweetsButton.setSize(new Dimension(componentsWidth, 20));
        searchTweetsButton.setPreferredSize(new Dimension(componentsWidth, 20));
        searchTweetsTextField = new JTextField();
        searchTweetsTextField.setSize(componentsWidth, 20);
        searchTweetsTextField.setPreferredSize(new Dimension(componentsWidth, 20));
        searchTweetsTextField.setMinimumSize(new Dimension(componentsWidth, 20));
        searchTweetsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                QueryResult q = null;
                java.util.List<Status> tweets = null;

                try {
                    q = twitterAPI.search(new Query(searchTweetsTextField.getText()));
                    tweets = q.getTweets();
                    String[] tweetArr = new String[tweets.size()];

                    for (int i = 0; i < tweets.size(); i++) {
                        tweetArr[i] = tweets.get(i).getUser().getName()+ "-" + tweets.get(i).getText();
                    }

                    // open a dialog box and show result
                    JFrame resultDialog = new JFrame();
                    JList list = new JList(tweetArr);
                    JScrollPane scrollPane = new JScrollPane(list,
                            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, // vertical bar
                            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    resultDialog.add(scrollPane);

                    resultDialog.setSize(new Dimension(1100, 320));
                    resultDialog.setPreferredSize(new Dimension(1100, 320));
                    resultDialog.setLocationRelativeTo(null);
                    resultDialog.setVisible(true);
                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error occurred during search, try again.");
                }
            }
        });
        this.add(searchTweetsButton);
        this.add(searchTweetsTextField);
    }
}