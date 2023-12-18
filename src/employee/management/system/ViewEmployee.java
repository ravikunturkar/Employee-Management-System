package employee.management.system;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class ViewEmployee extends JFrame implements ActionListener {
    
    JTable table;
    Choice employeeIdCh;
    JButton search,print,update,back;
    
    ViewEmployee(){
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel searchlbl=new JLabel("Search by employee id");
        searchlbl.setBounds(20, 20, 150, 20);
        add(searchlbl);
        
        employeeIdCh=new Choice();
        employeeIdCh.setBounds(180, 20, 150, 20);
        add(employeeIdCh);
        
        try{
            Conn c=new Conn();
            ResultSet rs=c.s.executeQuery("select * from employee");
            while(rs.next()){
                employeeIdCh.add(rs.getString("empId"));
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        search=new JButton("Search");
        search.setBounds(20, 60, 80, 20);
        search.addActionListener(this);
        add(search);
        
        print=new JButton("Print");
        print.setBounds(120, 60, 80, 20);
        print.addActionListener(this);
        add(print);
        
        update=new JButton("Update");
        update.setBounds(220, 60, 80, 20);
        update.addActionListener(this);
        add(update);
        
        back=new JButton("Back");
        back.setBounds(320, 60, 80, 20);
        back.addActionListener(this);
        add(back);
        
        
        table=new JTable();
        
        try{
            Conn c=new Conn();
            ResultSet rs=c.s.executeQuery("select * from employee");
            table.setModel(DbUtils.resultSetToTableModel(rs));
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        JScrollPane jsp=new JScrollPane(table);
        jsp.setBounds(0, 100, 900, 600);
        add(jsp);
        
        setSize(900,700);
        setLocation(250,20);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==search){
            String query="select * from employee where empId='"+employeeIdCh.getSelectedItem()+"'";
        try{
            Conn c=new Conn();
            ResultSet rs=c.s.executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(rs));
            
        }catch(Exception e){
            e.printStackTrace();
        }            
            
        }else if(ae.getSource()==print){
        try{
            table.print();
        }catch(Exception e){
            e.printStackTrace();
        }
        }else if(ae.getSource()==update){
            setVisible(false);
            new UpdateEmployee(employeeIdCh.getSelectedItem());
        }else{
            setVisible(false);
            new Home();
        }
    }
    
    public static void main(String[] args){
        new ViewEmployee();
    }
}
