/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phucplh.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author ADMIN
 */
public class DBHelpers implements Serializable{
    public static Connection makeConnection()
        throws /*ClassNotFoundException*/ NamingException, SQLException{
        //1. get current system file
        Context context = new InitialContext();
        //2. get container context
        Context tomcatContext = (Context)context.lookup("java:comp/env");
        //3. get DataSource from container
        DataSource ds = (DataSource) tomcatContext.lookup("PHUC17");
        //4. get Connectons
        Connection con = ds.getConnection();
        
        return con;
//        //1. Load Driver
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        //2. Create Connection String
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=Phuc;instanceName=SQLEXPRESS";
//        //3. Open Conection
//        Connection con = DriverManager.getConnection(url, "sa", "123456");
//        
//        return con;
    }
}
