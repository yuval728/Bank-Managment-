package bank.management;

import javax.swing.*;
import java.awt.*; 
import java.awt.event.*; 
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Login extends JFrame {
    JLabel phone,password,create,title,time,image;
    JPanel pic;
    JPasswordField pass;
    JTextField em;
    JButton log,sign;
    Container c;
    String phone1;
    Login()
    {
        setTitle("Bank Managanement");
        setBounds(200,50,580,600);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE); 
        c=getContentPane();
        c.setBackground(Color.BLACK);
        c.setLayout(null);
        
        title=new JLabel("KKMKB Bank");
        title.setBounds(160, 0, 250, 150);
        title.setFont(new Font("Michroma", Font.BOLD, 30));
        title.setForeground(Color.WHITE);
        c.add(title);
        
        time=new JLabel("00:00:00");
        time.setBounds(450, 450, 150, 150);
        time.setFont(new Font("Michroma", Font.PLAIN, 20));
        time.setForeground(Color.WHITE);
        c.add(time);
        Timer updatetimer;
        int delay=100;
        updatetimer=new Timer(delay, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                java.util.Date currTime=new java.util.Date();
                String format="hh:mm:ss";
                DateFormat forTime=new SimpleDateFormat(format);
                 String time1=forTime.format(currTime);
                time.setText(time1);
            }
        });
        
         updatetimer.start();
        
       
        
        phone=new JLabel("Phone no:");
        phone.setBounds(60, 90, 150, 150);
        phone.setFont(new Font("Michroma", Font.ITALIC, 25));
        phone.setForeground(Color.WHITE);
        c.add(phone);
        em=new JTextField("");
        em.setBounds(190,140,150,50);
        c.add(em);
        
        password=new JLabel("Password:");
        password.setBounds(60, 200, 150, 150);
        password.setFont(new Font("Michroma", Font.ITALIC, 25));
        password.setForeground(Color.WHITE);
        c.add(password);
        pass=new JPasswordField("");
        pass.setBounds(190,250,150,50);
        c.add(pass);
        
        log=new JButton("Login");
        log.setFont(new Font("Arial", Font.PLAIN, 15)); 
        log.setBounds(190,320,100,50);
        log.setForeground(Color.BLACK);
        c.add(log);
        
        create=new JLabel("Don't have an Account?  Create now:");
        create.setBounds(40, 405, 250, 50);
        create.setFont(new Font("Product sans", Font.PLAIN, 13));
        create.setForeground(Color.WHITE);
        c.add(create);
        sign=new JButton("Create");
        sign.setFont(new Font("Arial", Font.PLAIN, 12)); 
        sign.setBounds(280,410,90,40);
        sign.setForeground(Color.BLACK);
        c.add(sign);
        
        sign.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent f) {
                Registration r=new Registration();
            }});
        
        log.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent f) {
                login();
            }});
        
        
        setVisible(true);
    }
    void login()
    {
        phone1=em.getText();
        String password=pass.getText();
        try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/accounts", "root","7140");
        Statement st;
           String sql = "SELECT * from accounts.account where phoneno='" + phone1 + "' and password='" + password + "'";
           st=con.createStatement();
           
            ResultSet rs=st.executeQuery(sql);
            if(rs.next()) 
            {
                JOptionPane.showMessageDialog(log, "You have successfully logged in");
                dispose();
                mainmenu mm=new mainmenu(phone1);
                mm.setVisible(true);
                mm.setLocation(400, 150);
            }
           else
            {
                JOptionPane.showMessageDialog(log, "Wrong Phone no. & Password");
                em.setText("");
                pass.setText("");
            }

        }
         catch(ClassNotFoundException | SQLException e)
        {
            Logger.getLogger(DBconnect.class.getName()).log(Level.SEVERE,null,e);
        }

        
    }
}
