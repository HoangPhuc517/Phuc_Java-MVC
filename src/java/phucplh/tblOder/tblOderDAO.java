/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phucplh.tblOder;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.naming.NamingException;
import phucplh.utils.DBHelpers;

/**
 *
 * @author ADMIN
 */
public class tblOderDAO implements Serializable {
    
    
    public boolean insertIntoOder (String id, float total)
            throws SQLException, NamingException {

        
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
            //1. Connect DB
            con = DBHelpers.makeConnection();

            if (con != null) {
                //2. Create SQL Statement String
                String sql = "Insert Into tblOder("
                        + "id, total"
                        + ") Values("
                        + "?, ? "
                        + ")";

                //3. Create Statement to set SQL
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                stm.setFloat(2, total);
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
    
    public boolean checkQuantity (int id, int quantity)
            throws SQLException, NamingException {

        
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int quantityStory = 0;
        
        try {
            //1. Connect DB
            con = DBHelpers.makeConnection();

            if (con != null) {
                //2. Create SQL Statement String
                String sql = "Select quantity "
                        + "From T_Product "
                        + "Where id = ?";

                //3. Create Statement to set SQL
                stm = con.prepareStatement(sql);
                stm.setInt(1, id);
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process
                quantityStory = rs.getInt("quantity");
                int ck = quantityStory - quantity;
                if (ck < 0) {
                    return false;
                }
            }
            
        } finally {           
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return true;
    }
    
    public String lastIdOder()
            throws SQLException, NamingException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String result = "";

        try {
            //1. Connect DB
            con = DBHelpers.makeConnection();

            if (con != null) {
                //2. Create SQL Statement String
                String sql = "SELECT TOP 1 id "
                        + "FROM tblOder "
                        + "ORDER BY id DESC";

                //3. Create Statement to set SQL
                stm = con.prepareStatement(sql);
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process
/*review*/      if (rs.next()) { 
                    result = rs.getString("id");
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
    
    
    public String autoIDBill(String lastIdOrder) {
        // Kiểm tra nếu lastIdOrder là chuỗi rỗng
        if (lastIdOrder.isEmpty()) {
            return "D001";
        } else {
            // Tìm số cuối cùng trong lastIdOrder bằng biểu thức chính quy
            Pattern p = Pattern.compile("\\d+");
            Matcher m = p.matcher(lastIdOrder);
            if (m.find()) {
                // Lấy số cuối cùng và tăng lên 1
                int next = Integer.parseInt(m.group()) + 1;
                // Format lại chuỗi mới theo định dạng Dxxxx
                return "D" + String.format("%03d", next);
            }
        }
        return null;
    }
}
