/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phucplh.T_Product;

import java.io.Serializable;
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
public class T_ProductDAO implements Serializable {
    
    private List<T_ProductDTO> items;

    public List<T_ProductDTO> getItems() {
        return items;
    }
   
    public void onlineShopping()
            throws SQLException, NamingException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. Connect DB
            con = DBHelpers.makeConnection();

            if (con != null) {
                //2. Create SQL Statement String
                String sql = "Select id, name, deseription, price "
                        + "From T_Product "
                        + "Where quantity > 0";

                //3. Create Statement to set SQL
                stm = con.prepareStatement(sql);
                
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process
                while (rs.next()) {
                    //get field/column
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String deseription = rs.getString("deseription");
                    float price = rs.getFloat("price");
                    
                    //create DTO instance 
                    T_ProductDTO dto = new T_ProductDTO(id, name, deseription, price);
                    
                    //add to account list
                    if(this.items == null) {
                        this.items = new ArrayList<>();
                    }
                    
                    //accounts is availble
                    this.items.add(dto);
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
}
