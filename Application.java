package devoriginal;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import javax.swing.*;

import java.awt.*;
import java.net.MalformedURLException;

public class Application extends JFrame {
    public Application() throws MalformedURLException, TwitterException {
        super("Simple Twitter App");
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

        //TwitterAPI objesi olusturulup konfigure ediliyor
		String authConsumerKey = "******************";
		String authConsumerSecret = ""******************";";
		// TwitterAPI objesi olusturuluyor
		TwitterAPI twitterAPI = new TwitterAPI();
		// kullanici giris bilgileri ile olusturulan obje konfigüre ediliyor
		twitterAPI.init(authConsumerKey, authConsumerSecret, true);
   	    boolean login = twitterAPI.login();

        add(new FollowerPanel(twitterAPI));
        add((new StatusTimeLine(twitterAPI)).getTimeLinePanel());
        add(new RightSideBar(twitterAPI));
    }

    public static void main(String[] args) throws MalformedURLException, TwitterException {
        Application application = new Application();
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        application.setVisible(true);

    }
}
