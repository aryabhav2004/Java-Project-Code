package electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class final1 extends JFrame implements ActionListener
{
    String m3;
    JButton bill;
    Choice choice;
    JTextArea area;
    final1(String m3)
    {
        this.m3 = m3;
        setSize(500,700);
        setLocation(500,30);
        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        JLabel heading  = new JLabel("Generate Bill");

        choice = new Choice();
        choice.add("January");
        choice.add("February");
        choice.add("March");
        choice.add("April");
        choice.add("May");
        choice.add("June");
        choice.add("July");
        choice.add("August");
        choice.add("September");
        choice.add("October");
        choice.add("November");
        choice.add("December");

        area= new JTextArea(50,15);
        area.setFont(new Font("serif",Font.PLAIN,15));
        bill = new JButton("Generate Bill");
        bill.setFont(new Font("serif",Font.PLAIN,20));
        bill.setBounds(250,650,200,20);
        bill.addActionListener(this);

        add(panel,"North");
        add(bill,"South");
        panel.add(heading);
        panel.add(choice);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        try
        {
            database c = new database();
            String smonth = choice.getSelectedItem();
            area.setText("\n Parshit Electricty Billing System\n");
            area.setText("\n Bill for Month"+smonth+" 2023\n");
            ResultSet resultSet = c.statement.executeQuery("select * from newCustomer where meter ='"+m3+"'");
            if (resultSet.next()){
                area.append("\nName        : "+resultSet.getString("name"));
                area.append("\nMeter Number: "+resultSet.getString("meter"));
                area.append("\nAddress     : "+resultSet.getString("address"));
                area.append("\nCity        : "+resultSet.getString("city"));
                area.append("\nState       : "+resultSet.getString("state"));
                area.append("\nEmail       : "+resultSet.getString("email"));
                area.append("\nPhone Number: "+resultSet.getString("phone"));

            }

            resultSet = c.statement.executeQuery("select * from meter_info where meterno ='"+m3+"'");
            if (resultSet.next()){
                area.append("Meter Type: "+resultSet.getString("meter_type"));
                area.append("Phase Code:"+resultSet.getString("phase_Code"));
                area.append("Bill Type: "+resultSet.getString("bill_type"));
            }
            resultSet = c.statement.executeQuery("select * from bill where meter_no = '"+m3+"' and month = '"+choice.getSelectedItem()+"'");
            if (resultSet.next()) {
                area.append("Current Month       : " + resultSet.getString("month"));
                area.append("Units Consumed: " + resultSet.getString("unit"));
                area.append("Total Charges   : " + resultSet.getString("total_Bill"));
            }


        }catch (Exception E ){
            E.printStackTrace();
        }

    }
    public static void main(String[] args) {
        new final1("");
    }
}
