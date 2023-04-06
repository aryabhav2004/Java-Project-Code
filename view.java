package electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class view extends JFrame implements ActionListener
{
    JLabel heading,name,meter,add,city,state,email,phone,name_txt,meter_txt,add_txt,city_txt,state_txt,email_txt,phone_txt;
    JButton cancel;
    String v;
    view(String v)
    {
        setBounds(340,70,500,700);
        this.v = v;
        heading = new JLabel("View Customer Information");
        heading.setBounds(100,20,310,20);
        heading.setFont(new Font("monospaced",Font.BOLD,20));
        add(heading);

        name = new JLabel("Name");
        name.setBounds(50,110,100,20);
        add(name);

        name_txt = new JLabel("");
        name_txt.setBounds(200,110,100,20);
        add(name_txt);

        meter = new JLabel("Meter Number");
        meter.setBounds(50,170,100,20);
        add(meter);

        meter_txt = new JLabel("");
        meter_txt.setBounds(200,170,100,20);
        add(meter_txt);

        add = new JLabel("Address");
        add.setBounds(50,230,100,20);
        add(add);

        add_txt = new JLabel("");
        add_txt.setBounds(200,230,100,20);
        add(add_txt);

        city = new JLabel("City");
        city.setBounds(50,290,100,20);
        add(city);

        city_txt = new JLabel("");
        city_txt.setBounds(200,290,100,20);
        add(city_txt);

        state = new JLabel("State");
        state.setBounds(50,350,100,20);
        add(state);
        state_txt = new JLabel("");
        state_txt.setBounds(200,350,100,20);
        add(state_txt);

        email = new JLabel("Email");
        email.setBounds(50,410,100,20);
        add(email);

        email_txt = new JLabel("");
        email_txt.setBounds(200,410,100,20);
        add(email_txt);

        phone = new JLabel("Phone");
        phone.setBounds(50,470,100,20);
        add(phone);
        phone_txt = new JLabel("");
        phone_txt.setBounds(200,470,100,20);
        add(phone_txt);

        try
        {
            database d = new database();
            ResultSet resultSet = d.statement.executeQuery("select * from newCustomer where meter = '"+v+"'");
            if(resultSet.next())
            {
                name_txt.setText(resultSet.getString("name"));
                meter_txt.setText(resultSet.getString("meter"));
                add_txt.setText(resultSet.getString("address"));
                city_txt.setText(resultSet.getString("city"));
                state_txt.setText(resultSet.getString("state"));
                email_txt.setText(resultSet.getString("email"));
                phone_txt.setText(resultSet.getString("phone"));


            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        cancel = new JButton("Cancel");
        cancel.setBounds(150,550,100,30);
        cancel.setForeground(Color.white);
        cancel.setBackground(Color.black);
        cancel.addActionListener(this);
        add(cancel);

        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == cancel)
        {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new view("");
    }

}
