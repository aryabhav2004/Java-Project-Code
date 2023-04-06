package electricity;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class deposit extends JFrame implements ActionListener
{
    JLabel search_by_meter_no , by_month;
    Choice search_by_meter_no_ch , by_month_ch;
    JButton search , close;
    JTable table;
    deposit()
    {
        setSize(700,600);
        setLocation(400,100);

        search_by_meter_no = new JLabel("Search by Meter Number");
        search_by_meter_no.setBounds(20,20,150,20);
        add(search_by_meter_no);

        search_by_meter_no_ch = new Choice();
        search_by_meter_no_ch.setBounds(200,20,130,20);
        try
        {
            String query = "select * from bill";
            database b = new database();
            ResultSet m = b.statement.executeQuery(query);
            while(m.next())
            {
                search_by_meter_no_ch.add(m.getString("meter_no"));
            }
        }catch (Exception x)
        {
            x.printStackTrace();
        }
        add(search_by_meter_no_ch);

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

        by_month = new JLabel("Search by Month");
        by_month.setBounds(380,20,100,20);
        add(by_month);

        by_month_ch = new Choice();
        by_month_ch.setBounds(500,20,130,20);
        try
        {
            String query3 = "select * from bill";
            database m1 = new database();
            ResultSet r1 = m1.statement.executeQuery(query3);
            while(r1.next())
            {
                by_month_ch.add(r1.getString("month"));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();;
        }
        add(by_month_ch);

        table  = new JTable();
        try
        {
            database B = new database();
            ResultSet resultSet = B.statement.executeQuery("select * from bill");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        }
        catch(Exception n)
        {
            n.printStackTrace();
        }
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(0,150,700,600);
        scroll.setBackground(Color.white);
        add(scroll);

        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == search)
        {
            try {
                database M = new database();
                ResultSet re = M.statement.executeQuery("select * from bill where meter_no ='" + search_by_meter_no_ch.getSelectedItem() + "' and month = '" + by_month_ch.getSelectedItem() + "'");
                table.setModel(DbUtils.resultSetToTableModel(re));
            }catch (Exception p)
            {
                p.printStackTrace();
            }

        }
        else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new deposit();
    }
}
