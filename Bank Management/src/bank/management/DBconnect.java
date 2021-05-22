package bank.management;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBconnect{
    void insert(String Name,String Email,String Phone,String Aadhar,String Pan,String password,Double balance)
    {
        try{
        Statement s=null;
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/accounts", "root","7140");
        System.out.print("Success");
        s=con.createStatement();
        String sql = "INSERT INTO account "
                +"(names,emailid,phoneno,aadharno,panno,password,balance)"
                + "Values('\n" + Name + "','\n"+ Email + "','"+ Phone + "'" + ",'"
                + Aadhar + "'" + ",'"+ Pan + "'" + ",'"+ password + "','" + balance + "') ";
        s.execute(sql);
        try {
if (s != null) {
    s.close();
    con.close(); } 
else {
}
}catch(SQLException e) {
System.out.println(e.getMessage());
}
        }
        catch(ClassNotFoundException | SQLException e)
        {
            Logger.getLogger(DBconnect.class.getName()).log(Level.SEVERE,null,e);
        }
        
    }
    }
        
    

