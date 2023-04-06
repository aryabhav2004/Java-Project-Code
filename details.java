package electricity;
import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class details extends JFrame implements ActionListener
{
    JLabel meter_no,name;
    Choice meter_no_ch,name_ch;
    JButton search , close;
    JTable table;
    details()
    {
        super("Customer details");
        setSize(700,500);
        setLocation(400,200);
        setLayout(null);

        meter_no = new JLabel("Search By Meter no");
        meter_no.setBounds(30,20,150,20);
        add(meter_no);

        meter_no_ch = new Choice();
        meter_no_ch.setBounds(200,20,100,20);
        try
        {
            String query = "select * from newCustomer";
            database b = new database();
            ResultSet r = b.statement.executeQuery(query);
            while(r.next())
            {
                meter_no_ch.add(r.getString("meter"));
            }
        }catch(Exception a)
        {
            a.printStackTrace();
        }
        add(meter_no_ch);

        name = new JLabel("Name");
        name.setBounds(500,20,50,20);
        add(name);

        name_ch = new Choice();
        name_ch.setBounds(580,20,100,20);
        try
        {
            String query2 = "select * from newCustomer";
            database m = new database();
            ResultSet p = m.statement.executeQuery(query2);
            while(p.next())
            {
                name_ch.add(p.getString("name"));
            }

        }catch(Exception x)
        {
            x.printStackTrace();
        }
        add(name_ch);

        search = new JButton("Search");
        search.setBounds(40,80,100,20);
        search.setBackground(Color.BLACK);
        search.setForeground(Color.WHITE);
        search.addActionListener(this);
        add(search);

        close = new JButton("Close");
        close.setBounds(530,80,100,20);
        close.setBackground(Color.BLACK);
        close.setForeground(Color.WHITE);
        close.addActionListener(this);

        add(close);
// we will make scroll pane for table , wont setbound coz scroll pane is done and we will pass table in scroll pane
        table = new JTable();
        try
        {
            database c = new database();
            ResultSet n = c.statement.executeQuery("select * from newCustomer");
            table.setModel(DbUtils.resultSetToTableModel(n));
        }catch(Exception E)
        {
            E.printStackTrace();
        }
        JScrollPane p =  new JScrollPane(table);
        p.setBounds(0,110,700,500);
        p.setBackground(Color.white);
        add(p);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == search)
        {
           String query3 = "select * from newCustomer where meter ='"+meter_no_ch.getSelectedItem()+"' and name = '"+name_ch.getSelectedItem()+"'";
           try
           {
               database x = new database();
               ResultSet s = x.statement.executeQuery(query3);
               table.setModel(DbUtils.resultSetToTableModel(s));

           }
           catch(Exception P)
           {
               P.printStackTrace();
           }
        }
        else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new details();
    }

}
