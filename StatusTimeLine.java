package devoriginal;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.Status;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class StatusTimeLine implements ActionListener {
    TwitterAPI twitterAPI;
    JPanel timeLinePanel;
    JPanel updatePanel;
    JTextField updateField;
    JButton updateButton;

    public StatusTimeLine(TwitterAPI twitterAPI) throws TwitterException {
        this.twitterAPI = twitterAPI;
        timeLinePanel = new JPanel();
        updateField = new JTextField();

        updatePanel = new JPanel(new FlowLayout());
        updatePanel.setSize(800, 30);
        updatePanel.setPreferredSize(new Dimension(800, 30));
        updatePanel.setMaximumSize(new Dimension(800, 30));

        updateButton = new JButton("Update");
        updateButton.setSize(90, 20);
        updateButton.setPreferredSize(new Dimension(90, 20));
        updateButton.setMaximumSize(new Dimension(90, 20));
        updateButton.addActionListener(this);

        updateField = new JTextField();
        updateField.setSize(600, 20);
        updateField.setPreferredSize(new Dimension(600, 20));
        updateField.setMaximumSize(new Dimension(600, 20));

        updatePanel.add(updateField);
        updatePanel.add(updateButton);
        timeLinePanel.add(updatePanel);
        updateTimePanel();

    }

    private void updateTimePanel() throws TwitterException {
        java.util.List<Status> statusList = twitterAPI.getUserTimeLine();

        String statusArr[] = new String[statusList.size()];
        timeLinePanel.setLayout(new BoxLayout(timeLinePanel, BoxLayout.Y_AXIS));

        for (int i = 0; i < statusList.size(); i++) {
            Date tweetDate = statusList.get(i).getCreatedAt();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy HH:mm");
            statusArr[i] = formatter.format(tweetDate) + "-" + statusList.get(i).getText();
        }

        JList statusJList = new JList(statusArr);
        statusJList.setFixedCellHeight(20);
        JScrollPane scrollPane = new JScrollPane(statusJList,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, // vertical bar
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        timeLinePanel.add(scrollPane);
    }

    public JPanel getTimeLinePanel() {
        return timeLinePanel;
    }

    public void actionPerformed(ActionEvent event) {
        try {
        	twitterAPI.postTweet(updateField.getText());
            updateTimePanel();
            timeLinePanel.remove(1);
            timeLinePanel.updateUI();
            updateField.setText("");
        } catch (TwitterException exception) {
            JOptionPane.showMessageDialog(null, "An error has occurred while updating.");
            exception.printStackTrace();
        }
    }
}