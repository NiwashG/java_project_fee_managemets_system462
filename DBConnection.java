/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fees_managemeant_system3;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Niwash Kumar
 */
public class DBConnection {
    public static Connection getConnection(){
        Connection con = null;
        try{
         Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/fees_managemeant_system","root","root");
    }
        catch(Exception e)
        {
            e.printStackTrace();
        }
       
        return con;
    }
}
