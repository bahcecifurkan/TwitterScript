/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Class.SQL;
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
public class UserDAO {

    SQL connect = new SQL();
    Statement st;
    Connection dbConnection = null;
    PreparedStatement ps = null;

    public UserDAO() {
        try {
            st = connect.baglantiAc();
            dbConnection = connect.getDBConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public User getUser(int iduser) {
        User user = new User();

        String sorgu = "SELECT * FROM user WHERE iduser=" + iduser;
        ResultSet userrs;
        try {
            userrs = st.executeQuery(sorgu);

            userrs.next();

            user.setUsername(userrs.getString("username"));
            user.setPassword(userrs.getString("password"));
            user.setName(userrs.getString("name"));
            user.setSurname(userrs.getString("surname"));
            user.setDate(userrs.getString("date"));
            user.setGender(userrs.getString("gender"));
            user.setIdusertypefk(userrs.getInt("idusertypefk"));
            user.setBio(userrs.getString("bio"));

        } catch (SQLException e) {
            System.out.println(e);
        }
        return user;
    }

    public String getUsername(int iduser) {
        User user = new User();

        String sorgu = "SELECT * FROM user WHERE iduser=" + iduser;
        ResultSet userrs;

        try {
            userrs = st.executeQuery(sorgu);

            userrs.next();

            String username = userrs.getString("username");
            System.out.println("=========" + username);
            user.setUsername(userrs.getString("username"));
            user.setPassword(userrs.getString("password"));
            user.setName(userrs.getString("name"));
            user.setSurname(userrs.getString("surname"));
            user.setDate(userrs.getString("date"));
            user.setGender(userrs.getString("gender"));
            user.setIdusertypefk(userrs.getInt("idusertypefk"));
            user.setBio(userrs.getString("bio"));

            return username;

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }

    }

    public int save(User user) {
        String sorgu = "INSERT INTO user "
                + "(username, password, name, surname, gender, date, idusertypefk, bio, email) VALUES "
                + "(?,?,?,?,?,?,?,?,?)";

        try {

            if (userControl(user.getUsername()) == 0) {
                ps = dbConnection.prepareStatement(sorgu);

                ps.setString(1, user.getUsername());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getName());
                ps.setString(4, user.getSurname());
                ps.setString(5, user.getGender());
                ps.setString(6, user.getDate());
                ps.setInt(7, user.getIdusertypefk());
                ps.setString(8, user.getBio());
                ps.setString(9, user.getEmail());

                ps.executeUpdate();
                return 1;
            } else {
                return 0;
            }

        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        }
    }

    public void delete(User user) {
        String sorgu = "DELETE FROM user WHERE iduser=?";

        try {
            ps = dbConnection.prepareStatement(sorgu);

            ps.setInt(1, user.getIduser());

            ps.executeUpdate();

            ps.close();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void update(User user) {
        String sorgu = "UPDATE user SET username=?, password=?, name=?, "
                + "surname=?, gender=?, date=?, idusertypefk=?, bio=? ";

        try {
            ps = dbConnection.prepareStatement(sorgu);

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.setString(4, user.getSurname());
            ps.setString(5, user.getGender());
            ps.setString(6, user.getDate());
            ps.setInt(7, user.getIdusertypefk());
            ps.setString(8, user.getBio());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<User> getAllUser() {
        List<User> userlist = new ArrayList();
        ResultSet allusers;
        String sorgu = "";
        try {

            allusers = ps.executeQuery(sorgu);

            while (allusers.next()) {
                User user = new User();

                user.setUsername(allusers.getString("username"));
                user.setPassword(allusers.getString("password"));
                user.setName(allusers.getString("name"));
                user.setSurname(allusers.getString("surname"));
                user.setGender(allusers.getString("gender"));
                user.setDate(allusers.getString("date"));

                userlist.add(user);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return userlist;

    }

    public List<User> getUsers() {
        List<User> userlist = new ArrayList();
        ResultSet allusers;
        String sorgu = "SELECT * from user";
        try {

            allusers = st.executeQuery(sorgu);

            while (allusers.next()) {
                User user = new User();

                user.setIduser(allusers.getInt("iduser"));
                user.setUsername(allusers.getString("username"));
                user.setName(allusers.getString("name"));
                user.setSurname(allusers.getString("surname"));
                user.setGender(allusers.getString("gender"));
                user.setDate(allusers.getString("date"));

                userlist.add(user);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return userlist;

    }

    public int userControl(String username) {
        ResultSet rs;
        String sorgu = "SELECT * FROM user WHERE username IN ('" + username + "')";

        try {
            rs = st.executeQuery(sorgu);
            rs.next();
            if (rs.next() == true) {//varsa 1
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            return -1;
        }
    }
    
    

//    public static void main(String[] args) {
//        SQL connec= new SQL();
//        try {
//            //varsa true yoksa false
//            String username="x";
//            Statement st = connec.baglantiAc();
//            ResultSet rs = st.executeQuery("SELECT * FROM user WHERE username IN ('"+username+"')");
//            
//            System.out.println(rs.next());
//        } catch (Exception ex) {
//            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
