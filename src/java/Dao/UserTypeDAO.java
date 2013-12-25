/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Class.SQL;
import Tables.UserType;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class UserTypeDAO {

    SQL connect = new SQL();
    Statement st;
    Connection dbConnection = null;
    PreparedStatement ps = null;

    public UserTypeDAO() {
        try {
            st = connect.baglantiAc();
            dbConnection = connect.getDBConnection();
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void save(UserType usertype) {
        String sorgu = "INSERT INTO usertype (name) VALUES (?)";
        
        try {
            ps=dbConnection.prepareStatement(sorgu);
            
            ps.setString(1, usertype.getName());
            
            ps.executeUpdate();
            
            ps.close();
            
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void delete(UserType usertype) {
        String sorgu = "DELETE FROM usertype WHERE idusertype=?";
        
        try {
            ps=dbConnection.prepareStatement(sorgu);
            
            ps.setInt(1, usertype.getIdusertype());
            
            ps.executeUpdate();    
            
            ps.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void update(UserType usertype) {
        String sorgu = "UPDATE usertype SET name=?";
        
        try {
            ps=dbConnection.prepareStatement(sorgu);
            
            ps.setString(1, usertype.getName());
            
            ps.executeUpdate();
            
            ps.close();
            
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<UserType> getAllUserType() {
        List<UserType> usertypelist = new ArrayList();

        return usertypelist;

    }
}
