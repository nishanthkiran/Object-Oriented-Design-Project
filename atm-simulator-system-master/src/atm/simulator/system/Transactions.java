/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package atm.simulator.system;

/**
 *
 * @author mikan
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import ASimulatorSystem.Deposit;
import ASimulatorSystem.FastCash;
import ASimulatorSystem.MiniStatement;
import ASimulatorSystem.Pin;
import ASimulatorSystem.Withdrawl;
import ASimulatorSystem.BalanceEnquiry;


import java.sql.*;

public class Transactions extends JFrame implements ActionListener{
    
     JLabel l1;
    JButton b1,b2,b3,b4,b5,b6,b7;
    String pin;

    public Transactions(String pin) {
        this.pin = pin;
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("atm/simulator/system/icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1000, 1180, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l2 = new JLabel(i3);
        l2.setBounds(0, 0, 960, 1080);
        add(l2);
        
        setFont(new Font ("System", Font. BOLD, 22));
        Font f = getFont();
        FontMetrics fm = getFontMetrics(f);
        int x = fm.stringWidth("NEW ACCOUNT APPLICATION FORM - PAGE 3");
        int y = fm.stringWidth(" ");
        int z = getWidth()/2 - (x/2);
        int w = z/y;
        String pad ="";
        //for (int i=0; i!=w; i++) pad +=" ";
        pad = String.format ("%"+w+"s", pad) ;
        setTitle(pad+"NEW ACCOUNT APPLICATION FORM - PAGE 3");
        
        
         l1 = new JLabel("Please Select Your Transaction");
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("System", Font.BOLD, 16));
        
       
        b1 = new JButton("DEPOSIT");
        b2 = new JButton("CASH WITHDRAWL");
        b3 = new JButton("FAST CASH");
        b4 = new JButton("MINI STATEMENT");
        b5 = new JButton("PIN CHANGE");
        b6 = new JButton("BALANCE ENQUIRY");
        b7 = new JButton("EXIT");
        
        setLayout(null);
        
        l1.setBounds(235,400,700,35);
        l2.add(l1);
        
        b1.setBounds(170,499,150,35);
        l2.add(b1);
        
        b2.setBounds(390,499,150,35);
        l2.add(b2);
        
        b3.setBounds(170,543,150,35);
        l2.add(b3);
        
        b4.setBounds(390,543,150,35);
        l2.add(b4);
        
        b5.setBounds(170,588,150,35);
        l2.add(b5);
        
        b6.setBounds(390,588,150,35);
        l2.add(b6);
        
        b7.setBounds(390,633,150,35);
        l2.add(b7);
        
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);
        
        
        setSize(960,1080);
        setLocation(500,0);
        setUndecorated(true);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==b1){ 
            setVisible(false);
            new Deposit(pin).setVisible(true);
        }else if(ae.getSource()==b2){ 
            setVisible(false);
            new Withdrawl(pin).setVisible(true);
        }else if(ae.getSource()==b3){ 
            setVisible(false);
            new FastCash(pin).setVisible(true);
        }else if(ae.getSource()==b4){ 
            new MiniStatement(pin).setVisible(true);
        }else if(ae.getSource()==b5){ 
            setVisible(false);
            new Pin(pin).setVisible(true);
        }else if(ae.getSource()==b6){ 
            this.setVisible(false);
            new BalanceEnquiry(pin).setVisible(true);
        }else if(ae.getSource()==b7){ 
            System.exit(0);
        }
    }
    
     public static void main(String[] args){
        new Transactions("").setVisible(true);
    }
    
}


