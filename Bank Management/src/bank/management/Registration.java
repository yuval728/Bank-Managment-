package bank.management;

import javax.swing.*;
import java.awt.*; 
import java.awt.event.*; 
import java.text.DateFormat;
import java.text.SimpleDateFormat;

class Registration extends JFrame 
{
    private static final long serialVersionUID = 1L;
    Container c;
    JLabel name,email,phone,aadhar,pan,password,conpassword,balance,time;
    JTextField n,e,ph,a,p,bal;
    JPasswordField pss,con;
    JButton submit,reset;
    JCheckBox term;
    DBconnect d=new DBconnect();
    
    Registration()
    {
        setTitle("Registration Form");
        setBounds(200,20,600,800);
        setResizable(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE); 
        c=getContentPane();
        c.setBackground(Color.BLACK);
        c.setLayout(null);

        name=new JLabel("Name:");
        name.setBounds(60, 30, 150, 150);
        name.setFont(new Font("Michroma", Font.ITALIC, 25));
        name.setForeground(Color.WHITE);
        c.add(name);
        n=new JTextField("");
        n.setBounds(190,80,150,50);
        c.add(n);

        email=new JLabel("Email-id:");
        email.setBounds(60, 90, 150, 150);
        email.setFont(new Font("Michroma", Font.ITALIC, 25));
        email.setForeground(Color.WHITE);
        c.add(email);
        e=new JTextField("");
        e.setBounds(190,140,150,50);
        c.add(e);

        phone=new JLabel("Phone no:");
        phone.setBounds(60, 150, 150, 150);
        phone.setFont(new Font("Michroma", Font.ITALIC, 25));
        phone.setForeground(Color.WHITE);
        c.add(phone);
        ph=new JTextField("");
        ph.setBounds(190,200,150,50);
        c.add(ph);

        aadhar=new JLabel("Aadhar no:");
        aadhar.setBounds(60, 210, 150, 150);
        aadhar.setFont(new Font("Michroma", Font.ITALIC, 25));
        aadhar.setForeground(Color.WHITE);
        c.add(aadhar);
        a=new JTextField("");
        a.setBounds(190,260,150,50);
        c.add(a);

        pan=new JLabel("Pan no:");
        pan.setBounds(60, 270, 150, 150);
        pan.setFont(new Font("Michroma", Font.ITALIC, 25));
        pan.setForeground(Color.WHITE);
        c.add(pan);
        p=new JTextField("");
        p.setBounds(190,320,150,50);
        c.add(p);

        password=new JLabel("Password:");
        password.setBounds(60, 330, 150, 150);
        password.setFont(new Font("Michroma", Font.ITALIC, 25));
        password.setForeground(Color.WHITE);
        c.add(password);
        pss=new JPasswordField("");
        pss.setBounds(190,380,150,50);
        c.add(pss);

        conpassword=new JLabel("Confirm:");
        conpassword.setBounds(60, 380, 150, 150);
        conpassword.setFont(new Font("Michroma", Font.ITALIC, 25));
        conpassword.setForeground(Color.WHITE);
        c.add(conpassword);
        con=new JPasswordField("");
        con.setBounds(190,440,150,50);
        c.add(con);
        
        balance=new JLabel("Balance:");
        balance.setBounds(60, 450, 150, 150);
        balance.setFont(new Font("Michroma", Font.ITALIC, 25));
        balance.setForeground(Color.WHITE);
        c.add(balance);
        bal=new JTextField("0");
        bal.setBounds(190,500,150,50);
        c.add(bal);
        
        time=new JLabel("00:00:00");
        time.setBounds(480, 10, 150, 50);
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

        term = new JCheckBox("Accept Terms And Conditions."); 
		term.setFont(new Font("Arial", Font.PLAIN, 15)); 
		term.setBounds(150,580,350,70);
                term.setForeground(Color.WHITE);
                term.setBackground(Color.BLACK);
		c.add(term);

        submit=new JButton("Submit");
        submit.setFont(new Font("Arial", Font.PLAIN, 15)); 
        submit.setBounds(300,670,100,50);
        
        c.add(submit);
        reset=new JButton("Reset");
        reset.setFont(new Font("Arial", Font.PLAIN, 15)); 
        reset.setBounds(100,670,100,50);
        
        c.add(reset);

        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
            if(RegisterData()) {
            JOptionPane.showMessageDialog(null,"Register Data Successfully");
            }
            }
            });
        
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent f) {
            n.setText("");
            e.setText("");
            ph.setText("");
            a.setText("");
            p.setText("");
            pss.setText("");
            con.setText("");
            bal.setText("");
            }
            });

        setVisible(true);
    }
    Boolean RegisterData()
    {
        String strPassword = new String(pss.getPassword());
        String strConfirmPassword = new String(con.getPassword());
        String strName = n.getText();
        String strEmail = e.getText();
        String strAadhar = a.getText();   
        String strPan = p.getText();
        String strPhone = ph.getText();
        Double balan=Double.parseDouble(bal.getText());
        if(strName.equals(""))
        {
        JOptionPane.showMessageDialog(null,"Please Input (Name)");
        return false;
        }  
        if(strEmail.equals(""))
        {
        JOptionPane.showMessageDialog(null,"Please Input (Email)");
        return false;
        }
        if(strPhone.equals(""))
        {
        JOptionPane.showMessageDialog(null,"Please Input (Phone no.)");
        return false;
        }
        if(strAadhar.equals(""))
        {
        JOptionPane.showMessageDialog(null,"Please Input (Aadhar no.)");
        return false;
        }
        if(strPan.equals(""))
        {
        JOptionPane.showMessageDialog(null,"Please Input (Pan no.)");
        return false;
        }
        if(strPassword.equals(""))
        {
        JOptionPane.showMessageDialog(null,"Please Input (Password)");
        return false;
        }
        if(strConfirmPassword.equals("")) 
        {
        JOptionPane.showMessageDialog(null,"Please Input (Confirm Password)");
        return false;
        }
        if(!strPassword.equals(strConfirmPassword)) 
        {
        JOptionPane.showMessageDialog(null,"Please Input (Password Not Match!)");
        return false;
        }
        if(!term.isSelected())
        {
         JOptionPane.showMessageDialog(null,"Please accept (Terms & Conditions)");
        return false;
        }
        
        Boolean status=false;
        try{
            d.insert(strName, strEmail, strPhone, strAadhar, strPan,strPassword,balan);
            status=true;
            dispose();
        }
        catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
}

return status;
    }
}
