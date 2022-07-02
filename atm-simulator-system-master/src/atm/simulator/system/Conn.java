/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package atm.simulator.system;

/**
 *
 * @author mikan
 * this class uses SQL package for java allowing us to
 * select , insert, delete , update data in SQL tables
 */

import java.sql.*;
public class Conn {
    Connection c;
    Statement s;
    public Conn(){
        try{
            Class.forName("com.mysql.jdbc.Driver"); //registering the class and loadiing in the memory 
            c = DriverManager.getConnection("jdbc:mysql:///project1","root","");    // setting up the connection with the database 
            s = c.createStatement();     //to access to our database.
        }
        catch(Exception e ){
            System.out.println(e);
        }
    }
    
}
