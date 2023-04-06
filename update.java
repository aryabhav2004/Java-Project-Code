package electricity;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class update extends JFrame implements ActionListener
{
    String m_no;
    JLabel heading,name,name_txt,meter_txt,meter_no,add,city,state,email,phone;
    JTextField add_txt, city_txt , state_txt , email_txt , phone_txt;
    JButton update,cancel;
    update(String m_no)
    {
        this.m_no = m_no;
        setBounds(500,70,500,700);

        heading = new JLabel("Update Customer Information");
        heading.setBounds(100,20,290,20);
        heading.setFont(new Font("serif", Font.BOLD,20));
        add(heading);

        name = new JLabel("Name");
        name.setBounds(30,90,100,20);
        add(name);
        name_txt = new JLabel("");
        name_txt.setBounds(170,90,150,20);
        add(name_txt);

        meter_no = new JLabel("Meter Number");
        meter_no.setBounds(30,150,100,20);
        add(meter_no);

        meter_txt = new JLabel("");
        meter_txt.setBounds(170,150,150,20);
        add(meter_txt);

        add = new JLabel("Address");
        add.setBounds(30,210,100,20);
        add(add);

        add_txt = new JTextField();
        add_txt.setBounds(170,210,150,20);
        add(add_txt);

        city = new JLabel("City");
        city.setBounds(30,270,100,20);
        add(city);

        city_txt = new JTextField();
        city_txt.setBounds(170,270,150,20);
        add(city_txt);

        state = new JLabel("State");
        state.setBounds(30,330,100,20);
        add(state);

        state_txt = new JTextField();
        state_txt.setBounds(170,330,150,20);
        add(state_txt);

        email = new JLabel("Email");
        email.setBounds(30,390,100,20);
        add(email);

        email_txt = new JTextField();
        email_txt.setBounds(170,390,150,20);
        add(email_txt);

        phone = new JLabel("Phone");
        phone.setBounds(30,450,100,20);
        add(phone);

        phone_txt = new JTextField();
        phone_txt.setBounds(170,450,150,20);
        add(phone_txt);

        update = new JButton("Update");
        update.setBounds(100,550,100,30);
        update.setForeground(Color.white);
        update.setBackground(Color.black);
        update.addActionListener(this);
        add(update);

        cancel = new JButton("Cancel");
        cancel.setBounds(250,550,100,30);
        cancel.setForeground(Color.white);
        cancel.setBackground(Color.black);
        cancel.addActionListener(this);
        add(cancel);

        try
        {
            database db = new database();
            ResultSet resultSet = db.statement.executeQuery("select * from newCustomer where meter = '"+m_no+"'");
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

        setLayout(null );
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == update)
        {
            String sadd = add_txt.getText();
            String scity = city_txt.getText();
            String sstate = state_txt.getText();
            String semail = email_txt.getText();
            String sphone = phone_txt.getText();

            try {
                database db1 = new database();
                db1.statement.executeUpdate("update newCustomer set address ='"+sadd+"', city = '"+scity+"', state = '"+sstate+"',email = '"+semail+"',phone = '"+sphone+"'");
                JOptionPane.showMessageDialog(null,"Customer details updated Successfully");
                setVisible(false);

            }catch(Exception E)
            {
                E.printStackTrace();
            }
        }
        else {
            setVisible(false) ;
        }
    }

    public static void main(String[] args) {
        new update("");
    }
}
