package devoriginal;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.management.Query;

import oauth.Main;
import twitter4j.DirectMessage;
import twitter4j.IDs;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;



public class TwitterAPI {

	private ConfigurationBuilder m_ConfigurationBuilder;
	private TwitterFactory m_TwitterFactory;
	private User m_User;
	private Twitter m_Twitter;
	
	
	public void init (String authConsumerKey, String authConsumerSecret, boolean debugEnabled) {

		m_ConfigurationBuilder = new ConfigurationBuilder();
		m_ConfigurationBuilder.setDebugEnabled(debugEnabled);
		
		m_ConfigurationBuilder.setOAuthConsumerKey(authConsumerKey);
  	  	m_ConfigurationBuilder.setOAuthConsumerSecret(authConsumerSecret);

  	    m_TwitterFactory = new TwitterFactory(m_ConfigurationBuilder.build());
  	    m_Twitter = m_TwitterFactory.getInstance();
	}
	
	public boolean  login () {
        try
        {
            RequestToken requestToken = m_Twitter.getOAuthRequestToken();
            AccessToken accessToken = null;
            while (null == accessToken)
            {
                System.out.println("Open the following URL and grant access to your account:");
                System.out.println(requestToken.getAuthorizationURL());
                URI uri = null;
                uri = URI.create(requestToken.getAuthorizationURL());
                try {
					Desktop.getDesktop().browse(uri);

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

                PINScreen pinScreen = new PINScreen();
                String pin = pinScreen.getPIN();
				System.out.println("pin is " + pin);
                try
                {
                    if (pin.length() > 0)
                    {
                        accessToken = m_Twitter.getOAuthAccessToken(requestToken, pin);
                    } else
                    {
                        accessToken = m_Twitter.getOAuthAccessToken();
                    }
                    return true;
                } catch (TwitterException te)
                {
                    if (401 == te.getStatusCode())
                    {
                        System.out.println("Unable to get the access token. " + te );
                    } else
                    {
                        te.printStackTrace();
                    }
                }
            }

        } catch (TwitterException ex) {
        	System.out.println(ex);
        }
        
        return false;
	}
	
	public void sendDirectMessage (String destUser, String strMessage) {

        try {
             DirectMessage message = m_Twitter.sendDirectMessage(destUser, strMessage);
            System.out.println("Direct message successfully sent to " + message.getRecipientScreenName());
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to send a direct message: " + te.getMessage());
             
        }
	}
	
	
	public  List<Status> getHomeTimeLine () {
		try {
            List<Status> statuses = m_Twitter.getHomeTimeline();

            return statuses;
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
             
		}
		return null;
	}
	
	public List<Status> getMentions () {
		try {
            List<Status> statuses = m_Twitter.getMentionsTimeline();
            return statuses;
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
             
		}
		return null;	
	}
	
	public List<Status> getUserTimeLine () {
		try {

            List<Status> statuses = m_Twitter.getUserTimeline();

            return statuses;
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
             
		}
		return null;
	}
	
	public List<User> getFollowersList () {
		try {
			System.out.println("getFollowersList for " + m_Twitter.getScreenName() );
		} catch (IllegalStateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (TwitterException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			return m_Twitter.getFollowersList(m_Twitter.getId(), -1);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ArrayList<String> getFollowerIDS () {
		try {
			ArrayList<String> idList = new ArrayList<String>();

	        long cursor = -1;
	        IDs ids;

	        do {
	            ids = m_Twitter.getFollowersIDs(cursor);

	            for (long id : ids.getIDs()) {
	                idList.add(Long.toString(id));
	            }
	        } while ((cursor = ids.getNextCursor()) != 0);
	    	
	        return idList;
	        
	    } catch (TwitterException te) {
	        te.printStackTrace();
	        System.out.println("Failed to update profile image: " + te.getMessage());
	         
	    }
		return null;
	}
	
	public void postTweet (String tweet) {
		try {
			twitter4j.Status status = m_Twitter.updateStatus(tweet );
	        System.out.println("Successfully updated the status to [" + status.getText() + "].");
	    	
	    } catch (TwitterException te) {
	        te.printStackTrace();
	        System.out.println("Failed to update profile image: " + te.getMessage());
	    }
	}
	
	public void showStatus (long id) {
		try {
            Status status = m_Twitter.showStatus(id);
            System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());	    	
	    	
	    } catch (TwitterException te) {
	        te.printStackTrace();
	        System.out.println("Failed to update profile image: " + te.getMessage()); 
	    }		
	}
	
	public void showUser (String userName) {
		try {
            User user = m_Twitter.showUser(userName);
            if (user.getStatus() != null) {
                System.out.println("@" + user.getScreenName() + " - " + user.getStatus().getText());
            } else {
                // the user is protected
                System.out.println("@" + user.getScreenName());
            }	    	
	    	
	    } catch (TwitterException te) {
	        te.printStackTrace();
	        System.out.println("Failed to update profile image: " + te.getMessage());
	         
	    }			
	}
	
	public void updateProfileImage (String imagePath) {
		try {
			m_Twitter.updateProfileImage(new File(imagePath));
            System.out.println("Successfully updated profile image.");
	    } catch (TwitterException te) {
	        te.printStackTrace();
	        System.out.println("Failed to update profile image: " + te.getMessage());
	    }			
	}
	
	public List<User> getFriendsList (long cursor) {
		try {
	    	List<User> users=m_Twitter.getFriendsList(m_User.getId(), cursor);
	    	return users;
	    } catch (TwitterException te) {
	        te.printStackTrace();
	        System.out.println("Failed to update profile image: " + te.getMessage());
	    }
		return null;			
	}
	
	public User getUser () {
		return m_User;
	}
	
	public long getId () {	
		try {
			return m_Twitter.getId();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;
	}
	
	public QueryResult search(twitter4j.Query query) {	
		try {
			return m_Twitter.search(query);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Trends getPlaceTrends (int WOEID) {
		try {
			return m_Twitter.getPlaceTrends(WOEID);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
}
