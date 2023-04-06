package electricity;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class bill_details extends JFrame implements ActionListener
{
    String m1;
    bill_details(String m1)
    {
        this.m1 = m1;
        setBounds(400,200,700,400);
        JTable table= new JTable();
        try
        {
            database b = new database();
            ResultSet resultSet = b.statement.executeQuery("select * from bill where meter_no ='"+m1+"'");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        JScrollPane s1 = new JScrollPane(table);
        s1.setBounds(0,0,700,400);
        add(s1);

        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        new bill_details("");
    }

}
