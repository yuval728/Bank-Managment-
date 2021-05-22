
package bank.management;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DepWdraw  {
    Double curr;
    ResultSet rs,rs2;
    Statement stmt=null;
    String phone;
    DepWdraw(String phoneno)
    {
        phone=phoneno;
    }
  
    void deposit(String phone,double bal)
    {
        try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/accounts", "root","7140");
        String sql ="SELECT balance from accounts.account where phoneno='" + phone + "'";
        stmt = (Statement) con.createStatement();
        rs=stmt.executeQuery(sql);
        rs.next();
        curr=rs.getDouble(1);
        curr+=bal;
        String query1 = "update account set balance='"+curr+"' " + "where phoneno='"+phone+"'";
        stmt.executeUpdate(query1);
        JOptionPane.showMessageDialog(null, "Deposit successful\nAvailable balance:" +curr);
         
       }
       catch(ClassNotFoundException | SQLException e)
       {
            Logger.getLogger(DBconnect.class.getName()).log(Level.SEVERE,null,e);
       } 
    }
    void withdraw(String phone,double bal)
    {
        try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/accounts", "root","7140");
        String sql ="SELECT balance from accounts.account where phoneno='" + phone + "'";
        stmt = (Statement) con.createStatement();
        rs=stmt.executeQuery(sql);
        rs.next();
        curr=rs.getDouble(1);
        if(bal<=curr)
        {
        curr-=bal;
        String query1 = "update account set balance='"+curr+"' " + "where phoneno='"+phone+"'";
        stmt.executeUpdate(query1);
        JOptionPane.showMessageDialog(null, "Withdraw successful\nAvailable balance:" +curr);
        }
        else
        {
         JOptionPane.showMessageDialog(null, "Withdrawing more than balance is forbidden");
        }
        }
        catch(ClassNotFoundException | SQLException e)
        {
            Logger.getLogger(DBconnect.class.getName()).log(Level.SEVERE,null,e);
        }
    }
    void transfer(String phone,String rec,double bal)
    {
        double curr2;
        try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/accounts", "root","7140");
        String sql ="SELECT balance from accounts.account where phoneno='" + phone + "'";
        stmt = (Statement) con.createStatement();
        rs=stmt.executeQuery(sql);
        rs.next();
        curr=rs.getDouble(1);
        String sql2 ="SELECT balance from accounts.account where phoneno='" + rec + "'";
        rs2=stmt.executeQuery(sql2);
        rs2.next();
        curr2=rs2.getDouble(1);
        if(bal<=curr)
        {
        curr-=bal;
        curr2+=bal;
        String query1 = "update account set balance='"+curr+"' " + "where phoneno='"+phone+"'";
        String query2="update account set balance='"+curr2+"' " + "where phoneno='"+rec+"'";
        stmt.executeUpdate(query1);
        stmt.executeUpdate(query2);
        JOptionPane.showMessageDialog(null, "Transfer successful");
        }
        else
        {
         JOptionPane.showMessageDialog(null, "You don't have enough balance");
        }
        }
        catch(ClassNotFoundException | SQLException e)
        {
            Logger.getLogger(DBconnect.class.getName()).log(Level.SEVERE,null,e);
        }

    }
   
}
