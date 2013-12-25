/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Class.SQL;
import Tables.Follow;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author furkan
 */
public class FollowDAO {

    SQL connect = new SQL();
    Statement st;
    Connection dbConnection = null;
    PreparedStatement ps = null;

    public FollowDAO() {
        try {
            st = connect.baglantiAc();
            dbConnection = connect.getDBConnection();
        } catch (Exception ex) {
            Logger.getLogger(FollowDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setFollow(Follow follow) {
        String sorgu = "Insert INTO follow (user_iduser,user_iduser1) VALUES (?,?)";
                
        try {
             ps = dbConnection.prepareStatement(sorgu);
             
             ps.setInt(1, follow.getFollowid());
             ps.setInt(2, follow.getFollowerid());
             
             ps.executeUpdate();
             
             ps.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void unFollow(Follow follow){
        String sorgu = "DELETE FROM follow WHERE user_iduser=? AND user_iduser1=?";
        
        try {
            ps=dbConnection.prepareStatement(sorgu);
            ps.setInt(1, follow.getFollowid());
            ps.setInt(2, follow.getFollowerid());
            ps.executeUpdate();
            
            ps.close();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public int getFollow(int follow, int follower){
        String sorgu="Select * From follow WHERE user_iduser="+follow+" AND user_iduser1="+follower;
        ResultSet rs;
        int ret=-1;
        try {
            rs=st.executeQuery(sorgu);
            if(rs.next()==true){//takip ediliyorsa 1
                ret=1;
            }else{
                ret=0;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return ret;
        
    }
    
    public static void main(String[] args) {

    }

}
