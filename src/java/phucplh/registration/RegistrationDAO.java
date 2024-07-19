/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phucplh.registration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import phucplh.utils.DBHelpers;

/**
 *
 * @author ADMIN
 */
public class RegistrationDAO {

    public RegistrationDTO checkLogin(String username, String password)
            throws SQLException, NamingException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        RegistrationDTO result = null;

        try {
            //1. Connect DB
            con = DBHelpers.makeConnection();

            if (con != null) {
                //2. Create SQL Statement String
                String sql = "Select lastname, isAdmin "
                        + "From Registration "
                        + "Where username = ? "
                        + "And password = ?";

                //3. Create Statement to set SQL
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process
/*review*/      if (rs.next()) { 
                    //map --> get data from rs & set data to DTO property
                    String fullName = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    result = new RegistrationDTO(username, null, fullName, role);
                }//end 
            }//end conection has been available
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    private List<RegistrationDTO> accounts;

    public List<RegistrationDTO> getAccounts() {
        return accounts;
    }

    public void searchLastname(String searchValue)
            throws SQLException, NamingException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. Connect DB
            con = DBHelpers.makeConnection();

            if (con != null) {
                //2. Create SQL Statement String
                String sql = "Select username, password, lastname, isAdmin "
                        + "From Registration "
                        + "Where lastname Like ?";

                //3. Create Statement to set SQL
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process
                while (rs.next()) {
                    //get field/column
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String lastname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    
                    //create DTO instance 
                    RegistrationDTO dto = new RegistrationDTO(username, password, lastname, role);
                    
                    //add to account list
                    if(this.accounts == null) {
                        this.accounts = new ArrayList<>();
                    }
                    
                    //accounts is availble
                    this.accounts.add(dto);
                }//end rs has more than one record
            }//end if connection is existed
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    
    public boolean DeleteAccount(String username) 
        throws SQLException, NamingException {

        Connection con = null;
        PreparedStatement stm = null;
        
        try {
            //1. Connect DB
            con = DBHelpers.makeConnection();

            if (con != null) {
                //2. Create SQL Statement String
                String sql = "Delete From Registration "
                        + "Where username = ?";

                //3. Create Statement to set SQL
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                //4. Execute Query
                int row = stm.executeUpdate();
                //5. Process
                if (row > 0) {
                    return true;
                }
            }
        } finally {           
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    
    public boolean UpdateAccount(String username, String password, boolean role) 
        throws SQLException, NamingException {

        Connection con = null;
        PreparedStatement stm = null;
        
        try {
            //1. Connect DB
            con = DBHelpers.makeConnection();

            if (con != null) {
                //2. Create SQL Statement String
                String sql = "Update Registration "
                        + "Set password = ?, isAdmin = ? "
                        + "Where username = ?";

                //3. Create Statement to set SQL
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, role);
                stm.setString(3, username);
                //4. Execute Query
                int row = stm.executeUpdate();
                //5. Process
                if (row > 0) {
                    return true;
                }
            }
        } finally {           
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    
    public boolean createAccount(RegistrationDTO account)
        throws SQLException, NamingException {

        if (account == null) {
            return false;
        }
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
            //1. Connect DB
            con = DBHelpers.makeConnection();

            if (con != null) {
                //2. Create SQL Statement String
                String sql = "Insert Into Registration("
                        + "username, password, lastname, isAdmin"
                        + ") Values("
                        + "?, ?, ?, ? "
                        + ")";

                //3. Create Statement to set SQL
                stm = con.prepareStatement(sql);
                stm.setString(1, account.getUsername());
                stm.setString(2, account.getPassword());
                stm.setString(3, account.getLastname());
                stm.setBoolean(4, account.isRole());
                //4. Execute Query
                int row = stm.executeUpdate();
                //5. Process
                if (row > 0) {
                    return true;
                }
            }
        } finally {           
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    
    

}

    