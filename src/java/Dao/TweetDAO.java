/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Class.SQL;
import Tables.Tweet;
import Tables.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author furkan
 */
public class TweetDAO {

    SQL connect = new SQL();
    Statement st;
    Connection dbConnection = null;
    PreparedStatement ps = null;

    public TweetDAO() {
        try {
            st = connect.baglantiAc();
            dbConnection = connect.getDBConnection();
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Tweet getTweet(int idtweet) {
        String sorgu = "Select * FROM tweet WHERE idtweet=" + idtweet;
        Tweet gettw = new Tweet();
        ResultSet tweetrs;
        try {
            tweetrs = st.executeQuery(sorgu);
            tweetrs.next();

            gettw.setContent(tweetrs.getString("content"));
            gettw.setDate(tweetrs.getString("date"));
            gettw.setIdtweet(tweetrs.getInt("idtweet"));
            gettw.setIduserfk(tweetrs.getInt("iduserfk"));

            st.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return gettw;
    }

    public void save(Tweet tweet) {
        String sorgu = "INSERT INTO tweet (content, date, iduserfk) VALUES (?,?,?)";

        try {
            ps = dbConnection.prepareStatement(sorgu);

            ps.setString(1, tweet.getContent());
            ps.setString(2, tweet.getDate());
            ps.setInt(3, tweet.getIduserfk());

            ps.executeUpdate();

            ps.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void delete(Tweet tweet) {
        String sorgu = "DELETE FROM tweet WHERE idtweet=?";
        try {
            ps = dbConnection.prepareStatement(sorgu);

            ps.setInt(1, tweet.getIdtweet());

            ps.executeUpdate();

            ps.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void update(Tweet tweet) {
        String sorgu = "UPDATE tweet (content, date, iduserfk) VALUES (?,?,?)";

        try {
            ps = dbConnection.prepareStatement(sorgu);

            ps.setString(1, tweet.getContent());
            ps.setString(2, tweet.getDate());
            ps.setInt(3, tweet.getIduserfk());

            ps.executeUpdate();

            ps.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public List<Tweet> getAllUserTweet(int useridfk) {
        List<Tweet> tweetlist = new ArrayList();
        String sorgu = "SELECT tweet.content, tweet.date, tweet.iduserfk, tweet.idtweet FROM("
                + "SELECT tweet.content, tweet.date, tweet.iduserfk, tweet.idtweet FROM tweet "
                + "INNER JOIN user ON user.iduser=tweet.iduserfk\n"
                + "INNER JOIN retweet ON retweet.rt_iduser=tweet.iduserfk AND retweet.rt_iduser=" + useridfk + " "
                + "UNION SELECT tweet.content, tweet.date, tweet.iduserfk, tweet.idtweet FROM retweet "
                + "INNER JOIN user ON user.iduser=retweet.rt_iduser AND user.iduser=" + useridfk + " "
                + "INNER JOIN tweet ON tweet.idtweet=retweet.rt_idtweet)"
                + "tweet ORDER BY tweet.date";
//        String sorgu = "Select * FROM tweet WHERE iduserfk=" + useridfk;
        ResultSet tweets;
        try {
            tweets = st.executeQuery(sorgu);

            while (tweets.next()) {
                Tweet tweet = new Tweet();
                tweet.setContent(tweets.getString("tweet.content"));
                tweet.setDate(tweets.getString("tweet.date"));
                tweet.setIduserfk(tweets.getInt("tweet.iduserfk"));
                tweet.setIdtweet(tweets.getInt("tweet.idtweet"));

                tweetlist.add(tweet);
            }
            st.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return tweetlist;
    }

    public List<User> getFollowingUsers(int followeruserid) {
        List<User> userlist = new ArrayList();
        String sorgu = "SELECT * FROM user INNER JOIN follow ON follow.user_iduser=user.iduser AND follow.user_iduser1=" + followeruserid;
        //String sorgu = "SELECT * FROM follow WHERE user_iduser1=" + followeruserid;
        ResultSet follow;

        try {
            follow = st.executeQuery(sorgu);

            while (follow.next()) {

                User user = new User();
                ResultSet userrs;
                int id = follow.getInt("user_iduser");

                userrs = st.executeQuery("SELECT * FROM user WHERE iduser=" + id);
                userrs.next();

                user.setIduser(id);
                user.setUsername(userrs.getString("user.username"));
                user.setPassword(userrs.getString("user.password"));
                user.setName(userrs.getString("user.name"));
                user.setSurname(userrs.getString("user.surname"));
                user.setDate(userrs.getString("user.date"));
                user.setGender(userrs.getString("user.gender"));
                user.setBio(userrs.getString("user.bio"));
                user.setIdusertypefk(userrs.getInt("user.idusertypefk"));

                userlist.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return userlist;
    }

    public void replyTweet(Tweet tweet) {
        String sorgu = "INSERT INTO tweet (content,iduserfk,parent) VALUES (?,?,?)";

        try {
            ps = dbConnection.prepareStatement(sorgu);

            ps.setString(1, tweet.getContent());
            ps.setInt(2, tweet.getIduserfk());
            ps.setInt(3, tweet.getParent());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<Tweet> showReply(int first) {
        List<Tweet> tweetlist = new ArrayList();

        int x = 1;
        try {

            while (x > 0) {
                ResultSet xs = st.executeQuery("SELECT * FROM tweet WHERE parent=" + first);
                xs.next();

                Tweet tweet = new Tweet();
                tweet.setIdtweet(first);
                tweet.setContent(xs.getString("content"));
                tweet.setIduserfk(xs.getInt("iduserfk"));
                tweet.setParent(xs.getInt("idtweet"));
                tweet.setDate(xs.getString("date"));

                first = xs.getInt("idtweet");

                if (first == 0) {
                    x = 0;
                }

                tweetlist.add(tweet);
            }
            st.executeQuery(null);
        } catch (SQLException e) {
            System.out.println(e);
        }

        return tweetlist;
    }

    public void reTweet(int idtweet, int iduser) {
        String sorgu = "INSERT INTO retweet (rt_iduser, rt_idtweet) VALUES (?,?) ";

        try {
            ps = dbConnection.prepareStatement(sorgu);
            ps.setInt(1, iduser);
            ps.setInt(2, idtweet);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public List<Tweet> getFollowingUserTweet(int iduser) {
        //Following user tweet & retweet
        List<Tweet> tweetlist = new ArrayList();
        String sorgu = "SELECT tweet.content, tweet.date, tweet.iduserfk, tweet.idtweet FROM\n"
                + "(\n"
                + "SELECT tweet.content, tweet.date, tweet.iduserfk, tweet.idtweet FROM tweet\n"
                + "WHERE tweet.iduserfk="+iduser+" "
                + "\n"
                + "UNION \n"
                + "SELECT tweet.content, tweet.date, tweet.iduserfk, tweet.idtweet FROM tweet \n"
                + "INNER JOIN user ON user.iduser=tweet.iduserfk \n"
                + "INNER JOIN follow ON follow.user_iduser=user.iduser AND follow.user_iduser1="+iduser+" "
                + "\n"
                + "UNION \n"
                + "SELECT tweet.content, tweet.date, tweet.iduserfk, tweet.idtweet FROM retweet \n"
                + "INNER JOIN user ON user.iduser=retweet.rt_iduser \n"
                + "INNER JOIN follow ON follow.user_iduser=user.iduser AND follow.user_iduser1="+iduser+" "
                + "INNER JOIN tweet ON tweet.idtweet=retweet.rt_idtweet) tweet ORDER BY tweet.date";

        try {
            ResultSet rs = st.executeQuery(sorgu);

            while (rs.next()) {
                Tweet tweet = new Tweet();
                tweet.setContent(rs.getString("tweet.content"));
                tweet.setDate(rs.getString("tweet.date"));
                tweet.setIduserfk(rs.getInt("tweet.iduserfk"));
                tweet.setIdtweet(rs.getInt("tweet.idtweet"));

                tweetlist.add(tweet);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return tweetlist;
    }

}
