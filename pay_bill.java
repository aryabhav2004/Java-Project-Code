package electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class pay_bill extends JFrame implements ActionListener
{
    String m2;
    JLabel meterno ,meterno_txt,name_txt, name,month,units,total,status,units_txt,bill_txt,status_txt;
    JButton pay,back;
    Choice c;
    pay_bill(String m2)
    {
        this.m2 = m2;
        setBounds(300,200,500,500);

        JLabel heading  = new JLabel("Pay Bill");
        heading.setBounds(200,20,180,20);
        heading.setFont(new Font("serif",Font.BOLD,20));
        add(heading);

        meterno = new JLabel("Meter Number");
        meterno.setBounds(40,90,180,20);
        add(meterno);

        meterno_txt =new JLabel("");
        meterno_txt.setBounds(200,90,180,20);
        add(meterno_txt);

        name = new JLabel("Name");
        name.setBounds(40,150,180,20);
        add(name);

        name_txt =new JLabel("");
        name_txt.setBounds(200,150,180,20);
        add(name_txt);

        month = new JLabel("Month");
        month.setBounds(40,210,100,20);
        add(month);

        c = new Choice();
        c.add("January");
        c.add("February");
        c.add("March");
        c.add("April");
        c.add("May");
        c.add("June");
        c.add("July");
        c.add("August");
        c.add("September");
        c.add("October");
        c.add("November");
        c.add("December");
        c.setBounds(200,210,150,20);
        add(c);

        units = new JLabel("Units");
        units.setBounds(40,270,180,20);
        add(units);

        units_txt =new JLabel("");
        units_txt.setBounds(200,270,180,20);
        add(units_txt);

        total = new JLabel("Total bill");
        total.setBounds(40,330,180,20);
        add(total);

        bill_txt =new JLabel("");
        bill_txt.setBounds(200,330,180,20);
        add(bill_txt);

        status = new JLabel("Status");
        status.setBounds(40,390,180,20);
        add(status);

        status_txt =new JLabel("");
        status_txt.setBounds(200,390,180,20);
        add(status_txt);

        pay = new JButton("Pay");
        pay.setBounds(100,420,100,20);
        pay.setForeground(Color.white);
        pay.setBackground(Color.black);
        pay.addActionListener(this);
        add(pay);

        back = new JButton("Back");
        back.setBounds(250,420,100,20);
        back.setForeground(Color.white);
        back.setBackground(Color.black);
        back.addActionListener(this);
        add(back);

        try
        {
            database db = new database();
            ResultSet resultSet = db.statement.executeQuery("select * from newCustomer where meter = '"+m2+"'");

            while(resultSet.next())
            {
                meterno_txt.setText(m2);
                name_txt.setText(resultSet.getString("name"));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        c.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                database db1 = new database();
                try
                {
                    ResultSet resultSet = db1.statement.executeQuery("select * from bill where meter_no = '"+m2+"' and month = '"+c.getSelectedItem()+"'");
                    while (resultSet.next())
                    {
                        units_txt.setText(resultSet.getString("unit"));
                        bill_txt.setText(resultSet.getString("total_Bill"));
                        status_txt.setText(resultSet.getString("status"));
                    }
                }
                catch(Exception p)
                {
                    p.printStackTrace();
                }
            }
        });

        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == pay)
        {
            try
            {
                database c1 = new database();
                c1.statement.executeUpdate("update bill set status = 'Paid' where meter_no = '"+m2+"' and month = '"+c.getSelectedItem()+"'");
            }
            catch(Exception e2)
            {
                e2.printStackTrace();
            }
            setVisible(false);
            new display_play();

        }
        else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new pay_bill("");
    }
}



